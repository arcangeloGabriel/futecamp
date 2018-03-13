package com.futecamp.biel.futecamp.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.futecamp.biel.futecamp.R;
import com.futecamp.biel.futecamp.activity.CadastroUsuario_form;
import com.futecamp.biel.futecamp.model.entity.Usuario;

/**
 * Created by biel on 12/03/2018.
 */

public class UsuarioHelper {

    private EditText edNome;
    private EditText edTelefone;
    private EditText edSenha;
    private EditText edEmail;
    private EditText edEndereco;
    private ImageView ivFoto;

    private Usuario usuario;

    public UsuarioHelper(CadastroUsuario_form formulario) {

        edNome = formulario.findViewById(R.id.edNomeId);
        edTelefone = formulario.findViewById(R.id.edTelefoneId);
        edEmail = formulario.findViewById(R.id.edEmailId);
        edSenha = formulario.findViewById(R.id.edSenhaId);
        edEndereco = formulario.findViewById(R.id.edEnderecoId);
        ivFoto = formulario.findViewById(R.id.fotoUsuarioId);

        usuario = new Usuario();
    }

    public Usuario getUsuario(){
        usuario.setNome(edNome.getText().toString());
        usuario.setTelefone(edTelefone.getText().toString());
        usuario.setEmail(edEmail.getText().toString());
        usuario.setSenha(edSenha.getText().toString());
        usuario.setEndereco(edEndereco.getText().toString());
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        edNome.setText(usuario.getNome());
        edTelefone.setText(usuario.getTelefone());
        edEmail.setText(usuario.getEmail());
        edSenha.setText(usuario.getSenha());
        edEndereco.setText(usuario.getEndereco());

        if(usuario.getFoto()!=null){
            carregarFoto(usuario.getFoto());
        }

        this.usuario = usuario;
    }

    public ImageView getIvFoto() {
        return ivFoto;

    }

    public void carregarFoto(String localArquivoFoto){
        Log.d("CH.carregarFoto()", localArquivoFoto);
        Bitmap imagem = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemReduzida =
                Bitmap.createScaledBitmap(imagem,
                        100, 100, true);
        usuario.setFoto(localArquivoFoto);
        ivFoto.setImageBitmap(imagemReduzida);
    }
}
