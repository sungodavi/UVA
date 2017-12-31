import java.util.*;
import java.io.*;

public class p11875 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(int k = 1; k <= times; k++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			ArrayList<Integer> list = new ArrayList<Integer>();
			while(st.hasMoreTokens())
			{
				list.add(Integer.parseInt(st.nextToken()));
			}
			Collections.sort(list);
			System.out.printf("Case %d: %d\n", k, list.get((list.size() + 1) >> 1));
		}
	}
}
