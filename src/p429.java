import java.util.*;
import java.io.*;
import java.awt.Point;

public class p429
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		boolean flag = false;
		while(times-- > 0)
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			ArrayList<String> words = new ArrayList<String>();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(String s = f.readLine(); !s.equals("*"); s = f.readLine())
			{
				words.add(s);
				map.put(s, words.size() - 1);
			}
			for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
			{
				st = new StringTokenizer(input);
				String start = st.nextToken();
				String end = st.nextToken();
				int u = map.get(start);
				int v = map.get(end);
				
				Queue<Point> q = new LinkedList<Point>();
				q.add(new Point(u, 0));
				boolean[] visited = new boolean[words.size()];
				while(!q.isEmpty())
				{
					Point p = q.poll();
					if(p.x == v)
					{
						System.out.printf("%s %s %d\n", start, end, p.y);
						break;
					}
					if(visited[p.x])
						continue;
					visited[p.x] = true;
					for(int i = 0; i < words.size(); i++)
						if(!visited[i] && hasEdge(words.get(p.x), words.get(i)))
							q.add(new Point(i, p.y + 1));
				}
			}
		}
	}
	
	public static boolean hasEdge(String a, String b)
	{
		if(a.length() != b.length())
			return false;
		int count = 0;
		for(int i = 0; i < a.length(); i++)
			if(a.charAt(i) != b.charAt(i))
				++count;
		return count == 1;
	}
}