package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DAOFactory;
import model.dao.DepartamentoDAO;
import model.entities.Departamento;

public class Program2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartamentoDAO departamentoDAO = DAOFactory.criarDepartamentoDAO();

        System.out.println("=== TESTE 1: procurarPorId ===");
        Departamento dep = departamentoDAO.procurarPorId(1);
        System.out.println(dep);

        System.out.println("=== TESTE 2: procurarTudo ====");
        List<Departamento> list = departamentoDAO.procurarTudo();
        for (Departamento d : list) {
            System.out.println(d);
        }

        System.out.println("=== TESTE 3: criar ===");
        Departamento newDepartment = new Departamento(null, "Music");
        departamentoDAO.criar(newDepartment);
        System.out.println("Inserido! Novo ID: " + newDepartment.getId());

        System.out.println("=== TESTE 4: atualizar ===");
        Departamento dep2 = departamentoDAO.procurarPorId(1);
        dep2.setNome("Food");
        departamentoDAO.atualizar(dep2);
        System.out.println("Atualização completa!");

        System.out.println("=== TESTE 5: deleção ===");
        System.out.print("Entre com o Id para o teste de deleção: ");
        int id = sc.nextInt();
        departamentoDAO.deletarPorId(id);
        System.out.println("Deleção concluída!");

        sc.close();
    }
}