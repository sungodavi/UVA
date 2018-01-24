import java.util.*;
import java.io.*;

public class p12086
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int caseNo = 0;
		boolean flag = false;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			System.out.printf("Case %d:\n", ++caseNo);
			int[] a = new int[size];
			for(int i = 0; i < size; i++)
				a[i] = Integer.parseInt(f.readLine());
			FenwickTree ft = new FenwickTree(a);
			for(String input = f.readLine(); !input.equals("END"); input = f.readLine())
			{
				st = new StringTokenizer(input);
				char type = st.nextToken().charAt(0);
				if(type == 'M')
				{
					int start = Integer.parseInt(st.nextToken()) - 1;
					int end = Integer.parseInt(st.nextToken()) - 1;
					System.out.println(ft.getSum(start, end));
				}
				else
				{
					int index = Integer.parseInt(st.nextToken()) - 1;
					int difference = -(a[index] - (a[index] = Integer.parseInt(st.nextToken())));
					ft.update(index, difference);
				}
			}
		}
	}
	
	static class FenwickTree
	{
		int[] tree;
		public FenwickTree(int[] a)
		{
			tree = new int[a.length + 1];
			for(int i = 0; i < a.length; i++)
				update(i, a[i]);
		}
		
		public int getSum(int s, int e)
		{
			return getSum(e + 1) - getSum(s);
		}
		
		public int getSum(int e)
		{
			if(e < 0)
				return 0;
			int sum = 0;
			while(e > 0)
			{
				sum += tree[e];
				e -= e & -e;
			}
			return sum;
		}
		
		public void update(int index, int val)
		{
			++index;
			while(index < tree.length)
			{
				tree[index] += val;
				index += index & -index;
			}
		}
	}
}