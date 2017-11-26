import java.util.*;
import java.io.*;

public class p11742 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		while(size > 0)
		{
			int[] a = new int[size];
			int count = 0;
			for(int i = 0; i < a.length; i++)
				a[i] = i;
			Constraint[] constraints = new Constraint[Integer.parseInt(st.nextToken())];
			for(int i = 0; i < constraints.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				constraints[i] = new Constraint(Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()));
			}
			int limit = factorial(a.length);
			for(int i = 1; i <= limit; i++)
			{
				boolean found = true;
				for(Constraint c : constraints)
					if(!c.check(a))
					{
						found = false;
						break;
					}
				if(found)
					count++;
				if(i == limit)
					break;
				int j = a.length - 2;
				while(j > 0 && a[j] > a[j+1])
					j--;
				
				int l = a.length - 1;
				while(l > 0 && a[j] > a[l])
					l--;
				swap(a, j, l);
				
				int k = j + 1;
				l = a.length - 1;
				while(k < l)
					swap(a, k++, l--);
				//System.out.println(Arrays.toString(a));
			}
			
			System.out.println(count);
			
			st = new StringTokenizer(f.readLine());
			size = Integer.parseInt(st.nextToken());
		}
		
	}
	
	public static int factorial(int num)
	{
		int result = 1;
		for(int i = 2; i <= num; i++)
			result *= i;
		return result;
	}
	
	public static void swap(int[] a, int x, int y)
	{
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class Constraint
	{
		int a, b, r;
		public Constraint(int a, int b, int r)
		{
			this.a = a;
			this.b = b;
			this.r = r;
		}
		
		public boolean check(int[] a)
		{
			int i1 = -1, i2 = -1;
			for(int i = 0; i < a.length; i++)
			{
				if(a[i] == this.a)
					i1 = i;
				else if(a[i] == b)
					i2 = i;
			}
			if(r < 0)
				return Math.abs(i1 - i2) >= -r;
			else
				return Math.abs(i1 - i2) <= r;
		}
	}
}
