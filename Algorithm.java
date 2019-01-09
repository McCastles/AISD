import java.util.ArrayList;
import java.util.List;

class Algorithm {

        /*
        {0;1},{1;0},{1;1},{2;1},{2;2}
        */

    static Move decide (boolean [][] board) {

        /* find all moves */
        List<Move> moveList = getAllMoves(board);
        Move decision;

        if (moveList.size() > 15)
            decision = moveList.get(0);
        else
            decision = smartMove(moveList);

        /* the result is the move with max rate */
        return decision;
    }

    private static Move smartMove(List<Move> moveList) {
        /* calculate rate for the first */
        Move decision = moveList.get(0);
        double maxRate = decision.calculateRate(moveList, decision);
        /* calculate rate for the rest and find max */
        for (int i = 1; i < moveList.size(); i++) {
            Move current = moveList.get(i);
            double rate = current.calculateRate(moveList, current);
            if (rate > maxRate) {
                maxRate = rate;
                decision = moveList.get(i);
            }
        }
        return decision;
    }

    /* find all possible moves in the current situation */
    private static List<Move> getAllMoves(boolean[][] originalBoard) {
        List<Move> moveList = new ArrayList<>();
        boolean [][] board = originalBoard.clone();
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++) {
                if (!board[i][j]) {
                    moveList.addAll( findMovesWithCurrentCell( board, i , j ) );
                    board[i][j] = true;
                }
            }
        return moveList;
    }

    /* look around the Cell and produce moves */
    private static List<Move> findMovesWithCurrentCell (boolean [][] board, int i, int j) {
        List<Move> list = new ArrayList<>();
        int borderIndex = board.length - 1;
        int adjacent;
        /* right */
        adjacent = (i == borderIndex) ? 0 : i + 1;
        if (!board[adjacent][j]) list.add(new Move(new Cell(i, j), new Cell(adjacent, j)));
        /* down */
        adjacent = (j == borderIndex) ? 0 : j + 1;
        if (!board[i][adjacent]) list.add(new Move(new Cell(i, j), new Cell(i, adjacent)));
        /* left */
        adjacent = (i == 0) ? borderIndex : i - 1;
        if (!board[adjacent][j]) list.add(new Move(new Cell(i, j), new Cell(adjacent, j)));
        /* up */
        adjacent = (j == 0) ? borderIndex : j - 1;
        if (!board[i][adjacent]) list.add(new Move(new Cell(i, j), new Cell(i, adjacent)));
        return list;
    }

}
