package com.elias.clinicaodontologica;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class Sobre extends AppCompatActivity {

    //Text que receberam os dados
    private TextView lblNomeApp = null;
    private TextView lblVersaoApp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //tirar barra superio padrao android studio   14/08/2019
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sobre);

        try {

            criarComponentes();


        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    //region recuperar Componentes
    private void criarComponentes() {
        lblNomeApp = (TextView) findViewById(R.id.lblNomeApp);
        lblVersaoApp = (TextView) findViewById(R.id.lblVersaoApp);

        getInformacoesApp();
    }


    //endregion


    //region informações do aplicativo  "NOME , VERSÃO"  14/08/2019
    private void getInformacoesApp() {

        PackageManager manager = getPackageManager();
        PackageInfo inf = null;

        try {
        inf = manager.getPackageInfo(getPackageName(), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        lblNomeApp.setText(getResources().getString(R.string.app_name).toUpperCase());//pega nome do app e exibe maiusculo 14/08/2019
        lblVersaoApp.setText("Versão : " + inf.versionName);//pega versão atual do arquivo gradlle, devo alterar em toda atualização  !!!!
    }
    //endregion

}
