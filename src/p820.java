import java.util.*;
import java.io.*;

public class p820
{
	static int[][] a;
	static int[] prev;
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			out.printf("Network %d\n", ++caseNo);
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int roads = Integer.parseInt(st.nextToken());
			a = new int[size + 1][size + 1];
			while(roads-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				a[r][c] += d;
				a[c][r] += d;
			}
			out.printf("The bandwidth is %d.\n\n", edmondKarp(u, v));
		}
		out.close();
	}
	
	public static int edmondKarp(int src, int sink)
	{
		int mf = 0;
		while(true)
		{
			prev = new int[a.length];
			Arrays.fill(prev, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(src);
			while(!q.isEmpty())
			{
				int r = q.poll();
				if(r == sink)
					break;
				for(int c = 0; c < a.length; c++)
				{
					if(a[r][c] > 0 && prev[c] == -1)
					{
						prev[c] = r;
						q.add(c);
					}
				}
			}
			int flow = augment(src, sink, INF);
			if(flow == 0)
				break;
			mf += flow;
		}
		return mf;
	}
	
	public static int augment(int src, int v, int edge)
	{
		if(v == src)
			return edge;
		int u = prev[v];
		if(u == -1)
			return 0;
		int f = augment(src, u, Math.min(edge, a[u][v]));
		a[u][v] -= f;
		a[v][u] += f;
		return f;
	}
}