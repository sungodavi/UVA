import java.util.*;
import java.io.*;

public class p10176 
{
	final static int mod = 131071;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int result = 0;
			outer:
			while(true)
			{
				if(input == null)
					return;
				for(char c : input.toCharArray())
				{
					if(c == '#')
						break outer;
					result <<= 1;
					if(c == '1')
						result += 1;
					result %= mod;
				}
				input = f.readLine();
			}
			System.out.println(result == 0 ? "YES" : "NO");
		}
	}
}
