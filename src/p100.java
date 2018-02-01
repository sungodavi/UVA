import java.util.*;
import java.io.*;

public class p100
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			System.out.printf("%d %d %d\n", s, e, solve(s, e));
		}
		out.close();
	}
	
	public static int solve(int s, int e)
	{
		if(s > e)
		{
			int temp = s;
			s = e;
			e = temp;
		}
		int result = 0;
		for(int i = s; i <= e; i++)
			result = Math.max(result, collatz(i));
		return result;
	}
	
	public static int collatz(long num)
	{
		int count = 1;
		while(num != 1)
		{
			if((num & 1) == 0)
				num >>= 1;
			else
				num = num * 3 + 1;
			count++;
		}
		return count;
	}
}