import java.util.*;
import java.io.*;

public class p10042 {
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(; times > 0; times--)
		{
			StringBuilder s = new StringBuilder();
			for(int temp = Integer.parseInt(f.readLine()); temp > 0; temp--)
			{
				int multiple = Integer.parseInt(f.readLine());
				String input = f.readLine();
				for(; multiple > 0; multiple--)
					s.append(input);
			}
			SegmentTree tree = new SegmentTree(s.toString());
			for(int k = Integer.parseInt(f.readLine()); k > 0; k--)
			{

			}
		}
	}
	
	static class SegmentTree
	{
		int[] tree;
		String s;
		public SegmentTree(String s)
		{
			tree = new int[4 * s.length()];
			this.s = s;
			build(0, s.length() - 1, 0);
		}
		
		public int build(int start, int end, int index)
		{
			if(start == end)
				return tree[index] = s.charAt(start) - '0';
			int mid = (start + end) / 2;
			return tree[index] = build(start, mid, (index << 1) + 1) + build(mid + 1, end, (index << 1) + 2); 
		}
		
		public int sum(int treeStart, int treeEnd, int start, int end, int index)
		{
			if(treeStart >= start && treeEnd <= end)
				return tree[index];
			if(treeStart > end || treeEnd < start)
				return 0;
			int mid = (treeStart + treeEnd) / 2;
			return sum(treeStart, mid, start, end, (index << 1) + 1) +
					sum(mid + 1, treeEnd, start, end, (index << 1) + 2);
		}
		
		public int sum(int start, int end)
		{
			return sum(0, s.length() - 1, start, end, 0);
		}
		
		public int clear(int treeStart, int treeEnd, int start, int end, int index)
		{
			if(treeStart > end || treeEnd < start)
				return tree[index];
			
			if(treeStart == treeEnd)
			{
				 return tree[index] = 0;
			}
			else
			{
				int mid = (treeStart + treeEnd) / 2;
				return tree[index] = clear(treeStart, mid, start, end, (index << 1) + 1) +
					clear(mid + 1, end, start, end, (index << 1) + 2);
			}
		}
	}
}

