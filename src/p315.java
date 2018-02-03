import java.util.*;
import java.io.*;

public class p315
{
	static ArrayList<Integer>[] list;
	static int[] dfs_low;
	static int[] dfs_num;
	static int[] parent;
	static int counter, root, rootChildren;
	static boolean[] articulationPoints;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			list = new ArrayList[size];
			for(int i =0; i < size; i++)
				list[i] = new ArrayList<Integer>();
			dfs_num = new int[size];
			dfs_low = new int[size];
			articulationPoints = new boolean[size];
			parent = new int[size];
			Arrays.fill(dfs_num, -1);
			int count = 0;
			for(String input = f.readLine(); !input.equals("0"); input = f.readLine())
			{
				st = new StringTokenizer(input);
				int u = Integer.parseInt(st.nextToken()) - 1;
				while(st.hasMoreTokens())
				{
					int v = Integer.parseInt(st.nextToken()) - 1;
					list[u].add(v);
					list[v].add(u);
				}
			}
			for(int i =0; i < size; i++)
				if(dfs_num[i] < 0)
				{
					root = i;
					rootChildren = 0;
					counter = 0;
					dfs(i);
					articulationPoints[i] = rootChildren > 1;
				}
			for(boolean b : articulationPoints)
				if(b)
					count++;
			out.println(count);
		}
		out.close();
	}
	public static void dfs(int u)
	{
		dfs_low[u] = dfs_num[u] = counter++;
		for(int v : list[u])
		{
			if(dfs_num[v] == -1)
			{
				parent[v] = u;
				if(u == root)
					rootChildren++;
				dfs(v);
				if(dfs_low[v] >= dfs_num[u])
					articulationPoints[u] = true;
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
			}
			else if(parent[u] != v)
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
		}
	}
}