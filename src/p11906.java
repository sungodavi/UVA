import java.util.*;
import java.io.*;

public class p11906 //UNSOLVED
{
	static boolean[][] water;
	static int even, odd;
	static int[] dx;
	static int[] dy;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			dx = new int[]{m, -m, n, -n};
			dy = new int[]{n, -n, m, -m};
			even = odd = 0;
			water = new boolean[r][c];

			for(int k = Integer.parseInt(f.readLine()); k > 0; k--)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				water[x][y] = true;
			}
		}
	}
}
