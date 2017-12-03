import java.util.*;
import java.io.*;
import java.awt.Point;
public class p821 
{
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int count = 1;
		for(String s = f.readLine(); !s.equals("0 0"); s = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(s);
			int max = 0;
			int[] map = new int[100];
			int size = 0;
			Arrays.fill(map, -1);
			ArrayList<Point> edges = new ArrayList<Point>();
			TreeSet<Integer> set = new TreeSet<Integer>();
			while(st.hasMoreTokens())
			{
				int r = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				if(r < 0 || c < 0)
					break;
				if(map[r] < 0)
				{
					map[r] = size++;
				}
				if(map[c] < 0)
				{
					map[c] = size++;
				}
				edges.add(new Point(r, c));
			}
			int[][] a = new int[size][size];
			for(int r = 0; r < a.length; r++)
				for(int c =0 ; c < a.length; c++)
					if(r != c)
						a[r][c] = (int)1e9;
			
			for(Point p : edges)
				a[map[p.x]][map[p.y]] = 1;
			
			for(int k =0; k < a.length; k++)
				for(int r = 0; r < a.length; r++)
					for(int c =0; c < a.length; c++)
						a[r][c] = Math.min(a[r][c], a[r][k] + a[k][c]);
			
			double avg = 0;
			int d =0;
			for(int r =0; r < a.length; r++)
				for(int c =0; c < a.length; c++)
					if(a[r][c] != 1e9 && a[r][c] != 0)
					{
						avg += a[r][c];
						d++;
					}
			System.out.printf("Case %d: average length between pages = %.3f clicks\n", count++, avg / d);
		}
	}
}