package model.dao;

import db.DB;
import model.dao.impl.DepartamentoDAOJDBC;
import model.dao.impl.VendedorDAOJDBC;

public class DAOFactory {

    public static VendedorDAO criarVendedorDAO() {
        return new VendedorDAOJDBC(DB.getConnection());
    }

    public static DepartamentoDAO criarDepartamentoDAO() { return new DepartamentoDAOJDBC(DB.getConnection()); }

}
