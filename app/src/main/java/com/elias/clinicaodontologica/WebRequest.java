package com.elias.clinicaodontologica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class WebRequest {

    static String response = null;
    public final static int GET = 1;// defini valor fixos para os metodos de  pegar(GET) e enviar salvar (PUT)
    public final static int POST = 2;//


    public WebRequest() {
    }


    //webservice Call
    //chamada de webservice
    public String makeWebServiceCall(String url, int requestmethod) {// paramentros url e requisiçãoMetodo
        return this.makeWebServiceCall(url, requestmethod, null);
    }


    //service Call
    //chamada de serviço
    public String makeWebServiceCall(String urladdress, int requestmethod,
                                     HashMap<String, String> params) {
        URL url;
        String response = "";//tinha esquecido dessa variavel 16/08/2019
        try {
            url = new URL(urladdress);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//erro nesse metodo 16/08/2019  catch nessa linha   // estava passando https ao invez de http!!  16/08/2019
            conn.setReadTimeout(15000);//tempo timeout
            conn.setConnectTimeout(15000);//tempo conect timeout
            conn.setDoInput(true);

            if (requestmethod == POST) {
                conn.setRequestMethod("POST");
            } else if (requestmethod == GET) {
                conn.setRequestMethod("GET");
            }

            if (params != null) {
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                StringBuilder result = new StringBuilder();
                boolean first = true;
                for (Map.Entry<String, String> entry : params.entrySet()) {

                    if (first) {
                        first = false;
                    } else
                        result.append("&");

                    result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    result.append("=");
                    result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }

                writer.write(result.toString());

                writer.flush();
                writer.close();
                os.close();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {// valor = 200
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {//enquanto linha
                    response += line;
                }
            } else
                response = "";


            //tete elias 05/09/2019  inicio
            //o close é em outro tipo de obj não conn !!! 05/09/2019
            conn.disconnect();// não percebi diferença ao colocar o diconect !!! 05/09/2019

            //fim teste

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
