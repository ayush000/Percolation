

/**
 * Created by ayush000 on 15/09/15.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //    int[] grid;
    int top,bottom;
    boolean[] isOpen;
    int N;
    WeightedQuickUnionUF unionUF;
    public Percolation(int N)
    {

        if(N<=0)
        {
            throw new IllegalArgumentException("N can't be negative");
        }
        else {
            this.N = N;
            int size = N * N;
            this.top=size;
            this.bottom=size+1;
            unionUF = new WeightedQuickUnionUF(size+2);
            for(int i=1;i<=N;i++)
            {
                unionUF.union(getIndex(1,i),top);
                unionUF.union(getIndex(N,i),bottom);
            }
//            this.grid = new int[size];
//
            this.isOpen = new boolean[size];
            for (int i = 0; i < size; i++) {
//                this.grid[i] = i;
                this.isOpen[i] = false;
            }
        }
    }

    public boolean isOpen(int i, int j)     // is site (row i, column j) open?
    {
        if(i<1||i>N||j<1||j>N)
        {
            throw new IndexOutOfBoundsException("row index out of bounds");
        }
        return isOpen[getIndex(i,j)];
    }
    private int getIndex(int i, int j)
    {
        return this.N*(i-1)+j-1;
    }
    public void open(int i, int j)
    {

        if(i<1||i>N||j<1||j>N)
        {
            throw new IndexOutOfBoundsException("row index out of bounds");
        }

        else {
            int index = getIndex(i, j);
            if (!this.isOpen[index]) {
                this.isOpen[index] = true;
                if(i!=1)
                {
                    if(isOpen(i-1,j))
                    {
                        unionUF.union(index,getIndex(i-1,j));
                    }
                }
                if(i!=N)
                {
                    if(isOpen(i+1,j))
                    {
                        unionUF.union(index,getIndex(i+1,j));
                    }
                }
                if (j!=1)
                {
                    if(isOpen(i,j-1))
                    {
                        unionUF.union(index,getIndex(i,j-1));
                    }
                }
                if(j!=N)
                {
                    if(isOpen(i,j+1))
                    {
                        unionUF.union(index,getIndex(i,j+1));
                    }
                }

            }
        }
//        System.out.println("Connected: "+unionUF.connected(getIndex(4,5),getIndex(4,4)));
//        System.out.println("Connected: "+unionUF.connected(top,bottom));

    }

    public boolean isFull(int i, int j)     // is site (row i, column j) full?
    {
        if(i<1||i>N||j<1||j>N)
        {
            throw new IndexOutOfBoundsException("row index out of bounds");
        }
        return unionUF.connected(getIndex(i,j),top);
    }
    public boolean percolates()             // does the system percolate?
    {
        return unionUF.connected(top,bottom);
    }

    public static void main(String[] args)
    {
        Percolation p = new Percolation(5);
//        System.out.println(p.isOpen(4,5));
//
        for(int i=1;i<=5;i++)
        {
            p.open(i,1);
        }
//        System.out.println(p.isOpen(4,5));
//        System.out.println();
//        System.out.println("percolates: "+p.isFull(4,1));
    }

}
