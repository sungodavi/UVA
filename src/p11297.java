import java.util.*;
import java.io.*;
import java.awt.Point;

public class p11297 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(f.readLine());
		int[][] a = new int[size][size];
		for(int r = 0; r < a.length; r++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			for(int c = 0; c < a.length; c++)
			{
				a[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		QuadTree tree = new QuadTree(a);
		//System.out.println(Arrays.toString(tree.tree));
		int queries = Integer.parseInt(f.readLine());
		for(; queries > 0; queries--)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			char type = st.nextToken().charAt(0);
			if(type == 'q')
			{
				int sr = Integer.parseInt(st.nextToken()) - 1;
				int sc = Integer.parseInt(st.nextToken()) - 1;
				int er = Integer.parseInt(st.nextToken()) - 1;
				int ec = Integer.parseInt(st.nextToken()) - 1;
				Point p = tree.get(sr, er, sc, ec);
				System.out.println(p.y + " " + p.x);
			}
			else
			{
				int r = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				int newVal = Integer.parseInt(st.nextToken());
				tree.update(r, c, newVal);
			}
		}
		
	}
	static class QuadTree
	{
		int[][] a;
		Point[] tree;
		public QuadTree(int[][] a)
		{
			this.a = a;
			tree = new Point[4 * a.length * a.length];
			build(0,a.length - 1, 0, a.length - 1, 0);
		}
		
		public Point best(Point a, Point b)
		{
			if(a == b && a == null)
				return null;
			if(a == null)
				return b;
			if(b == null)
				return a;
			return new Point(Integer.min(a.x, b.x), Integer.max(a.y, b.y));
		}
		
		public static int[][] getLeaves(int sr, int er, int sc, int ec)
		{
			int midR = (sr + er) / 2;
			int midC = (sc + ec) / 2;
			int[][] a = {{sr, sr, midR + 1, midR + 1},
						{midR, midR, er, er},
						{sc, midC + 1, sc, midC + 1},
						{midC, ec, midC, ec}};
			return a;
		}
		
		public Point build(int sr, int er, int sc, int ec, int index)
		{
			if(sr > er || sc > ec)
				return null;
			if(sr == er && sc == ec)
				return tree[index] = new Point(a[sr][sc], a[sr][sc]);
			
			Point best = null;
			int[][] leaves = getLeaves(sr, er, sc, ec);
			for(int i = 0; i < leaves.length; i++)
			{
				best = best(best, build(leaves[0][i], 
										leaves[1][i], 
										leaves[2][i], 
										leaves[3][i], 
										(index << 2) + 1 + i));
			}
			return tree[index] = best;
		}
		
		public Point get(int sr, int er, int sc, int ec, int qsr, int qer, int qsc, int qec, int index)
		{
			if(sr > er || sc > ec || sr > qer || er < qsr || sc > qec || ec < qsc)
				return null;
			if(sr >= qsr && er <= qer && sc >= qsc && ec <= qec)
				return tree[index];
			Point best = null;
			int[][] leaves = getLeaves(sr, er, sc, ec);
			for(int i = 0; i < leaves.length; i++)
			{
				best = best(best, get(leaves[0][i], 
										leaves[1][i], 
										leaves[2][i], 
										leaves[3][i],
										qsr,
										qer,
										qsc,
										qec,
										(index << 2) + 1 + i));
			}
			return best;
		}
		
		public Point update(int sr, int er, int sc, int ec, int r, int c, int newVal, int index)
		{
			if(sr > er || sc > ec)
				return null;
			
			if(r < sr || r > er || c < sc || c > ec)
				return tree[index];
			
			if(r == sr && r == er && c == sc && c == ec)
				return tree[index] = new Point(newVal, newVal);
			
			Point best = null;
			int[][] leaves = getLeaves(sr, er, sc, ec);
			for(int i = 0; i < leaves.length; i++)
			{
				best = best(best, update(leaves[0][i], 
										leaves[1][i], 
										leaves[2][i], 
										leaves[3][i],
										r,
										c,
										newVal,
										(index << 2) + 1 + i));
			}
			return tree[index] = best;
		}
		
		public Point get(int sr, int er, int sc, int ec)
		{
			return get(0,a.length - 1, 0, a.length - 1, sr, er, sc, ec, 0);
		}
		
		public void update(int r, int c, int newVal)
		{
			update(0,a.length - 1, 0, a.length - 1, r, c, newVal, 0);
		}
	}
}
