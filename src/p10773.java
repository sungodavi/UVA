import java.util.*;
import java.io.*;

public class p10773 
{
	static double epsilon = 1e-9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(int k = 1; k <= times; k++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int d = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			System.out.printf("Case %d: ", k);
			try
			{
				double theta = Math.asin((double) v / u);
				//System.out.println(theta);
				double cos = Math.cos(theta);
				if(cos < epsilon || u == 0)
					throw new Exception();
				double diff = (double) d / u * (1 / cos - 1);
				//System.out.println(diff);
				if(diff > epsilon)
					System.out.printf("%.3f\n", diff);
				else
					throw new Exception();
			}
			catch (Exception e)
			{
				System.out.println("can't determine");
			}
		}
	}
}
