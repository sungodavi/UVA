import java.util.*;
import java.io.*;

public class p10004 
{
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			list = new ArrayList[size];
			int edges = Integer.parseInt(f.readLine());
			while(edges-- > 0)
			{
				StringTokenizer st = new StringTokenizer(f.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				if(list[start] == null)
					list[start] = new ArrayList<Integer>();
				if(list[end] == null)
					list[end] = new ArrayList<Integer>();
				list[start].add(end);
				list[end].add(start);
			}
			if(bfs())
			{
				System.out.println("BICOLORABLE");
			}
			else
				System.out.println("NOT BICOLORABLE");
		}
	}
	
	static boolean bfs()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		int[] colors = new int[list.length];
		Arrays.fill(colors, -1);
		colors[0] = 0;
		while(!q.isEmpty())
		{
			int r = q.poll();
			if(list[r] != null)
				for(int c : list[r])
				{
					if(colors[c] == colors[r])
						return false;
					else if(colors[c] == -1)
					{
						colors[c] = 1 - colors[r];
						q.add(c);
					}
				}
		}
		return true;
	}
}
