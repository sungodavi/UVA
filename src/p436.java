import java.util.*;
import java.io.*;

public class p436 //TURN IN
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(int i = 0; i < size; i++)
				map.put(f.readLine(), i);
			double[][] a = new double[size][size];
			int connections = Integer.parseInt(f.readLine());
			while(connections-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = map.get(st.nextToken());
				double w = Double.parseDouble(st.nextToken());
				int v = map.get(st.nextToken());
				a[u][v] = w;
			}
			out.printf("Case %d: %s\n", ++caseNo, solve(a) ? "Yes" : "No");
			f.readLine();
		}
		out.close();
	}
	
	public static boolean solve(double[][] a)
	{
		int n = a.length;
		double[][] dp = new double[n][n];
		for(int r = 0; r < n; r++)
			for(int c = 0; c < n; c++)
			{
				if(a[r][c] > 0)
					dp[r][c] = a[r][c];
				else if(r == c)
					dp[r][c] = 1;
			}
		
		for(int k = 0; k < n; k++)
			for(int r = 0; r < n; r++)
				for(int c = 0; c < n; c++)
					dp[r][c] = Math.max(dp[r][c], dp[r][k] * dp[k][c]);
		
		for(int r = 0; r < a.length; r++)
			if(dp[r][r] > 1)
				return true;
		return false;
	}
}