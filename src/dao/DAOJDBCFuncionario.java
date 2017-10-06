/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.Chefe;
import model.Funcionario;

/**
 *
 * @author Loc
 */
public class DAOJDBCFuncionario implements DAO<Funcionario> {

    private Connection conn;

    public DAOJDBCFuncionario() {
        String url = "jdbc:mysql://localhost:3306/funcionario";
        String username = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o DB My SQL! " + e.getMessage());
        }
    }

    @Override
    public int salvar(Funcionario f) throws DAOException {
        if (f.getId() == -1) {
            try (Statement stmt = conn.createStatement()) {
                String query = "INSERT INTO FUNCIONARIO (NOME, JORNADA, ESPECIALIDADES) VALUES('"
                        + f.getNome() + "','"
                        + f.getJornadaSemanal() + "','"
                        + f.getEspeciliadades() + "')";
                System.out.println("Comando: " + query);
                if (stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) != 1) {
                    throw new DAOException("Erro ao inserir!");
                } else {
                    ResultSet rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        f.setId(rs.getInt(1));
                    }//fim if
                } //fim if
            } catch (SQLException e) {
                throw new DAOException("Erro ao salvar! " + e.getMessage());
            }

        } else {
            /*Melhor formato para fazer acessos ao banco. Impede sql injection*/
            String query = "UPDATE FUNCIONARIO SET"
                    + " NOME = ?, JORNADA = ?,"
                    + " ESPECIALIDADES = ?, ID_CHEFE = ?"
                    + " WHERE ID = ?";
            System.out.println("Comando: " + query);
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, f.getNome());
                ps.setString(2, f.getJornadaSemanal());
                ps.setString(3, f.getEspeciliadades());
                ps.setInt(4, f.getChefe().getId());
                ps.setInt(5, f.getId());
                if (ps.executeUpdate() != 1) {
                    throw new DAOException("Erro ao atualizar");
                } //fim if
            } catch (SQLException e) {
                throw new DAOException("Erro ao gravar " + e.getMessage());
            } //fim try
        } //fim if
        return f.getId();
    }

    @Override
    public boolean excluir(Funcionario f) throws DAOException {
        String query = "DELETE FROM FUNCIONARIO"
                + " WHERE ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, f.getId());
            return ps.execute();
        } catch (SQLException e) {
            throw new DAOException("Erro ao excluir!" + e.getMessage());
        } //fim try
    }

    @Override
    public Funcionario buscarId(int id) throws DAOException {
        String query = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Funcionario f = new Funcionario(
                        rs.getInt(1), //ID
                        rs.getString(2), //nome
                        rs.getString(3), //jornada
                        rs.getString(4)); //especialidades
                String queryChefe = "SELECT * FROM CHEFE"
                        + " WHERE ID = ?";
                try (PreparedStatement psChefe = conn.prepareStatement(queryChefe)){
                    psChefe.setInt(1, rs.getInt(5)); //id do chefe
                    ResultSet rsChefe = psChefe.executeQuery();
                    if (rsChefe.next()){
                        Chefe c = new Chefe(
                                rsChefe.getInt(1), //id do chefe
                                rsChefe.getString(2), //nome do chefe
                                rsChefe.getString(3), //setor
                                rsChefe.getString(4), //jornada
                                null);                 //funcionarios
                    f.setChefe(c);
                    } //fim if
                    return f;
                } catch (SQLException e) {
                    throw new DAOException("Erro ao buscar chefe!" + e.getMessage());
                }
            
         
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar por ID " + e.getMessage());
        } //fim try
        return null;
    }

    @Override
    public List<Funcionario> buscarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
