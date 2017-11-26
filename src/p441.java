import java.util.*;
import java.io.*;

public class p441 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		while(size > 0)
		{
			int[] nums = new int[size];
			for(int i = 0; i < nums.length; i++)
				nums[i] = Integer.parseInt(st.nextToken());
			
			for(int a = 0; a < nums.length - 5; a++)
				for(int b = a + 1; b < nums.length - 4; b++)
					for(int c = b + 1; c < nums.length - 3; c++)
						for(int d = c + 1; d < nums.length - 2; d++)
							for(int e = d + 1; e < nums.length - 1; e++)
								for(int g = e + 1; g < nums.length; g++)
								{
									System.out.printf("%d %d %d %d %d %d\n", nums[a], 
																			nums[b],
																			nums[c], 
																			nums[d], 
																			nums[e], 
																			nums[g]);
								}
			st = new StringTokenizer(f.readLine());
			size = Integer.parseInt(st.nextToken());
			if(size > 0)
				System.out.println();
		}
	}
}
