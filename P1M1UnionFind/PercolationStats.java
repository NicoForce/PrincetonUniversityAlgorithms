import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] pResult;
    private int tests;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        } else {
            tests = trials;
            pResult = new double[tests];
            for (int i = 0; i < trials; i++) {
                Percolation p = new Percolation(n);
                while (!p.percolates()) {
                    int row = StdRandom.uniformInt(n) + 1;
                    int col = StdRandom.uniformInt(n) + 1;
                    p.open(row, col);
                }
                pResult[i] = p.numberOfOpenSites() / (double) (n * n);
            }
        }
    }

    private double confidence() {
        return (1.96*stddev())/Math.sqrt(tests);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(pResult);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(pResult);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean()-confidence();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean()+confidence();
    }

   // test client (see below)
   public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int trialsAmount = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(gridSize, trialsAmount);
        StdOut.print("mean                    = " + ps.mean() + "\n");
        StdOut.print("stddev                  = " + ps.stddev() + "\n");
        StdOut.print("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
   }
}
