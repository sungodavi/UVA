import java.util.*;
import java.io.*;

public class p10140 //UNSOLVED
{
	static BitSet a = new BitSet(Integer.MAX_VALUE);
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		load();
		System.out.println(a);
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int min1, min2;
			int max1, max2;
			min1 = max1 = max2 = 0;
			min2 = Integer.MAX_VALUE;
			int index = 0;
			while(list.get(index) < start)
				index++;
			index++;
			while(list.get(index) <= end)
			{
				int p2 = list.get(index);
				int p1 = list.get(index - 1);
				if(p2 - p1 < min2 - min1)
				{
					min2 = p2;
					min1 = p1;
				}
				if(p2 - p1 > max2 - max1)
				{
					max2 = p2;
					max1 = p1;
				}
				++index;
			}
			if(max1 == max2)
				System.out.println("There are no adjacent primes.");
			else
				System.out.printf("%d,%d are closest, %d,%d are most distant.\n", min2, min1, max2, max1);
				
		}
	}
	
	static void load()
	{
		boolean flag = true;
		for(int i = 2; i <= Integer.MAX_VALUE; i++)
			a.set(i);
		for(int i = 2; i <= Integer.MAX_VALUE; i++)
		{
			if(a.get(i))
			{
				list.add(i);
				if(!flag || i * i > a.length())
					flag = false;
				if(flag)
					for(int j = i * i; j < a.length(); j += i)
						a.flip(j);
			}
		}
	}
}
