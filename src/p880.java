import java.util.*;
import java.io.*;

public class p880
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(String input = f.readLine(); input != null; input = f.readLine())
			out.println(solve(Long.parseLong(input)));
		out.close();
	}
	
	public static String solve(long num)
	{
		long k = (long)Math.sqrt(num << 1);
		if(k * (k + 1) >= 2 * num)
			k--;
		num -= k * (k + 1) >> 1;
		long top = k + 2 - num;
		long bottom = num;
		return String.format("%d/%d", top, bottom);
	}
}