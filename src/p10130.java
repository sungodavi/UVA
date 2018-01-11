import java.util.*;
import java.io.*;

public class p10130 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			Item[] a = new Item[Integer.parseInt(f.readLine())];
			for(int i = 0; i < a.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				int p = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				a[i] = new Item(p, w);
			}
			int[] ppl = new int[Integer.parseInt(f.readLine())];
			for(int i = 0; i < ppl.length; i++)
				ppl[i] = Integer.parseInt(f.readLine());
			Arrays.sort(ppl);
			
			int[][] dp = new int[a.length + 1][ppl[ppl.length - 1] + 1];
			for(int r = 1; r < dp.length; r++)
			{
				Item item = a[r - 1];
				for(int c = 1; c < dp[0].length; c++)
				{
					if(item.w > c)
						dp[r][c] = dp[r - 1][c];
					else
						dp[r][c] = Math.max(dp[r - 1][c - item.w] + item.p, dp[r - 1][c]);
				}
			}
			int sum = 0;
			for(int person : ppl)
				sum += dp[a.length][person];
			out.println(sum);
		}
		out.close();
	}
	
	static class Item
	{
		int p, w;
		public Item(int p, int w)
		{
			this.p = p;
			this.w = w;
		}
	}
}