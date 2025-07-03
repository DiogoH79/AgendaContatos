package com.diogo.agendadecontatos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diogo.agendadecontatos.R;
import com.diogo.agendadecontatos.controller.ContatoController;
import com.diogo.agendadecontatos.model.Contato;

import java.util.ArrayList;

public class EditarContatosActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Contato> contatos;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> nomesContatos;
    private ContatoController controller;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contatos);

        listView = findViewById(R.id.listaContatosEditar);
        controller = ContatoController.getInstance();
        contatos = controller.getListaContatos();
        btnVoltar = findViewById(R.id.btnVoltar);


        nomesContatos = new ArrayList<>();
        for (Contato c : contatos) {
            nomesContatos.add(c.getNome());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomesContatos);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            contatos.remove(position);
            nomesContatos.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Contato apagado", Toast.LENGTH_SHORT).show();
            return true;
        });


        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(EditarContatosActivity.this , MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
