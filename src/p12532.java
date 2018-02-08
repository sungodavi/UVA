import java.util.*;
import java.io.*;

public class p12532
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			int queries = Integer.parseInt(st.nextToken());
			FenwickTree zeros = new FenwickTree(size);
			ProductTree tree = new ProductTree(size);
			int[] a = new int[size];
			st = new StringTokenizer(f.readLine());
			for(int i = 0; i < size; i++)
			{
				int num = Integer.parseInt(st.nextToken());
				if(num == 0)
					zeros.update(i, 1);
				tree.update(i, convert(num));
				a[i] = num;
			}
			StringBuilder result = new StringBuilder();
			while(queries-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				char type = st.nextToken().charAt(0);
				if(type == 'C')
				{
					int index = Integer.parseInt(st.nextToken()) - 1;
					int val = Integer.parseInt(st.nextToken());
					if(val == 0 && a[index] != 0)
						zeros.update(index, 1);
					else if(val != 0 && a[index] == 0)
						zeros.update(index, -1);
					tree.update(index, convert(val) / convert(a[index]));
					a[index] = val;
				}
				else
				{
					int s = Integer.parseInt(st.nextToken()) - 1;
					int e = Integer.parseInt(st.nextToken()) - 1;
					if(zeros.sum(s, e) > 0)
						result.append(0);
					else
						result.append(tree.sum(s, e) > 0 ? '+' : '-');
				}
			}
			System.out.println(result);
		}
	}
	
	public static int convert(int num)
	{
		if(num == 0)
			return 1;
		return num / Math.abs(num);
	}
	
	static class ProductTree
	{
		int[] a;
		public ProductTree(int size)
		{
			a = new int[size + 1];
			Arrays.fill(a, 1);
		}
		
		public void update(int i, int val)
		{
			i++;
			if(val != 1)
				for(; i < a.length; i += i & -i)
					a[i] *= val;
		}
		
		public int sum(int i)
		{
			int sign = 1;
			for(; i > 0; i -= i & -i)
				sign *= a[i];
			return sign;
		}
		
		public int sum(int s, int e)
		{
			return sum(e+1) / sum(s);
		}
		
		public String toString()
		{
			return Arrays.toString(a);
		}
		
	}
	
	static class FenwickTree
	{
		int[] a;
		public FenwickTree(int size)
		{
			a = new int[size + 1];
		}
		
		public void update(int i, int val)
		{
			i++;
			for(; i < a.length; i += i & -i)
				a[i] += val;
		}
		
		public int sum(int i)
		{
			int sum = 0;
			for(; i > 0; i -= i & -i)
				sum += a[i];
			return sum;
		}
		
		public int sum(int s, int e)
		{
			return sum(e+1) - sum(s);
		}
		
		public String toString()
		{
			return Arrays.toString(a);
		}
	}
}