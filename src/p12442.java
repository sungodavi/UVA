import java.util.*;
import java.io.*;

public class p12442 
{
	static int[] graph;
	static int size;
	static boolean[] visited;
	static int[] a;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(int k = 1; k <= times; k++)
		{
			size = Integer.parseInt(f.readLine());
			graph = new int[size + 1];
			a = new int[size + 1];
			for(int i = 0; i < size; i++)
			{
				StringTokenizer st = new StringTokenizer(f.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph[r] = c;
			}
			int index = 0;
			for(int i = 1; i <= size; i++)
			{
				if(a[i] == 0)
				{
					visited = new boolean[size + 1];
					count(i);
				}
				if(a[index] < a[i])
					index = i;
			}
			System.out.printf("Case %d: %d\n", k, index);
		}
	}
	public static int count(int r)
	{
		visited[r] = true;
		int c = graph[r];
		if(!visited[c])
			return a[r] = count(c) + 1;
		else
			return a[r] = 1;
	}
}