import java.util.*;
import java.io.*;

public class p750 
{
	static int targetR = -1, targetC = -1;
	static int solutionCount = 1;
	static int[] board = new int[8];
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		for(int i = 0; i < times; i++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			f.readLine();
			targetR = Integer.parseInt(st.nextToken()) - 1;
			targetC = Integer.parseInt(st.nextToken()) - 1;
			solutionCount = 1;
			System.out.println("SOLN       COLUMN");
			System.out.println(" #      1 2 3 4 5 6 7 8");
			System.out.println();
			recurse(0, 0);
			if(i != times - 1)
				System.out.println();
		}
	}
	
	public static void recurse(int c, int queenCount)
	{
		if(c == board.length)
		{
			if(queenCount == 8 && board[targetC] == targetR)
				display();
		}
		else
		{
			if(c == targetC)
			{
				board[c] = targetR;
				recurse(c + 1, queenCount + 1);
			}
			else
			{
				for(int r = 0; r < board.length; r++)
					if(canPlace(r, c))
					{
						board[c] = r;
						recurse(c + 1, queenCount + 1);
					}
			}
		}
	}
	
	public static void display()
	{
		System.out.printf("%2d      %d", solutionCount++, board[0] + 1);
		for (int j = 1; j < 8; j++) 
			System.out.printf(" %d", board[j] + 1);
		System.out.println();
	}
	
	public static boolean canPlace(int r, int c)
	{
		for(int k = 0; k < c; k++)
		{
			if(board[k] == r || Math.abs(r - board[k]) == Math.abs(c - k))
				return false;
		}
		return true;
	}
}
