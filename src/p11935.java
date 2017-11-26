import java.util.*;
import java.io.*;

public class p11935 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		st.nextToken(); st.nextToken(); st.nextToken();
		double rate = Integer.parseInt(st.nextToken());
		
		while(rate > 0)
		{
			double distance = 0;
			double leaks = 0;
			double best = 0;
			double gallons = 0;
			outer:
			while(true)
			{
				st = new StringTokenizer(f.readLine());
				double newDistance = Integer.parseInt(st.nextToken());
				String s = st.nextToken();
				gallons += (newDistance - distance) * (leaks * 100 + rate) / 100;
				distance = newDistance;
				if(s.equals("Goal")) //Goal
				{
					break;
				}
				else if(s.equals("Fuel")) //Fuel Consumption
				{
					st.nextToken();
					rate = Integer.parseInt(st.nextToken());
				}
				else if(s.equals("Gas")) //Gas Station
				{
					st.nextToken();
					best = Math.max(best, gallons);
					gallons = 0;
				}
				else if(s.equals("Leak")) //Leak
				{
					leaks++;
				}
				else //Mechanic
				{
					leaks = 0;
				}
			}
			
			System.out.printf("%.3f\n", Math.max(best, gallons));
			st = new StringTokenizer(f.readLine());
			st.nextToken(); st.nextToken(); st.nextToken();
			rate = Integer.parseInt(st.nextToken());
		}
	}
}