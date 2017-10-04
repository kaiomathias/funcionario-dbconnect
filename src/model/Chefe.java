/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author igordev
 */
public class Chefe {

    private int _id;
    private String setor;
    private String nome;
    private String jornadaSemanal;
    private List<Funcionario> funcionarios;

    public Chefe(int _id, String nome, String setor, String jornadaSemanal, List<Funcionario> funcionarios) {
        this._id = _id;
        this.nome = nome;
        this.setor = setor;
        this.jornadaSemanal = jornadaSemanal;
        this.funcionarios = funcionarios;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getJornadaSemanal() {
        return jornadaSemanal;
    }

    public void setJornadaSemanal(String jornadaSemanal) {
        this.jornadaSemanal = jornadaSemanal;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        String info = "Chefe: " + _id + " - " + nome;
        info += " Setor: " + setor;
        info += " Jornada: " + jornadaSemanal + "\n";
        for(Funcionario f: funcionarios) {
            info+= "\t"+ f.toString() + "\n";
        }
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if( o instanceof Chefe) {
            Chefe c = (Chefe) o;
            return this._id == _id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this._id;
    }
    

}
