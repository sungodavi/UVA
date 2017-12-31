import java.util.*;
import java.io.*;

public class p10916 
{
	static double epsilon = 1e-9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(int num = Integer.parseInt(f.readLine()); num != 0; num = Integer.parseInt(f.readLine()))
		{
			int e = 1 << ((num - 1960) / 10 + 2);
			double fact = 0;
			int i;
			for(i = 1; e - fact > epsilon; i++)
			{
				fact += Math.log(i) / Math.log(2);
				//System.out.println(fact);
			}
			System.out.println(i - 2);
		}
	}
}
