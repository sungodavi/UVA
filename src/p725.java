import java.util.*;
import java.io.*;

public class p725 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(f.readLine());
		while(num > 0)
		{
			boolean found = false;
			for(int i = 1234; i <= 98765 / num; i++)
			{
				if(check(i, i * num))
				{
					found = true;
					System.out.printf("%05d / %05d = %d\n", i * num, i, num);
				}
			}
			if(!found)
				System.out.printf("There are no solutions for %d.\n", num);
			num = Integer.parseInt(f.readLine());
			if(num > 0)
				System.out.println();
		}
	}
	
	public static boolean check(int a, int b)
	{
		boolean[] nums = new boolean[10];
		if(a < 10000)
			nums[0] = true;
		while(a > 0)
		{
			nums[a % 10] = true;
			a /= 10;
		}
		while(b > 0)
		{
			nums[b % 10] = true;
			b /= 10;
		}
		for(boolean bool : nums)
			if(!bool)
				return false;
		return true;
	}
}
