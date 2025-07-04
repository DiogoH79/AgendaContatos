package com.diogo.agendadecontatos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diogo.agendadecontatos.R;
import com.diogo.agendadecontatos.controller.DB_Controller;
import com.diogo.agendadecontatos.model.Contato;

public class MainActivity extends AppCompatActivity {

    private EditText editNome, editTelefone, editEmail;
    private Button buttonSalvar, buttonContatos;
    private DB_Controller dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbController = new DB_Controller(this);

        editNome = findViewById(R.id.editNome);
        editTelefone = findViewById(R.id.editTelefone);
        editEmail = findViewById(R.id.editEmail);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonContatos = findViewById(R.id.buttonContatos);

        buttonSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String telefone = editTelefone.getText().toString().trim();
            String email = editEmail.getText().toString().trim();

            if (!nome.isEmpty() && !telefone.isEmpty() && !email.isEmpty()) {
                Contato contato = new Contato(nome, telefone, email);
                dbController.insert(contato);
                Toast.makeText(MainActivity.this, "Contato salvo!", Toast.LENGTH_SHORT).show();

                editNome.setText("");
                editTelefone.setText("");
                editEmail.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonContatos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaContatosActivity.class);
            startActivity(intent);
        });
    }
}
