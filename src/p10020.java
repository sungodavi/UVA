import java.util.*;
import java.io.*;
import java.awt.Point;

public class p10020 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		boolean flag = false;
		while(times-- > 0)
		{
			if(flag)
				out.println();
			else
				flag = true;
			int m = Integer.parseInt(f.readLine());
			ArrayList<Point> list = new ArrayList<Point>();
			for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
			{
				st = new StringTokenizer(input);
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				list.add(new Point(s, e));
			}
			Collections.sort(list, new Comparator<Point>() {
				public int compare(Point a, Point b)
				{
					return a.x == b.x ? b.y - a.y : a.x - b.x;
				}
			});
			//System.out.println(list);
			
			int currRight = 0;
			int i = 0;
			ArrayList<Point> result = new ArrayList<Point>();
			boolean found = true;
			while(currRight < m && found)
			{
				int best = -1;
				found = false;
				int j;
				for(j = i; j < list.size() && list.get(j).x <= currRight; j++)
				{
					found = true;
					if(best < 0 || list.get(best).y < list.get(j).y)
						best = j;
				}
				if(best >= 0)
				{
					result.add(list.get(best));
					currRight = list.get(best).y;
				}
				i = j;
			}
			if(currRight < m)
				out.println(0);
			else
			{
				out.println(result.size());
				for(Point p : result)
					out.println(p.x + " " + p.y);
			}
			f.readLine();
		}
		out.close();
	}
}