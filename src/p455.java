import java.util.*;
import java.io.*;

public class p455
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int times = Integer.parseInt(f.readLine());
		boolean flag = false;
		while(times-- > 0)
		{
			f.readLine();
			if(flag)
				out.println();
			else
				flag = true;
			out.println(period(f.readLine()));
		}
		out.close();
	}
	
	public static int period(String s)
	{
		for(int size = 1; size < s.length(); size++)
		{
			boolean found = true;
			if(s.length() % size != 0)
				continue;
			for(int i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) != s.charAt(i % size))
				{
					found = false;
					break;
				}
			}
			if(found)
				return size;
		}
		return s.length();
	}
}