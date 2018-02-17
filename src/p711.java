import java.util.*;
import java.io.*;

public class p711
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 1;
		for(String input = f.readLine(); !input.equals("0 0 0 0 0 0"); input = f.readLine(), caseNo++)
		{
			st = new StringTokenizer(input);
			int[] a = new int[6];
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			out.printf("Collection #%d:\n%s be divided.\n\n", caseNo, check(a) ? "Can" : "Can't");
		}
		out.close();
	}
	
	public static boolean check(int[] a)
	{
		int sum = sum(a);
		if(sum % 2 != 0)
			return false;
		boolean[] dp = new boolean[(sum >> 1) + 1];
		dp[0] = true;
		for(int i = 0; i < a.length; i++)
		{
			int c = i + 1;
			for(int times = 0; times < a[i]; times++)
				for(int k = dp.length - 1; k >= c; k--)
					if(dp[k - c])
					{
						dp[k] = true;
						if(k == a.length - 1)
							return true;
					}
		}
		return dp[dp.length - 1];
	}
	
	public static int sum(int[] a)
	{
		int sum = 0;
		for(int i =0; i < a.length; i++)
			sum += (i + 1) * a[i];
		return sum;
	}
}