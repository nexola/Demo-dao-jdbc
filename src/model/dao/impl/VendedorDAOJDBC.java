package model.dao.impl;

import db.DB;
import db.DbIntegrityException;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendedorDAOJDBC implements VendedorDAO {

    private Connection conn;

    public VendedorDAOJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void criar(Vendedor obj) {

    }

    @Override
    public void atualizar(Vendedor obj) {

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
            DB.closeConnection(rs);
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
        return null;
    }
}
