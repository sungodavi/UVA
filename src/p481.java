import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class p481 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
		{
			list.add(Integer.parseInt(input));
		}
		Integer[] a = (Integer[]) list.toArray(new Integer[list.size()]);
		int[] prevs = new int[a.length];
		int[] tails = new int[a.length];
		Arrays.fill(prevs, -1);
		int length = 1;
		for(int i = 1; i < a.length; i++)
		{
			if(a[i] <= a[tails[0]])
				tails[0] = i;
			else if(a[i] > a[tails[length - 1]])
			{
				prevs[i] = tails[length - 1];
				tails[length++] = i;
			}
			else
			{
				int k = search(a, tails, length, a[i]);
				prevs[i] = tails[k - 1];
				tails[k] = i;
			}
		}
		int[] result = new int[length];
		int index = result.length - 1;
		for(int i = tails[length - 1]; i >= 0; i = prevs[i])
			result[index--] = a[i];
		System.out.println(length);
		System.out.println("-");
		for(int num : result)
			System.out.println(num);
	}
	
	public static int binarySearch(Integer[] a, int[] tails, int length, int num)
	{
		int low = -1;
		int high = length - 1;
		while(high - low > 1)
		{
			int mid = low + (high - low) / 2;
			if(a[tails[mid]] >= num)
				high = mid;
			else
				low = mid;
		}
		return high;
	}
	
	public static int search(Integer[] a, int[] tails, int length, int num)
	{
		int low = 0;
		int high = length - 1;
		while(low <= high)
		{
			int mid = (low + high) / 2;
			if(a[tails[mid]] < num)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return low;
	}
}
