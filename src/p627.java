import java.util.*;
import java.io.*;

public class p627
{
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			System.out.println("-----");
			int size = Integer.parseInt(input);
			int[][] a = new int[size][size];
			for(int k = 0; k < size; k++)
			{
				st = new StringTokenizer(f.readLine(), "[-,]");
				int u = Integer.parseInt(st.nextToken()) - 1;
				while(st.hasMoreTokens())
				{
					int v = Integer.parseInt(st.nextToken()) - 1;
					a[u][v] = 1;
				}
			}
			
			int[][] dist = new int[size][size];
			int[][] next = new int[size][size];
			for(int[] temp : next)
				Arrays.fill(temp, -1);
			for(int r = 0; r < size; r++)
			{
				for(int c = 0; c < size; c++)
				{
					if(a[r][c] == 1)
					{
						dist[r][c] = 1;
						next[r][c] = c;
					}
					else if(r != c)
						dist[r][c] = INF;
				}
			}
			
			for(int k = 0; k < size; k++)
				for(int r = 0; r < size; r++)
					for(int c = 0; c < size; c++)
					{
						if(dist[r][c] > dist[r][k] + dist[k][c])
						{
							dist[r][c] = dist[r][k] + dist[k][c];
							next[r][c] = next[r][k];
						}
					}
			int queries = Integer.parseInt(f.readLine());
			while(queries-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				if(dist[u][v] == INF)
					System.out.println("connection impossible");
				else
				{
					System.out.print(u + 1);
					int k = next[u][v];
					while(k != v)
					{
						System.out.print(" " + (k + 1));
						k = next[k][v];
					}
					System.out.println(" " + (v + 1));
				}
			}
		}
	}
}