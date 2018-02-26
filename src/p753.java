import java.util.*;
import java.io.*;

public class p753 
{
	static HashMap<String, Integer> ports;
	static HashSet<Integer>[] list;
	static HashMap<String, LinkedList<String>> map;
	static HashSet<String> visited;
	static int[] match;
	public static void main(String[] args) throws IOException //UNSOLVED
	{
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			f.readLine();
			ports = new HashMap<String, Integer>();
			int size = Integer.parseInt(f.readLine());
			for(int i = 0; i < size; i++)
				ports.put(f.readLine(), i);
			map = new HashMap<String, LinkedList<String>>();
			size = Integer.parseInt(f.readLine());
			list = new HashSet[size];
			String[] temp = new String[size];
			for(int i = 0; i < size; i++)
			{
				list[i] = new HashSet<Integer>();
				st = new StringTokenizer(f.readLine());
				st.nextToken();
				temp[i] = st.nextToken(); 
			}
			size = Integer.parseInt(f.readLine());
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				String u = st.nextToken();
				if(!map.containsKey(u))
					map.put(u, new LinkedList<String>());
				map.get(u).add(st.nextToken());				
			}
			for(int i = 0; i < list.length; i++)
			{
				visited = new HashSet<String>();
				fill(list[i], temp[i]);
			}
			System.out.println(ports);
			System.out.println(Arrays.toString(list));
			System.out.println(map);
			int count = list.length;
			match = new int[ports.size()];
			Arrays.fill(match, -1);
			for(int i = 0; i < list.length; i++)
				if(augment(i, new boolean[list.length]))
					count--;
			out.println(count);
		}
		out.close();
	}
	
	public static boolean augment(int u, boolean[] visited)
	{
		if(visited[u])
			return false;
		visited[u] = true;
		for(int v : list[u])
		{
			if(match[v] == -1 || augment(v, visited))
			{
				match[v] = u;
				return true;
			}
		}
		return false;
	}
	
	public static void fill(HashSet<Integer> set, String u)
	{
		if(visited.contains(u))
			return;
		visited.add(u);
		if(ports.containsKey(u))
			set.add(ports.get(u));
		if(map.containsKey(u))
			for(String v : map.get(u))
				fill(set, v);
	}
}