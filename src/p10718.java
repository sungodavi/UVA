import java.util.*;
import java.io.*;

public class p10718 
{
	static final long INF = Long.MAX_VALUE;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			long n = Long.parseLong(st.nextToken());
			long start = Long.parseLong(st.nextToken());
			long end = Long.parseLong(st.nextToken());
			long target = INF ^ n;

			Stack<Long> bits = getBits(target);
			while(!bits.isEmpty())
			{
				long p = bits.pop();
				if((start | p) <= end)
					start |= p;
			}
			out.println(start);
		}
		out.close();
	}
	
	public static Stack<Long> getBits(long num)
	{
		Stack<Long> st = new Stack<Long>();
		while(num > 0)
		{
			long p = num & -num;
			st.add(p);
			num -= p;
		}
		return st;
	}
}