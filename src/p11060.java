import java.util.*;
import java.io.*;

public class p11060 //UNSOLVED
{
	static String[] drinks;
	static int[] incoming;
	static ArrayList<Integer>[] list;
	static HashMap<String, Integer> map;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int count = 1;
		for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
		{
			int size = Integer.parseInt(input);
			drinks = new String[size];
			map = new HashMap<String, Integer>();
			list = new ArrayList[size];
			for(int i = 0; i < size; i++)
			{
				drinks[i] = f.readLine();
				map.put(drinks[i], i);
			}
			int links = Integer.parseInt(f.readLine());
			incoming = new int[size];
			for(int i = 1; i <= links; i++)
			{
				StringTokenizer st = new StringTokenizer(f.readLine());
				String start = st.nextToken();
				String end = st.nextToken();
				int index = map.get(start);
				int index2 = map.get(end);
				if(list[index] == null)
					list[index] = new ArrayList<Integer>();
				list[index].add(index2);
				incoming[index2]++;
			}
			System.out.printf("Case #%d: Dilbert should drink beverages in this order:", count++);
			krusts();
			System.out.println();
			f.readLine();
		}
	}
	
	public static void krusts()
	{
		boolean[] visited = new boolean[list.length];
		for(int i = 0; i < list.length; i++)
		{
			int index = minDistance(visited, incoming);
			visited[index] = true;
			System.out.print(" " + drinks[index]);
			if(list[index] != null)
				for(int neighbor : list[index])
				{
					incoming[neighbor]--;
				}
		}
		System.out.println(".");
	}
	
	public static int minDistance(boolean[] visited, int[] incoming)
	{
		int index = -1;
		for(int i = 0; i < visited.length; i++)
		{
			if(!visited[i])
			{
				if(index < 0 || incoming[i] < incoming[index])
					index = i;
					
			}
		}
		return index;
	}
}