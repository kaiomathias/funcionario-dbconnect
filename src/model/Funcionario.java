/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author igordev
 */
public class Funcionario {
    private int _id;
    private String nome;
    private String jornadaSemanal;
    private String especiliadades;
    private Chefe chefe = null;

    public Funcionario(int _id, String nome, String jornadaSemanal, String especiliadades) {
        this._id = _id;
        this.nome = nome;
        this.jornadaSemanal = jornadaSemanal;
        this.especiliadades = especiliadades;
    }

    public int getId() {
        return _id;
    }

    public String getNome() {
        return nome;
    }

    public String getJornadaSemanal() {
        return jornadaSemanal;
    }

    public String getEspeciliadades() {
        return especiliadades;
    }

    public Chefe getChefe() {
        return chefe;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setJornadaSemanal(String jornadaSemanal) {
        this.jornadaSemanal = jornadaSemanal;
    }

    public void setEspeciliadades(String especiliadades) {
        this.especiliadades = especiliadades;
    }

    public void setChefe(Chefe chefe) {
        this.chefe = chefe;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Funcionario) {
            Funcionario f = (Funcionario) o;
            return this._id == f._id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this._id;
    }

    @Override
    public String toString() {
        String info = "Funcionario: " + _id + " - " + nome;
        info += String.format(" - Jornada[%1$s]", jornadaSemanal);
        return info;
    }
    
     
}
