import java.util.*;
import java.io.*;

public class p11057 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String input = f.readLine();
		while(input != null && !input.isEmpty())
		{
			int size = Integer.parseInt(input);
			StringTokenizer st = new StringTokenizer(f.readLine());
			ArrayList<Integer> list = new ArrayList<Integer>(size);
			ArrayList<Integer> temp = new ArrayList<Integer>(size - 1);
			while(st.hasMoreTokens())
			{
				int num = Integer.parseInt(st.nextToken());
				list.add(num);
				temp.add(num);
			}
			int money = Integer.parseInt(f.readLine());
			f.readLine();
			Collections.sort(list);
			Collections.sort(temp);
			int tempIndex = 0;
			int previous = temp.remove(0);
			int p1 = 0, p2 = Integer.MAX_VALUE;
			for(int i = 0; i < list.size(); i++)
			{
				int price = list.get(i);
				//System.out.println(temp + " " + price);
				if(Collections.binarySearch(temp, money - list.get(i)) >= 0)
				{
					if(2 * price > money)
						price = money - price;
					if(money - 2 * price < p2 - p1)
					{
						p2 = money - price;
						p1 = price;
					}
				}
				if(tempIndex == temp.size())
					break;
				previous = temp.set(tempIndex++, previous);
			}
			System.out.printf("Peter should buy books whose prices are %d and %d.\n\n", p1, p2);
			input = f.readLine();
		}
	}
}