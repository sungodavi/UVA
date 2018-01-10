import java.util.*;
import java.io.*;

public class p10226
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		boolean flag = false;
		while(times-- > 0)
		{
			if(flag)
				out.println();
			else
				flag = true;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			int count = 0;
			for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
			{
				count++;
				if(map.containsKey(input))
					map.put(input, map.get(input) + 1);
				else
					map.put(input, 1);
			}
			for(String key : map.keySet())
				out.printf("%s %.4f\n", key, 100.0 * map.get(key) / count);
		}
		out.close();
	}
}