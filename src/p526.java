import java.util.*;
import java.io.*;

public class p526 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		for(String a = f.readLine(); a != null; a = f.readLine())
		{
			if(flag)
				flag = false;
			else
				System.out.println();
			String b = f.readLine();
			int[][] dp = new int[a.length() + 1][b.length() + 1];
			for(int i = 0; i < dp.length; i++)
				dp[i][0] = i;
			for(int i = 0; i < dp[0].length; i++)
				dp[0][i] = i;
			for(int r = 1; r < dp.length; r++)
				for(int c = 1; c < dp[0].length; c++)
				{
					char c1 = a.charAt(r - 1);
					char c2 = b.charAt(c - 1);
					if(c1 == c2)
					{
						dp[r][c] = dp[r - 1][c - 1];
					}
					else
						dp[r][c] = Math.min(dp[r - 1][c - 1], Math.min(dp[r - 1][c], dp[r][c - 1])) + 1;
				}
			for(int[] temp: dp)
				System.out.println(Arrays.toString(temp));
			
			Stack<String> result = new Stack<String>();
			int r = dp.length - 1;
			int c = dp[0].length - 1;
			while(r > 0 && c > 0)
			{
				char c1 = a.charAt(r - 1);
				char c2 = b.charAt(c - 1);
				if(c1 == c2)
				{
					r--;
					c--;
				}
				else
				{
					int o1 = dp[r-1][c-1];
					int o2 = dp[r-1][c];
					int o3 = dp[r][c-1];
					if(o1 <= o2 && o2 <= o3)
					{
						result.push(String.format("Replace %d,%c", r, c2));
						r--;
						c--;
					}
					else if(o2 <= o3)
					{
						result.push(String.format("Delete %d", r));
						r--;
					}
					else
					{
						result.push(String.format("Insert %d,%c", r, c2));
						c--;
					}
				}
			}
			while(r > 0)
			{
				result.push(String.format("Delete %d", r));
				r--;
			}
			while(c > 0)
			{
				result.push(String.format("Insert %d,%c", r + 1, b.charAt(c - 1)));
				c--;
			}
			int k = 1;
			while(!result.isEmpty())
			{
				System.out.printf("%d %s\n", k++, result.pop());
			}
		}
	}
}