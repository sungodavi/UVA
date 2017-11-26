import java.util.*;
import java.io.*;

public class p410 {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input = f.readLine();
		int cases = 1;
		while(input != null && !input.isEmpty())
		{
			st = new StringTokenizer(input);
			Mass[][] containers = new Mass[Integer.parseInt(st.nextToken())][2];
			Mass[] a = new Mass[2 * containers.length];
			int temp = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			double avg = 0;
			for(int i = 0; i < temp; i++)
			{
				int weight = Integer.parseInt(st.nextToken());
				avg += weight;
				a[i] = new Mass(i, weight);
			}
			avg /= containers.length;
			Arrays.sort(a, new C2());
			//System.out.println(Arrays.toString(a));
			int index = 0;
			for(int i = 0; i < (a.length + 1) / 2; i++)
			{
				
				Mass m1 = a[i];
				Mass m2 = a[a.length - 1 - i];
				if(m1 == null && m2 == null)
					continue;
				if(m1 == null)
					containers[index][0] = m2;
				else
				{
					if(m1.index < m2.index)
					{
						containers[index][0] = m1;
						containers[index][1] = m2;
					}
					else
					{
						containers[index][0] = m2;
						containers[index][1] = m1;
					}
				}
				index++;
			}
			Arrays.sort(containers, new C1());
			//System.out.println(Arrays.deepToString(containers));
			double imbalance = 0;
			System.out.printf("Set #%d\n", cases++);
			for(int i = 0; i < containers.length; i++)
			{
				System.out.printf(" %d:", i);
				int sum = 0;
				for(Mass m : containers[i])
				{
					if(m == null)
						break;
					sum += m.weight;
					System.out.print(" " + m.weight);
				}
				imbalance += Math.abs(sum - avg);
				System.out.println();
			}
			System.out.printf("IMBALANCE = %.5f\n", imbalance);
			System.out.println();
			input = f.readLine();
			
		}
	}
	
	static class Mass
	{
		int weight;
		int index;
		public Mass(int index, int weight)
		{
			this.index = index;
			this.weight = weight;
		}
		
		public String toString()
		{
			return index + " " + weight;
		}
	}
	
	static class C1 implements Comparator<Mass[]>
	{
		public int compare(Mass[] m1, Mass[] m2)
		{
			if(m1[0] == null && m2[0] == null)
				return 0;
			if(m1[0] == null)
				return 1;
			if(m2[0] == null)
				return -1;
			return m1[0].index - m2[0].index;
		}
	}
	
	static class C2 implements Comparator<Mass>
	{
		public int compare(Mass m1, Mass m2)
		{
			if(m1 == null && m2 == null)
				return 0;
			if(m1 == null)
				return -1;
			if(m2 == null)
				return 1;
			return m1.weight - m2.weight;
		}
	}
	
	
}