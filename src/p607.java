import java.util.*;
import java.io.*;

public class p607
{
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int caseNo = 0;
		boolean flag = false;
		for(int topics = Integer.parseInt(f.readLine()); topics > 0; topics = Integer.parseInt(f.readLine()))
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			st = new StringTokenizer(f.readLine());
			int length = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			int[] a = new int[topics];
			for(int i =0; i < a.length; i++)
				a[i] = Integer.parseInt(f.readLine());
			
			System.out.printf("Case %d:\n"
					+ "Minimum number of lectures: %d\n"
					+ "Total dissatisfaction index: %d\n", ++caseNo, s.curr, s.di);
		}
	}
	
	
	public static long calcDI(int length, int curr, int c)
	{
		if(length - curr <= 10 && length != curr)
			return c;
		else
			return (length - curr - 10) * (length - curr - 10);
	}
}