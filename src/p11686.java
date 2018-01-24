import java.util.*;
import java.io.*;

public class p11686
{
	static LinkedList<Integer>[] list;
	static int[] visited;
	static ArrayList<Integer> order;
	public static void main(String[] args) throws IOException
	{
		//System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine();; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			if(n == 0)
				break;
			int m = Integer.parseInt(st.nextToken());
			list = new LinkedList[n];
			visited = new int[n];
			order = new ArrayList<Integer>(n);
			while(m-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				if(list[u] == null)
					list[u] = new LinkedList<Integer>();
				list[u].add(v);
			}
			try
			{
				for(int i = 0; i < n; i++)
					if(visited[i] == 0)
						dfs(i);

				for(int i = n - 1; i >= 0; i--)
					out.println(order.get(i) + 1);
			}
			catch(Exception e)
			{
				out.println("IMPOSSIBLE");
			}
			
		}
		out.close();
	}
	
	public static void dfs(int u)
	{
		if(visited[u] == 2)
			return;
		visited[u] = 1;
		if(list[u] != null)
			for(int v : list[u])
			{
				if(visited[v] == 1)
					throw new IllegalArgumentException();
				else if(visited[v] == 0)
					dfs(v);
			}
		visited[u] = 2;
		order.add(u);
	}
}