import java.util.*;
import java.io.*;
import java.awt.Point;
public class p1103 
{
	static int[][] a;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException
	{
		//System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int count = 1;
		int rows = Integer.parseInt(st.nextToken());
		int columns = 4 * Integer.parseInt(st.nextToken());
		while(rows > 0)
		{
			a = new int[rows][columns];
			for(int r = 0; r < a.length; r++)
			{
				String s = f.readLine();
				//System.out.println(r + " " + s);
				for(int c = 0; c < a[0].length; c += 4)
				{
					int num = Character.isDigit(s.charAt(c / 4)) ? s.charAt(c / 4) - '0' : s.charAt(c / 4) - 'a' + 10;
					for(int i = c + 3; i >= c; i--)
					{
						a[r][i] = num & 1;
						num >>= 1;
					}
				}
			}
			
			ArrayList<Character> list = new ArrayList<Character>();
			
			for(int r = 0; r < a.length; r++)
				for(int c = 0; c < a[0].length; c++)
				{
					if(a[r][c] == 1)
					{
						int result = dfs(r, c);
						

						switch(result)
						{
						case 0: list.add('W'); break;
						case 1: list.add('A'); break;
						case 2: list.add('K'); break;
						case 3: list.add('J'); break;
						case 4: list.add('S'); break;
						case 5: list.add('D'); break;
						}
					}
						
				}
			Collections.sort(list);
			System.out.printf("Case %d: ", count++);
			for(char c : list)
				System.out.print(c);
			System.out.println();
			
			st = new StringTokenizer(f.readLine());
			rows = Integer.parseInt(st.nextToken());
			columns = 4 * Integer.parseInt(st.nextToken());
		}		
	}
	
	public static int dfs(int r, int c)
	{
		a[r][c] = 2;
		int holes = 0;
		for(int i = 0; i < dx.length; i++)
		{
			int x = r + dx[i];
			int y = c + dy[i];
			if(isValid(x, y) && a[x][y] == 1)
				holes += dfs(x, y);
		}
		for(int i = 0; i < dx.length; i++)
		{
			int x = r + dx[i];
			int y = c + dy[i];
			if(isValid(x, y) && a[x][y] == 0)
			{
				if(fill(x, y))
					holes++;
			}
		}
		return holes;
	}
	
	public static boolean fill(int r, int c)
	{
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(r, c));
		boolean found = true;;
		while(!q.isEmpty())
		{
			Point p = q.poll();
			System.out.println(p);
			for(int[] temp : a)
				System.out.println(Arrays.toString(temp));
			a[p.x][p.y] = 2;
			for(int i = 0; i < dx.length; i++)
			{
				int x = p.x + dx[i];
				int y = p.y + dy[i];
				if(!isValid(x, y))
					found = false;
				else if(a[x][y] == 0)
				{
					q.add(new Point(x, y));
				}
			}
		}
		return found;
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && r < a.length && c >= 0 && c < a[0].length;
	}
}
