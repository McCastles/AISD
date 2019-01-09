class Cell {

    private int x;
    private int y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() { return x; }
    int getY() { return y; }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (! (o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return (this.x == cell.x) && (this.y == cell.y);
    }
}
