import java.util.*;
import java.io.*;

public class p11060 //UNSOLVED
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
		{
			int size = Integer.parseInt(input);
			String[] drinks = new String[size];
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ArrayList<String>[] list = new ArrayList[size];
			for(int i = 0; i < size; i++)
			{
				drinks[i] = f.readLine();
				map.put(drinks[i], i);
			}
			int links = Integer.parseInt(f.readLine());
			for(int i = 1; i <= links; i++)
			{
				StringTokenizer st = new StringTokenizer(f.readLine());
				String start = st.nextToken();
				String end = st.nextToken();
				int index = map.get(start);
				if(list[index] == null)
					list[index] = new ArrayList<String>();
				list[index].add(end);
			}
			Comparator<String> C = new C(map);
			for(ArrayList<String> a : list)
				Collections.sort(a, C);
		}
	}
	
	static class C implements Comparator<String>
	{
		HashMap<String, Integer> map;
		public C(HashMap<String, Integer> map)
		{
			this.map = map;
		}
		
		public int compare(String a, String b)
		{
			return map.get(a) - map.get(b);
		}
	}
}