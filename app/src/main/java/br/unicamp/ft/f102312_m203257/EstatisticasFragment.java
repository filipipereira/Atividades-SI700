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

        String sql = "Select Nome,tentativaEx,Acerto, Erro from alunos";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            String str = "";
            do {
                String texto = cursor.getString(0);
                int tentativas = cursor.getInt(1);
                int acertos = cursor.getInt(2);
                int erro = cursor.getInt(3);


                str = str + "Nome: " + texto
                        + ", TentativaEx: " + tentativas
                        + ", Acertos: " + acertos
                        + ", Erros : " + erro
                        + "\n";

            } while (cursor.moveToNext());
            txtDados.setText(str);
        }
        cursor.close();
    }

}
