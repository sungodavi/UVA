import java.util.*;
import java.io.*;

public class p10305
{
	public static ArrayList<Integer>[] list;
	public static boolean[] visited;
	public static ArrayList<Integer> order;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			visited = new boolean[n];
			list = new ArrayList[n];
			order = new ArrayList(n);
			while(m-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				if(list[u] == null)
					list[u] = new ArrayList<Integer>();
				list[u].add(v);
			}
			//System.out.println(Arrays.toString(list));
			for(int i = 0; i < n; i++)
			{
				if(!visited[i])
					dfs(i);
			}
			boolean flag = false;
			for(int i = order.size() - 1; i >= 0; i--)
			{
				if(flag)
					out.print(" ");
				else
					flag = true;
				out.print(order.get(i) + 1);
			}
			out.println();
		}
		out.close();
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