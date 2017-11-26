import java.util.*;
import java.io.*;

public class p793 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		for(; times > 0; times--)
		{
			int correct = 0, incorrect = 0;
			UnionFind uf = new UnionFind(Integer.parseInt(f.readLine()));
			String query = f.readLine();
			while(query != null && !query.isEmpty())
			{
				StringTokenizer st = new StringTokenizer(query);
				if(st.nextToken().charAt(0) == 'c')
				{
					uf.union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				}
				else
				{
					if(uf.connected(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())))
						correct++;
					else
						incorrect++;
				}
				query = f.readLine();
			}
			System.out.printf("%d,%d\n", correct, incorrect);
			if(times > 1)
				System.out.println();
		}
	}
	
	static class UnionFind
	{
		int[] parent;
		int[] rank;
		public UnionFind(int size)
		{
			parent = new int[size + 1];
			rank = new int[size + 1];
			for(int i = 1; i <= size; i++)
				parent[i] = i;
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
		
		public boolean connected(int p, int q)
		{
			return root(p) == root(q);
		}
		
		public void union(int p, int q)
		{
			int r1 = root(p);
			int r2 = root(q);
			if(rank[r1] < rank[r2])
				parent[r1] = r2;
			else
			{
				parent[r2] = r1;
				if(rank[r1] == rank[r2])
					rank[r1]++;
			}
		}
	}
}
