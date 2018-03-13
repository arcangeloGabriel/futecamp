package com.futecamp.biel.futecamp.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.futecamp.biel.futecamp.model.entity.Usuario;

/**
 * Created by biel on 12/03/2018.
 */

public class UsuarioDao extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "Usuario";
    private static final String BD = "FuteCamp";

    private static final String TAG = Usuario.class.getCanonicalName();

    public UsuarioDao(Context context) {
        super(context, BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String ddl = "CREATE TABLE " + TABELA +
                "(id INTEGER PRIMARY KEY, " +
                "  nome TEXT, telefone TEXT, " +
                "  email TEXT, senha TEXT, " +
                "  endereco TEXT, foto TEXT" +
                ")";
        db.execSQL(ddl);
        Log.i(TAG, "Criação da tabela " + TABELA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String ddl = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(ddl);
        onCreate(db);
        Log.i(TAG, "Atualização da tabela " + TABELA);

    }

    public void cadastrar(Usuario usuario) {
        ContentValues values = new ContentValues();

        values.put("nome", usuario.getNome());
        values.put("telefone", usuario.getTelefone());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        values.put("endereco", usuario.getEndereco());
        values.put("foto", usuario.getFoto());
        getWritableDatabase().insert(TABELA,
                null,
                values);
    }

}
