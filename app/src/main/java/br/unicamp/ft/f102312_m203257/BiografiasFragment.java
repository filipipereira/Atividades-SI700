package br.unicamp.ft.f102312_m203257;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.f102312_m203257.alunos.Aluno;
import br.unicamp.ft.f102312_m203257.alunos.Alunos;


/**
 * A simple {@link Fragment} subclass.
 */
public class BiografiasFragment extends Fragment {

    View view;
    TextView txtNome;
    TextView txtInfo;
    ImageView imgFoto;

    int index;

    private ArrayList<Aluno> alunos;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public BiografiasFragment() {
        index = 0;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_biografias, container, false);
            alunos = new ArrayList(Arrays.asList(Alunos.alunos));
            atualizaDados();
        }
        buttonProximoListener(view);
        buttonAnteriorListener(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        atualizaDados();
    }

    public void atualizaDados(){

        txtInfo = view.findViewById(R.id.txtinfo);
        txtNome= view.findViewById(R.id.txtnome);
        imgFoto = view.findViewById(R.id.imgFoto);

        txtInfo.setText(alunos.get(index).getDescricao());
        txtNome.setText(alunos.get(index).getNome());
        imgFoto.setImageResource(alunos.get(index).getFoto());
    }

    private void buttonProximoListener(View view){
        Button proximo = view.findViewById(R.id.btnProximo);
        proximo.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        int index = BiografiasFragment.this.index + 1;
                        if(index < alunos.size()){
                            setIndex(index);
                            atualizaDados();
                        }
                    }
                }
        );
    }

    private void buttonAnteriorListener(View view){
        Button anterior = view.findViewById(R.id.btnAnterior);
        anterior.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        int index = BiografiasFragment.this.index - 1;
                        if(index >= 0){
                            setIndex(index);
                            atualizaDados();
                        }
                    }
                }
        );
    }
}
