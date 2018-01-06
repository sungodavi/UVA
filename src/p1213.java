import java.util.*;
import java.io.*;

public class p1213 
{
	static ArrayList<Integer> list;
	static int[][][] dp;
	public static void main(String[] args) throws IOException 
	{
		load(1120);
		dp = new int[1121][15][list.size()];
		for(int[][] temp : dp)
			for(int[] temp2 : temp)
				Arrays.fill(temp2, -1);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			out.println(recurse(n, k, 0));
		}
		out.close();
	}
	
	public static int recurse(int n, int k, int index)
	{
		//System.out.println(n + " " + k);
		if(n == 0)
		{
			return k == 0 ? 1 : 0;
		}
		if(k == 0 || index == list.size())
			return 0;
		if(dp[n][k][index] >= 0)
			return dp[n][k][index];
		int count = 0;
		for(int i = index; i < list.size() && list.get(i) <= n; i++)
		{
			count += recurse(n - list.get(i), k - 1, i + 1);
		}
		return dp[n][k][index] = count;
	}
	
	public static void load(int size)
	{
		boolean[] a = new boolean[size + 1];
		list = new ArrayList<Integer>();
		
		for(int i = 2; i < a.length; i++)
		{
			if(!a[i])
			{
				list.add(i);
				for(int j = i * i; j < a.length; j += i)
					a[j] = true;
			}
		}
	}
}