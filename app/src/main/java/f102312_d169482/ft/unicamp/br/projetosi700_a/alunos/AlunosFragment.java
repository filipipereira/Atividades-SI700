package f102312_d169482.ft.unicamp.br.projetosi700_a.alunos;


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

import f102312_d169482.ft.unicamp.br.projetosi700_a.MyFirstAdapter;
import f102312_d169482.ft.unicamp.br.projetosi700_a.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragment extends Fragment {

    RecyclerView mRecyclerView;
    MyFirstAdapter mAdapter;

    public AlunosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alunos, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new MyFirstAdapter(new ArrayList(Arrays.asList(Alunos.alunos)));
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setItemClickListener(new MyFirstAdapter.MyOnItemClickListener() {
            @Override
            public void MyOnItemClick(String nome) {
                Toast toast = Toast.makeText(getContext(), nome, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        if(view == null) {
            return inflater.inflate(R.layout.fragment_alunos, container, false);
        }
        return view;
    }

}
