import java.util.*;
import java.io.*;

public class p196
{
	static int[][] a;
	static String[][] formulas;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			f.readLine();
			st = new StringTokenizer(f.readLine());
			int cols = Integer.parseInt(st.nextToken());
			int rows = Integer.parseInt(st.nextToken());
			a = new int[rows][cols];
			formulas = new String[rows][cols];
			
			for(int r = 0; r < a.length; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < a[0].length; c++)
				{
					String s = st.nextToken();
					//System.out.println(s);
					if(s.charAt(0) == '=')
						formulas[r][c] = s.substring(1);
					else
						a[r][c] = Integer.parseInt(s);
				}
			}
			for(int r = 0; r < a.length; r++)
				for(int c = 0; c < a[0].length; c++)
					if(formulas[r][c] != null)
						a[r][c] = recurse(r, c);
			
			display(a, out);
		}
		out.close();
	}
	
	public static int recurse(int r, int c)
	{
		if(formulas[r][c] == null)
			return a[r][c];
	
		StringTokenizer st = new StringTokenizer(formulas[r][c], "+");
		int sum = 0;
		while(st.hasMoreTokens())
		{
			String temp = st.nextToken();
			int[] pos = convert(temp);
			sum += recurse(pos[0], pos[1]);
		}
		a[r][c] = sum;
		formulas[r][c] = null;
		return sum;
	}
	
	public static int[] convert(String pos)
	{
		int i;
		int e = 0;
		int r = 0;
		for(i = 0; i < pos.length() && Character.isAlphabetic(pos.charAt(i)); i++)
		{
			r += (pos.charAt(i) - 'A' + 1) * (int)Math.pow(26, e++);
		}
		return new int[] {Integer.parseInt(pos.substring(i)) - 1, r - 1};
	}
	
	public static void display(int[][] a, PrintWriter out)
	{
		for(int[] row : a)
		{
			boolean flag = false;
			for(int num : row)
			{
				if(flag)
					out.print(" ");
				else
					flag = true;
				out.print(num);
			}
			out.println();
		}
	}
}