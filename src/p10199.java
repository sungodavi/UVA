import java.util.*;
import java.io.*;

public class p10199 
{
	static LinkedList<Integer>[] list;
	static TreeSet<Integer> set;
	static int[] low, num, parent;
	static int counter;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int caseNo = 0;
		boolean flag = false;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			if(flag)
				out.println();
			else
				flag = true;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			String[] indexes = new String[size];
			for(int i = 0; i < size; i++)
				indexes[i] = f.readLine();
			Arrays.sort(indexes);
			for(int i = 0; i < size; i++)
				map.put(indexes[i], i);
			int roads = Integer.parseInt(f.readLine());
			list = new LinkedList[size];
			for(int i = 0; i < size; i++)
				list[i] = new LinkedList<Integer>();
			while(roads-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = map.get(st.nextToken());
				int v = map.get(st.nextToken());
				list[u].add(v);
				list[v].add(u);
			}
			init(size);
			for(int i = 0; i < size; i++)
				if(num[i] == -1)
					dfs(i);
			out.printf("City map #%d: %d camera(s) found\n", ++caseNo, set.size());
			for(int k : set)
				out.println(indexes[k]);
		}
		out.close();
	}
	
	public static void init(int size)
	{
		low = new int[size];
		num = new int[size];
		parent = new int[size];
		counter = 0;
		set = new TreeSet<Integer>();
		for(int i = 0; i < size; i++)
			num[i] = parent[i] = -1;
	}
	
	public static void dfs(int u)
	{
		num[u] = low[u] = counter++;
		int children = 0;
		for(int v : list[u])
		{
			if(num[v] == -1)
			{
				children++;
				parent[v] = u;
				dfs(v);
				if(parent[u] != -1 && low[v] >= num[u])
				{
					set.add(u);
				}
				low[u] = Math.min(low[u], low[v]);
			}
			else if(parent[u] != v)
				low[u] = Math.min(low[u], num[v]);
		}
		if(parent[u] == -1 && children > 1)
		{
			set.add(u);
		}
	}
}