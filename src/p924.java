import java.util.*;
import java.io.*;
import java.awt.Point;

public class p924
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		int size = Integer.parseInt(f.readLine());
		LinkedList<Integer>[] list = new LinkedList[size];
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			st.nextToken();
			list[i] = new LinkedList<Integer>();
			while(st.hasMoreTokens())
				list[i].add(Integer.parseInt(st.nextToken()));
		}
		
		int queries = Integer.parseInt(f.readLine());
		while(queries-- > 0)
		{
			int u = Integer.parseInt(f.readLine());
			Queue<Point> q = new LinkedList<Point>();
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			q.add(new Point(u, 0));
			int max = 0;
			int time = 0;
			boolean[] visited = new boolean[size];
			while(!q.isEmpty())
			{
				Point p = q.poll();
				if(visited[p.x])
					continue;
				visited[p.x] = true;
				if(map.containsKey(p.y))
					map.put(p.y, map.get(p.y) + 1);
				else
					map.put(p.y, 1);
				int curr = map.get(p.y);
				if(curr > max && p.x != u)
				{
					max = curr;
					time = p.y;
				}
				for(int v : list[p.x])
				{
					if(!visited[v])
					{
						q.add(new Point(v, p.y + 1));
					}
				}
			}
			if(max == 0)
				System.out.println(0);
			else
				System.out.printf("%d %d\n", max, time);
		}
	}
}