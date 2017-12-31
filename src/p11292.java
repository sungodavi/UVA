import java.util.*;
import java.io.*;

public class p11292
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int[] dragons = new int[Integer.parseInt(st.nextToken())];
			int[] knights = new int[Integer.parseInt(st.nextToken())];
			for(int i = 0; i < dragons.length; i++)
				dragons[i] = Integer.parseInt(f.readLine());
			for(int i =0; i < knights.length; i++)
				knights[i] = Integer.parseInt(f.readLine());
			Arrays.sort(dragons);
			Arrays.sort(knights);
			
			try
			{
				int result = 0;
				int index = 0;
				for(int i = 0; i < dragons.length; i++)
				{
					int head = dragons[i];
					while(knights[index] < head)
						index++;
					result += knights[index++];
				}
				System.out.println(result);
			}
			catch(Exception e)
			{
				System.out.println("Loowater is doomed!");
			}
		}
	}
}