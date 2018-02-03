import java.util.*;
import java.io.*;
import java.awt.Point;
public class p10171
{
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			LinkedList<Path>[] young = new LinkedList[26];
			LinkedList<Path>[] old = new LinkedList[26];
			for(int i = 0; i < size; i++)
			{
				char type = (char)f.read();
				f.read();
				char dir = (char)f.read();
				f.read();
				int u = f.read() - 'A';
				f.read();
				int v = f.read() - 'A';
				f.read();
				int w = Integer.parseInt(f.readLine());
				if(type == 'Y')
				{
					if(young[u] == null)
						young[u] = new LinkedList<Path>();
					young[u].add(new Path(v, w));
					if(dir == 'B')
					{
						if(young[v] == null)
							young[v] = new LinkedList<Path>();
						young[v].add(new Path(u, w));
					}
				}
				else
				{
					if(old[u] == null)
						old[u] = new LinkedList<Path>();
					old[u].add(new Path(v, w));
					if(dir == 'B')
					{
						if(old[v] == null)
							old[v] = new LinkedList<Path>();
						old[v].add(new Path(u, w));
					}
				}
			}
			int u1 = f.read() - 'A';
			f.read();
			int u2 = f.read() - 'A';
			f.readLine();
			int[] d1 = dijkstras(young, u1);
			int[] d2 = dijkstras(old, u2);
			
			int best = min(d1, d2);
			if(best >= INF)
				out.println("You will never meet.");
			else
			{
				out.print(best);
				for(int i = 0; i < d1.length; i++)
					if(d1[i] + d2[i] == best)
						out.print(" " + (char)(i + 'A'));
				out.println();
			}
		}
		out.close();
	}
	
	public static int min(int[] d1, int[] d2)
	{
		int best = d1[0] + d2[0];
		for(int i = 1; i < d1.length; i++)
			if(best > d1[i] + d2[i])
				best = d1[i] + d2[i];
		return best;
	}
	
	public static int[] dijkstras(LinkedList<Path>[] list, int start)
	{
		int[] a = new int[list.length];
		boolean[] visited = new boolean[list.length];
		Arrays.fill(a, INF);
		a[start] = 0;
		Queue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>() {
			public int compare(Point a, Point b)
			{
				return a.y - b.y;
			}
		});
		pq.add(new Point(start, 0));
		while(!pq.isEmpty())
		{
			Point p = pq.poll();
			if(visited[p.x])
				continue;
			visited[p.x] = true;
			if(list[p.x] != null)
			{
				for(Path path : list[p.x])
				{
					if(!visited[path.v])
					{
						a[path.v] = Math.min(a[path.v], a[p.x] + path.w);
						pq.add(new Point(path.v, a[path.v]));
					}
				}
			}
		}
		return a;
	}
	
	static class Path
	{
		int v, w;
		public Path(int v, int w)
		{
			this.v = v;
			this.w = w;
		}
	}
}