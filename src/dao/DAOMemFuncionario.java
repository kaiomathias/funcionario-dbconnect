/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

/**
 *
 * @author Loc
 */
public class DAOMemFuncionario implements DAO<Funcionario> {
    private static int ULTIMO_ID = 0;
    private static final List<Funcionario> funcionarioDB = new ArrayList<>();
    @Override
    public int salvar(Funcionario f) throws DAOException {
        if (f.getId() == -1) {
            f.setId(++ULTIMO_ID);
            try{
                this.funcionarioDB.add(f);
            } catch (RuntimeException ex) {
                throw new DAOException("Erro ao salvar" + ex.getMessage());
            } //fim try
        }else {
            excluir(f);
            this.funcionarioDB.add(f);
        }//fim if
        return f.getId();
    } //fim salvar

    @Override
    public boolean excluir(Funcionario f) throws DAOException {
        try {
            return this.funcionarioDB.remove(f);
        } catch (RuntimeException ex) {
            throw new DAOException("Erro ao excluir! " + ex.getMessage());
        } //fim catch
    } //fim excluir

    @Override
    public Funcionario buscarId(int id) throws DAOException {
        try {
            for (Funcionario f : funcionarioDB) {
                if (f.getId() == id) {
                    return f;
                } //fim if
            } //fim for
        } catch (RuntimeException ex) {
            throw new DAOException("Erro ao buscar por ID! " + ex.getMessage());
        } //fim try
        return null;
    }

    @Override
    public List<Funcionario> buscarTodos() throws DAOException {
        try {
            return funcionarioDB;
        } catch(RuntimeException ex){
            throw new DAOException("Erro ao listar todos " + ex.getMessage());
        } //fim try
        
    }

    @Override
    public void close() throws DAOException {
        System.out.println("[Memória] - fechado");
    }
    
}
