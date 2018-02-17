import java.util.*;
import java.awt.Point;

public class SuffixArray
{
	public static int[] sa;
	public static int[] ra;
	public static int[] lcp;
	public static String t;
	public static void main(String[] args)
	{
		build("GATAGACA$");
		display();
		String p = "GA";
		System.out.println(compare(p, 7));
		System.out.println(search(p));
		lcp();
		System.out.println(Arrays.toString(lcp));
		System.out.println(longestRepeatedSubstring());
		
		System.out.println(lcs("GATAGACA", "CATA"));
	}
	
	public static Point lcs(String a, String b)
	{
		build(a + "$" + b + "#");
		lcp();
		int max = 0;
		int index = 0;
		for(int i = 1; i < sa.length; i++)
		{
			if(lcp[i] > max && sa[i] < a.length() && sa[i - 1] > a.length())
			{
				max = lcp[i];
				index = i;
			}
		}
		if(index == 0)
			return null;
		return new Point(sa[index], sa[index - 1] - a.length() - 1);
	}
	
	public static String longestRepeatedSubstring()
	{
		int index = 0;
		for(int i = 1; i < lcp.length; i++)
		{
			if(lcp[i] > lcp[index])
				index = i;
		}
		return t.substring(sa[index], sa[index] + lcp[index]);
	}
	public static void lcp()
	{
		int[] phi = new int[sa.length];
		int[] plcp = new int[sa.length];
		lcp = new int[sa.length];
		int len = 0;
		phi[sa[0]] = -1;
		for(int i = 1; i < sa.length; i++)
			phi[sa[i]] = sa[i - 1];
		for(int i = 0; i < sa.length; i++, len = Math.max(len - 1, 0))
		{
			if(phi[i] == -1)
			{
				plcp[i] = 0;
				continue;
			}
			while(i + len < t.length() && phi[i] + len < t.length() && t.charAt(i + len) == t.charAt(phi[i] + len))
				len++;
			plcp[i] = len;
		}
		for(int i = 0; i < sa.length; i++)
			lcp[i] = plcp[sa[i]];
	}
	
	public static Point search(String p)
	{
		int low = 0;
		int high = sa.length - 1;
		int s = -1;
		int e = -1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(compare(p, sa[mid]) == 0 && (mid == 0 || compare(p, sa[mid - 1]) > 0))
			{
				s = mid;
				break;
			}
			else if(compare(p, sa[mid]) < 0)
				high = mid - 1;
			else
				low = mid + 1;
		}
		if(s == -1)
			return null;
		
		low = 0;
		high = sa.length - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(compare(p, sa[mid]) == 0 && (mid == sa.length - 1 || compare(p, sa[mid + 1]) < 0))
			{
				e = mid;
				break;
			}
			else if(compare(p, sa[mid]) < 0)
				high = mid - 1;
			else
				low = mid + 1;
			
		}
		return new Point(s, e);
	}
	
	public static int compare(String p, int index)
	{
		if(p.length() > t.length() - index - 1)
			return 1;
		for(int i = 0; i < p.length(); i++, index++)
			if(p.charAt(i) != t.charAt(index))
				return p.charAt(i) - t.charAt(index);
		return 0;
	}
	
	public static void display()
	{
		for(int num : sa)
			System.out.println(t.substring(num));
	}
	
	public static void build(String s)
	{
		t = s;
		sa = new int[s.length()];
		ra = new int[s.length()];
		for(int i = 0; i < sa.length; i++)
		{
			sa[i] = i;
			ra[i] = s.charAt(i);
		}
		for(int k = 1; k < sa.length; k <<= 1)
		{
			sort(k);
			sort(0);
			int[] temp = new int[sa.length];
			int r = temp[sa[0]] = 0;
			for(int i = 1; i < sa.length; i++)
				temp[sa[i]] = (ra[sa[i]] == ra[sa[i - 1]] && ra[sa[i] + k] == ra[sa[i - 1] + k] ? r : ++r);
			System.arraycopy(temp, 0, ra, 0, sa.length);
		}
	}
	
	public static void sort(int k)
	{
		int[] c = new int[255];
		for(int i = 0; i < sa.length; i++)
		{
			int num = i + k;
			c[num < sa.length ? ra[num] : 0]++;
		}
		for(int i = 1; i < c.length; i++)
			c[i] += c[i - 1];
		int[] temp = new int[sa.length];
		for(int i = sa.length - 1; i >= 0; i--)
		{
			int num = sa[i] + k;
			temp[--c[num < sa.length ? ra[num] : 0]] = sa[i];
		}
		System.arraycopy(temp, 0, sa, 0, sa.length);
	}
}