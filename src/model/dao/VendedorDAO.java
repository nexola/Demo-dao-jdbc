package model.dao;

import model.entities.Vendedor;

import java.util.List;

public interface VendedorDAO {

    void criar(Vendedor obj);

    void atualizar(Vendedor obj);

    void deletarPorId(Integer id);

    Vendedor procurarPorId(Integer id);

    List<Vendedor> procurarTudo();

}
