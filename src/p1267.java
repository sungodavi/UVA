import java.util.*;
import java.io.*;
import java.awt.Point;

public class p1267 //UNSOLVED
{
	static boolean[] connected;
	static int[] a;
	static ArrayList<Integer>[] list;
	static int best;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		StringTokenizer st;
		while(times-- > 0)
		{
			best = 0;
			int size = Integer.parseInt(f.readLine());
			list = new ArrayList[size];
			for(int r = 0; r < list.length; r++)
				list[r] = new ArrayList<Integer>();
			connected = new boolean[size];
			
			st = new StringTokenizer(f.readLine());
			int server = Integer.parseInt(st.nextToken()) - 1;
			int range = Integer.parseInt(st.nextToken());

			int count = 0;
			for(int i = 1; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				int r = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				list[r].add(c);
				list[c].add(r);
			}
			//System.out.println("Data:");
			//System.out.println(server + " " + range);
			//System.out.println(Arrays.toString(list));
			//System.out.println("-----");
			int totalConnected = connect(server, range);
			for(ArrayList<Integer> temp : list)
				if(temp.size() > 1)
					totalConnected++;
			while(totalConnected < size)
			{
				a = new int[size];
				for(int i =0; i < size; i++)
					if(list[i].size() == 1 && !connected[i])
					{
						fill(i, range);
					}
				totalConnected += connect(best, range);
				count++;
			}
			System.out.println(count);
		}
	}
	
	public static int connect(int a, int b)
	{
		boolean[] visited = new boolean[list.length];
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(a, b));
		int count = 0;
		while(!q.isEmpty())
		{
			Point p = q.poll();
			int r = p.x;
			int d = p.y;
			if(visited[r])
				continue;
			visited[r] = true;
			if(list[r].size() == 1)
			{
				count++;
				connected[r] = true;
			}
			if(d > 0)
				for(int c : list[r])
					q.add(new Point(c, d - 1));
		}
		return count;
	}
	public static void fill(int x, int b)
	{
		boolean[] visited = new boolean[list.length];
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x, b));
		while(!q.isEmpty())
		{
			Point p = q.poll();
			int r = p.x;
			int d = p.y;
			if(visited[r])
				continue;
			visited[r] = true;
			if(list[r].size() > 1)
			{
				a[r]++;
				if(a[r] > a[best])
					best = r;
			}
			if(d > 0)
				for(int c : list[r])
					q.add(new Point(c, d - 1));
		}
	}
} 