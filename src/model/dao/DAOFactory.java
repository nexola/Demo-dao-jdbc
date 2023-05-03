package model.dao;

import db.DB;
import model.dao.impl.VendedorDAOJDBC;

public class DAOFactory {

    public static VendedorDAO criarVendedorDAO() {
        return new VendedorDAOJDBC(DB.getConnection());
    }

}
