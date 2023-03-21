import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF m;
    private WeightedQuickUnionUF mWOvBottom;
    private boolean[] sites;
    private int vTop = 0;
    private int vBottom;
    private int rowSize;
    private int sitesOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else {
            sitesOpen = 0;
            rowSize = n;
            vBottom = (rowSize * rowSize) + 1;
            sites = new boolean[(rowSize * rowSize)];
            m = new WeightedQuickUnionUF(vBottom + 1);
            mWOvBottom = new WeightedQuickUnionUF(vBottom + 1);
        }
    }

    private int value(int row, int col) {
        return (row - 1) * rowSize + (col - 1);
    }

    private void checkInputs(int row, int col) {
        if (row <= 0 || col <= 0 || row > rowSize || col > rowSize) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkInputs(row, col);
        int e = value(row, col);
        if (!sites[e]) {
            sites[e] = true;
            sitesOpen++;
        }

        if (row == 1) {
            m.union(e + 1, vTop);
            mWOvBottom.union(e + 1, vTop);
        } else {
            int topValue = value(row - 1, col);
            if (sites[topValue]) {
                m.union(topValue + 1, e + 1);
                mWOvBottom.union(topValue + 1, e + 1);
            }
        }

        if (row == rowSize) {
            m.union(e + 1, vBottom);
        } else {
            int bottomValue = value(row + 1, col);
            if (sites[bottomValue]) {
                m.union(bottomValue + 1, e + 1);
                mWOvBottom.union(bottomValue + 1, e + 1);
            }
        }

        if (col != 1) {
            int leftValue = value(row, col - 1);
            if (sites[leftValue]) {
                m.union(leftValue + 1, e + 1);
                mWOvBottom.union(leftValue + 1, e + 1);
            }
        }

        if (col != rowSize) {
            int rightValue = value(row, col + 1);
            if (sites[rightValue]) {
                m.union(rightValue + 1, e + 1);
                mWOvBottom.union(rightValue + 1, e + 1);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkInputs(row, col);
        return sites[value(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkInputs(row, col);
        return mWOvBottom.find(value(row, col) + 1) == mWOvBottome.find(vTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return sitesOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return m.find(vTop) == m.find(vBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        StdOut.print(p.percolates());
        p.open(1, 1);
        p.open(1, 2);
        p.open(1, 4);
        p.open(2, 4);
        p.open(3, 2);
        StdOut.print(p.percolates());
        p.open(3, 3);
        p.open(3, 4);
        p.open(3, 5);
        p.open(4, 1);
        p.open(4, 3);
        p.open(5, 1);
        p.open(5, 2);
        p.open(5, 3);
        p.open(5, 4);
        p.open(5, 5);
        StdOut.print(p.percolates());
    }
}