import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p10306 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(f.readLine());
		//f.readLine();
		while(cases-- > 0)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int[][] coins = new int[Integer.parseInt(st.nextToken())][2];
			int value = Integer.parseInt(st.nextToken());
			int result = Integer.MAX_VALUE;
			int[][] a = new int[value + 1][value + 1];
			for(int[] temp : a)
				Arrays.fill(temp, Integer.MAX_VALUE);
			for(int i = 0; i < coins.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				for(int j = 0; j < 2; j++)
					coins[i][j] = Integer.parseInt(st.nextToken());
			}
			a[0][0] = 0;
			for(int r = 0; r < a.length; r++)
			{
				for(int c = 0; c < a.length && r * r + c * c <= value * value; c++)
				{
					//System.out.println(r + " " + c);
					int best = a[r][c];
					for(int[] coin : coins)
					{
						if(coin[0] > r || coin[1] > c || a[r - coin[0]][c - coin[1]] == Integer.MAX_VALUE)
							continue;
						best = Math.min(best, a[r - coin[0]][c - coin[1]] + 1);
					}
					a[r][c] = best;
					if(r * r + c * c == value * value)
						result = Math.min(result, best);
				}
			}
			if(result == Integer.MAX_VALUE)
				System.out.println("not possible");
			else
				System.out.println(result);
			f.readLine();
			
		}
	}

}
