package com.elias.clinicaodontologica;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//mysql 23/09/2019
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
//fim imports Mysql 23/09/2019

//aplicativo atual utilizado  23/09/2019   elias martins
public class MainActivity extends AppCompatActivity {


    //listView para as opções da tela
    ListView listViewOpcoesDaTela;


    //vibrar
    Vibrator vibrator;
    long milliseconds = 500;//definir tempo do celular vibrar

    //hora na tela
    TextView txtHora;


    // teste dos imagebutons 05/09/2019
    private ImageButton btnDashBoard;
    private ImageButton btnSobre;

    //teste botao Logout 05/09/2019
    private ImageButton btnLogout;


    //para Mysql 23/09/2019 elias

//    private static final String url = "jdbc:mysql://10.0.0.166:3306/db_consultorio";//meu ip maquina virtual e banco
//    private static final String user = "clinica";//meu usuario
//    private static final String pass = "facial123";//minha senha // comentei metodo Mysql 24/09/2019 utilizaremos serviço mesmo! mais recomendado


    //  Button btnMysql;
    //  TextView txtData;
    //fim teste colocar conexão na splash 23/09/2019

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //tirar barra superio padrao android studio
        getSupportActionBar().hide();


        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);//global tenho de confirmar 14/08/2019

        setContentView(R.layout.activity_principal);//(R.layout.activity_main)

        try {


            // gerarDiretorio();  // se for criar um diretorio devo implementar esse metodo 14/08/2019
            vibrator.vibrate(milliseconds);//vibrar ao abrir tela inicial 14/08/2019

            //test permissão 14/08/2019
            this.solicitarPermissoes();
            //fim teste


            //metodos de inicializar , recuperar componentes
            this.criarComponentes();
            this.criarEventos();
//            this.carregaOpcoesDaLista();
//            this.exibirHoraAtualCelular();//havia comentado todos esses metodos 05/09/2019  para retirar lista do layout inicail


        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

//    @Override// a implementar quando colocar icone para fechar app 14/08/2019
//    public void onBackPressed() {// para desabilitar o botao voltar  dessa tela    // tenho que estudar esse caso de sair ou não peo celular ??? 05/09/2019
//        //não chame o super
//    }

    //region solicita permissões do app
    private void solicitarPermissoes() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.VIBRATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        }
    }

    //outros metodos necessarios
    @Override
    public void onRequestPermissionsResult(int requestCode, String permission[], int[] grantResults) {

        switch (requestCode) {

            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatckPictureIntent(); // metodo a ser criado por min
                } else
                    Toast.makeText(this, "o aplicativo não irá funcionar corretamente sem as permissões necessaria ", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void dispatckPictureIntent() {

    }
    //endregion


    //region criarComponentes tela

    private void criarComponentes() {//seria um recuperar componentes


        //teste 05/09/2019  imagButonn a implementar
        btnDashBoard = (ImageButton) findViewById(R.id.btnDashBoard);
        btnSobre = (ImageButton) findViewById(R.id.btnSobre);
        btnLogout = (ImageButton) findViewById(R.id.btnLogout);


    }

    //endregion


    //region criarEventos da tela
    protected void criarEventos() {//confirmar essa parada de private e protected ?? 14/08/2019
        try {


            //botão horario
            btnDashBoard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // finish();//teste
                    Intent intentRedirecionaTela;
                    intentRedirecionaTela = new Intent(getApplicationContext(), List.class);//mudar aki 27/09/2019
                    startActivity(intentRedirecionaTela);
                }
            });


            //botão sobre
            btnSobre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // finish();//teste
                    Intent intentRedirecionaTela;
                    intentRedirecionaTela = new Intent(getApplicationContext(), Sobre.class);
                    startActivity(intentRedirecionaTela);
                }
            });

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertLogout();//05/09/2019 crie metodo separado para confirma logout e redirecionamento

                }
            });


        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    //endregion


    //region redirecionarTela
    protected void redirecionarTela(String opcoesDoMenu) {
        Intent intentRedirecionaTela;
        if (opcoesDoMenu.equals(("LISTA"))) {

            intentRedirecionaTela = new Intent(this, List.class);
            startActivity(intentRedirecionaTela);

            //finaliza activity anterior
            //  finish(); // a resolver 14/08/2019


        } else if (opcoesDoMenu.equals(("SOBRE"))) {
            //redireciona para tela sobre do app
            intentRedirecionaTela = new Intent(this, Sobre.class);
            startActivity(intentRedirecionaTela);

            //finaliza activity anterior
            //  finish(); // a resolver 14/08/2019

        } else
            Toast.makeText(this, "A implementar", Toast.LENGTH_LONG).show();

    }
    //endregion


    //region carregaOpçõe da Lista
    private void carregaOpcoesDaLista() {
        try {
            String[] itensMenu = new String[3];// defino numero de opções da minha lista aki [2]  coloquei so duas  para teste
            itensMenu[0] = "AGENDAMENTOS";
            itensMenu[1] = "LISTA";
            itensMenu[2] = "SOBRE";


            ArrayAdapter<String> arrayItensTela = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, itensMenu);
            listViewOpcoesDaTela.setAdapter(arrayItensTela);

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    //endregion


    //region exibirHorario na tela
    private void exibirHoraAtualCelular() {
    }
    //endregion

    //region alert sair deslogar 05/09/2019
    private void alertLogout() {

        //cria o gerenciadpr do AlertDialog
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        //defini titulo para o alert
        alerta.setTitle("Aviso");

        //defini icone para o alert em caso de utiliza padrão e não customizado 05/09/2019
        alerta.setIcon(R.drawable.close_app); //se caso eu quisser colocar o icone não é obrigatório 05/09/2019

        //defini a mensagem exibida no meu alert
        alerta.setMessage("Desja realme realizar logout do aplicativo ? ");

        //define botão com positivo para o alert
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //teste nova preferences para logout 06/09/2019
                SharedPreferences SM = getSharedPreferences("userrecord", 0);
                SharedPreferences.Editor edit = SM.edit();
                edit.putBoolean("userlogin", false);
                edit.commit();


                //redireciona para fora do app
                Intent intentLogout = new Intent(getApplicationContext(), Login.class);
                startActivity(intentLogout);

                //finaliza atividade atual
                finish();
            }
        });

        //define o botão com opçãoo de negativo
        alerta.setNegativeButton("NÃO", null);

        //exibe o alerta na tela
        alerta.show();
    }


}
