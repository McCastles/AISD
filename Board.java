import java.util.ArrayList;
import java.util.List;

class Board {

    private boolean [][] board;
    private int freeCellsNumber;
    private double freeCellsRatio;
    private int cellsNumber;

    /* constructor */
    Board (int size) {
        this.board = new boolean[size][size];
    }

    /* updates the state of the board after receiving judge's response */
    void update (List <Cell> cellsList) {
        for (Cell cell : cellsList)
            board[cell.getX()][cell.getY()] = true;

        /* throw Exception ? */
    }

    /* make a decision and respond to judge */
    void makeMove () {
        Move move = Algorithm.decide(board);
        move.print();
    }

    /* turns a judge string to a list of Cell objects */
    List<Cell> stringIntoCells (String obstacles) {
        List<Cell> list = new ArrayList<>();
        String [] points = obstacles.split(",");
        for (String point : points) {
            point = point.replaceAll("[{]", "").replaceAll("[}]", "");
            String [] coordinates = point.split("[;]");
            list.add( new Cell (
                    Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1])
            ) );
        }
        return list;
    }

}
