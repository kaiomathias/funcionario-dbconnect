/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Loc
 */
public interface DAO<T> extends AutoCloseable {
    //CRUD
    public int salvar (T t) throws DAOException;
    public boolean excluir (T t) throws DAOException;
    public T buscarId (int id) throws DAOException;
    public List<T> buscarTodos() throws DAOException;
    @Override
    public void close() throws DAOException;
}
