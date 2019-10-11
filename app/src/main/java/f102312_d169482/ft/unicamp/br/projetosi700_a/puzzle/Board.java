package f102312_d169482.ft.unicamp.br.projetosi700_a.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private int numLines;
    private int numColumns;
    private List<Integer> blocks;
    private int width;
    private int height;
    private ArrayList<Integer> gameIndex;


    public Board(int numLines, int numColumns, List blocks, int width, int height){
        this.numLines = numLines;
        this.numColumns = numColumns;
        this.blocks = blocks;
        this.width = width;
        this.height = height;

        gameIndex = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++){
            gameIndex.add(i);}
        this.startGame();
    }

    public int getNumLines() {
        return numLines;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void startGame(){
        Collections.shuffle(this.gameIndex);
    }

    public int getCorrectBlock(int line, int column){
        int resultado = line * numColumns + column;
        return resultado;
    }

    public int getGameBlock(int line, int column){
        int resultado = line * numColumns + column;
        return resultado;
    }

    public void swap(int line1, int column1, int line2, int column2){
        gameIndex.set(line1 * numColumns + column1, gameIndex.get( line2 * numColumns +column2));
        gameIndex.set(line2 *numColumns + column2, gameIndex.get (line1 * numColumns + column1 ));
    }
}


