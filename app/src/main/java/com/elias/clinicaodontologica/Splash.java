package com.elias.clinicaodontologica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    //definindo variavel de tempo de execução da minha splash no futuro será alterado tempo pelo webservice 14/08/2019
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //tirar barra superio padrao android studio   14/08/2019
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);

        //teste full screen 24/09/2019 elias
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//funcionou ok !!!


        //teste preferences marcos 05/09/2019
        SharedPreferences preferences = getSharedPreferences("user_preferences_marcos", MODE_PRIVATE);

//        if (preferences.contains("ja_abriu_app")) {
//            iniciarApp();//mostra a tela de login pulando a tela SPLASH  05/09/2019
//        } else {
//            adicionarPreferencesJaAbriu(preferences);
//            mostrarSplash();
//        }

        //teste elias para login com splash
        boolean jaLogou = preferences.getBoolean("userLogin", false);

        if (jaLogou) {
    //        iniciarApp();//mostra a tela de login pulando a tela SPLASH  05/09/2019
            iniciarAppComSpalsh();//com splash
        } else {
            adicionarPreferencesJaAbriu(preferences);
            mostrarSplash();
        }

//        Toast.makeText(this, "Aguarde carregamento da aplicação !", Toast.LENGTH_SHORT).show();


        //criando icone na tela
        createShortCut();

//        new Handler().postDelayed(new Runnable() {// original se preferences para primeiro acesso 05/09/2019
//            @Override
//            public void run() {
//                iniciarApp();
//            }
//        }, SPLASH_TIME_OUT);

    }




    //region mostrar tela Splash
    private void mostrarSplash() {
        Toast.makeText(this, "Aguarde carregamento da aplicação !", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iniciarApp();
            }
        }, SPLASH_TIME_OUT);
    }

    //endregion


    //region iniciar Aplicação

    private void iniciarApp() {

        Intent home = new Intent(Splash.this, Login.class);//terei de validar se terá login aki 14/08/2019  provavelmente sim para cada dentista !!!!
        startActivity(home);//verificar se terei de mudar esse nome home para login! 14/08/2019
        finish();

    }



    //iniciar com splash
    private void iniciarAppComSpalsh() {
        Intent home = new Intent(Splash.this, MainActivity.class);//terei de validar se terá login aki 14/08/2019  provavelmente sim para cada dentista !!!!
        startActivity(home);//verificar se terei de mudar esse nome home para login! 14/08/2019
        finish();

    }



    //endregion

    //region icone na tela inicial do celular (createShortCut)
    private void createShortCut() {

        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isAppInstalled = appPreferences.getBoolean("isAppInstalled", false);

        // verifica se o atalho já foi criado anteriormente   , se atalho já existe no celular
        //if (!getSharedPreferences(APP_PREFERENCE, Activity.MODE_PRIVATE).getBoolean(PREFERENCE_TAG, false)) {//false  original
        if (isAppInstalled == false) {

            Intent shortcutIntent = new Intent(getApplicationContext(), Splash.class);//alterei de Maictivy para splash 25/07/2019

            shortcutIntent.setAction(Intent.ACTION_MAIN);

            Intent addIntent = new Intent();
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.app_name));// THOTH ECV // EXEMPLO JOAO PRAXIS
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                    Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.facialclinica));//, R.drawable.ic_launcher)

            addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            getApplicationContext().sendBroadcast(addIntent);

            // salva informação de que o atalho foi criado
            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putBoolean("isAppInstalled", true);
            editor.commit();

        }
    }
    //endregion

    //region Preferences ja abriu aplicativo (futuramente validar se ja foi realizou login no app algumas vez no  para evitar de ter de ficar logando toda vez)
    private void adicionarPreferencesJaAbriu(SharedPreferences preferences) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ja_abriu_app", true);
        editor.commit();

        //teste de colocar mais informações no mesmo aruivo preferences 05/09/2019
        editor.putBoolean("outra_preferencia_teste", true);
        editor.commit();
    }
    //endregion
}
