package com.futecamp.biel.futecamp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.futecamp.biel.futecamp.R;
import com.futecamp.biel.futecamp.config.ConfiguracaoFirebase;
import com.futecamp.biel.futecamp.model.entity.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private EditText emailLogin;
    private EditText senhaLogin;
    private TextView cadastrese;
    private Button btEntrar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = findViewById(R.id.usuarioLoginId);
        senhaLogin = findViewById(R.id.senhaLoginId);
        btEntrar = findViewById(R.id.botaoEntrarId);
        cadastrese = findViewById(R.id.cadastrarUsuarioId);

        cadastrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,CadastroUsuario_form.class);
                startActivity(intent);
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emailLogin.getText().equals("")&& !senhaLogin.getText().equals("")){

                    usuario = new Usuario();
                    usuario.setEmail(emailLogin.getText().toString());
                    usuario.setSenha(senhaLogin.getText().toString());

                    validarLogin();



                }else {
                    Toast.makeText(Login.this, "preencha os campos de email e senha válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail().toString(),usuario.getSenha().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()){

                            abrirTelaPrincipal();
                            Toast.makeText(Login.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Login.this, "Usuário ou senha inválidos! tente novamente", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
             }

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity( intent );
        finish();
    }

       }
