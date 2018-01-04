import java.util.*;
import java.io.*;

public class p118 
{
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {-1, 1, 0, 0};
	static char[] direction = {'N', 'S', 'E', 'W'};
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = getIndex(st.nextToken().charAt(0));
			boolean lost = false;
			
			char[] steps = f.readLine().toCharArray();
			for(char c : steps)
			{
				if(c == 'F')
				{
					x += dx[d];
					y += dy[d];
					
				}
			}
		}
	}
	
	public static int getIndex(char c)
	{
		for(int i = 0; i < a.length; i++)
			if(direction[i] == c)
				return i;
		return -1;
	}
}