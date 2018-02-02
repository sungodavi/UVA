import java.util.*;
import java.io.*;
import java.awt.Point;

public class p10354
{
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			int roads = Integer.parseInt(st.nextToken());
			int boss = Integer.parseInt(st.nextToken()) - 1;
			int office = Integer.parseInt(st.nextToken()) - 1;
			int home = Integer.parseInt(st.nextToken()) - 1;
			int market = Integer.parseInt(st.nextToken()) - 1;
			int[][] a = new int[size][size];
			while(roads-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int r = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				a[r][c] = a[c][r] = Integer.parseInt(st.nextToken());
			}
			int[][] dist = floyd(a);
			HashSet<Integer> set = new HashSet<Integer>();
			for(int k = 0; k < a.length; k++)
				if(dist[boss][office] == dist[boss][k] + dist[k][office])
					set.add(k);
			for(int k : set)
				remove(a, k);
			dist = floyd(a);
			out.println(dist[home][market] != INF ? dist[home][market] : "MISSION IMPOSSIBLE.");
		}
		out.close();
	}
	
	public static void remove(int[][] a, int i)
	{
		for(int k = 0; k < a.length; k++)
			a[i][k] = a[k][i] = INF;
	}
	
	public static int[][] floyd(int[][] a)
	{
		int n = a.length;
		int[][] dist = new int[n][n];
		for(int r = 0; r < n; r++)
			for(int c = 0; c < n; c++)
			{
				if(a[r][c] > 0)
					dist[r][c] = a[r][c];
				else if(r != c)
					dist[r][c] = INF;
			}
		
		for(int k = 0; k < n; k++)
			for(int r = 0; r < n; r++)
				for(int c = 0; c < n; c++)
					dist[r][c] = Math.min(dist[r][c], dist[r][k] + dist[k][c]);
		return dist;
			
	}
}