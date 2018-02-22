import java.util.*;
import java.io.*;

public class p11909 {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			double l = Double.parseDouble(st.nextToken());
			double w = Double.parseDouble(st.nextToken());
			double h = Double.parseDouble(st.nextToken());
			double deg = Double.parseDouble(st.nextToken());
			System.out.printf("%.3f mL\n", solve(l, w, h, deg));
		}
	}
	
	public static double solve(double l, double w, double h, double deg)
	{
		double rad = deg * Math.PI / 180;
		double c = Math.atan2(h, l);
		if(rad < c)
			return w * (l * h - l * l * Math.tan(rad) / 2);
		else
			return w * h * h / (2 * Math.tan(rad));
	}
}