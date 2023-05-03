package application;

import model.dao.DAOFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.List;


public class Programa {
    public static void main(String[] args) {

        VendedorDAO vendedorDAO = DAOFactory.criarVendedorDAO();

        System.out.println("=== TESTE 1: Vendedor procurarPorId ===");

        Vendedor vendedor = vendedorDAO.procurarPorId(3);

        System.out.println(vendedor);

        System.out.println("=== TESTE 2: Vendedor procurarPorDepartamento ===");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> list = vendedorDAO.procurarPorDepartamento(departamento);

        for (Vendedor vend : list) {
            System.out.println(vend);
        }

    }
}
