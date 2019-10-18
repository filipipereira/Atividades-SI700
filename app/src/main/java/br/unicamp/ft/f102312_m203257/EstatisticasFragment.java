package br.unicamp.ft.f102312_m203257;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.unicamp.ft.f102312_m203257.database.DatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstatisticasFragment extends Fragment {

    View view;
    TextView txtDados;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EstatisticasFragment() {
        // Required empty public constructor
    }

    public void onStart() {
        super.onStart();
        //getActivity().deleteDatabase("EXEMPLO");
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        mostrarDados();
    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_estatisticas, container, false);
        }
        txtDados = view.findViewById(R.id.txtStatus);
        return  view;
    }

    private void mostrarDados(){

        String str = "O aluno que o jogador menos sabe o nome:\n";
        String sql = "";
        sql = "Select Nome, MAX(Erro) from alunos group by Nome order by 2 desc limit 1";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);


        if (cursor.moveToFirst()) {

            do {
                String texto = cursor.getString(0);
                int erro = cursor.getInt(1);

                str = str + "Nome: " + texto
                        + ", Erros : " + erro
                        + "\n";
            } while (cursor.moveToNext());
        }

        str += "\nO nome que mais fez o jogador errar:\n";

        sql = "Select Nome, MAX(tentativaEx) from alunos group by Nome order by 2 desc limit 1";
        cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {
                String texto = cursor.getString(0);
                int erro = cursor.getInt(1);


                str = str + "Nome: " + texto.split(" ")[0]
                        + ", Tentativas : " + erro
                        + "\n";

            } while (cursor.moveToNext());
        }

        sql = "Select  SUM(Erro * 100) / SUM(Erro + Acerto) from alunos";

        cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {
                int erro = cursor.getInt(0);
                str = str + "\nPorcentagem de erros nas jogadas: " + erro + "%"
                        + "\n";

            } while (cursor.moveToNext());

        }
        txtDados.setText(str);
        cursor.close();
    }

}
