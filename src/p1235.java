import java.util.*;
import java.io.*;

public class p1235 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int size = Integer.parseInt(st.nextToken());
			int[] a = new int[size];
			for(int i = 0; i < size; i++)
				a[i] = Integer.parseInt(st.nextToken());
			out.println(prims(a));
		}
		out.close();
	}
	
	public static int prims(int[] a)
	{
		Queue<Edge> pq = new PriorityQueue<Edge>();
		for(int i = 1; i < a.length; i++)
			pq.add(new Edge(0, i, cost(a[0], a[i])));
		boolean[] visited = new boolean[a.length];
		visited[0] = true;
		int cost = 0;
		while(!pq.isEmpty())
		{
			Edge e = pq.poll();
			if(visited[e.v])
				continue;
			visited[e.v] = true;
			cost += e.weight;
			for(int i = 1; i < a.length; i++)
			{
				if(!visited[i])
					pq.add(new Edge(e.v, i, cost(a[e.v], a[i])));
			}
		}
		int min = Integer.MAX_VALUE;
		for(int num : a)
			min = Math.min(min, cost(0, num));
		return cost + min;
	}
	public static int cost(int a, int b)
	{
		int cost = 0;
		for(int i = 0; i < 4; i++)
		{
			int n1 = a % 10;
			int n2 = b % 10;
			if(n1 > n2)
			{
				int temp = n1;
				n1 = n2;
				n2 = temp;
			}
			cost += Math.min(n2 - n1, 10 - n2 + n1);
			a /= 10;
			b /= 10;
		}
		return cost;
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
	}
}