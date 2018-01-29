import java.util.*;
import java.io.*;

public class p200 
{
	static LinkedList<Integer>[] list;
	static LinkedList<Integer>[] reverse;
	static Stack<Character> order = new Stack<Character>();
	static boolean[] visited;
	static char[] indexes = new char[26];
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int[] map = new int[26];
		Arrays.fill(map, -1);
		int count = 0;
		ArrayList<String> words = new ArrayList<String>();
		for(String input = f.readLine(); !input.equals("#"); input = f.readLine())
		{
			for(int i = 0; i < input.length(); i++)
			{
				char c = input.charAt(i);
				if(map[c - 'A'] == -1)
				{
					map[c - 'A'] = count;
					indexes[count++] = c;
				}
			}
			words.add(input);
		}
		reverse = new LinkedList[count];
		list = new LinkedList[count];
		visited = new boolean[count];
		for(int i = 0; i < words.size() - 1; i++)
		{
			String s1 = words.get(i);
			String s2 = words.get(i + 1);
			for(int k = 0; k < Math.min(s1.length(), s2.length()); k++)
			{
				if(s1.charAt(k) != s2.charAt(k))
				{
					int u = map[s1.charAt(k) - 'A'];
					int v = map[s2.charAt(k) - 'A'];
					if(list[u] == null)
						list[u] = new LinkedList<Integer>();
					if(reverse[v] == null)
						reverse[v] = new LinkedList<Integer>();
					list[u].add(v);
					reverse[v].add(u);
					break;
				}
			}
		}
		int root = root(0);
		visited = new boolean[count];
		topoSort(root);
		while(!order.isEmpty())
			out.print(order.pop());
		out.println();
		out.close();
	}
	
	public static void topoSort(int u)
	{
		if(visited[u])
			return;
		visited[u] = true;
		if(list[u] != null)
		{
			for(int v : list[u])
				topoSort(v);
		}
		order.push(indexes[u]);
	}
	public static int root(int u)
	{
		if(visited[u])
			return -1;
		visited[u] = true;
		if(reverse[u] == null)
			return u;
		for(int v : reverse[u])
		{
			int result = root(v);
			if(result >= 0)
				return result;
		}
		return -1;
	}
}