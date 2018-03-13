package com.futecamp.biel.futecamp.model.entity;

import com.futecamp.biel.futecamp.model.dao.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

/**
 * Created by biel on 12/03/2018.
 */

public class Usuario implements Serializable{


    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String endereco;
    private String foto;






    @Override
    public String toString() {return nome;}


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   @Exclude
    public String getSenha() {
        return senha;
    }
    @Exclude
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
