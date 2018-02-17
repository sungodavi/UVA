import java.util.*;
import java.io.*;

public class p1223
{
	public static int[] sa, ra, lcp;
	static String t;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			build(f.readLine());
			int max = 0;
			for(int i = 1; i < lcp.length; i++)
				max = Math.max(max, lcp[i]);
			out.println(max);
		}
		out.close();
	}
	
	public static void lcp()
	{
		int[] temp = new int[sa.length];
		lcp = new int[sa.length];
		int[] phi = new int[sa.length];
		phi[sa[0]] = -1;
		for(int i = 1; i < sa.length; i++)
			phi[sa[i]] = sa[i - 1];
		int len = 0;
		for(int i = 0; i < sa.length; i++, len = Math.max(len - 1, 0))
		{
			if(phi[i] == -1)
				temp[i] = 0;
			else
			{
				int j = phi[i];
				while(i + len < sa.length && j + len < sa.length && t.charAt(i + len) == t.charAt(j + len))
					len++;
				temp[i] = len;
			}
		}
		for(int i = 0; i < sa.length; i++)
			lcp[i] = temp[sa[i]];
	}
	
	public static void build(String s)
	{
		t = s + '$';
		sa = new int[t.length()];
		ra = new int[t.length()];	
		for(int i = 0; i < sa.length; i++)
		{
			sa[i] = i;
			ra[i] = t.charAt(i);
		}
		for(int k = 1; k < sa.length; k <<= 1)
		{
			sort(k);
			sort(0);
			int[] temp = new int[ra.length];
			int r = 0;
			for(int i = 1; i < ra.length; i++)
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