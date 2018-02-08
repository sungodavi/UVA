import java.util.*;
import java.awt.Point;

public class SuffixArray
{
	public static int[] sa;
	public static int[] ra;
	public static String t;
	public static void main(String[] args)
	{
		build("GATAGACA");
		System.out.println(Arrays.toString(sa));
	}
	
	public static void build(String s)
	{
		s += '$';
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