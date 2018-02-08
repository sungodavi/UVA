import java.util.*;
import java.io.*;

public class p10913
{
	static int[][] dp, a;
	static boolean[][] visited;
	static int[] dx = {1, 0, 0};
	static int[] dy = {0, 1, -1};
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		for(String input = f.readLine();; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			if(size == 0)
				break;
			int neg = Integer.parseInt(st.nextToken());
			dp = new int[size][size];
			a = new int[size][size];
			for(int[] temp : dp)
				Arrays.fill(temp, Integer.MAX_VALUE);
			visited = new boolean[size][size];
			for(int r = 0; r < size; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < size; c++)
					a[r][c] = Integer.parseInt(st.nextToken());
			}
			out.printf("Case %d: ", ++caseNo);
			int result = recurse(0, 0, neg);
			if(result == -INF)
				out.println("impossible");
			else
				out.println(result);
		}
		out.close();
	}
	
	public static int recurse(int r, int c, int neg)
	{
		if(r == a.length - 1 && c == a.length - 1)
		{
			if(a[r][c] < 0)
				return neg > 0 ? a[r][c] : -INF;
			return a[r][c];
		}
		if(dp[r][c] != Integer.MAX_VALUE)
			return dp[r][c];
		visited[r][c] = true;
		int best = -INF;
		for(int i = 0; i < dx.length; i++)
		{
			int x = r + dx[i];
			int y = c + dy[i];
			if(x >= 0 && x < a.length && y >= 0 && y < a[0].length && !visited[x][y])
			{
				if(a[x][y] >= 0 || neg > 0)
				{
					int result = recurse(x, y, a[r][c] < 0 ? neg - 1 : neg);
					if(result != -INF)
						best = Math.max(best, result + a[r][c]);
				}
			}
		}
		visited[r][c] = false;
		return dp[r][c] = best;
	}
}