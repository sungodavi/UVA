import java.util.*;
import java.io.*;

public class p10511
{
	static Edge[] prev;
	static LinkedList<Edge>[] list;
	public static void main(String[] args) throws IOException //UNSOLVED
	{
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		for(int t = 0; t < times; t++)
		{
			if(t > 0)
				out.println();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ArrayList<Input> data = new ArrayList<Input>();
			ArrayList<String> parties = new ArrayList<String>();
			ArrayList<String> clubs = new ArrayList<String>();
			ArrayList<String> people = new ArrayList<String>();
			for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
			{
				Input i = new Input(input);
				map.put(i.name, people.size());
				people.add(i.name);
				if(!map.containsKey(i.party))
				{
					map.put(i.party, parties.size());
					parties.add(i.party);
				}
				for(String c : i.clubs)
				{
					if(!map.containsKey(c))
					{
						map.put(c, clubs.size());
						clubs.add(c);
					}
				}
				data.add(i);
			}
			list = new LinkedList[clubs.size() + people.size() + parties.size() + 2];
			for(int i = 0; i < list.length; i++)
				list[i] = new LinkedList<Edge>();
			for(int i = 0; i < clubs.size(); i++)
			{
				Edge e = new Edge(0, i + 1, 1);
				list[0].add(e);
				list[i + 1].add(e);
			}
			for(Input i : data)
			{
				int u = map.get(i.name) + 1 + clubs.size();
				int v = map.get(i.party) + 1 + clubs.size() + people.size();
				Edge e = new Edge(u, v, 1);
				list[u].add(e);
				list[v].add(e);
				for(String c : i.clubs)
				{
					v = map.get(c) + 1;
					e = new Edge(v, u, 1);
					list[u].add(e);
					list[v].add(e);
				}
			}
			int v = list.length - 1;
			int w = clubs.size() >> 1;
			for(int i = 0; i < parties.size(); i++)
			{
				int u = i + 1 + clubs.size() + people.size();
				Edge e = new Edge(u, v, w);
				list[u].add(e);
				list[v].add(e);
			}
			int mf = edmondKarp();
			if(mf != clubs.size())
				out.println("Impossible");
			else
			{
				for(int i = 1; i <= clubs.size(); i++)
				{
					for(Edge e : list[i])
					{
						v = e.v(i);
						if(v > 0 && e.res == 1)
							out.printf("%s %s\n", people.get(v - clubs.size() - 1), clubs.get(i - 1));
					}
				}
			}
		}
		out.close();
	}
	
	public static int edmondKarp()
	{
		int mf = 0;
		while(true)
		{
			prev = new Edge[list.length];
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(0);
			while(!q.isEmpty())
			{
				int u = q.poll();
				if(u == list.length - 1)
					break;
				for(Edge e : list[u])
				{
					int v = e.v(u);
					if(e.w(u) > 0 && prev[v] == null)
					{
						prev[v] = e;
						q.add(v);
					}
				}
			}
			int f = augment(list.length - 1, Integer.MAX_VALUE);
			if(f == 0)
				break;
			mf += f;
		}
		return mf;
	}
	
	public static int augment(int v, int edge)
	{
		if(v == 0)
			return edge;
		Edge e = prev[v];
		if(e == null)
			return 0;
		int u = e.v(v);
		int f = augment(u, Math.min(e.w(u), edge));
		e.update(u, f);
		return f;
	}
	
	static class Edge
	{
		int u, v, w, res;
		public Edge(int u, int v, int w)
		{
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		public int v(int n)
		{
			return n == u ? v : u;
		}
		
		public int w(int n)
		{
			return n == u ? w - res : res;
		}
		
		public void update(int n, int f)
		{
			if(n == u)
				res += f;
			else
				res -= f;
		}
		
		public String toString()
		{
			return u + " " + v + " " + w + " " + res;
		}
	}
	
	static class Input
	{
		String name, party;
		String[] clubs;
		public Input(String s)
		{
			StringTokenizer st = new StringTokenizer(s);
			name = st.nextToken();
			party = st.nextToken();
			clubs = new String[st.countTokens()];
			for(int i = 0; i < clubs.length; i++)
				clubs[i] = st.nextToken();
		}
	}
}