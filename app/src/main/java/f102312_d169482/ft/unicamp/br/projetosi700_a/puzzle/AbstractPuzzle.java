package f102312_d169482.ft.unicamp.br.projetosi700_a.puzzle;

import android.widget.ImageView;

import java.util.ArrayList;

public abstract class AbstractPuzzle {

    private Board board;
    private ArrayList<ImageView> imageviewlist;

    public AbstractPuzzle (Board board, ArrayList <ImageView> imageviewlist){
        this.board = board;
        this.imageviewlist = imageviewlist;
        board.startGame();
        redraw();
        for (int i = 0; i < board.getNumLines(); i++){
            for (int j = 0; j < board.getNumColumns(); j++){
                addListener(imageviewlist.get(i*board.getNumColumns()+j),i,j);
            }
        }
    }

    protected void redraw(){
        for(int i =0; i < board.getNumLines(); i++){
            for (int j=0; j < board.getNumColumns(); j++) {
               imageviewlist.get(i * board.getNumColumns() + j).setImageResource(board.getGameBlock(i, j));
            }
        }
    }

    public abstract void addListener(ImageView imageView, int line, int column);
    public abstract boolean endGame();
}
