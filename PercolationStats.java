import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by ayush000 on 16/09/15.
 */
public class PercolationStats {
    double[] percolationThreshold;

    public PercolationStats(int N, int T) {
        this.percolationThreshold = new double[T];
        for (int index = 0; index < T; index++) {
            int openCount = 0;
            Percolation p = new Percolation(N);
            while (!p.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!p.isOpen(i, j)) {
                    p.open(i, j);
                    openCount++;
                }
            }
            percolationThreshold[index] = ((double) openCount) / ((double) N * N);

        }
    }

    public double mean()                      // sample mean of percolation threshold
    {
//        double sum=0;
//        for(int i=0;i<percolationThreshold.length;i++)
//        {
//            sum+=percolationThreshold[i];
//        }
//        return sum/percolationThreshold.length;
        return StdStats.mean(percolationThreshold);
    }

    public double stddev()                    // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(percolationThreshold);
    }

    public double confidenceLo()              // low  endpoint of 95% confidence interval
    {
        return this.mean() - (1.96 * this.stddev()) / Math.sqrt((double) percolationThreshold.length);
    }

    public double confidenceHi()              // high endpoint of 95% confidence interval{
    {
        return this.mean() + (1.96 * this.stddev()) / Math.sqrt((double) percolationThreshold.length);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(400, 1000);
        System.out.println("mean: " + ps.mean());
        System.out.println("SD: " + ps.stddev());
        System.out.println("95% confidence: " + ps.confidenceLo() + ", " + ps.confidenceHi());
//        int x=3;
//        int y=2;
//        System.out.println((double)x/(double)y);
    }
}
