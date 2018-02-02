
public class CountingSort 
{
	public static void countSort(char[] a)
	{
		int[] counts = new int[256];
		for(char c : a)
			counts[c]++;
		for(int i = 1; i < counts.length; i++)
			counts[i] += counts[i - 1];
		char[] temp = new char[a.length];
		for(int i = 0; i < a.length; i++)
		{
			char c = a[i];
			temp[--counts[c]] = c; 
		}
		System.arraycopy(temp, 0, a, 0, a.length);
	}
	
	public static void main(String[] args)
	{
		String s = "geeksforgeeks";
		char[] a = s.toCharArray();
		countSort(a);
		System.out.println(new String(a));
	}
}
