package br.unicamp.ft.f102312_m203257;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MailFragment extends Fragment {

    private View view;
    EditText dest;
    EditText mensagem;

    public MailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(view == null){
            view = inflater.inflate(R.layout.fragment_mail, container, false);
        }

        dest = view.findViewById(R.id.txtDest);
        mensagem = view.findViewById(R.id.txtMenssagem);

        Button enviar = view.findViewById(R.id.btn_send);
        enviar.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        String msg = MailFragment.this.mensagem.getText().toString();
                        ((MainActivity)getActivity()).doSomething(msg);
                    }
                }
        );

        return  view;
    }

}
