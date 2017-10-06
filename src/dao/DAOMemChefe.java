/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Chefe;

/**
 *
 * @author Loc
 */
public class DAOMemChefe implements DAO<Chefe> {
    private static int ULTIMO_ID = 0;
    private static final List<Chefe> chefeDB = new ArrayList<>();
    
    @Override
    public int salvar(Chefe c) throws DAOException {
        if(c.getId() == -1){
            c.setId(++ULTIMO_ID);
            try{
                chefeDB.add(c);
            }catch (RuntimeException ex) {
                throw new DAOException("Erro ao Salvar!" + ex.getMessage());
            } //fim try
        } else {
            excluir(c);
            chefeDB.add(c);
        } //fim if
        return c.getId();
    }

    @Override
    public boolean excluir(Chefe c) throws DAOException {
        try {
            return this.chefeDB.remove(c);
        } catch (RuntimeException ex) {
            throw new DAOException("Erro ao excluir! " + ex.getMessage());
        } //fim catch
    }

    @Override
    public Chefe buscarId(int id) throws DAOException {
        try {
            for (Chefe c : chefeDB) {
                if(c.getId() == id) {
                    return c;
                }
            }
        } catch (RuntimeException ex) {
            throw new DAOException ("Erro ao buscar por ID!" + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Chefe> buscarTodos() throws DAOException {
        try {
            return chefeDB;
        } catch (RuntimeException ex) {
            throw new DAOException("Erro ao buscar todos! " + ex.getMessage());
        }
    }

    @Override
    public void close() throws DAOException {
        System.out.println("[Memória] - fechado");
    }
    
}
