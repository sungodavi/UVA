import java.util.*;
import java.io.*;

public class p102 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			int[][] trash = new int[3][3];
			for(int r = 0; r < trash.length; r++)
				for(int c = 0; c < trash.length; c++)
					trash[r][c] = Integer.parseInt(st.nextToken());
			int[] a = {0, 1, 2};
			int best = Integer.MAX_VALUE;
			String key = "";
			for(int i = 1; i <= 6; i++, next(a))
			{
//				System.out.println(Arrays.toString(a) + " " + count(trash, a));
				int count = count(trash, a);
				String s = convert(a);
				if(count < best)
				{
					best = count;
					key = s;
				}
				else if(count == best && s.compareTo(key) < 0)
				{
					key = s;
				}
			}
			System.out.println(key + " " + best);
		}
	}
	
	public static String convert(int[] a)
	{
		char[] temp = {'B', 'G', 'C'};
		char[] result = new char[3];
		for(int i = 0; i < 3; i++)
			result[i] = temp[a[i]];
		return new String(result);
	}
	public static void next(int[] a)
	{
		int j = a.length - 2;
		int l = a.length - 1;
		while(j > 0 && a[j] > a[j + 1])
			j--;
		while(l > 0 && a[j] > a[l])
			l--;
		swap(a, j, l);
		l = a.length - 1;
		int k = j + 1;
		while(k < l)
		{
			swap(a, k, l);
			k++;
			l--;
		}
	}
	
	public static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static int count(int[][] trash, int[] a)
	{
		int count = 0;
		for(int k = 0; k < a.length; k++)
		{
			for(int c = 0; c < trash.length; c++)
			{
				if(a[k] != c)
					count += trash[k][c];
			}
		}
		return count;
	}
}
