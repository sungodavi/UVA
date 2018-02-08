import java.util.*;

public class EulerianPath 
{
	static LinkedList<Edge>[] list;
	static LinkedList<Edge> path;
	public static void main(String[] args) //Page 204
	{
		list = new LinkedList[5];
		for(int i = 0; i < list.length; i++)
			list[i] = new LinkedList<Edge>();
		Edge e01 = new Edge(0, 1);
		Edge e02 = new Edge(0, 2);
		Edge e12 = new Edge(1, 2);
		Edge e13 = new Edge(1, 3);
		Edge e14 = new Edge(1, 4);
		Edge e23 = new Edge(2, 3);
		Edge e24 = new Edge(2, 4);
		Edge e34 = new Edge(3, 4);
		list[0].addAll(Arrays.asList(new Edge[]{e01, e02}));
		list[1].addAll(Arrays.asList(new Edge[]{e01, e12, e13, e14}));
		list[2].addAll(Arrays.asList(new Edge[]{e02, e12, e23, e24}));
		list[3].addAll(Arrays.asList(new Edge[]{e13, e23, e34}));
		list[4].addAll(Arrays.asList(new Edge[]{e14, e24, e34}));
		
		path = new LinkedList<Edge>();
		
	}
	
	public static void eulerTour(int u)
	{
		if(list[u] != null)
		{
			for(Edge e : list[u])
			{
				if(e.flag)
				{
					e.flag = false;
					path.add(e);
					eulerTour(e.v(u));
				}
			}
		}
	}
	
	static class Edge
	{
		int u, v;
		boolean flag;
		public Edge(int u, int v)
		{
			flag = true;
			this.u = u;
			this.v = v;
		}
		
		public int v(int n)
		{
			return n == u ? v : u;
		}
		
		public String toString()
		{
			return u + " " + v;
		}
	}
}
