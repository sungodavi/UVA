import java.util.*;
import java.io.*;

public class p11283 
{
	static char[][] a;
	static boolean[][] visited;
	static int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
	static int[] dy = {-1, 1, 0, -1, 1, 0, -1, 1};
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		//f.readLine();
		for(int k = 1; k <= times; k++)
		{
			a = new char[4][];
			for(int r =0; r < a.length; r++)
				a[r] = f.readLine().toCharArray();
			int words = Integer.parseInt(f.readLine());
			int score = 0;
			while(words-- > 0)
			{
				score += find(f.readLine());
			}
			System.out.printf("Score for Boggle game #%d: %d\n", k, score);
			//f.readLine();
		}
	}
	
	public static int find(String s)
	{
		visited = new boolean[4][4];
		for(int r = 0; r < a.length; r++)
			for(int c = 0; c < a[0].length; c++)
				if(recurse(r, c, 0, s))
					return convert(s);
		return 0;
	}
	
	public static int convert(String s)
	{
		int len = s.length();
		if(len < 3)
			return 0;
		if(len >= 8)
			return 11;
		switch(len)
		{
		case 3:
		case 4:
			return 1;
		case 5:
			return 2;
		case 6:
			return 3;
		case 7:
			return 5;
		}
		return -1;
	}
	
	public static boolean recurse(int r, int c, int index, String s)
	{
		if(!isValid(r, c) || s.charAt(index) != a[r][c])
			return false;
		
		visited[r][c] = true;
		
		if(index == s.length() - 1)
			return true;
		
		for(int i = 0; i < dx.length; i++)
			if(recurse(r + dx[i], c + dy[i], index + 1, s))
				return true;
		visited[r][c] = false;
		return false;
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && c >= 0 && r < a.length && c < a[0].length && !visited[r][c];
	}
}