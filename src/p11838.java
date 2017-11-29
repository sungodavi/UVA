import java.util.*;
import java.io.*;
public class p11838 
{
	static ArrayList<Integer>[] list;
	static int[] dfsLow;
	static int[] dfsNum;
	static boolean[] visited;
	static int counter;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		while(n > 0)
		{
			list = new ArrayList[n];
			dfsNum = new int[n];
			Arrays.fill(dfsNum, n); //undefined
			dfsLow = new int[n];
			Arrays.fill(dfsLow, n); //undefined
			counter = 0;
			visited = new boolean[n];
			for(int i = 1; i <= m; i++)
			{
				st = new StringTokenizer(f.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int p = Integer.parseInt(st.nextToken());
				if(list[start] == null)
					list[start] = new ArrayList<Integer>();
				list[start].add(end);
				if(p == 2)
				{
					if(list[end] == null)
						list[end] = new ArrayList<Integer>();
					list[end].add(start);
				}
				
			}
			findCycles(0);
			if(check())
				System.out.println(1);
			else
				System.out.println(0);
			st = new StringTokenizer(f.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}
	}
	
	static boolean check()
	{
		for(int i = 1; i < dfsNum.length; i++)
			if(dfsNum[i] == dfsLow[i])
				return false;
		return true;
	}
	
	static void findCycles(int r)
	{
		dfsNum[r] = dfsLow[r] = counter++;
		visited[r] = true;
		if(list[r] != null)
			for(int c : list[r])
			{
				if(!visited[c])
					findCycles(c);
				dfsLow[r] = Math.min(dfsLow[r], dfsLow[c]);
			}
	}
}
