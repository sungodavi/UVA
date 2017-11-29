import java.util.*;
import java.io.*;

public class p11831 
{
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static char[][] a;
	
	public static void main(String[] args) throws IOException
	{
		//System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); input.charAt(0) != '0'; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int rows = Integer.parseInt(st.nextToken());
			a = new char[rows][];
			int startR = -1;
			int startC = -1;
			for(int r = 0; r < a.length; r++)
			{
				a[r] = f.readLine().toCharArray();
				for(int c = 0; c < a[r].length; c++)
					if(Character.isAlphabetic(a[r][c]))
					{
						startR = r;
						startC = c;
					}
			}
			char[] moves = f.readLine().toCharArray();
			System.out.println(move(moves, startR, startC));
		}
		
	}
	
	static int move(char[] moves, int r, int c)
	{
		int count = 0;
		int direction;
		char temp = a[r][c];
		if(temp == 'N')
			direction = 0;
		else if(temp == 'L')
			direction = 1;
		else if(temp == 'S')
			direction = 2;
		else
			direction = 3;
		for(char move : moves)
		{
			//System.out.println(r + " " + c + " " + direction);

			if(move == 'E')
				direction = (direction + 3) % 4;
			else if(move == 'D')
				direction = (direction + 1) % 4;
			else
			{
				r += dy[direction];
				c += dx[direction];
				if(r < 0 || r >= a.length || c < 0 || c >= a[0].length || a[r][c] == '#')
				{
					r -= dy[direction];
					c -= dx[direction];
				}
			}
			if(a[r][c] == '*')
			{
				count++;
				a[r][c] = '.';
			}
		}
		return count;
	}

}
