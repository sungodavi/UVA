import java.util.*;
import java.io.*;

public class p11463 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(int k = 1; k <= times; k++)
		{
			int size = Integer.parseInt(f.readLine());
			int roads = Integer.parseInt(f.readLine());
			int[][] a = new int[size][size];
			while(roads-- > 0)
			{
				StringTokenizer st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				a[u][v] = a[v][u] = 1;				
			}
			StringTokenizer st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int[][] dist = floydWarshall(a);
			int sum = 0;
			for(int i = 0; i < a.length; i++)
		}
		
	}
	public static int[][] floydWarshall(int[][] a)
	{
		int[][] dist = new int[a.length][a.length];
		for(int r = 0; r < dist.length; r++)
			for(int c =0 ; c < dist.length; c++)
				if(r != c)
				{
					if(a[r][c] > 0)
						dist[r][c] = a[r][c];
					else
						dist[r][c] = (int)1e9;
				}
		return dist;
	}
}
