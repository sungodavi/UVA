import java.util.*;
import java.io.*;

public class p469 
{
	static int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
	static int[] dy = {1, -1, 0, 1, -1, 0, 1, -1};
	static boolean[][] visited;
	static char[][] a;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(f.readLine());
		f.readLine();
		while(cases-- > 0)
		{
			ArrayList<String> input = new ArrayList<String>();
			String s;
			for(s = f.readLine(); !Character.isDigit(s.charAt(0)); s = f.readLine())
				input.add(s);
			a = new char[input.size()][];
			for(int i = 0; i < a.length; i++)
				a[i] = input.get(i).toCharArray();
			for(; s != null && !s.isEmpty(); s = f.readLine())
			{
				StringTokenizer st = new StringTokenizer(s);
				int r = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				visited = new boolean[a.length][a[0].length];
				System.out.println(fill(r, c));
			}
			if(cases > 0)
				System.out.println();
		}
	}
	
	public static int fill(int r, int c)
	{
		if(!isValid(r, c))
			return 0;
		visited[r][c] = true;
		int sum = 1;
		for(int i = 0; i < dx.length; i++)
		{
			sum += fill(r + dx[i], c + dy[i]);
		}
		return sum;
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && c >= 0 && r < a.length && c < a[0].length && !visited[r][c] && a[r][c] == 'W';
	}
}