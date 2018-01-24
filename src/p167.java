import java.util.*;
import java.io.*;

public class p167
{
	static int[][] board;
	static boolean[] columns, ld, rd;
	static int OK = (1 << 8) - 1;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			board = new int[8][8];
			columns = new boolean[8];
			ld = new boolean[15];
			rd = new boolean[15];
			for(int r = 0; r < board.length; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < board.length; c++)
				{
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.printf("%5d\n", recurse(0, 0, 0, 0, 0));
		}
	} 
	
	public static int recurse(int r, int score)
	{
		if(r == board.length)
			return score;
		int best = 0;
		for(int c = 0; c < board.length; c++)
		{
			int lIndex = c - r + 7;
			int rIndex = r + c;
			if(!columns[c] && !ld[lIndex] && !rd[rIndex])
			{
				columns[c] = ld[lIndex] = rd[rIndex] = true;
				best = Math.max(best, recurse(r + 1, score + board[r][c]));
				columns[c] = ld[lIndex] = rd[rIndex] = false;
			}
		}
		return best;
	}
	
	public static int recurse(int r, int cols, int ld, int rd, int score)
	{
		if(r == board.length)
			return score;
		int best = 0;
		int canUse = cols | ld | rd;
		for(int c = 0; c < board.length; c++)
		{
			int flag = 1 << c;
			if((canUse & flag) == 0)
			{
				best = Math.max(best, recurse(r + 1, cols | flag, (ld | flag) << 1, (rd | flag) >> 1, score + board[r][c]));
			}
		}
		return best;
	}
}