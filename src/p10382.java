import java.util.*;
import java.io.*;

public class p10382 
{
	static int l, w;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input = f.readLine();
		while(input != null && !input.isEmpty())
		{
			st = new StringTokenizer(input);
			int times = Integer.parseInt(st.nextToken());
			ArrayList<Sprinkler> list = new ArrayList<Sprinkler>(times);
			l = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			for(int i = 0; i < times;i++)
			{
				st = new StringTokenizer(f.readLine());
				int p = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				list.add(new Sprinkler(p,r));
			}
			Collections.sort(list);
			for(int i = list.size() - 2; i >= 0; i--)
			{
				if(list.get(i).contains(list.get(i + 1)))
					list.remove(i + 1);
			}
			System.out.println(list);
			int count =1;
			if(list.get(0).left > 0)
			{
				System.out.println(-1);
				input = f.readLine();
				continue;
			}
			boolean found = true;
			double currRight = list.get(0).right;
			for(int i = 1; i < list.size(); i++)
			{
				if(currRight < list.get(i).left)
				{
					found = false;
					break;
				}
				while(i < list.size() && currRight >= list.get(i).left)
					++i;

				//System.out.println(currRight + " " + a[i]);
				if(i != list.size())
				{
					count++;
					currRight = list.get(i).right;
				}
			}
			if(!found && currRight < l)
				System.out.println(-1);
			else
				System.out.println(count);
			input = f.readLine();
		}
	}
	
	static class Sprinkler implements Comparable<Sprinkler>
	{
		double left, right;
		public Sprinkler(int p, int r)
		{
			double temp1 = w * w / 4.0;
			double temp2 = r * r;
			double d = Math.sqrt(temp2 - temp1);
			if(Double.isNaN(d))
				left = right = p;
			else
			{
				left = Math.max(p - d, 0);
				right = Math.min(p + d, l);
			}
		}
		
		public int compareTo(Sprinkler s)
		{
			if(left == s.left)
				return s.right == right ? 0 : s.right < right ? -1 : 1;
			return left < s.left ? -1 : 1;
		}
		
		public boolean contains(Sprinkler s)
		{
			return left <= s.left && right >= s.right;
		}
		public String toString()
		{
			return String.format("%.3f %.3f", left, right);
		}
	}
}