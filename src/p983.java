import java.util.*;
import java.io.*;

public class p983 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		boolean flag = false;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			if(flag)
				out.println();
			else
				flag = true;
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] a = new int[n][n];
			for(int r = n - 1; r >= 0; r--)
			{
				for(int c = 0; c < n; c++)
				{
					a[r][c] = Integer.parseInt(f.readLine());
					if(r < n - 1)
						a[r][c] += a[r + 1][c];
				}
			}
			long sum = 0;
			for(int r = n - m; r >= 0; r--)
			{
				long blur = 0;
				for(int c = 0; c < n; c++)
				{
					blur += a[r][c] - (r != n - m ? a[r + m][c] : 0);
					if(c >= m - 1)
					{
						if(c > m - 1)
							blur -= a[r][c - m] - (r != n - m ? a[r + m][c - m] : 0);
						out.println(blur);
						sum += blur;
					}
				}
			}
			out.println(sum);
			f.readLine();
		}
		out.close();
	}
}