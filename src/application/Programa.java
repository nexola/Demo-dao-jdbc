package application;

import model.entities.Departamento;
import model.entities.Vendedor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Programa {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Departamento departamento = new Departamento(1, "Logística");
        System.out.println(departamento);

        Vendedor vendedor = new Vendedor(1, "José", "joseantonio@gmail.com", LocalDate.parse("19/05/2002", dtf), 2200.0, departamento);

        System.out.println(vendedor);

    }
}
