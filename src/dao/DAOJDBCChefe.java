package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Chefe;
import model.Funcionario;

/**
 *
 * @author Loc
 */
public class DAOJDBCChefe implements DAO<Chefe> {

    private Connection conn;

    public DAOJDBCChefe() {
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
    public int salvar(Chefe c) throws DAOException {
        if (c.getId() == -1) {
            String query = "INSERT INTO CHEFE (NOME, SETOR, JORNADA)"
                    + " VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, c.getNome());
                ps.setString(2, c.getSetor());
                ps.setString(3, c.getJornadaSemanal());
                if (ps.executeUpdate() != 1) {
                    throw new DAOException("Erro ao inserir!");
                } else {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        c.setId(rs.getInt(1));

                    } //fim if
                }// fim if
            } catch (SQLException ex) {
                throw new DAOException("Erro ao inserir!" + ex.getMessage());
            } //fim try
        } else {
            String query = "UPDATE CHEFE SET "
                    + "NOME = ?,"
                    + "SETOR = ?,"
                    + "JORNADA = ? WHERE ID = ?";
            System.out.println(query);
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, c.getNome());
                ps.setString(2, c.getSetor());
                ps.setString(3, c.getJornadaSemanal());
                ps.setInt(4, c.getId());
                if (ps.executeUpdate() != 1) {
                    throw new DAOException("Erro ao atualizar!");
                } //fim if
            } catch (SQLException ex) {
                throw new DAOException("Erro ao salvar! " + ex.getMessage());
            } //fim try
        } //fim if
        return c.getId();
    }

    @Override
    public boolean excluir(Chefe c) throws DAOException {
        String query = "DELETE FROM CHEFE WHERE ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, c.getId());
            return ps.execute();

        } catch (SQLException ex) {
            throw new DAOException("Erro ao excluir! " + ex.getMessage());
        } //fim try
    }

    @Override
    public Chefe buscarId(int id) throws DAOException {
        String query = "SELECT * FROM CHEFE WHERE ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Chefe chefe = new Chefe(
                        rs.getInt(1), //ID
                        rs.getString(2), //NOME
                        rs.getString(3), //SETOR
                        rs.getString(4), //jornada
                        null);
                String queryFunc = "SELECT * FROM FUNCIONARIO "
                        + "WHERE ID_CHEFE = ?";
                try (PreparedStatement psFunc = conn.prepareStatement(queryFunc)) {
                    psFunc.setInt(1, chefe.getId());
                    ResultSet rsFunc = psFunc.executeQuery();
                    List<Funcionario> funcionarios = new ArrayList<>();
                    while (rs.next()) {
                        Funcionario f = new Funcionario(
                                rsFunc.getInt(1), //id
                                rsFunc.getString(2), //nome
                                rsFunc.getString(3), //jornada
                                rsFunc.getString(4)); //especialidade
                        f.setChefe(chefe);
                        funcionarios.add(f);
                    } //fim while
                    chefe.setFuncionarios(funcionarios);
                } catch (SQLException ex) {
                    throw new DAOException("Erro ao buscar ID! " + ex.getMessage());
                } //fim try
                return chefe;
            } //fim if
        } catch (SQLException ex) {
            throw new DAOException("Erro ao buscar! " + ex.getMessage());
        } //fim try
        return null;
    }

    @Override
    public List<Chefe> buscarTodos() throws DAOException {
        List<Chefe> chefes = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM CHEFE";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Chefe chefe = new Chefe(
                        rs.getInt(1), //ID
                        rs.getString(2), //NOME
                        rs.getString(3), //SETOR
                        rs.getString(4), //jornada
                        null);
                String queryFunc = "SELECT * FROM FUNCIONARIO "
                        + "WHERE ID_CHEFE = ?";
                try (PreparedStatement psFunc = conn.prepareStatement(queryFunc)) {
                    psFunc.setInt(1, chefe.getId());
                    ResultSet rsFunc = psFunc.executeQuery();
                    List<Funcionario> funcionarios = new ArrayList<>();
                    while (rsFunc.next()) {
                        Funcionario f = new Funcionario(
                                rsFunc.getInt(1), //id
                                rsFunc.getString(2), //nome
                                rsFunc.getString(3), //jornada
                                rsFunc.getString(4)); //especialidade
                        f.setChefe(chefe);
                        funcionarios.add(f);
                    } //fim while
                    chefe.setFuncionarios(funcionarios);
                    chefes.add(chefe);
                } catch (SQLException ex) {
                    throw new DAOException("Erro ao buscar ID! " + ex.getMessage());
                } //fim try
            } //fim while
            return chefes;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao buscar! " + ex.getMessage());
        } //fim try

    }

    @Override
    public void close() throws DAOException {
        try {
            conn.close();
            System.out.println("Conexão fechada!");
        } catch (SQLException ex) {
            throw new DAOException("Erro ao fechar conexão! " + ex.getMessage());
        }
    }

}
