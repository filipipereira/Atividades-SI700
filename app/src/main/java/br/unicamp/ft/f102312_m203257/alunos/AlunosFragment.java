package br.unicamp.ft.f102312_m203257.alunos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.f102312_m203257.alunos.AlunosAdapter.MyOnLongItemClickListener;
import br.unicamp.ft.f102312_m203257.R;
import br.unicamp.ft.f102312_m203257.interfaces.OnBiografiaRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragment extends Fragment {

    RecyclerView mRecyclerView;
    AlunosAdapter mAdapter;
    private OnBiografiaRequest onBiografiaRequest;

    public AlunosFragment() {
        // Required empty public constructor
    }

    public void setOnBiografiaRequest(OnBiografiaRequest onBiografiaRequest) {
        this.onBiografiaRequest = onBiografiaRequest;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alunos, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new AlunosAdapter(new ArrayList(Arrays.asList(Alunos.alunos)));
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setItemClickListener(new AlunosAdapter.MyOnItemClickListener() {
            @Override
            public void MyOnItemClick(String nome) {
                Toast toast = Toast.makeText(getContext(), nome, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        mAdapter.setItemLongClickListener(new MyOnLongItemClickListener() {
            @Override
            public void myOnLongItemClick(int position) {
                if (onBiografiaRequest != null){
                    onBiografiaRequest.onRequest(position);
                }
            }
        });

        if(view == null) {
            return inflater.inflate(R.layout.fragment_alunos, container, false);
        }
        return view;
    }

}
