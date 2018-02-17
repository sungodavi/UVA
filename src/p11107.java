import java.util.*;
import java.io.*;

public class p11107
{
	static int[] sa, ra, lcp, sizes;
	static String t;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		boolean flag = false;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			if(flag)
				out.println();
			else
				flag = true;
			if(size == 1)
			{
				out.println(f.readLine());
				continue;
			}
			StringBuilder s = new StringBuilder();
			sizes = new int[size];
			for(int i = 0; i < size; i++)
			{
				String temp = f.readLine();
				s.append(temp);
				s.append('$');
				sizes[sizes.length - i - 1] = temp.length() + 1;
			}
			for(int i = 1; i < sizes.length; i++)
				sizes[i] += sizes[i - 1];
			
			build(s.toString(), size);
			display();
			System.out.println(Arrays.toString(lcp));
			LinkedList<String> list = find();
			if(list.isEmpty())
				out.println("?");
			else
				for(String sub : list)
					out.println(sub);
		}
		out.close();
	}
	
	public static LinkedList<String> find()
	{
		LinkedList<String> list = new LinkedList<String>();
		boolean[] flags;
		int count = 0;
		int best = 1;
		for(int i = 1; i < sa.length; i++)
		{
			if(lcp[i] >= best)
			{
				flags = new boolean[sizes.length];
				int curr = lcp[i];
				int i1 = search(t.length() - sa[i - 1]);
				int i2 = search(t.length() - sa[i]);
				flags[i1] = true;
				flags[i2] = true;
				if(i1 == i2)
					count = 1;
				else
					count = 2;
				int j = i + 1;
				while(j < sa.length && lcp[j] >= curr)
				{
					int index = search(t.length() - sa[j]);
					//System.out.println(t.substring(sa[j]) + " " + (t.length() - sa[j]) + " " + lcp[j] + " " + index);
					if(!flags[index])
					{
						count++;
						flags[index] = true;
					}
					j++;
				}
				//System.out.println(t.substring(sa[i]) + " " + count + " " + Arrays.toString(flags) + " " + i + " " + j);
				if(count << 1 > sizes.length)
				{
					//System.out.println(t.substring(sa[i], sa[i] + curr));
					if(curr > best)
					{
						list = new LinkedList<String>();
						best = curr;
					}
					list.add(t.substring(sa[i], sa[i] + curr));
				}
				i = j - 1;
			}
		}
		return list;
	}
	
	public static void display()
	{
		for(int num : sa)
			System.out.println(t.substring(num));
	}
	
	public static int search(int val)
	{
		int low = 0;
		int high = sizes.length - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(sizes[mid] == val)
				return mid;
			if(sizes[mid] > val)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}
	
	public static void lcp()
	{
		lcp = new int[sa.length];
		int[] phi = new int[sa.length];
		phi[sa[0]] = -1;
		for(int i = 1; i < sa.length; i++)
			phi[sa[i]] = sa[i - 1];
		int len = 0;
		int[] temp = new int[sa.length];
		for(int i = 0; i < sa.length; i++)
		{
			if(phi[i] == -1)
				temp[i] = 0;
			else
			{
				while(t.charAt(i + len) == t.charAt(phi[i] + len) && t.charAt(i + len) != '$')
					len++;
				temp[i] = len;
				len = Math.max(0, len - 1);
			}
		}
		for(int i = 0; i < sa.length; i++)
			lcp[i] = temp[sa[i]];
	}
	
	public static void build(String s, int count)
	{
		t = s;
		sa = new int[t.length()];
		ra = new int[t.length()];
		for(int i = 0; i < sa.length; i++)
		{
			sa[i] = i;
			ra[i] = t.charAt(i) == '$' ? --count : t.charAt(i) + 100;
		}
		for(int k = 1; k < sa.length; k <<= 1)
		{
			sort(k);
			sort(0);
			int[] temp = new int[ra.length];
			int r = temp[sa[0]] = 0;
			for(int i = 1; i < sa.length; i++)
			{
				if(ra[sa[i]] == ra[sa[i - 1]] && ra[sa[i] + k] == ra[sa[i - 1] + k])
					temp[sa[i]] = r;
				else
					temp[sa[i]] = ++r;
			}
			System.arraycopy(temp, 0, ra, 0, ra.length);
		}
		lcp();
	}
	
	public static void sort(int k)
	{
		int[] counts = new int[Math.max(355, sa.length)];
		for(int i = 0; i < sa.length; i++)
		{
			int num = i + k;
			counts[num < sa.length ? ra[num] : 0]++;
		}
		for(int i = 1; i < counts.length; i++)
			counts[i] += counts[i - 1];
		int[] temp = new int[sa.length];
		for(int i = sa.length - 1; i >= 0; i--)
		{
			int num = sa[i] + k;
			temp[--counts[num < sa.length ? ra[num] : 0]] = sa[i];
		}
		System.arraycopy(temp, 0, sa, 0, sa.length);
	}
}