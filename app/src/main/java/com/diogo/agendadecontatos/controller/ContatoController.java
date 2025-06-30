package com.diogo.agendadecontatos.controller;

import com.diogo.agendadecontatos.model.Contato;

import java.util.ArrayList;

public class ContatoController {

    private static ContatoController instance;
    private ArrayList<Contato> listaContatos;

    private ContatoController() {
        listaContatos = new ArrayList<>();
    }

    public static ContatoController getInstance() {
        if (instance == null) {
            instance = new ContatoController();
        }
        return instance;
    }

    public boolean salvarContato(String nome, String telefone, String email) {
        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            return false;
        }

        Contato contato = new Contato(nome, telefone, email);
        listaContatos.add(contato);
        return true;
    }

    public ArrayList<Contato> getListaContatos() {
        return listaContatos;
    }
}
