import java.util.*;
import java.io.*;

public class p12192 {
	static int[][] a;
	public static void main(String[] args) throws IOException 
	{
		//System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int rows = Integer.parseInt(st.nextToken());
		int columns = Integer.parseInt(st.nextToken());
		while(rows > 0)
		{
			if(rows <= columns)
				a = new int[rows][columns];
			else
				a = new int[columns][rows];
			for(int r = 0; r < rows; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < columns; c++)
				{
					if(rows <= columns)
						a[r][c] = Integer.parseInt(st.nextToken());
					else
						a[c][r] = Integer.parseInt(st.nextToken());
				}
			}
				
			int queries = Integer.parseInt(f.readLine());
			for(int i = 0; i < queries; i++)
			{
				st = new StringTokenizer(f.readLine());
				int low = Integer.parseInt(st.nextToken());
				int high = Integer.parseInt(st.nextToken());
				System.out.println(findMax(low, high));
			}
			System.out.println("-");
			st = new StringTokenizer(f.readLine());
			rows = Integer.parseInt(st.nextToken());
			columns = Integer.parseInt(st.nextToken());
		}
	}
	
	public static int findMax(int low, int high)
	{
		int max = 0;
		for(int r = 0; r < a.length - max; r++)
		{
			//System.out.println("Search: " + fuzzySearch(a[r], low));
			int c = fuzzySearch(a[r], low);
			if(c < 0)
				continue;
			int limit = Math.min(a[0].length - c, a.length - r);
			for(int size = max + 1; size <= limit; size++)
			{
				if(a[r + size - 1][c + size - 1] <= high)
				{
					max = size;
				}
				else
					break;
			}

		}
		return max;
	}
	
	public static int fuzzySearch(int[] a, int num)
	{
		int low = 0;
		int high = a.length - 1;
		if(a[high] < num)
			return -1;
		while(low <= high)
		{
			int mid = (low + high) / 2;
			int curr = a[mid];
			if(curr == num)
			{
				while(mid >= 0 && a[mid] == num)
					mid--;
				return mid + 1;
			}
			if(curr > num)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}
}