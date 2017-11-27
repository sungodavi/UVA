import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p10819 //UNSOLVED
{
	static int[][] items;
	static int[][] table;
	static int budget;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String input = f.readLine();
		while(input != null && !input.isEmpty())
		{
			StringTokenizer st = new StringTokenizer(input);
			budget = Integer.parseInt(st.nextToken());
			if(budget > 2000)
				budget += 200;
			items = new int[Integer.parseInt(st.nextToken())][2];
			int maxPrice = 0;
			for(int i =0; i < items.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				int price = Integer.parseInt(st.nextToken());
				int reward = Integer.parseInt(st.nextToken());
				maxPrice += price;
				items[i] = new int[] {price, reward};
			}
			table = new int[items.length][maxPrice + 1];
			for(int[] temp : table)
				Arrays.fill(temp, -1);
			System.out.println(recurse(0, 0));
			input = f.readLine();
		}
	}
	
	public static int recurse(int price, int index)
	{
		if(price > budget)
			return Integer.MIN_VALUE;
		if(index >= items.length)
			return 0;
		if(table[index][price] >= 0)
			return table[index][price];
		int best = 0;
		for(int i = index; i < items.length; i++)
			best = Math.max(best, recurse(price + items[i][0], i + 1) + items[i][1]);
		return table[index][price] = best;
	}

}
