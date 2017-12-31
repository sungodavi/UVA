import java.util.*;
import java.io.*;
import java.awt.Point;

public class p516 
{
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException
	{
		load();
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); !input.equals("0"); input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			long num = 1;
			while(st.hasMoreTokens())
			{
				int b = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				num *= (long)Math.pow(b, e);
			}
			num--;
			ArrayList<Point> factors = new ArrayList<Point>();
			for(int i = 0; i < list.size(); i++)
			{
				int p = list.get(i);
				if(p * p > num)
					break;
				int e = 0;
				while(num % p == 0)
				{
					e++;
					num /= p;
				}
				if(e > 0)
					factors.add(new Point(p, e));
			}
			if(num != 1)
				factors.add(new Point((int)num, 1));
			for(int i = factors.size() - 1; i >= 0; i--)
			{
				Point p = factors.get(i);
				System.out.print(p.x + " " + p.y);
				if(i > 0)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static void load()
	{
		boolean[] a = new boolean[32768];
		for(int i = 2; i < a.length; i++)
		{
			if(!a[i])
			{
				list.add(i);
				for(int j = i * i; j < a.length; j += i)
					a[j] = true;
			}
		}
	}
}
