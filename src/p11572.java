import java.util.*;
import java.io.*;

public class p11572
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
			int size = Integer.parseInt(f.readLine());
			int max = 0;
			int start = 0;
			for(int i = 0; i < size; i++)
			{
				int num = Integer.parseInt(f.readLine());
				if(map.containsKey(num))
				{
					max = Math.max(max, i - start);
					start = Math.max(start, map.get(num) + 1);
					//System.out.println(max);
				}
				map.put(num, i);
			}
			out.println(Math.max(max, size - start));
		}
		out.close();
	}
}