import java.util.ArrayList;
import java.util.List;

class Move {

    private Cell cellA;
    private Cell cellB;

    Move (Cell cellA, Cell cellB) {
        this.cellA = cellA;
        this.cellB = cellB;
    }

    Cell getCellA() { return cellA; }
    Cell getCellB() { return cellB; }

    void print () {
        String response = "{" + cellA.getX() + ";" + cellA.getY() + "},{" +
                + cellB.getX() + ";" + cellB.getY() + "}";
        System.out.println(response);
    }

    private void pop(List<Move> queue) {
        queue.remove(this);
        for (int i = 0; i < queue.size(); i++) {
            Cell otherA = queue.get(i).getCellA();
            Cell otherB = queue.get(i).getCellB();
            if (this.cellA.equals(otherA) || this.cellA.equals(otherB) ||
                this.cellB.equals(otherA) || this.cellB.equals(otherB))
            {
                    queue.remove(queue.get(i));
                    i--;
            }
        }
    }

    double calculateRate(List<Move> moveList, Move pov) {

        List<Move> queue = new ArrayList<>();
        queue.addAll(moveList);
        pov.pop(queue);
        if (queue.isEmpty()) return 1.0;

        int total = queue.size();
        int good = 0;
        for (Move move : queue) {
            List<Move> subQueue = new ArrayList<>();
            subQueue.addAll(queue);
            move.pop(subQueue);
            if (!subQueue.isEmpty()) good++;
        }
        return good / total;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Move other = (Move) o;
        Cell otherA = other.getCellA();
        Cell otherB = other.getCellB();
        return ( this.cellA.equals(otherA) &&  this.cellB.equals(otherB) ||
                this.cellA.equals(otherB) && this.cellB.equals(otherA));
    }

}
