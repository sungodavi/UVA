import java.util.*;
import java.io.*;
import java.awt.Point;

public class p663 
{
	static int[] match;
	static boolean[] visited;
	static LinkedList<Integer>[] list;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			out.printf("Heap %d\n", ++caseNo);
			Box[] boxes = new Box[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				boxes[i] = new Box(x1, x2, y1, y2);
			}
			list = new LinkedList[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				list[i] = new LinkedList<Integer>();
				Point p = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				for(int j = 0; j < size; j++)
					if(boxes[j].contains(p))
						list[i].add(j);
			}
			match = new int[size];
			Arrays.fill(match, -1);
			if(solve())
			{
				for(int i = 0; i < size; i++)
				{
					out.printf("(%c,%d)", (char)(i + 'A'), match[i] + 1);
					if(i < size - 1)
						out.print(" ");
				}
				out.println();
			}
			else
				out.println("none");
		}
		out.close();
	}
	
	public static boolean solve()
	{
		for(int i = 0; i < list.length; i++)
		{
			visited = new boolean[list.length];
			if(!augment(i))
				return false;
		}
		return true;
	}
	
	public static boolean augment(int u)
	{
		if(visited[u])
			return false;
		visited[u] = true;
		for(int v : list[u])
			if(match[v] == -1 || augment(match[v]))
			{
				match[v] = u;
				return true;
			}
		return false;
	}
	
	static class Box
	{
		int x1, y1, x2, y2;
		public Box(int x1, int x2, int y1, int y2)
		{
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
		
		public boolean contains(Point p)
		{
			return x1 <= p.x && p.x <= x2 && y1 <= p.y && p.y <= y2;
		}
	}
}