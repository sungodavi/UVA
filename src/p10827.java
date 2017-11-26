import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p10827 //UNSOLVED
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(f.readLine());
		while(cases-- > 0)
		{
			int size = Integer.parseInt(f.readLine());
			int[][] a = new int[size][2 * size];
			int best = Integer.MIN_VALUE;
			for(int r = 0; r < size; r++)
			{
				StringTokenizer st = new StringTokenizer(f.readLine());
				for(int c = 0; c < size; c++)
				{
					int num = Integer.parseInt(st.nextToken());
					//System.out.println(num);
					a[r][c] = a[r][c + size] = num;
					best = Math.max(best, num);
					if(c > 0)
					{
						//System.out.println(a[r][c] + " " + a[r][c-1]);
						a[r][c] += a[r][c - 1];
					}
				}
			}
			for(int r = 0; r < size; r++)
				for(int c = size; c < 2 * size; c++)
				{
					a[r][c] += a[r][c-1];
				}
			
			for(int[] temp : a)
				System.out.println(Arrays.toString(temp));
							
			for(int width = 0; width < size; width++)
			{
				
				for(int c = width; c < width + size; c++)
				{
					int sum = 0;
					int count = 0;
					for(int r = 0; r < 2 * size; r++)
					{
						if(r >= size)
						{
							if(count == size)
								break;
							sum += a[r - size][c];
							if(c > width)
								sum -= a[r - size][c - width - 1];
						}
						else
						{
							sum += a[r][c];
							if(c > width)
								sum -= a[r][c - width - 1];
						}
						count++;
						//System.out.println(sum);
						if(sum < 0)
						{
							count = 0;
							sum = 0;
						}
						else if(sum > best)
							best = sum;
					}
				}
			}
			System.out.println(best);
		}
		
	}

}
