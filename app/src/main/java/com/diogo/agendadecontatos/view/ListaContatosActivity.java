package com.diogo.agendadecontatos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.diogo.agendadecontatos.R;
import com.diogo.agendadecontatos.controller.ContatoController;
import com.diogo.agendadecontatos.model.Contato;

import java.util.ArrayList;

public class ListaContatosActivity extends AppCompatActivity {

    private ListView listViewContatos;
    private ContatoController contatoController;
    private Button btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(ListaContatosActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button btnEditar = findViewById(R.id.btnEditar);

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(ListaContatosActivity.this, EditarContatosActivity.class);
            startActivity(intent);
        });

        listViewContatos = findViewById(R.id.listViewContatos);
        contatoController = ContatoController.getInstance();

        ArrayList<String> nomesContatos = new ArrayList<>();
        for (Contato c : contatoController.getListaContatos()) {
            nomesContatos.add(c.getNome() + " - " + c.getTelefone() + " - " + c.getEmail());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, nomesContatos);
        listViewContatos.setAdapter(adapter);
    }
}
