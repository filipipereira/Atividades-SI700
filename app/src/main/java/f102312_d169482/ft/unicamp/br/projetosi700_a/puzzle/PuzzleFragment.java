package f102312_d169482.ft.unicamp.br.projetosi700_a.puzzle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import f102312_d169482.ft.unicamp.br.projetosi700_a.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PuzzleFragment extends Fragment {


    LinearLayout view;
    AbstractPuzzle abstractPuzzle;

    public PuzzleFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = (LinearLayout) inflater.inflate(R.layout.fragment_puzzle, container, false);
            startPuzzle(0, view);
        }
        return view;
    }

    private void startPuzzle(int puzzle, LinearLayout view) {
        Board board = Boards.getPuzzle(puzzle);
        ArrayList<ImageView> imageViews = new ArrayList();


        for(int i = 0; i < board.getNumLines(); i++){

            LinearLayout row = new LinearLayout(getContext());

            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));

            for(int j = 0; j < board.getNumColumns(); j++){
                ImageView imageView = new ImageView(getContext());
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                imageView.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                board.getWidth(),
                                board.getHeight()));
                imageViews.add(imageView);
                row.addView(imageView);
            }
        }
        abstractPuzzle = new EmptyPuzzle(board, imageViews);
    }
}
