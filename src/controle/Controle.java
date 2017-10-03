package controle;

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
        //Funcionario funcionario = new Funcionario(-1, nome, jornada, especialidades);

        //invocar DAO
        
        return -1;
    } //fim método

    public int salvarFuncionario(Funcionario funcionario) {

        //invocar DAO

        return -1;
    } //fim método

    public int salvarChefe(int _id, String nome, String setor, String jornadaSemanal, List<Funcionario> funcionarios) {
        //Chefe chefe = new Chefe(-1, nome, setor, jornadaSemanal, funcionarios);
        int codigoChefe = -1;

        //invocar DAO

        return codigoChefe;

    }//fim metodo

    public Funcionario buscaFuncionarioId(int id) {

        //invocar DAO

        return null;
    }//fim metodo

    public Chefe buscarChefeId(int id) {

        //invocar DAO

        return null;
    }

    public List<Funcionario> getTempFuncionarios() {
        return tempFuncionarios;
    }

    public List<Chefe> buscarChefes() {

        //invocar DAO

        return null;
    }

    public void limparTempFuncionario() {
        this.tempFuncionarios = new ArrayList<>();
    }
} //fim classse

