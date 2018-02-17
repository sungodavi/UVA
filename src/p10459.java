import java.util.*;
import java.io.*;

public class p10459
{
	static LinkedList<Integer>[] list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException //UNSOLVED
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int size = Integer.parseInt(input);
			list = new LinkedList[size + 1];
			for(int i = 1; i <= size; i++)
			{
				st = new StringTokenizer(f.readLine());
				st.nextToken();
				list[i] = new LinkedList<Integer>();
				while(st.hasMoreTokens())
					list[i].add(Integer.parseInt(st.nextToken()));
			}
			int[] a = max();
			LinkedList<Integer> mins = new LinkedList<Integer>();
			LinkedList<Integer> maxes = new LinkedList<Integer>();
			int max = 0;
			int min = Integer.MAX_VALUE;
			for(int i = 1; i < list.length; i++)
			{
				int num = a[i];
				if(num <= min)
				{
					if(num < min)
						mins = new LinkedList<Integer>();
					mins.add(i);
					min = num;
				}
				if(num >= max)
				{
					if(num > max)
						maxes = new LinkedList<Integer>();
					maxes.add(i);
					max = num;
				}
			}
			System.out.print("Best Roots :");
			for(int num : mins)
				System.out.print(" " + num);
			System.out.println();
			System.out.print("Worst Roots :");
			for(int num : maxes)
				System.out.print(" " + num);
			System.out.println();
		}
	}
	
	public static int[] max()
	{
		int[] a = new int[list.length];
		for(int i = 1; i < list.length; i++)
		{
			visited = new boolean[list.length];
			a[i] = dfs(i);
		}
		return a;
	}
	
	public static int dfs(int u)
	{
		if(visited[u])
			return 0;
		visited[u] = true;
		int best = 0;
		for(int v : list[u])
			if(!visited[v])
				best = Math.max(dfs(v), best);
		return best + 1;
	}
}