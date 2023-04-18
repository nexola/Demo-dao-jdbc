package model.dao;

import model.entities.Departamento;

import java.util.List;

public interface DepartamentoDAO {

    void criar(Departamento obj);

    void atualizar(Departamento obj);

    void deletarPorId(Integer id);

    Departamento procurarPorId(Integer id);

    List<Departamento> procurarTudo();

}
