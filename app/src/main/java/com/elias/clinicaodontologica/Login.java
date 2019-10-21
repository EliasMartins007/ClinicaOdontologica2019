package com.elias.clinicaodontologica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    //declaro componentes que irei utilizar exemplos campos da tela 05/09/2019
    private Button btLogar; //campo login da tela
    private EditText txtLogin = null; // campo login
    private EditText txtSenha = null; //campo senha

    SharedPreferences SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //retirar barra superior do android
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        //recuperar componente da tela
        criarComponentes();

        //eventos
        criarEventos();

        //teste internet preferences logout 06/09/2019
        SM = getSharedPreferences("userrecord", 0);
        Boolean islogin = SM.getBoolean("userlogin", false);
        if (islogin) {
            Intent intent = new Intent(Login.this, MainActivity.class);

            //inicia intenção
            startActivity(intent);

            //finaliza atividade anterior
            finish();

            return;
        }
    }


    //region componentes
    private void criarComponentes() {
        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btLogar = (Button) findViewById(R.id.btLogar);

    }
    //endregion


    //region Eventos da Tela
    private void criarEventos() {
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDadosAcesso();
            }
        });
    }


    //endregion

    //region validar Acesso ao sistema
    private void validarDadosAcesso() {
        try {
            if (txtLogin.getText().toString().length() == 0) {
                txtLogin.setError("Digite Usuario ");
            } else if (txtSenha.getText().toString().length() == 0) {
                txtSenha.setError("Digite Senha ");
            } else if (txtLogin.getText().toString().trim().equals("marcos") && txtSenha.getText().toString().trim().equals("marcos")) {
                SharedPreferences.Editor edit = SM.edit();
                edit.putBoolean("userlogin", true);//usuario logado !!!   // terei de adicionar essa linha quando realizar login e quando realizar logout  09/10/2019
                edit.commit();
                usuarioLogado();
            } else {
                Toast.makeText(getApplicationContext(), "Usuario ou senha invalidos !!!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    //endregion


    //region usuario logado
    private void usuarioLogado() {
        Intent home = new Intent(Login.this, MainActivity.class);

        //inicio minha atividade
        startActivity(home);

        super.finish();//olhar por que desse metodo aqui ??? 05/08/2019   elias
    }
    //endregion
}
