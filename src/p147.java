import java.util.*;
import java.io.*;
public class p147 
{
	static int[] coins = {5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); !input.equals("0.00"); input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input, "\\.");
			int size = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			long[] a = new long[size + 1];
			a[0] = 1;
			for(int coin : coins)
			{
				for(int i = coin; i < a.length; i++)
				{
					a[i] += a[i - coin];
				}
			}
			System.out.printf("%6s%17d\n", input, a[size]);
		}
	}
}
