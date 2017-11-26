import java.util.*;
import java.io.*;

public class p183 
{
	static StringBuilder result;
	static int[][] a;
	static int index;
	static String s;
	public static void main(String[] args) throws IOException 
	{
		//System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		char input = (char) f.read();
		while(input != '#')
		{
			f.read();
			StringTokenizer st = new StringTokenizer(f.readLine());
			result = new StringBuilder();
			index = 0;
			if(input == 'B')
			{
				a = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
				for(int r = 0; r < a.length; r++)
					for(int c = 0; c < a[0].length; c++)
					{
						int in = f.read();
						while(in < '0')
							in = f.read();
						a[r][c] = in - '0';
					}
				f.readLine();
				build(0, a.length - 1, 0, a[0].length - 1);
				System.out.printf("D%4d%4d\n", a.length, a[0].length);
				print(result.toString());
			}
			else
			{
				a = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
				s = f.readLine();
				unbuild(0, a.length - 1, 0, a[0].length - 1);
				System.out.printf("B%4d%4d\n", a.length, a[0].length);
				print(create());
			}
			input = (char)f.read();
		}
	}
	
	public static void build(int sr, int er, int sc, int ec)
	{

		if(sr > er || sc > ec)
			return;
		if(sr == er && sc == ec)
		{
			result.append(a[sr][sc]);
			return;
		}
		int num = a[sr][sc];
		boolean flag = true;
		for(int r = sr; r <= er; r++)
			for(int c = sc; c <= ec; c++)
			{
				if(a[r][c] != num)
				{
					flag = false;
					result.append('D');
					int midR = (sr + er) / 2;
					int midC = (sc + ec) / 2;
					build(sr, midR, sc, midC);
					build(sr, midR, midC + 1, ec);
					build(midR + 1, er, sc, midC);
					build(midR + 1, er, midC + 1, ec);
					return;
				}
			}
		result.append(num);
	}
	
	public static void unbuild(int sr, int er, int sc, int ec)
	{
		if(sr > er || sc > ec)
			return;
		char temp = s.charAt(index++);
		if(temp != 'D')
		{
			int num = temp - '0';
			for(int r = sr; r <= er; r++)
				for(int c = sc; c <= ec; c++)
					a[r][c] = num;
		}
		else
		{
			int midR = (sr + er) / 2;
			int midC = (sc + ec) / 2;
			unbuild(sr, midR, sc, midC);
			unbuild(sr, midR, midC + 1, ec);
			unbuild(midR + 1, er, sc, midC);
			unbuild(midR + 1, er, midC + 1, ec);
		}
	}
	
	public static String create()
	{
		char[] result = new char[a.length * a[0].length];
		int index = 0;
		for(int r = 0; r < a.length; r++)
			for(int c = 0; c < a[0].length; c++)
				result[index++] = (char)(a[r][c] + '0');
		return new String(result);
	}
	
	public static void print(String s)
	{
		for(int i = 0; i < s.length(); i += 50)
		{
			System.out.println(s.substring(i, Math.min(s.length(), i + 50)));
		}
	}
}