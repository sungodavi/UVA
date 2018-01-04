import java.util.*;
import java.io.*;

public class p10382 
{
	static int l, w;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int times = Integer.parseInt(st.nextToken());
			Sprinkler[] a = new Sprinkler[times];
			l = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			for(int i = 0; i < times;i++)
			{
				st = new StringTokenizer(f.readLine());
				int p = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				a[i] = new Sprinkler(p, r);
			}
			Arrays.sort(a);
			double currRight = 0;
			int count = 0;
			int i = 0;
			boolean check = true;
			
			while(currRight < l && check)
			{
				int best = -1;
				check = false;
				int j;
				for(j = i; j < a.length && a[j].left <= currRight; j++)
				{
					check = true;
					if(best < 0 || a[best].right < a[j].right)
					{
						best = j;
					}
				}
				if(best >=0)
				{
					count++;
					currRight = a[best].right;
				}
				i = j;
			}
			if(!check)
				out.println(-1);
			else
				out.println(count);
		}
		out.close();
	}
	
	static class Sprinkler implements Comparable<Sprinkler>
	{
		double left, right;
		public Sprinkler(long p, long r)
		{
			double temp1 = w * w / 4.0;
			double temp2 = r * r;
			double d = Math.sqrt(temp2 - temp1);
			
			left = p - d;
			right = p + d;
		}
		
		public int compareTo(Sprinkler s)
		{
			return Double.compare(left, s.left) == 0 ? Double.compare(s.right, right) : Double.compare(left, s.left);
		}
		
		public String toString()
		{
			return String.format("%.3f %.3f", left, right);
		}
	}
}