package controle;

import dao.DAO;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOMemChefe;
import dao.DAOMemFuncionario;
import java.util.ArrayList;
import java.util.List;
import model.Chefe;
import model.Funcionario;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class Controle {
    
    private List<Funcionario> tempFuncionarios = new ArrayList();
    //pegar uma instancia da fábrica de DAO
    private DAOFactory factory = DAOFactory.getInstance();
    public void adicionarFuncionarioTemp(Funcionario funcionario) {
        if (!tempFuncionarios.contains(funcionario)) {
            this.tempFuncionarios.add(funcionario);
        }
    }

    public void removerFuncionarioTemp(Funcionario funcionario) {
        this.tempFuncionarios.remove(funcionario);
    }

    public boolean contemFuncionarioTemp(Funcionario funcionario) {
        return tempFuncionarios.contains(funcionario);
    }

    public int salvarFuncionario(String nome,
            String jornada, String especialidades) {
        Funcionario funcionario = new Funcionario(-1, nome, jornada, especialidades);

        //invocar DAO
        try (DAO daoFuncionario = factory.createDAO(Funcionario.class)) {
            return daoFuncionario.salvar(funcionario);

        } catch (DAOException ex) {
            System.out.println("Erro ao gravar dados!" + ex.getMessage());
        }
        return -1;
    } //fim método

    public int salvarFuncionario(Funcionario funcionario) {

        //invocar DAO
        try (DAO daoFuncionario = factory.createDAO(Funcionario.class)) {
            return daoFuncionario.salvar(funcionario);

        } catch (DAOException e) {
            System.out.println("Erro ao gravar dados!" + e.getMessage());
        }

        return -1;
    } //fim método

    public int salvarChefe(int _id, String nome, String setor, String jornadaSemanal, List<Funcionario> funcionarios) {

        Chefe chefe = new Chefe(-1, nome, setor, jornadaSemanal, funcionarios);
        int codigoChefe = -1;

        //invocar DAO
        try (DAO daoChefe = factory.createDAO(Chefe.class)) {
            codigoChefe = daoChefe.salvar(chefe);
            for (Funcionario f : funcionarios) {
                f.setChefe(chefe);
                salvarFuncionario(f);
            } //fim for
        } catch (DAOException e) {
            System.out.println("Erro ao gravar dados!" + e.getMessage());
        }
        return codigoChefe;

    }//fim metodo

    public Funcionario buscaFuncionarioId(int id) {

        //invocar DAO
        try (DAO daoFuncionario = factory.createDAO(Funcionario.class)) {
            return (Funcionario) daoFuncionario.buscarId(id);
        } catch (DAOException e) {
            System.out.println("Dados não localizados" + e.getMessage());
        }
        return null;
    }//fim metodo

    public Chefe buscarChefeId(int id) {
        try (DAO daoChefe = factory.createDAO(Chefe.class)) {
            return (Chefe) daoChefe.buscarId(id);
        } catch (DAOException e) {
            System.out.println("Dados não localizados! " + e.getMessage());
        } //fim try

        return null;
    }

    public List<Funcionario> getTempFuncionarios() {
        return tempFuncionarios;
    }

    public List<Chefe> buscarChefes() {
        try(DAO daoChefe = factory.createDAO(Chefe.class)) {
            return daoChefe.buscarTodos();
        } catch (DAOException e) {
            System.out.println("Dados não localizados! " + e.getMessage());
        }
        return null;
    }

    public void limparTempFuncionario() {
        this.tempFuncionarios = new ArrayList<>();
    }
} //fim classse

