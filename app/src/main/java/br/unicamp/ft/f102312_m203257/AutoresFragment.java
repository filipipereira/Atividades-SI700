package br.unicamp.ft.f102312_m203257;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AutoresFragment extends Fragment {

    View view;
    TextView nome;
    TextView RA;
    TextView mail;
    ImageView foto;


    public AutoresFragment() {
        // Required empty public constructor
    }

    public void setText(String msg){
        if (view != null) {
            mail = view.findViewById(R.id.txtMail);
            mail.setText("");
            mail.setText("Mail Message: " +  msg);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_autores, container, false);
        }
        return view;
    }
}
