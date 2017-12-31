import java.util.*;
import java.io.*;

public class p10407 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); !input.equals("0"); input = f.readLine())
		{
			ArrayList<Integer> list = new ArrayList<Integer>();
			StringTokenizer st = new StringTokenizer(input);
			outer:
			while(true)
			{
				while(st.hasMoreTokens())
				{
					int num = Integer.parseInt(st.nextToken());
					if(num == 0)
						break outer;
					list.add(num);
				}
				st = new StringTokenizer(f.readLine());
			}
			Collections.sort(list);
			//System.out.println(input);
			int first = list.get(0);
			for(int i = 1; i < list.size(); i++)
				list.set(i, list.get(i) - first);
			int gcd = list.get(1);
			for(int i = 2; i < list.size(); i++)
				gcd = gcd(gcd, list.get(i));
			System.out.println(gcd);
		}
	}
	public static int gcd(int a, int b)
	{
		while(b > 0)
		{
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
}
