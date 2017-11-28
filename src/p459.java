import java.util.*;
import java.io.*;

class p459
{
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(f.readLine());
		f.readLine();
		while(cases-- > 0)
		{
			list = new ArrayList[f.readLine().charAt(0) - 'A' + 1];
			//System.out.println(list.length);
			for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
			{
				int a = input.charAt(0) - 'A';
				int b = input.charAt(1) - 'A';

				if(list[a] == null)
					list[a] = new ArrayList<Integer>();
				if(list[b] == null)
					list[b] = new ArrayList<Integer>();
				list[a].add(b);
				list[b].add(a);
			}

			int count = 0;
			visited = new boolean[list.length];
			for(int i = 0; i < list.length; i++)
			{
				if(!visited[i])
				{
					count++;
					dfs(i);
				}
			}
			System.out.println(count);
			if(cases > 0)
				System.out.println();
		}
	}
	
	static void dfs(int r)
	{
		visited[r] = true;
		if(list[r] != null)
			for(int c : list[r])
				if(!visited[c])
					dfs(c);
	}
}

class p459UnionFind
{
	static int[] parent;
	static int[] rank;
	static int groups;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(f.readLine());
		f.readLine();
		while(cases-- > 0)
		{
			int size = f.readLine().charAt(0) - 'A' + 1;
			parent = new int[size];
			for(int i = 0; i < parent.length; i++)
				parent[i] = i;
			rank = new int[size];
			groups = size;
			for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
			{
				int a = input.charAt(0) - 'A';
				int b = input.charAt(1) - 'A';
				
				union(a, b);
				//System.out.println(input + " " + Arrays.toString(parent) + " " + groups);
			}
			System.out.println(groups);
			if(cases > 0)
				System.out.println();
		}
	}
	
	public static int find(int p)
	{
		while(p != parent[p])
		{
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}
	
	public static void union(int a, int b)
	{
		int p = find(a);
		int q = find(b);
		if(p == q)
			return;
		groups--;
		if(rank[p] < rank[q])
			parent[p] = q;
		else
		{
			parent[q] = p;
			if(rank[p] == rank[q])
				rank[p]++;
		}
	}
}