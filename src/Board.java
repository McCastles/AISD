import java.util.ArrayList;
import java.util.List;

class Board {

    private static boolean [][] board;
    private List<Cell> freeCells;

    static boolean[][] getBoard () { return board; }

    /* constructor */
    Board (int size) {
        board = new boolean[size][size];
        freeCells = new ArrayList<>();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                freeCells.add(new Cell(i,j));
    }

    /* updates the state of the board after receiving judge's response */
    void update (String command) {
        List<Cell> cellsList = stringIntoCells(command);
        for (Cell cell : cellsList) {
            freeCells.remove(cell);
            board[cell.getX()][cell.getY()] = true;
        }
    }

    /* make a decision and respond to judge */
     void makeMove () {
         Move move = Algorithm.decide(freeCells);
         board[move.getCellA().getX()][move.getCellA().getY()] = true;
         board[move.getCellB().getX()][move.getCellB().getY()] = true;
         freeCells.remove(move.getCellA());
         freeCells.remove(move.getCellB());
         move.print();
    }

    /* turns a judge string to a list of Cell objects */
    private List<Cell> stringIntoCells (String obstacles) {
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
