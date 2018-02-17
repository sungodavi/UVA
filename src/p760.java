import java.util.*;
import java.io.*;

public class p760
{
	static int[] sa, ra, lcp;
	static String t;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		boolean flag = false;
		for(String a = f.readLine(); a != null; f.readLine(), a = f.readLine())
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			String b = f.readLine();
			build(a, b);
//			for(int num : sa)
//				System.out.println(t.substring(num));
//			System.out.println(Arrays.toString(lcp));
			int lcs = lcs(a.length());
			if(lcs == 0)
				System.out.println("No common sequence.");
			else
			{
				TreeSet<String> seqs = new TreeSet<String>();
				for(int i = 0; i < list.size(); i++)
				{
					int index = sa[list.get(i)];
					seqs.add(t.substring(index, index + lcs));
				}
				for(String s : seqs)
					System.out.println(s);
			}
		}
	}
	
	public static int lcs(int size)
	{
		int max = 0;
		list = new ArrayList<Integer>();
		for(int i = 1; i < sa.length; i++)
		{
			if(lcp[i] >= max && (sa[i] < size && sa[i - 1] >= size || sa[i] >= size && sa[i - 1] < size))
			{
				if(lcp[i] > max)
					list = new ArrayList<Integer>();
				list.add(i);
				max = lcp[i];
			}
		}
		return max;
	}
	
	public static void lcp()
	{
		int[] plcp = new int[sa.length];
		int[] phi = new int[sa.length];
		lcp = new int[sa.length];
		phi[0] = -1;
		for(int i = 1; i < sa.length; i++)
			phi[sa[i]] = sa[i - 1];
		int len = 0;
		for(int i = 0; i < sa.length; i++, len = Math.max(len - 1, 0))
		{
			if(phi[i] == -1)
			{
				plcp[i] = 0;
				continue;
			}
			while(i + len < sa.length && phi[i] + len < sa.length && t.charAt(i + len) == t.charAt(phi[i] + len))
				len++;
			plcp[i] = len;
		}
		for(int i = 0; i < sa.length; i++)
			lcp[i] = plcp[sa[i]];
	}
	
	public static void build(String a, String b)
	{
		t = a + "$" + b + "#";
		sa = new int[t.length()];
		ra = new int[t.length()];
		for(int i = 0; i < t.length(); i++)
		{
			sa[i] = i;
			ra[i] = t.charAt(i);
		}
		for(int k = 1; k < t.length(); k <<= 1)
		{
			sort(k);
			sort(0);
			int[] temp = new int[ra.length];
			int r = 0;
			for(int i = 1; i < t.length(); i++)
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
		int[] counts = new int[Math.max(255, sa.length)];
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