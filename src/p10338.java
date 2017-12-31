import java.util.*;
public class p10338 
{
	static long[] f = new long[21];
	public static void main(String[] args)
	{
		f[0] = 1;
		for(int i = 1; i < f.length; i++)
			f[i] = f[i - 1] * i;
		Scanner scan = new Scanner(System.in);
		int times = scan.nextInt();
		scan.nextLine();
		for(int k = 1; k <= times; k++)
		{
			String s = scan.nextLine();
			int[] counts = new int[26];
			long result = f[s.length()];
			for(char c : s.toCharArray())
				counts[c - 'A']++;
			for(int num : counts)
				result /= f[num];
			System.out.printf("Data set %d: %d\n", k, result);
		}
		
	}

}
