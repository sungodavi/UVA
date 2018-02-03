import java.util.*;
import java.io.*;

public class p10298
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(String input = f.readLine(); !input.equals("."); input = f.readLine())
			out.println(input.length() / period(input));
		out.close();
	}
	
	public static int period(String s)
	{
		for(int size = 1; size < s.length(); size++)
		{
			if(s.length() % size != 0)
				continue;
			boolean found = true;
			for(int i = 0; i < s.length(); i++)
				if(s.charAt(i) != s.charAt(i % size))
				{
					found = false;
					break;
				}
			if(found)
				return size;
		}
		return s.length();
	}
}