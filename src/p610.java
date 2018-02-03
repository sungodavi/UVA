import java.util.*;
import java.io.*;

public class p610 
{
	static LinkedList<Integer>[] list;
	static int[] low;
	static int[] num;
	static int counter;
	static int[] parent;
	static int[] color;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int caseNo = 0;
		for(String input = f.readLine(); input.charAt(0) != '0'; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			int roads = Integer.parseInt(st.nextToken());
			list = new LinkedList[size + 1];
			for(int i = 1; i <= size; i++)
				list[i] = new LinkedList<Integer>();
			while(roads-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				list[u].add(v);
				list[v].add(u);
			}
			init(size);
			System.out.println(++caseNo);
			System.out.println();
			dfs(1);
			System.out.println('#');
		}
	}
	
	public static void init(int size)
	{
		parent = new int[size + 1];
		Arrays.fill(parent, -1);
		num = new int[size + 1];
		Arrays.fill(num, -1);
		low = new int[size + 1];
		counter = 0;
		color = new int[size + 1];
	}
	
	public static void dfs(int u)
	{ 
		num[u] = low[u] = counter++;
		color[u] = 1;
		for(int v : list[u])
		{
			if(color[v] == 0)
			{
				System.out.println(u + " " + v);
				parent[v] = u;
				dfs(v);
				if(low[v] > num[u])
				{
					System.out.println(v + " " + u);
				}
				low[u] = Math.min(low[u], low[v]);
			}
			else if(parent[u] != v)
			{
				low[u] = Math.min(low[u], num[v]);
				if(color[v] == 1)
					System.out.println(u + " " + v);
			}
		}
		color[u] = 2;
	}
}