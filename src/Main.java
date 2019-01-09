import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main (String [] args) throws IOException {

        /* scanner */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        /* read the board size */
        final int boardSize = Integer.parseInt(in.readLine());
        Board board = new Board(boardSize);
        System.out.println("ok");

        /* initial positions of the occupied cells */
        String obstacles = in.readLine();
        board.update(board.stringIntoCells(obstacles));
        System.out.println("ok");

        /* receive the first command */
        String firstCommand = in.readLine();

        /* make a move OR respond to opponent's turn and then make a move */
        if (firstCommand.equals("start")) {
            board.makeMove();
        }
        else {
            board.update(board.stringIntoCells(firstCommand));
            board.makeMove();
        }

        /* game loop */
        while (true) {
            String command = in.readLine();
            board.update(board.stringIntoCells(command));
            board.makeMove();
        }
    }
}
