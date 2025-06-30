package com.diogo.agendadecontatos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diogo.agendadecontatos.R;
import com.diogo.agendadecontatos.controller.ContatoController;

public class MainActivity extends AppCompatActivity {

    private EditText editNome, editTelefone, editEmail;
    private Button buttonSalvar, buttonContatos;
    private ContatoController contatoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contatoController = ContatoController.getInstance();

        editNome = findViewById(R.id.editNome);
        editTelefone = findViewById(R.id.editTelefone);
        editEmail = findViewById(R.id.editEmail);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonContatos = findViewById(R.id.buttonContatos);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString().trim();
                String telefone = editTelefone.getText().toString().trim();
                String email = editEmail.getText().toString().trim();

                if (contatoController.salvarContato(nome, telefone, email)) {
                    Toast.makeText(MainActivity.this, "Contato salvo!", Toast.LENGTH_SHORT).show();
                    editNome.setText("");
                    editTelefone.setText("");
                    editEmail.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaContatosActivity.class);
                startActivity(intent);
            }
        });
    }
}
