import java.util.*;
import java.io.*;

public class p1208 
{
	static int[][] a;
	static ArrayList<Edge> list;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		int count = 1;
		while(times-- > 0)
		{
			 int size = Integer.parseInt(f.readLine());
			 a = new int[size][size];
			 for(int r = 0; r < a.length; r++)
			 {
				 StringTokenizer st = new StringTokenizer(f.readLine(), ", ");
				 for(int c = 0; c < a.length; c++)
					 a[r][c] = Integer.parseInt(st.nextToken());
			 }
			 list = new ArrayList<Edge>();
			 prims();
			 Collections.sort(list);
			 System.out.printf("Case %d:\n", count++);
			 for(Edge e: list)
				 System.out.println(e);
		}
	}
	
	static void prims()
	{
		Queue<Edge> pq = new PriorityQueue<Edge>();
		for(int i = 0; i < a.length; i++)
			if(a[0][i] > 0)
				pq.add(new Edge(0, i, a[0][i]));
		boolean[] visited = new boolean[a.length];
		visited[0] = true;
		while(!pq.isEmpty())
		{
			Edge e = pq.poll();
			if(visited[e.v])
				continue;
			visited[e.v] = true;
			list.add(e);
			int u = e.v;
			for(int v = 0; v < a.length; v++)
				if(a[u][v] > 0 && !visited[v])
					pq.add(new Edge(u, v, a[u][v]));
					
		}
	}
	
	
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, weight;
		public Edge(int u, int v, int weight)
		{
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		
		public int compareTo(Edge e)
		{
			return weight - e.weight;
		}
		
		public String toString()
		{
			int x = Math.min(u, v);
			int y = Math.max(u, v);
			return String.format("%c-%c %d", (char)(x + 'A'), (char)(y + 'A'), weight);
		}
	}
}