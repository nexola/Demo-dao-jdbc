package model.dao;

import model.entities.Departamento;

import java.util.List;

public interface DepartamentoDAO {

    Departamento procurarPorId(Integer id);

    List<Departamento> procurarTudo();

    void criar(Departamento obj);

    void atualizar(Departamento obj);

    void deletarPorId(Integer id);

}
