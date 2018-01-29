import java.util.*;
import java.io.*;

public class p11503 
{
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			int index = 0;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			int connections = Integer.parseInt(f.readLine());
			UnionFind uf = new UnionFind(connections << 1);
			while(connections-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int p, q;
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				if(map.containsKey(s1))
					p = map.get(s1);
				else
				{
					map.put(s1, index);
					p = index++;
				}
				if(map.containsKey(s2))
					q = map.get(s2);
				else
				{
					map.put(s2, index);
					q = index++;
				}
				out.println(uf.union(p, q));
			}
		}
		out.close();
	}
	
	static class UnionFind
	{
		int[] parent;
		int[] rank;
		int[] size;
		
		public UnionFind(int size)
		{
			parent = new int[size];
			rank = new int[size];
			this.size = new int[size];
			Arrays.fill(this.size, 1);
			for(int i = 1; i < parent.length; i++)
				parent[i] = i;
		}
		
		public int find(int p)
		{
			while(p != parent[p])
			{
				parent[p] = parent[parent[p]];
				p = parent[p];
			}
			return p;
		}
		
		public int union(int a, int b)
		{
			int p = find(a);
			int q = find(b);
			if(p == q)
				return size[p];
			if(rank[p] <= rank[q])
			{
				if(rank[p] == rank[q])
					rank[q]++;
				parent[p] = q;
				size[q] += size[p];
				return size[q];
			}
			else
			{
				parent[q] = p;
				size[p] += size[q];
				return size[p];
			}
		}
	}
}