import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author Mansi_Ganatra
 *
 */
public class PercolationStats
{
  
  private int T;
  private double[] pThreshold;
  
  public PercolationStats(int n, int trials)
  {
    if(n <= 0 || trials <= 0)
    {
      throw new IllegalArgumentException("Improper value for n or trials: " + n +"," +trials);
    }
    this.T = trials;
    this.pThreshold = new double[trials];
    
    for (int i = 0; i < trials; i++)
    {
      Percolation p = new Percolation(n);
      while(!p.percolates())
      {
        p.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
      }
      pThreshold[i] = (double)p.numberOfOpenSites()/(double)(n*n);
    }
  }
  
  public double mean()
  {
    return StdStats.mean(pThreshold);
  }
  
  public double stddev()
  {
    return StdStats.stddev(pThreshold);
  }
  
  public double confidenceLo()
  {
    return (mean() - 1.96*stddev()/Math.sqrt(T));
  }
  public double confidenceHi()
  {
    return (mean() + 1.96*stddev()/Math.sqrt(T));
  }
  
  
  public static void main(String[] args)
  {
    int n = StdIn.readInt();
    int trials = StdIn.readInt();
    
    Stopwatch  watch = new Stopwatch();
    PercolationStats ps = new PercolationStats(n, trials);
    
    System.out.println("mean                    = " + ps.mean());
    System.out.println("stddev                  = " + ps.stddev());
    System.out.println("95% confidence interval = " + "[" + ps.confidenceLo() + ", " 
                                              + ps.confidenceHi() + "]");
    System.out.println("Time taken: " + watch.elapsedTime());
  }
  
}
