package com.diogo.agendadecontatos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diogo.agendadecontatos.R;
import com.diogo.agendadecontatos.controller.DB_Controller;
import com.diogo.agendadecontatos.model.Contato;

import java.util.ArrayList;

public class EditarContatosActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Contato> contatos;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> nomesContatos;
    private DB_Controller dbController;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contatos);

        listView = findViewById(R.id.listaContatosEditar);
        btnVoltar = findViewById(R.id.btnVoltar);
        dbController = new DB_Controller(this);

        carregarContatos();

        listView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Contato contatoParaApagar = contatos.get(position);

            dbController.delete(contatoParaApagar.getId());
            Toast.makeText(this, "Contato apagado", Toast.LENGTH_SHORT).show();
            carregarContatos();

            return true;
        });

        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(EditarContatosActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void carregarContatos() {
        contatos = dbController.listarContatos();
        nomesContatos = new ArrayList<>();
        for (Contato c : contatos) {
            nomesContatos.add(c.getNome());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomesContatos);
        listView.setAdapter(adapter);
    }
}
