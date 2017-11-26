import java.util.*;
import java.io.*;

public class p599 
{
	static ArrayList<Integer>[] list;
	static int[] sizes;
	static boolean[] visited;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(; times > 0; times--)
		{
			sizes = new int[26];
			list = new ArrayList[26];
			visited = new boolean[26];
			String input = f.readLine();
			int forest =0, acorn = 0;
			while(input.charAt(0) != '*')
			{
				int r = input.charAt(1) - 'A';
				int c = input.charAt(3) - 'A';
				if(list[r] == null)
					list[r] = new ArrayList<Integer>();
				if(list[c] == null)
					list[c] = new ArrayList<Integer>();
				list[r].add(c);
				list[c].add(r);
				input = f.readLine();
			}
			StringTokenizer st = new StringTokenizer(f.readLine(), ",");
			while(st.hasMoreTokens())
			{
				int c = st.nextToken().charAt(0) - 'A';
				if(sizes[c] == 0)
				{
					int size = dfs(c);
					if(size == 1)
						acorn++;
					else
						forest++;
				}
			}
			
			System.out.printf("There are %d tree(s) and %d acorn(s).\n", forest, acorn);
		}
	}
	
	public static int dfs(int c)
	{
		if(list[c] == null)
			return 1;
		visited[c] = true;
		int size = 1;
		for(int n : list[c])
			if(!visited[n])
				size += dfs(n);
		return sizes[c] = size;
	}
}
