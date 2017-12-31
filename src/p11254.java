import java.util.*;
import java.io.*;

public class p11254 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(int num = Integer.parseInt(f.readLine()); num >= 0; num = Integer.parseInt(f.readLine()))
		{
			int[] result = solve(num);
			System.out.printf("%d = %d + ... + %d\n", num, result[0], result[1]);
		}
		
	}
	public static int[] solve(int num)
	{
		int max = 1;
		int maxStart = num;
		int tri = 1;
		for(int i = 2; i + tri <= num; tri += i, i++)
		{
			if((num - tri) % i == 0)
			{
				max = i;
				maxStart = (num - tri) / i;
			}
		}
		return new int[]{maxStart, maxStart + max - 1};
	}
}
