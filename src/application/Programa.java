package application;

import model.dao.DAOFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Programa {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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

        System.out.println("=== TESTE 3: Vendedor procurarTudo ===");
        list = vendedorDAO.procurarTudo();

        for (Vendedor vend : list) {
            System.out.println(vend);
        }

        System.out.println("=== TESTE 4: Vendedor criar ===");
        Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, departamento);
        vendedorDAO.criar(novoVendedor);
        System.out.println("Inserido! Novo ID = " + novoVendedor.getId());

        System.out.println("=== TESTE 5: Vendedor atualizar ===");
        vendedor = vendedorDAO.procurarPorId(1);
        vendedor.setNome("Martha Waine");
        vendedorDAO.atualizar(vendedor);
        System.out.println("Atualização completa");

        System.out.println("=== TESTE 5: Vendedor deletarPorId ===");
        System.out.println("Entre um id para o teste de deleção: ");
        int id = sc.nextInt();
        vendedorDAO.deletarPorId(id);
        System.out.println("Deleção concluída!");

        sc.close();
    }
}
