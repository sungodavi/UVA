import java.util.*;
import java.io.*;
import java.awt.Point;

public class p10000
{
	static LinkedList<Integer>[] list;
	static Point[] dp;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			int root = Integer.parseInt(f.readLine());
			list = new LinkedList[size + 1];
			dp = new Point[size + 1];
			for(int i = 1; i <= size; i++)
				list[i] = new LinkedList<Integer>();
			for(String input = f.readLine(); input.charAt(0) != '0'; input = f.readLine())
			{
				st = new StringTokenizer(input);
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				list[u].add(v);
			}
			Point result = recurse(root);
			out.printf("Case %d: The longest path from %d has length %d, finishing at %d.\n\n", ++caseNo, root, result.y - 1, result.x);
		}
		out.close();
	}
	
	public static Point recurse(int u)
	{
		if(dp[u] != null)
			return dp[u];
		int best = 0;
		int index = u;
		for(int v : list[u])
		{
			Point p = recurse(v);
			if(p.y > best)
			{
				best = p.y;
				index = p.x;
			}
			else if(p.y == best && p.x < index)
				index = p.x;
		}
		return dp[u] = new Point(index, best + 1);
	}
}