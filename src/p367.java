import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p367
{
	static Rule[] rules;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			out.printf("FRS #%d:\n", ++caseNo);
			rules = new Rule[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				rules[i] = new Rule(new BigInteger(st.nextToken()), new BigInteger(st.nextToken()));
			}
			int queries = Integer.parseInt(f.readLine());
			while(queries-- > 0)
			{
				BigInteger num = new BigInteger(f.readLine());
				out.printf("     %s becomes %s\n", num, convert(num));
			}
			out.println();
		}
		out.close();
	}
	
	public static BigInteger convert(BigInteger num)
	{
		boolean found = false;
		while(!found)
		{
			found = true;
			for(Rule p : rules)
			{
				if(num.mod(p.x).equals(BigInteger.ZERO))
				{
					num = num.divide(p.x).multiply(p.y);
					found = false;
					break;
				}
			}
		}
		return num;
	}
	
	static class Rule
	{
		BigInteger x, y;
		public Rule(BigInteger x, BigInteger y)
		{
			this.x = x;
			this.y = y;
		}
	}
}