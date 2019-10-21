package com.elias.clinicaodontologica;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class List extends ListActivity {

    //url get contatos JSON estudents
    // private static String url = "http://10.0.0.136/studentsinfo.json";//teste meuu json maquina praxis 16/08/2019


    //teste elias 16/08/2019
    //private static String url = "http://10.0.0.136/teste.json";
//internet 08/2019
    private static String url = "https://mdn.github.io/learning-area/javascript/oojs/json/superheroes.json"; // original 05/09/2019
    // private static String url = "https://api.github.com/";//teste url RestFull 05/09/2019   não deu certo !!!!05/09/2019


    //JSON node nomes
//    private static final String TAG_STUDENTINFO = "studentsinfo";
//    private static final String TAG_ID = "id";//id
//    private static final String TAG_NAME = "name";//nome
//    private static final String TAG_EMAIL = "email";//email
//    private static final String TAG_ADDRESS = "address";//endereço
//    private static final String TAG_GENDER = "gender";//genero masculino/feminino
//    private static final String TAG_PHONE = "phone";//generico duvida aki 16/08/2019
//    private static final String TAG_PHONE_MOBILE = "mobile";//celular
//    private static final String TAG_PHONE_HOME = "home";//casa


    //meu exemplo 16/08/2019 node nomes
//    private static final String TAG_PESSOA = "pessoa";
//    private static final String TAG_ID2 = "id2";//id
//    private static final String TAG_NOME = "nome";//nome
//    private static final String TAG_CPF = "cpf";//email

    //internet
    private static final String TAG_MEMBERS = "members";//heroes internet

    private static final String TAG_NAME = "name";//nome
    private static final String TAG_AGE = "age";//idade
    private static final String TAG_SECRET_IDENTITY = "secretIdentity";//email

    private static final String TAG_POWERS = "powers";//generico duvida aki 16/08/2019
//    private static final String TAG_POWER1 = "power1";//celular


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);//chama lista PAI

        new GetStudents().execute();//no futuro sera os agendamentos aqui 14/08/2019  comentei essa linha para testar se opp

    }


    //region Class AsyncTask Getstudents
    private class GetStudents extends AsyncTask<Void, Void, Void> {

        ArrayList<HashMap<String, String>> studentList;//nome qualquer
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //mostrando o dialogo de carregamento do progresso
            pDialog = new ProgressDialog(List.this); //instancio progressdialog  // acredito que aqui não esteja pegando o contexto  pois no exemplo e maiActivity.tis
            pDialog.setMessage("por favor aguarde ...");//era o context mesmo 16/08/2019
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected Void doInBackground(Void... arg0) {//diferença retorno do metodo  16/08/20196

            //criando instancia da classe do manipulador de serviços
            WebRequest webreq = new WebRequest();//obj da class webrequest 16/08/2019

            //fazendo uma solicitação para url e obtendo resposta   request , response
            String jsonStr = webreq.makeWebServiceCall(url, webreq.GET);

            Log.d("Response: ", ">" + jsonStr);//log

            studentList = ParseJSON(jsonStr);//metodo ParseJSON será criado no fim dessa classe 16/08/2019

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            pDialog.isShowing();
            pDialog.dismiss();

            //update parsed data into ListView
//            ListAdapter adapter = new SimpleAdapter(
//                    getApplicationContext(), studentList,
//                    R.layout.list_item, new String[]{TAG_NAME, TAG_EMAIL, TAG_PHONE_MOBILE, TAG_ADDRESS, TAG_GENDER},
//                    new int[]{R.id.name,
//                            R.id.email, R.id.mobile, R.id.adreass, R.id.genero});


            //meu exemplo 16/08/2019


//            ListAdapter adapter = new SimpleAdapter(
//                    getApplicationContext(), studentList,
//                    R.layout.list_item, new String[]{TAG_NOME, TAG_CPF},
//                    new int[]{R.id.name,
//                            R.id.cpf});


//internet
            ListAdapter adapter = new SimpleAdapter(
                    getApplicationContext(), studentList,
                    R.layout.list_item, new String[]{TAG_NAME, TAG_AGE, TAG_SECRET_IDENTITY, TAG_POWERS},//chama lista Filha personalizada 11/09/2019
                    new int[]{R.id.name, R.id.cpf,
                            R.id.email});


            setListAdapter(adapter);
        }//fim do onPostExecute


        //metodo ParseJSON
        private ArrayList<HashMap<String, String>> ParseJSON(String json) {
            if (json != null) {
                try {
                    //Hashmap para ListView
                    ArrayList<HashMap<String, String>> studentsList = new ArrayList<HashMap<String, String>>();//pai

                    JSONObject jsonObj = new JSONObject(json);

                    //obtendo o nó JSON Array
                    //               JSONArray students = jsonObj.getJSONArray(TAG_STUDENTINFO); //pega o arquivo json

                    //teste elias 16/08/2019   novo json
                    //  JSONArray students = jsonObj.getJSONArray( TAG_PESSOA);

                    //teste internet
                    JSONArray students = jsonObj.getJSONArray(TAG_MEMBERS);

                    //percorre todos todos os registro do json
                    for (int i = 0; i < students.length(); i++) {
//                        JSONObject conteudo = students.getJSONObject(i);//pega conteudo no jason
//                        String id = conteudo.getString(TAG_ID);//
//                        String name = conteudo.getString(TAG_NAME);//
//                        String email = conteudo.getString(TAG_EMAIL);//
//                        String address = conteudo.getString(TAG_ADDRESS);//
//                        String gender = conteudo.getString(TAG_GENDER);//GENERO
//                        //id não conta terei de adicionar o telefone 16/08/2019
//                        JSONObject phone = conteudo.getJSONObject(TAG_PHONE);
//                        String mobile = phone.getString(TAG_PHONE_MOBILE);


                        //meu exemplo elias 16/08/2019 novo json

//                        JSONObject conteudo = students.getJSONObject(i);//pega conteudo no jason
//                        String id2 = conteudo.getString(TAG_ID2);//
//                        String nome = conteudo.getString(TAG_NOME);//
//                        String cpf = conteudo.getString(TAG_CPF);//


                        //teste internet
                        JSONObject conteudo = students.getJSONObject(i);//pega conteudo no jason
                        String nome = conteudo.getString(TAG_NAME);//
                        String age = conteudo.getString(TAG_AGE);//
                        String cpf = conteudo.getString(TAG_SECRET_IDENTITY);//


                        //    JSONObject powers = conteudo.getJSONObject(TAG_POWERS);//da catch aki 16/08/2019  15:00
                        //    String powers1 = powers.getString(TAG_POWERS);


                        //HashMap para registro unico do json  necessario
                        HashMap<String, String> studentFilho = new HashMap<String, String>();

                        //adicono cada nó filho do json a chave HashMap => Value
//                        studentFilho.put(TAG_ID, id);
//                        studentFilho.put(TAG_NAME, name);
//                        studentFilho.put(TAG_EMAIL, email);
//                        studentFilho.put(TAG_ADDRESS, address);
//                        studentFilho.put(TAG_GENDER, gender);
//                        studentFilho.put(TAG_PHONE_MOBILE, mobile);


                        //meu exemplo elias 16/08/2019 novo json
//                        //adicono cada nó filho do json a chave HashMap => Value
//                        studentFilho.put(TAG_ID2, id2);
//                        studentFilho.put(TAG_NOME, nome);
//                        studentFilho.put(TAG_CPF, cpf);


                        //teste internet
                        //adicono cada nó filho do json a chave HashMap => Value
                        studentFilho.put(TAG_NAME, nome);
                        studentFilho.put(TAG_AGE, age);
                        studentFilho.put(TAG_SECRET_IDENTITY, cpf);

                        //  studentFilho.put(TAG_POWERS, powers1);


                        //adicionando filho ao lista de registros  ao pai
                        studentsList.add(studentFilho);
                    }
                    return studentsList;//tenho que retornar o Hashmap para ListView 16/08/2019
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    return null;
                }
            } else {
                Log.e("ServiceHandler", "Nenhum dado recebido da solicitação HTTP");
                return null;
            }
        }

        //fim da classe interna
    }
    //endregion
}
