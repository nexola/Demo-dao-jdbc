package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartamentoDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAOJDBC implements DepartamentoDAO {

    private Connection conn;

    public DepartamentoDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Departamento procurarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Departamento obj = new Departamento();
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("Name"));
                return obj;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Departamento> procurarTudo() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {

            st = conn.prepareStatement(
                    "SELECT * FROM department ORDER BY Name");
            rs = st.executeQuery();

            List<Departamento> list = new ArrayList<>();

            while (rs.next()) {
                Departamento obj = new Departamento();
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("Name"));
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void criar(Departamento obj) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO department " +
                            "(Id, Name) " +
                            "VALUES " +
                            "(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getId());
            st.setString(2, obj.getNome());

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
    public void atualizar(Departamento obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE department " +
                    "SET Name = ? " +
                    "WHERE Id = ?");

            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarPorId(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM department " +
                    "WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

}
