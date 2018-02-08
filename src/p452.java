import java.util.*;
import java.io.*;

public class p452
{
	static LinkedList<Integer>[] list;
	static ArrayList<Integer> order;
	static boolean[] visited;
	static int[] weight;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		for(int t = 0; t < times; t++)
		{
			if(t > 0)
				out.println();
			list = new LinkedList[26];
			weight = new int[26];
			int size = 0;
			int[] counts = new int[26];
			for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
			{
				size++;
				st = new StringTokenizer(input);
				int index = st.nextToken().charAt(0) - 'A';
				weight[index] = Integer.parseInt(st.nextToken());
				if(list[index] == null)
					list[index] = new LinkedList<Integer>();
				if(st.hasMoreTokens())
				{
					String roads = st.nextToken();
					for(int i = 0; i < roads.length(); i++)
					{
						int u = roads.charAt(i) - 'A';
						list[index].add(u);
						counts[u]++;
					}
				}
			}
			order = new ArrayList<Integer>(size);
			visited = new boolean[26];
			for(int i = 0; i < counts.length; i++)
				if(counts[i] == 0 && list[i] != null)
					dfs(i);
			
			out.println(longestPath());
		}
		out.close();
	}
	
	public static int longestPath()
	{
		int[] dp = new int[26];
		int max = dp[order.get(0)] = weight[order.get(0)];
		for(int i = 1; i < order.size(); i++)
		{
			int best = 0;
			int u = order.get(i);
			for(int v : list[u])
				best = Math.max(best, dp[v]);
			dp[u] = best + weight[u];
			max = Math.max(dp[u], max);
		}
		return max;
	}
	
	public static void dfs(int u)
	{
		if(visited[u])
			return;
		visited[u] = true;
		if(list[u] != null)
			for(int v : list[u])
				dfs(v);
		order.add(u);
	}
}