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

        String strEscolhido = "Mais Escolhidx(s): \n";
        String strMenosEscolhido = "Menos Escolhidx(s): \n";
        String maisescolhido = "";
        String menosescolhido = "";
        String sql = "Select Nome, MAX(TentativaGlobal) from alunos group by Nome order by 2 desc limit 1";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String texto = cursor.getString(0);
                //int erro = cursor.getInt(1);
                //int acertos = cursor.getInt(2);
                int tentativas = cursor.getInt(1);

                maisescolhido = maisescolhido + "Nome: " + texto
                        //+ ", Erros: " + erro
                        //+ ", Acertos: " + acertos
                        + ", Tentativas : " + tentativas
                        + "\n\n";

            } while (cursor.moveToNext());
        }

          String  sqlmenos = "Select Nome, MIN(TentativaGlobal) from alunos group by Nome order by 2 limit 1";
          Cursor cursormenos = sqLiteDatabase.rawQuery(sqlmenos, null);
            if (cursormenos.moveToFirst()) {
                do {
                    String texto = cursormenos.getString(0);
                    //int erro = cursor.getInt(1);
                    //int acertos = cursor.getInt(2);
                    int tentativas = cursormenos.getInt(1);

                    menosescolhido = menosescolhido + "Nome: " + texto
                            //+ ", Erros: " + erro
                            //+ ", Acertos: " + acertos
                            + ", Tentativas : " + tentativas
                            + "\n\n";

                } while (cursormenos.moveToNext());
            }

        txtDados.setText(strEscolhido + maisescolhido + strMenosEscolhido + menosescolhido);
        cursor.close();
        cursormenos.close();;
    }

}
