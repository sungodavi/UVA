import java.util.*;
import java.io.*;

public class p11389
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); !input.equals("0 0 0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int[] mornings = new int[n];
			int[] afternoons = new int[n];
			st = new StringTokenizer(f.readLine());
			for(int i = 0; i < n; i++)
				mornings[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			for(int i = 0; i < n; i++)
				afternoons[i] = Integer.parseInt(st.nextToken());
	
			Arrays.sort(mornings);
			Arrays.sort(afternoons);
			
			int result = 0;
			for(int i = 0; i < n; i++)
			{
				int time = mornings[i] + afternoons[n - i - 1];
				result += Math.max(0, time - d) * r;
			}
			System.out.println(result);
		}
	}
}