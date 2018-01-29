import java.util.*;
import java.io.*;

public class p11747 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		for(String input = f.readLine();; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			if(size == 0)
				break;
			int roads = Integer.parseInt(st.nextToken());
			Edge[] edges = new Edge[roads];
			for(int i = 0; i < edges.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(u, v, w);
			}
			ArrayList<Integer> result = mst(edges, size);
			if(result.isEmpty())
				out.println("forest");
			else
			{
				boolean flag = false;
				for(int num : result)
				{
					if(flag)
						out.print(" ");
					else
						flag = true;
					out.print(num);
				}
				out.println();
			}
		}
		out.close();
	}
	
	static ArrayList<Integer> mst(Edge[] edges, int size)
	{
		UnionFind uf = new UnionFind(size);
		ArrayList<Integer> list = new ArrayList<Integer>();
		Arrays.sort(edges);
		int i;
		for(i = 0; i < edges.length; i++)
			if(!uf.union(edges[i].u, edges[i].v))
				list.add(edges[i].w);
		return list;
	}
	
	static class UnionFind
	{
		int[] parent;
		int[] rank;
		int size;
		public UnionFind(int size)
		{
			parent = new int[size];
			for(int i = 1; i < size; i++)
				parent[i] = i;
			rank = new int[size];
			this.size = size;
		}
		
		public int root(int p)
		{
			while(p != parent[p])
			{
				parent[p] = parent[parent[p]];
				p = parent[p];
			}
			return p;
		}
		
		public boolean union(int a, int b)
		{
			int p = root(a);
			int q = root(b);
			if(p == q)
				return false;
			size--;
			if(rank[p] <= rank[q])
			{
				if(rank[p] == rank[q])
					rank[q]++;
				parent[p] = q;
			}
			else
				parent[q] = p;
			return true;
		}
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, w;
		public Edge(int u, int v, int w)
		{
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Edge e)
		{
			return w - e.w;
		}
	}
}