package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Vendedor implements Serializable {

    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataDeNascimento;
    private Double salarioBase;
    private Departamento departamento;

    public Vendedor() {}

    public Vendedor(Integer id, String nome, String email, LocalDate aniversario, Double salarioBase, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataDeNascimento = aniversario;
        this.salarioBase = salarioBase;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getAniversario() {
        return dataDeNascimento;
    }

    public void setAniversario(LocalDate aniversario) {
        this.dataDeNascimento = aniversario;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return Objects.equals(id, vendedor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Vendedor " + '\n' +
                " ID: " + id + '\n' +
                " Nome: " + nome + '\n' +
                " Email: " + email + '\n' +
                " Data de nascimento: " + dataDeNascimento.format(dtf) + '\n' +
                " Salario base: $" + String.format("%.2f", salarioBase) + '\n' +
                " Departamento: " + departamento;
    }
}
