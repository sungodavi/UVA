import java.util.*;
import java.io.*;

public class p1174 
{
	static HashMap<String, Integer> map;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
	}
	static class UnionFind
	{
		int[] parent;
		int[] rank;
		int sets;
		public UnionFind(int size)
		{
			parent = new int[size];
			rank = new int[size];
			sets = size;
			for(int i = 0; i < size; i++)
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
		
		public boolean union(int a, int b)
		{
			int p = find(a);
			int q = find(b);
			if(p == q)
				return false;
			sets--;
			if(rank[p] < rank[q])
			{
				parent[p] = q;
			}
			else
			{
				parent[q] = p;
				if(rank[p] == rank[q])
					rank[p]++;
			}
			return true;
		}
	}
}
