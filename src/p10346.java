import java.util.*;
import java.io.*;

public class p10346 
{
	public static void main(String[] args) throws IOException
	{
		//System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			System.out.println(solve(a, b));
		}
	}
	public static long solve(long a, long b)
	{
		long count = 0;
		long buts = 0;
		while(a > 0)
		{
			count += a;
			buts += a;
			a = buts / b;
			buts %= b;
		}
		return count;
	}

}
