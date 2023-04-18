package model.dao;

import model.dao.impl.VendedorDAOJDBC;

public class DAOFactory {

    public static VendedorDAO criarVendedorDAO() {
        return new VendedorDAOJDBC();
    }

}
