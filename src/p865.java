import java.util.*;
import java.io.*;

public class p865
{
	static HashMap<Character, Character> key;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		for(int t = 0; t < times; t++)
		{
			if(t > 0)
				out.println();
			key = new HashMap<Character, Character>();
			String plain = f.readLine();
			String input = f.readLine();
			for(int i = 0; i < plain.length(); i++)
				key.put(plain.charAt(i), input.charAt(i));
			out.println(input);
			out.println(plain);
			for(input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
				out.println(replace(input));
		}
		out.close();
	}
	public static StringBuilder replace(String input)
	{
		StringBuilder s = new StringBuilder(input);
		for(int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if(check(c))
				s.setCharAt(i, key.get(c));
		}
		return s;
	}
	
	public static boolean check(char c)
	{
		return key.containsKey(c);
	}
}