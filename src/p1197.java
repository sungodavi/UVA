import java.util.*;
import java.io.*;

public class p1197 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int groups = Integer.parseInt(st.nextToken());
		while(size > 0)
		{
			UnionFind uf = new UnionFind(size);
			for(int k = 0; k < groups; k++)
			{
				st = new StringTokenizer(f.readLine());
				if(st.nextToken().equals("0"))
					continue;
				int num = Integer.parseInt(st.nextToken());
				while(st.hasMoreTokens())
					uf.union(num, Integer.parseInt(st.nextToken()));
				//System.out.println(Arrays.toString(uf.size));
			}
			System.out.println(uf.size[0]);
			st = new StringTokenizer(f.readLine());
			size = Integer.parseInt(st.nextToken());
			groups = Integer.parseInt(st.nextToken());
		}
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
			rank[0] = Integer.MAX_VALUE;
			this.size = new int[size];
			for(int i = 0; i < size; i++)
			{
				parent[i] = i;
				this.size[i] = 1;
			}
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
		
		public void union(int p, int q)
		{
			int r1 = root(p);
			int r2 = root(q);
			if(r1 == r2)
				return;
			if(rank[r1] < rank[r2])
			{
				parent[r1] = r2;
				size[r2] += size[r1];
				size[r1] = 0;
			}
			else
			{
				parent[r2] = r1;
				size[r1] += size[r2];
				size[r2] = 0;
				if(rank[r1] == rank[r2])
					rank[r1]++;
			}
		}
	}
}
