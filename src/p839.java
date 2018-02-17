import java.util.*;
import java.io.*;

public class p839 
{
	static ArrayList<int[]> nodes;
	static int index;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		
		for(int t = 0; t < times; t++)
		{
			if(t > 0)
				out.println();
			nodes = new ArrayList<int[]>();
			for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
				nodes.add(load(input));
			index = 0;
			out.println(check() != -1 ? "YES" : "NO");
		}
		out.close();
	}
	
	public static int[] load(String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		int[] a = new int[4];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(st.nextToken());
		return a;
	}
	public static int check()
	{
		int[] a = nodes.get(index);
		int wl, wr;
		if(a[0] == 0)
		{
			index++;
			wl = check();
			if(wl == -1)
				return -1;
		}
		else
			wl = a[0];
		if(a[2] == 0)
		{
			index++;
			wr = check();
			if(wr == -1)
				return -1;
		}
		else
			wr = a[2];
		return wl * a[1] == wr * a[3] ? wl + wr : -1;				
	}
}