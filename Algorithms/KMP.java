import java.util.Arrays;


public class KMP 
{
	public static int[] preprocess(String s)
	{
		int[] lps = new int[s.length() + 1];
		int i = 0;
		int j = -1;
		lps[0] = -1;
		while(i < s.length())
		{
			while(j >= 0 && s.charAt(i) != s.charAt(j))
				j = lps[j];
			lps[++i] = ++j;
		}
		return lps;
	}
	
	public static void search(String a, String b)
	{
		int[] lps = preprocess(b);
		int i = 0;
		int j = -1;
		while(i < a.length())
		{
			while(j >= 0 && a.charAt(i) != b.charAt(j))
				j = lps[j];
			i++;
			j++;
			if(j == b.length())
				System.out.println("Found at " + (i - j));
		}
	}
	
	public static void main(String[] args)
	{
		String p = "ABABA";
		int[] lps = preprocess(p);
		System.out.println(Arrays.toString(lps));
	}
}
