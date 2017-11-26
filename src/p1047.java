import java.util.*;
import java.io.*;

public class p1047 
{
	static int[] populations;
	static ArrayList<Intersection>[] intersections;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int caseNumber = 1;
		while(n > 0)
		{
			st = new StringTokenizer(f.readLine());
			populations = new int[n];
			for(int i = 0; i < n; i++)
				populations[i] = Integer.parseInt(st.nextToken());
			intersections = new ArrayList[n];
			int temp = Integer.parseInt(f.readLine());
			boolean[] used = new boolean[temp];
			for(int i = 0; i < intersections.length; i++)
				intersections[i] = new ArrayList<Intersection>();
			
			for(int i = 0; i < temp; i++)
			{
				Intersection inter = new Intersection(f.readLine(), i);
				for(int tower : inter.towers)
				{
					intersections[tower].add(inter);
					populations[tower] -= inter.overlap;
				}
			}
			
			int[] choose = new int[r];
			int[] best = new int[r];
			int maxCustomers = 0;
			for(int i = 0; i < choose.length; i++)
				choose[i] = i;
			//System.out.println(r);
			do
			{
				//System.out.println(Arrays.toString(choose));
				used = new boolean[temp];
				int sum = 0;
				for(int index : choose)
				{
					sum += populations[index];
					for(Intersection i : intersections[index])
					{
						if(!used[i.index])
						{
							used[i.index] = true;
							sum += i.overlap;
						}
					}
				}
				if(sum > maxCustomers)
				{
					maxCustomers = sum;
					for(int i = 0; i < best.length; i++)
						best[i] = choose[i];
				}
				//System.out.println(sum + " " + Arrays.toString(choose));
			} while(generate(choose, populations.length - 1));
			
			System.out.printf("Case Number  %d\n", caseNumber++);
			System.out.printf("Number of Customers: %d\n", maxCustomers);
			System.out.print("Locations recommended:");
			for(int num : best)
				System.out.print(" " + (num + 1));
			System.out.println("\n");
			
			st = new StringTokenizer(f.readLine());
			n = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
		}
	}
	
	public static boolean generate(int[] choose, int max)
	{
		int i = choose.length - 1;
		while(i >= 0 && choose[i] == max - (choose.length - 1 - i))
			i--;
		if(i < 0)
			return false;
		choose[i]++;
		for(int j = i + 1; j < choose.length; j++)
			choose[j] = choose[j - 1] + 1;
		return true;
	}
	
	static class Intersection
	{
		int[] towers;
		int overlap;
		int index;
		public Intersection(String input, int index)
		{
			StringTokenizer st = new StringTokenizer(input);
			towers = new int[Integer.parseInt(st.nextToken())];
			for(int i =0; i < towers.length; i++)
				towers[i] = Integer.parseInt(st.nextToken()) - 1;
			overlap = Integer.parseInt(st.nextToken());
			this.index = index;
		}
		
		public String toString()
		{
			return index + " " + Arrays.toString(towers) + " " + overlap;
		}
	}
	
}