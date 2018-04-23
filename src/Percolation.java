import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Mansi_Ganatra
 *
 */
public class Percolation
{
	WeightedQuickUnionUF uf;
	int N;
	boolean[] openPositions;
	int numberOfOpenSites;
	
	public Percolation(int n)
	{
		if(n <= 0)
		{
			throw new IllegalArgumentException("Improper value for n: " + n);
		}
		
		int totalNodes = (n*n) + 2;
		this.N = n;
		uf = new WeightedQuickUnionUF(totalNodes);
		openPositions = new boolean[(n*n)+1];
	}
	
	public void open(int row, int column)
	{
		if(row < 1 || column < 1)
		{
			throw new IllegalArgumentException("Improper values for "
					+ "row or column: " + row + " " + column);
		}
		
		int currentPosition = ((row-1)*N) +column;
		int left = ((row-1)*N) + column-1;
		int right = ((row-1)*N) + column+1;
		int top = ((row-2)*N) + column;
		int bottom = (row*N) + column;
		
		openPositions[currentPosition] = true;
		numberOfOpenSites++;
		
		if(row == 1)
		{
			uf.union(0, currentPosition);
		}
		if(row == N)
		{
			uf.union(currentPosition, (N*N)+1);
		}
		if(column-1 > 0)
		{
			uf.union(currentPosition, left);
		}
		if(column+1 <= N)
		{
			uf.union(currentPosition, right);
		}
		if(row+1 <= N)
		{
			uf.union(currentPosition, bottom);
		}
		if(row-1 > 0)
		{
			uf.union(currentPosition, top);
		}
	}
	
	public boolean isOpen(int row, int column)
	{
		if(row < 1 || column < 1)
		{
			throw new IllegalArgumentException("Improper values for "
					+ "row or column: " + row + " " + column);
		}
		
		int currentPosition = ((row-1)*N) +column;
		return openPositions[currentPosition];
	}
	
	public boolean isFull(int row, int column)
	{
		if(row < 1 || column < 1)
		{
			throw new IllegalArgumentException("Improper values for "
					+ "row or column: " + row + " " + column);
		}
		
		int currentPosition = ((row-1)*N) +column;
		return uf.connected(0, currentPosition);
	}
	
	public int numberOfOpenSites()
	{
		return numberOfOpenSites;
	}
	
	public boolean percolates()
	{
		return uf.connected(0, (N*N)+1);
	}
	
	public static void main(String args[])
	{
		System.out.println("Enter the value for N: ");
		int N = StdIn.readInt();
		Percolation p = new Percolation(N); 
		p.open(1, 1);
		p.open(2, 2);
		p.open(3, 2);
		p.open(4, 3);
		p.open(5, 4);
		//p.open(2, 5);
		System.out.println("No of open sites: " + p.numberOfOpenSites());
		System.out.println("Percolates: " + p.percolates());
	}
}
