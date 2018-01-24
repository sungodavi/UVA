import java.util.*;
import java.io.*;

public class p11417
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int num = Integer.parseInt(f.readLine()); num > 0; num = Integer.parseInt(f.readLine()))
		{
			int sum = 0;
			for(int i = 1; i < num; i++)
				for(int j = i + 1; j <= num; j++)
					sum += gcd(i, j);
			out.println(sum);
		}
		out.close();
	}
	
	public static int gcd(int x, int y)
	{
		while(y > 0)
		{
			int temp = y;
			y = x % y;
			x = temp;
		}
		return x;
	}
}