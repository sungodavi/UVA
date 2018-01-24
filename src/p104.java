import java.util.*;
import java.io.*;

public class p104
{
	public static void main(String[] args) throws IOException
	{
		//BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		outer:
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int size = Integer.parseInt(input);
			double[][][] dp = new double[size][size][size];
			double[][] a = new double[size][size];
			int[][][] next = new int[size + 1][size][size];
			for(int r = 0; r < size; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < size; c++)
				{
					if(r == c)
						dp[0][r][c] = a[r][c] = 1;
					else
						dp[0][r][c] = a[r][c] = Double.parseDouble(st.nextToken());
				}
			}
			
			for(int n = 0; n < size - 1; n++)
			{
				for(int k = 0; k < size; k++)
				{
					for(int r = 0; r < size; r++)
					{
						for(int c = 0; c < size; c++)
						{
							if(dp[n + 1][r][c] < dp[n][k][c] * a[r][k])
							{
								dp[n + 1][r][c] = dp[n][k][c] * a[r][k];
								next[n + 1][r][c] = k;
							}
						}
					}
				}
				
				for(int r = 0; r < size; r++)
					if(dp[n + 1][r][r] >= 1.01)
					{
						System.out.println(printPath(dp, next, n + 1, r));
						continue outer;
					}
				
			}
			
			System.out.println("no arbitrage sequence exists");
		}
	}
	public static String printPath(double[][][] dp, int[][][] next, int k, int r)
	{
		StringBuilder s = new StringBuilder();
		int c = r;
		s.append(r + 1);
		while(k > 0)
		{
			s.append(' ');
			s.append(1 + (r = next[k--][r][c]));
			
		}
		s.append(' ');
		s.append(c + 1);
		return s.toString();
	}
}