import java.util.*;
import java.io.*;

public class p105
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int min = Integer.MAX_VALUE;
		int max = 0;
		int[] skyline = new int[10000];
		for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int left = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			min = Integer.min(min, left);
			max = Integer.max(max, right);
			for(int i = left; i <= right; i++)
				if(skyline[i] < h)
					skyline[i] = h;
		}
		int h = skyline[min];
		System.out.print(min + " " + h);
		for(int i = min + 1; i <= max; i++)
			if(skyline[i] != h)
			{
				System.out.printf(" %d %d", i - (h > skyline[i] ? 1 : 0), h = skyline[i]);
			}
		System.out.println(" " + max + " 0");
	}
}