/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Chefe;
import model.Funcionario;

/**
 *
 * @author Loc
 */
public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();
    
    public DAO createDAO(Class clazz) {
        if (clazz == Funcionario.class) {
            return new DAOJDBCFuncionario();
        } else if (clazz == Chefe.class) {
            return new DAOJDBCChefe();
        }
        return null;
    }
    
    private DAOFactory(){}
    
    public static DAOFactory getInstance() {
        return instance;
    }
}
