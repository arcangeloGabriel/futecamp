package com.futecamp.biel.futecamp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.futecamp.biel.futecamp.R;
import com.futecamp.biel.futecamp.helper.Base64Custom;
import com.futecamp.biel.futecamp.helper.Preferencias;
import com.futecamp.biel.futecamp.model.dao.ConfiguracaoFirebase;
import com.futecamp.biel.futecamp.model.entity.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.SQLException;

public class CadastroUsuario_form extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText email;
    private EditText senha;
    private EditText endereco;
    private Button cadastarar;
    private ImageView ivfoto;
    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_form);

        nome = findViewById(R.id.edNomeId);
        telefone = findViewById(R.id.edTelefoneId);
        email = findViewById(R.id.edEmailId);
        senha = findViewById(R.id.edSenhaId);
        endereco= findViewById(R.id.edEnderecoId);
        cadastarar = findViewById(R.id.btCadastrarId);
        ivfoto = findViewById(R.id.fotoUsuarioId);

        cadastarar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    usuario = new Usuario();
                    usuario.setNome(nome.getText().toString());
                    usuario.setTelefone(telefone.getText().toString());
                    usuario.setEmail(email.getText().toString());
                    usuario.setSenha(senha.getText().toString());
                    usuario.setEndereco(endereco.getText().toString());

                    //chamada de metodo para cadastros de usuarios
                    cadastrarUsuario();

            }
        });
    }

    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuario_form.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    insereUsuario(usuario);


                } else {

                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digite yma senha mais forte, contento no minimo 8 caracteres e que contenha letras e números";
                  } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "O email e inválido, digite outro email";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Esse email já esta cadastrado";
                    } catch (Exception e) {
                        erroExcecao = "erro ao efetuar o cadastro";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroUsuario_form.this,"erro" + erroExcecao,Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    private Boolean insereUsuario(Usuario usuario){

        try {

            //DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
            //referenciaFirebase.child("usuarios").setValue( this );

            reference =ConfiguracaoFirebase.getFirebase().child("usuarios");
            reference.push().setValue(usuario);//inseri os usuarios com a chave exclusive dele

            Toast.makeText(CadastroUsuario_form.this,"Usuario gravado com sucesso",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CadastroUsuario_form.this,Login.class);
            startActivity(intent);
            return true;

        }catch (Exception e){
            Toast.makeText(CadastroUsuario_form.this,"Erro ao gravar o usuario",Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }

    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroUsuario_form.this, Login.class);
        startActivity(intent);
        finish();
    }

}
