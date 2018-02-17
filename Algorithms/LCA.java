import java.util.*;

public class LCA 
{
	static int[] h, e, l;
	static boolean[] visited;
	static int index;
	static int[][] dp;
	static int[][] a;
	public static void main(String[] args)
	{
		int n = 10;
		a = new int[n][n];
		a[0][1] = a[1][0] =
				a[1][2] = a[2][1] =
				a[1][3] = a[3][1] = 
				a[1][6] = a[6][1] =
				a[3][4] = a[4][3] = 
				a[3][5] = a[5][3] = 
				a[0][7] = a[7][0] = 
				a[7][8] = a[8][7] = 
				a[7][9] = a[9][7] = 1;
		h = new int[n];
		e = new int[2 * n - 1];
		l = new int[2 * n - 1];
		index = 0;
		visited = new boolean[n];
		dfs(0, 0);
		System.out.println(Arrays.toString(h));
		System.out.println(Arrays.toString(e));
		System.out.println(Arrays.toString(l));
		int[][] table = build(l);
		System.out.println(lca(table, 4, 6));
	}
	
	public static int lca(int[][] table, int u, int v)
	{
		return e[rmq(table, h[u], h[v])];
	}
	
	public static int rmq(int[][] table, int s, int e)
	{
		int k = lg(e - s + 1);
		return Math.min(table[k][s], table[k][e - (1 << k) + 1]);
	}
	
	public static int[][] build(int[] a)
	{
		int size = lg(a.length) + 1;
		int[][] dp = new int[size][a.length];
		System.arraycopy(a, 0, dp[0], 0, a.length);
		for(int i = 1; i < size; i++)
		{
			int f1 = 1 << (i - 1);
			for(int j = 0; j + f1 < a.length; j++)
				dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + f1]);
		}
		return dp;
	}
	
	public static int lg(int num)
	{
		return 31 - Integer.numberOfLeadingZeros(num);
	}
	
	public static boolean dfs(int u, int depth)
	{
		if(visited[u])
			return false;
		System.out.println(u + " " + index);
		visited[u] = true;
		l[index] = depth;
		h[u] = index;
		e[index] = u;
		index++;
		for(int c = 0; c < a.length; c++)
		{
			if(a[u][c] > 0)
			{
				if(dfs(c, depth + 1))
				{
					l[index] = depth;
					e[index] = u;
					index++;
				}
			}
		}
		return true;
	}
}
