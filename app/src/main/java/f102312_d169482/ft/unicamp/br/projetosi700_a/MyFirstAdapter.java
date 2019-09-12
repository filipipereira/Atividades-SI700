package f102312_d169482.ft.unicamp.br.projetosi700_a;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import f102312_d169482.ft.unicamp.br.projetosi700_a.alunos.Aluno;

public class MyFirstAdapter extends RecyclerView.Adapter{

    private ArrayList<Aluno> alunos;
    MyOnItemClickListener itemClickListener;


    public MyFirstAdapter(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }


    public class MyFirstViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        // private ImageView imgFoto;
        private TextView txtNome;
        private TextView txtDescricao;


        public MyFirstViewHolder(View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
        }

        public void Bind(final Aluno aluno) {
            imgFoto.setImageResource(aluno.getFoto());
            txtNome.setText(aluno.getNome());
            txtDescricao.setText(Html.fromHtml(aluno.getDescricao()));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, parent, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    TextView txtNome = view.findViewById(R.id.txtNome);
                    itemClickListener.MyOnItemClick(txtNome.getText().toString());
                }
            }
        });
        return new MyFirstViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position){

        ((MyFirstViewHolder) viewHolder).Bind(alunos.get(position));

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alunos.remove(position);
                notifyDataSetChanged();
                return true;
            }
        });
    }


    @Override
    public int getItemCount(){
        return alunos.size();
    }

    public interface MyOnItemClickListener{
        void MyOnItemClick(String nome);
    }

    public void setItemClickListener(MyOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


}