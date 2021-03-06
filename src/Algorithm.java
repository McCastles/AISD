import java.util.ArrayList;
import java.util.List;

class Algorithm {

    private static final int freeLimit = 15;
    private static int randomVar = 2;

    static Move decide (List<Cell> freeCells) {
        /* find all moves */
        List<Move> moveList = getAllMoves(freeCells);
        Move finalDecision;
        if (moveList.size() > freeLimit) {
            int ala = moveList.size() / randomVar;
            randomVar++;
            finalDecision = moveList.get(ala);
        }
        else
            finalDecision = smartMove(moveList);
        return finalDecision;
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
        /* the result is the move with max rate */
        return decision;
    }

    /* find all possible moves in the current situation */
    private static List<Move> getAllMoves(List<Cell> cellsList) {
        List <Move> neighbors = new ArrayList<>();
        for (Cell cell : cellsList)
            findNeighbors(neighbors, cell);
        return neighbors;
    }

    /* look around the Cell and produce moves */
    private static void  findNeighbors (List<Move> neighbors, Cell cell) {

        boolean[][] board = Board.getBoard();
        List<Move> list = new ArrayList<>();
        int borderIndex = board.length - 1;
        int adjacent;
        int x = cell.getX();
        int y = cell.getY();

        /* right */
        adjacent = (x == borderIndex) ? 0 : x + 1;
        if (!board[adjacent][y]) list.add(new Move(new Cell(x, y), new Cell(adjacent, y)));
        /* down */
        adjacent = (y == borderIndex) ? 0 : y + 1;
        if (!board[x][adjacent]) list.add(new Move(new Cell(x, y), new Cell(x, adjacent)));
        /* left */
        adjacent = (x == 0) ? borderIndex : x - 1;
        if (!board[adjacent][y]) list.add(new Move(new Cell(x, y), new Cell(adjacent, y)));
        /* up */
        adjacent = (y == 0) ? borderIndex : y - 1;
        if (!board[x][adjacent]) list.add(new Move(new Cell(x, y), new Cell(x, adjacent)));

        for (Move move : list)
            if (!neighbors.contains(move)) neighbors.add(move);
    }

}
