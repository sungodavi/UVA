import java.util.*;
import java.io.*;

public class p259
{
	static final int INF = Integer.MAX_VALUE;
	static int[] prev;
	static int f;
	static int end;
	static int[][] a;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			ArrayList<String> list = new ArrayList<String>();
			while(input != null && !input.isEmpty())
			{
				list.add(input);
				input = f.readLine();
			}
			a = new int[list.size() + 12][list.size() + 12];
			prev = new int[list.size() + 12];
			char[] map = new char[list.size() + 1];
			int sum = 0;
			for(int i = 1; i <= list.size(); i++)
			{
				String s = list.get(i - 1);
				sum += a[0][i] = s.charAt(1) - '0';
				map[i] = s.charAt(0);
				String connections = s.substring(s.indexOf(" ") + 1, s.length() - 1);
				for(char c : connections.toCharArray())
				{
					a[i][(c - '0') + 1 + list.size()] = INF;
				}
			}
			for(int i = list.size() + 1; i < a.length - 1; i++)
				a[i][a.length - 1] = 1;
			int flow = maxFlow();
			
			if(flow != sum)
				System.out.println("!");
			else
			{
				char[] result = new char[10];
				outer:
				for(int i = 0; i < result.length; i++)
				{
					int r = list.size() + 1 + i;
					for(int c = 1; c <= list.size(); c++)
						if(a[r][c] > 0)
						{
							result[i] = map[c];
							continue outer;
						}
					result[i] = '_';
				}
				System.out.println(new String(result));
			}
		}
	}
	
	static int maxFlow()
	{
		int mf = 0;
		while(true)
		{
			f = 0;
			Arrays.fill(prev, -1);
			prev[0] = 0;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(0);
			while(!q.isEmpty())
			{
				int r = q.poll();
				if(r == a.length - 1)
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
			augment(a.length - 1, INF);
			if(f == 0)
				break;
			mf += f;
		}
		return mf;
	}
	
	public static void augment(int v, int minEdge)
	{
		if(v == 0)
			f = minEdge;
		else
		{
			int u = prev[v];
			if(u != -1)
			{
				augment(u, Math.min(minEdge, a[u][v]));
				a[u][v] -= f;
				a[v][u] += f;
			}
		}
	}
}