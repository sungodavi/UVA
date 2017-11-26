import java.util.*;
import java.io.*;

public class p10341 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String input = f.readLine();
		while(input != null && !input.isEmpty())
		{
			StringTokenizer st = new StringTokenizer(input);
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			
			double result = find(p,q,r,s,t,u);
			if(result < 0)
				System.out.println("No solution");
			else
				System.out.printf("%.4f\n", result);
			input = f.readLine();
		}
	}
	public static double eval(double p, double q, double r, double s, double t, double u, double x)
	{
		return p * Math.exp(-x) + q * Math.sin(x) + r * Math.cos(x) + s * Math.tan(x) + t * x * x + u;
	}
	
	public static double find(double p, double q, double r, double s, double t, double u)
	{
		double low = 0;
		double high = 1;
		while(high - low >= 1e-15)
		{
			double mid = (low + high) / 2;
			double result = eval(p,q,r,s,t,u,mid);
			//System.out.println(mid + " " + result);
			if(Math.abs(result) <= 1e-9)
				return mid;
			if(result > 0)
				low = mid;
			else
				high = mid;
		}
		return -1;
	}
}