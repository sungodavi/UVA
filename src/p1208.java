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
		Queue<Edge> q = new PriorityQueue<Edge>();
		for(int c = 0; c < a.length; c++)
			if(a[0][c] > 0)
				q.add(new Edge(0, c, a[0][c]));
		boolean[] visited = new boolean[a.length];
		visited[0] = true;
		while(!q.isEmpty())
		{
			Edge e = q.poll();
			list.add(e);
			visited[e.v] = true;
			for(int c = 0; c < a.length; c++)
			{
				if(a[e.v][c] > 0 && !visited[c])
					q.add(new Edge(e.v, c, a[e.v][c]));
			}
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
			if(weight == e.weight)
			{
				if(Math.min(u, v) == Math.min(e.u, e.v))
					return Math.max(u, v) - Math.max(e.u, e.v);
				return Math.min(u, v) - Math.min(e.u, e.v);
					
			}
			return weight - e.weight;
		}
		
		public String toString()
		{
			return String.format("%c-%c %d", (char)(Math.min(u, v) + 'A'), (char)(Math.max(u, v) + 'A'), weight);
		}
	}
}