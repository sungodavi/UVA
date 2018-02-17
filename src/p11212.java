import java.util.*;
import java.io.*;

public class p11212
{
	static final int INF = (int)1e9;
	static long target;
	static int size;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		size = 5;
		for(size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			build(size);
			st = new StringTokenizer(f.readLine());
			long num = 0;
			while(st.hasMoreTokens())
				num = num * 10 + Long.parseLong(st.nextToken());
			out.printf("Case %d: %d\n", ++caseNo, solve(num));
		}
		out.close();
	}
	
	public static int solve(long num)
	{
		HashMap<Long, Integer> map = bfs(num, 2);
		if(map.containsKey(target))
			return map.get(target);
		long temp = target;
		target = num;
		num = temp;
		HashMap<Long, Integer> map2 = bfs(num, 2);
		Set<Long> keys = map.keySet();
		keys.retainAll(map2.keySet());
		if(keys.isEmpty())
			return 5;
		for(long key : keys)
			if(map.get(key) + map2.get(key) == 3)
				return 3;
		return 4;
	}
	
	public static void build(int size)
	{
		target = 0;
		for(int i = 1; i <= size; i++)
			target = target * 10 + i;
	}
	
	public static HashMap<Long, Integer> bfs(long num, int depth)
	{
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		Queue<State> q = new LinkedList<State>();
		q.add(new State(num, 0));
		while(!q.isEmpty())
		{
			State s = q.poll();
			if(map.containsKey(s.num))
				continue;
			map.put(s.num, s.d);
			if(s.num == target)
				break;
			if(s.d < depth)
			{
				for(int i = 0; i < size; i++)
				{
					for(int j = i; j < size; j++)
					{
						for(int k = 0; k < i; k++)
						{
							long n = move(s.num, i, j, k);
							if(!map.containsKey(n))
								q.add(new State(n, s.d + 1));
						}
						
						for(int k = j + 1; k <= size; k++)
						{
							long n = move(s.num, i, j, k);
							if(!map.containsKey(n))
								q.add(new State(n, s.d + 1));
						}
					}
				}
			}
		}
		return map;
	}
	
	public static int search(long start) //A* search doesn't work check hueristic
	{
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		Queue<State> q = new PriorityQueue<State>();
		q.add(new State(start, 0, h(start)));
		while(!q.isEmpty())
		{
			State s = q.poll();
			if(map.containsKey(s.num) && map.get(s.num) < s.d)
				continue;
			if(s.num == target)
				return s.d;
			map.put(s.num, s.d);
			for(int i = 0; i < size; i++)
			{
				for(int j = i; j < size; j++)
				{
					for(int k = 0; k < i; k++)
					{
						long n = move(s.num, i, j, k);
						if(!map.containsKey(n))
							q.add(new State(n, s.d + 1, h(n)));
					}
					
					for(int k = j + 1; k <= size; k++)
					{
						long n = move(s.num, i, j, k);
						if(!map.containsKey(n))
							q.add(new State(n, s.d + 1, h(n)));
					}
				}
			}
		}
		return -1;
	}
	
	public static int h(long num)
	{
		int result = 0;
		for(int i = 0; i < size - 1; i++, num /= 10)
		{
			long n = num / 10 % 10;
			long d = num % 10;
			if(n + 1 != d)
				result++;
		}
		return result;
	}
	
	public static LinkedList<Integer> convert(long num)
	{
		LinkedList<Integer> list = new LinkedList<Integer>();
		while(num > 0)
		{
			list.add((int)(num % 10));
			num /= 10;
		}
		return list;
	}
	
	public static int convert(LinkedList<Integer> list)
	{
		int num = 0;
		int e = 1;
		for(int n : list)
		{
			num += e * n;
			e *= 10;
		}
		return num;
	}
	
	public static long move(long num, int start, int end, int pos)
	{
		LinkedList<Integer> list = convert(num);
		ListIterator<Integer> iter = list.listIterator(start);
		LinkedList<Integer> temp = new LinkedList<Integer>();
		int len = end - start + 1;
		for(int i = 0; i < len; i++)
		{
			temp.add(iter.next());
			iter.remove();
		}
		iter = list.listIterator(pos > end ? pos - len : pos);
		for(int n : temp)
			iter.add(n);
		return convert(list);
	}
	
	static class State implements Comparable<State>
	{
		long num;
		int d, h;
		public State(long num, int d)
		{
			this.num = num;
			this.d = d;
		}
		
		public State(long num, int d, int h)
		{
			this.num = num;
			this.d = d;
			this.h = h;
		}
		
		public int compareTo(State s)
		{
			return d + h - (s.d + s.h);
		}
	}
}