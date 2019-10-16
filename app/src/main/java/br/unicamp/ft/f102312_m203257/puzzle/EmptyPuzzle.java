package br.unicamp.ft.f102312_m203257.puzzle;

import android.widget.ImageView;

import java.util.ArrayList;

public class EmptyPuzzle extends AbstractPuzzle {

    public EmptyPuzzle(Board board, ArrayList<ImageView> imageViews){
        super(board, imageViews);
    }

    public void addListener(ImageView imageView, int line, int column){

    }
    public boolean endGame(){
        return false;
    }

}
