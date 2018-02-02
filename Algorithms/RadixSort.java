import java.util.Arrays;

public class RadixSort
{
	public static void radixSort(int[] a)
	{
		int d = getMaxDigits(a);
		int e = 1;
		for(int i = 0; i < d; i++, e *= 10)
			countSort(a, e);
	}
	public static void countSort(int[] a, int exp)
	{
		int[] counts = new int[10];
		int[] temp = new int[a.length];
		for(int num : a)
			counts[getDigit(num, exp)]++;
		for(int i = 1; i < counts.length; i++)
			counts[i] += counts[i - 1];
		for(int i = temp.length - 1; i >= 0; i--)
		{
			int num = a[i];
			temp[--counts[getDigit(num, exp)]] = num;
		}
		System.arraycopy(temp, 0, a, 0, a.length);
	}
	
	public static int getDigit(int num, int exp)
	{
		return (num / exp) % 10;
	}
	
	public static int getMaxDigits(int[] a)
	{
		int max = a[0];
		for(int num : a)
			max = Math.max(max, num);
		return (int)Math.log10(max) + 1;
	}
	public static void main(String[] args)
	{
		int[] a = {170, 45, 75, 90, 802, 24, 2, 66};
		radixSort(a);
		System.out.println(Arrays.toString(a));
	}
}