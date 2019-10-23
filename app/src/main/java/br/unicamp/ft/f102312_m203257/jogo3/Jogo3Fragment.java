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
    TextView textView;
    String nomeCorreto;
    RadioGroup radioGroup;
    RadioButton bt1;
    RadioButton bt2;
    RadioButton bt3;
    RadioButton bt4;
    RadioButton bt5;


    public Jogo3Fragment() {
        nomeCorreto = "";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            return inflater.inflate(R.layout.fragment_jogo3, container, false);

        textView = textView.findViewById(R.id.frase);
        radioGroup = view.findViewById(R.id.jogo3radioGroup);
        bt1 = view.findViewById(R.id.nome1);
        bt2 = view.findViewById(R.id.nome2);
        bt3 = view.findViewById(R.id.nome3);
        bt4 = view.findViewById(R.id.nome4);
        bt5 = view.findViewById(R.id.nome5);

        view.findViewById(R.id.btnAtualizar).setOnClickListener(

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

    public void setPhraseAndNames(String frase, String nome1, String nome2, String nome3, String nome4, String nome5) {
        textView.setText(frase);
        bt1.setText(nome1);
        bt2.setText(nome2);
        bt3.setText(nome3);
        bt4.setText(nome4);
        bt5.setText(nome5);
    }

    public void validateCorrect() {
        int id = radioGroup.getCheckedRadioButtonId();
        if (((RadioButton) view.findViewById(id)).getText().equals(this.nomeCorreto)) {
            Toast.makeText(getContext(), "Acertou", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Errou", Toast.LENGTH_SHORT).show();
        }
        radioGroup.clearCheck();

        new MyViaCepAsyncTask(this).execute();
    }

    public class gameItemClick implements View.OnClickListener {
        Jogo3Fragment playerGame;
        String chute;

        public gameItemClick(Jogo3Fragment playerGame) {
            new MyViaCepAsyncTask(playerGame).execute();
            this.playerGame = playerGame;
        }


        @Override
        public void onClick(View v) {
            if (playerGame != null) {
                new MyViaCepAsyncTask(playerGame).execute();
            }
        }
    }



}
