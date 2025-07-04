package com.diogo.agendadecontatos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.diogo.agendadecontatos.R;
import com.diogo.agendadecontatos.controller.DB_Controller;
import com.diogo.agendadecontatos.model.Contato;

import java.util.ArrayList;

public class ListaContatosActivity extends AppCompatActivity {

    private ListView listViewContatos;
    private Button btnVoltar, btnEditar;
    private DB_Controller dbController;
    private ArrayList<Contato> listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        dbController = new DB_Controller(this);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(ListaContatosActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(ListaContatosActivity.this, EditarContatosActivity.class);
            startActivity(intent);
        });

        listViewContatos = findViewById(R.id.listViewContatos);

        listaContatos = dbController.listarContatos();

        ArrayList<String> nomesContatos = new ArrayList<>();
        for (Contato c : listaContatos) {
            nomesContatos.add(c.getNome() + " - " + c.getTelefone() + " - " + c.getEmail());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, nomesContatos);
        listViewContatos.setAdapter(adapter);


        listViewContatos.setOnItemClickListener((parent, view, position, id) -> {
            Contato contatoSelecionado = listaContatos.get(position);
            Intent intent = new Intent(ListaContatosActivity.this, EditarContatosActivity.class);
            intent.putExtra("id", contatoSelecionado.getId());
            intent.putExtra("nome", contatoSelecionado.getNome());
            intent.putExtra("telefone", contatoSelecionado.getTelefone());
            intent.putExtra("email", contatoSelecionado.getEmail());
            startActivity(intent);
        });
    }
}
