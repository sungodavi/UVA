import java.util.*;
import java.io.*;

public class p10360 
{
	static int[][] city;
	static int d;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(; times > 0; times --)
		{
			city = new int[1025][1025];
			d = Integer.parseInt(f.readLine());
			int rats = Integer.parseInt(f.readLine());
			for(; rats > 0; rats--)
			{
				StringTokenizer st = new StringTokenizer(f.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				fill(r, c, p);
			}
			int r = 0, c = 0;
			for(int i = 0; i < city.length; i++)
				for(int j = 0; j < city.length; j++)
				{
					if(city[i][j] > city[r][c])
					{
						r = i;
						c = j;
					}
				}
			System.out.printf("%d %d %d\n", r, c, city[r][c]);
			//f.readLine();
		}
	}
	
	public static void fill(int r, int c, int p)
	{
		int maxR = Integer.min(city.length - 1, r + d);
		int maxC = Integer.min(city.length - 1, c + d);
		for(int i = Integer.max(0, r - d); i <= maxR; i++)
			for(int j = Integer.max(0, c - d); j <= maxC; j++)
				city[i][j] += p;
	}
}
