package com.diogo.agendadecontatos.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diogo.agendadecontatos.model.Contato;
import com.diogo.agendadecontatos.model.DB;

import java.util.ArrayList;

public class DB_Controller {

    private SQLiteDatabase db;
    private DB banco;

    public DB_Controller(Context context) {
        banco = new DB(context);
    }

    public void insert(Contato contato) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB.NOME, contato.getNome());
        values.put(DB.TELEFONE, contato.getTelefone());
        values.put(DB.EMAIL, contato.getEmail());

        db.insert(DB.TABLE, null, values);
        db.close();
    }

    public void update(Contato contato) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB.NOME, contato.getNome());
        values.put(DB.TELEFONE, contato.getTelefone());
        values.put(DB.EMAIL, contato.getEmail());

        db.update(DB.TABLE, values, DB.ID + " = ?", new String[]{String.valueOf(contato.getId())});
        db.close();
    }

    public void delete(int id) {
        db = banco.getWritableDatabase();
        db.delete(DB.TABLE, DB.ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<Contato> listarContatos() {
        db = banco.getReadableDatabase();

        ArrayList<Contato> lista = new ArrayList<>();
        Cursor cursor = db.query(DB.TABLE, null, null, null, null, null, DB.NOME);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DB.ID));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(DB.NOME));
            String telefone = cursor.getString(cursor.getColumnIndexOrThrow(DB.TELEFONE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DB.EMAIL));

            lista.add(new Contato(id, nome, telefone, email));
        }

        cursor.close();
        return lista;
    }
}
