import java.util.*;
import java.io.*;

public class p12572
{
	static int mod = (int)1e9 + 7;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int size = Integer.parseInt(input);
			char[] arr = f.readLine().toCharArray();
			int[] a = new int[arr.length];
			for(int i = 0; i < a.length; i++)
				a[i] = arr[i] - '0';
			System.out.println(new SegmentTree(a).result);
		}
	}
	
	static class SegmentTree
	{
		int[] tree;
		int[] a;
		int result = 0;
		public SegmentTree(int[] a)
		{
			this.a = a;
			tree = new int[4 * a.length];
			build(0, a.length - 1, 0);
			System.out.println(Arrays.toString(tree));
		}
		
		public int build(int s, int e, int index)
		{
			if(s == e)
			{
				tree[index] = a[s];
				result = (result + a[s]) % mod;
				return tree[index];
			}
			int mid = (s + e) >> 1;
			int p1 = build(s, mid, (index << 1) + 1);
			int p2 = build(mid + 1, e, (index << 1) + 2);
			result = (result + Math.min(p1,  p2)) % mod;
			return tree[index] = Math.min(p1, p2);
		}
	}
}
