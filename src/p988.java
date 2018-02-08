import java.util.*;
import java.io.*;

public class p988
{
	static int[] dp;
	static LinkedList<Integer>[] list;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean flag = false;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			int size = Integer.parseInt(input);
			list = new LinkedList[size];
			for(int i = 0; i < size; i++)
			{
				list[i] = new LinkedList<Integer>();
				st = new StringTokenizer(f.readLine());
				st.nextToken();
				while(st.hasMoreTokens())
					list[i].add(Integer.parseInt(st.nextToken()));
			}
			dp = new int[size];
			Arrays.fill(dp, -1);
			System.out.println(recurse(0));
			f.readLine();
		}
	}
	
	public static int recurse(int u)
	{
		if(list[u].isEmpty())
			return 1;
		if(dp[u] != -1)
			return dp[u];
		int paths = 0;
		for(int v : list[u])
			paths += recurse(v);
		return dp[u] = paths;
	}
}