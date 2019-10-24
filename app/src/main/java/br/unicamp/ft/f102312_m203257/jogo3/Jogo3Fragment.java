package br.unicamp.ft.f102312_m203257.jogo3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.unicamp.ft.f102312_m203257.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Jogo3Fragment extends Fragment {

    View view;
    String nomeCorreto;
    RadioButton btn1;
    RadioButton btn2;
    RadioButton btn3;
    RadioButton btn4;
    RadioButton btn5;
    RadioGroup rg;
    TextView textView;
    TextView saida;

    public Jogo3Fragment() {
        // Required empty public constructor
        nomeCorreto = "";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null)
            view = inflater.inflate(R.layout.fragment_jogo3, container, false);

        textView = view.findViewById(R.id.frase);
        rg = view.findViewById(R.id.jogo3radioGroup);
        btn1 = view.findViewById(R.id.nome1);
        btn2 = view.findViewById(R.id.nome2);
        btn3 = view.findViewById(R.id.nome3);
        btn4 = view.findViewById(R.id.nome4);
        btn5 = view.findViewById(R.id.nome5);

        view.findViewById(R.id.btnAtualizar).setOnClickListener(
                new gameItemClick(this));

        view.findViewById(R.id.btnChutar).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        validateCorrect();
                    }
                }
        );

        return view;
    }

    public void setCorrectName(String nome) {
        this.nomeCorreto = nome;
    }

    public void setPhraseAndNames(String frase, String n1, String n2, String n3, String n4, String n5) {
        textView.setText(frase);
        btn1.setText(n1);
        btn2.setText(n2);
        btn3.setText(n3);
        btn4.setText(n4);
        btn5.setText(n5);
    }

    public void validateCorrect() {
        int id = rg.getCheckedRadioButtonId();
        if (((RadioButton) view.findViewById(id)).getText().equals(this.nomeCorreto)) {
            Toast.makeText(getContext(), "Acertou", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Errou", Toast.LENGTH_SHORT).show();
        }
        rg.clearCheck();
        new MyViaCepAsyncTask(this).execute();
    }

    public class gameItemClick implements View.OnClickListener {
        Jogo3Fragment pg;
        String chute;

        public gameItemClick(Jogo3Fragment pg) {
            new MyViaCepAsyncTask(pg).execute();
            this.pg = pg;
        }

        @Override
        public void onClick(View v) {
            if (pg != null)
                new MyViaCepAsyncTask(pg).execute();
        }

    }
}
