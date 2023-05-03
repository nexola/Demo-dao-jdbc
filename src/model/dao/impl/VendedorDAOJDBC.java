package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDAOJDBC implements VendedorDAO {

    private Connection conn;

    public VendedorDAOJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void criar(Vendedor obj) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO seller " +
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDate(3, new Date(obj.getAniversario().getTime()));
            st.setDouble(4, obj.getSalarioBase());
            st.setInt(5, obj.getDepartamento().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado, nenhuma linha foi afetada!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Vendedor obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE seller " +
                            "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
                            "WHERE Id = ?");

            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDate(3, new Date(obj.getAniversario().getTime()));
            st.setDouble(4, obj.getSalarioBase());
            st.setInt(5, obj.getDepartamento().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarPorId(Integer id) {

    }

    @Override
    public Vendedor procurarPorId(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");

            st.setInt(1, id); // Faz a busca baseado no ID do vendedor
            rs = st.executeQuery(); // Executa a busca retornando ao ResultSet o objeto da tabela

            if (rs.next()) {

                // Instanciando o departamento
                Departamento dep = instanciarDepartamento(rs);

                // Instanciando o vendedor
                Vendedor obj = instanciarVendedor(rs, dep);

                return obj;
            }
            return null;

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("Id"));
        obj.setNome(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setSalarioBase(rs.getDouble("BaseSalary"));
        obj.setAniversario(rs.getDate("BirthDate"));
        obj.setDepartamento(dep);
        return obj;
    }

    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setNome(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Vendedor> procurarTudo() {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "ORDER BY Name");

            rs = st.executeQuery(); // Executa a busca retornando ao ResultSet o objeto da tabela

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()) {

                Departamento dep = map.get(rs.getInt("DepartmentId"));

                // Um departamento apenas para x vendedores presentes na lista
                if (dep == null) {
                    // Instanciando o departamento
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                // Instanciando o vendedor
                Vendedor obj = instanciarVendedor(rs, dep);

                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vendedor> procurarPorDepartamento(Departamento departamento) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE DepartmentId = ? " +
                    "ORDER BY Name");

            st.setInt(1, departamento.getId()); // Faz a busca baseado no ID do departamento
            rs = st.executeQuery(); // Executa a busca retornando ao ResultSet o objeto da tabela

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()) {

                Departamento dep = map.get(rs.getInt("DepartmentId"));

                // Um departamento apenas para x vendedores presentes na lista
                if (dep == null) {
                    // Instanciando o departamento
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                // Instanciando o vendedor
                Vendedor obj = instanciarVendedor(rs, dep);

                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
