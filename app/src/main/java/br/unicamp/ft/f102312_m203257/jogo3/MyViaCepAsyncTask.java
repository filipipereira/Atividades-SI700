package br.unicamp.ft.f102312_m203257.jogo3;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ulisses on 9/27/17.
 */

public class MyViaCepAsyncTask extends AsyncTask<String, Void, String> {

    Jogo3Fragment fgm;

    public MyViaCepAsyncTask(Jogo3Fragment fgm) {
        this.fgm = fgm;
    }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... args) {

        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://sa4a4dtiv4.execute-api.eu-west-1.amazonaws.com/default/PythonHTTP1?kind=alunos&num_outros=4";

        /*
          Abrindo uma conexão com o servidor
        */

            URL url = new URL(HOST);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
        /*
          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        // Via Cep

        try {
          JSONObject jsonObject = new JSONObject(args);
            ArrayList<String> lst = new ArrayList<String>();
            lst.add( jsonObject.getJSONArray("outros").get(0).toString());
            lst.add( jsonObject.getJSONArray("outros").get(1).toString());
            lst.add( jsonObject.getJSONArray("outros").get(2).toString());
            lst.add( jsonObject.getJSONArray("outros").get(3).toString());
            lst.add( jsonObject.getString("nome"));
            Collections.shuffle(lst);
            fgm.setPhraseAndNames("Frase: " + jsonObject.getString("frase"), lst.get(0), lst.get(1), lst.get(2),lst.get(3),lst.get(4));
            fgm.setCorrectName(jsonObject.getString("nome"));

        } catch(JSONException e) {
            fgm.setPhraseAndNames("ERRO: Não foi possível converter em JSONObject: " + args+"\n", "", "", "", "", "");
            fgm.setCorrectName("");

        }


    }
}
