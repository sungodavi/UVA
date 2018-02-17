import java.util.*;
import java.io.*;

public class p11138 
{
	static LinkedList<Integer>[] list;
	static int[] match;
	static boolean[] visited;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 1; t <= times; t++)
		{
			st = new StringTokenizer(f.readLine());
			int rows = Integer.parseInt(st.nextToken());
			int cols = Integer.parseInt(st.nextToken());
			match = new int[cols];
			Arrays.fill(match, -1);
			list = new LinkedList[rows];
			for(int i = 0; i < rows; i++)
			{
				st = new StringTokenizer(f.readLine());
				list[i] = new LinkedList<Integer>();
				for(int c = 0; c < cols; c++)
				{
					if(st.nextToken().equals("1"))
						list[i].add(c);
				}
			}
			int count = 0;
			for(int r = 0; r < rows; r++)
			{
				visited = new boolean[rows];
				if(augment(r))
					count++;
			}
			out.printf("Case %d: a maximum of %d nuts and bolts can be fitted together\n", t, count);
		}
		out.close();
	}
	
	public static boolean augment(int u)
	{
		if(visited[u])
			return false;
		visited[u] = true;
		for(int v : list[u])
		{
			if(match[v] == -1 || augment(match[v]))
			{
				match[v] = u;
				return true;
			}
		}
		return false;
	}
}