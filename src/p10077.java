import java.util.*;
import java.io.*;

public class p10077 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(String input = f.readLine(); !input.equals("1 1"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Fraction target = new Fraction(n, d);
			Fraction curr = new Fraction(1, 1);
			Fraction left = new Fraction(0, 1);
			Fraction right = new Fraction(1, 0);
			StringBuilder result = new StringBuilder();
			while(!curr.equals(target))
			{
				//System.out.println(left + " " + curr + " " + right + " " + result);
				if(curr.compareTo(target) > 0)
				{
					Fraction temp = curr;
					curr = curr.add(left);
					right = temp;
					result.append('L');
				}
				else
				{
					Fraction temp = curr;
					curr = curr.add(right);
					left = temp;
					result.append('R');
				}
			}
			out.println(result);
		}
		out.close();
	}
	
	static class Fraction
	{
		int n, d;
		public Fraction(int n, int d)
		{
			this.n = n;
			this.d = d;
		}
		
		public Fraction add(Fraction f)
		{
			return new Fraction(n + f.n, d + f.d);
		}
		
		public int compareTo(Fraction f)
		{
			return n * f.d - d * f.n;
		}
		
		public boolean equals(Fraction f)
		{
			return compareTo(f) == 0;
		}
		
		public String toString()
		{
			return n + "/" + d;
		}
	}
}