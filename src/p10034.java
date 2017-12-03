import java.util.*;
import java.io.*;
import java.awt.geom.Point2D;


public class p10034 
{
	static Point2D[] points;
	static double[][] distances;
	
	public static void main(String[] args) throws IOException
	{
		System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		
		while(times-- > 0)
		{
			f.readLine();
			int size = Integer.parseInt(f.readLine());
			points = new Point2D[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				points[i] = new Point2D.Double(x, y);
			}
			distances = new double[size][size];
			for(int r = 0; r < size; r++)
				for(int c = 0; c < r; c++)
					distances[r][c] = distances[c][r] = points[r].distance(points[c]);
			System.out.printf("%.2f\n", prims());
		}
	}
	
	static double prims()
	{
		double cost = 0;
		Queue<Point> q = new PriorityQueue<Point>();
		for(int c = 1; c < distances.length; c++)
			q.add(new Point(c, distances[0][c]));
		boolean[] visited = new boolean[distances.length];
		visited[0] = true;
		while(!q.isEmpty())
		{
			//System.out.println(q.peek());
			Point p = q.poll();
			if(visited[p.r])
				continue;
			cost += p.cost;
			visited[p.r] = true;
			for(int c = 0; c < distances.length; c++)
				if(p.r != c && !visited[c])
					q.add(new Point(c, distances[p.r][c]));
		}
		return cost;
	}
	
	static class Point implements Comparable<Point>
	{
		int r;
		double cost;
		public Point(int r, double cost)
		{
			this.r = r;
			this.cost = cost;
		}
		
		public int compareTo(Point p)
		{
			double diff = cost - p.cost;
			if(diff < 0)
				return -1;
			if(diff == 0)
				return 0;
			return 1;
		}
		
		public String toString()
		{
			return r + " " + cost;
		}
	}
}
