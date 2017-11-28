import java.util.*;
import java.io.*;

public class p11402 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(int num = 1; num <= times; num++)
		{
			System.out.println("Case " + num + ":");
			StringBuilder s = new StringBuilder();
			int parts = Integer.parseInt(f.readLine());
			while(parts-- > 0)
			{
				int k = Integer.parseInt(f.readLine());
				String part = f.readLine();
				while(k-- > 0)
				{
					s.append(part);
				}
			}
			SegmentTree tree = new SegmentTree(s.toString());
			int count = 1;
			for(int i = Integer.parseInt(f.readLine()); i > 0; i--)
			{
				char type = (char)f.read();
				StringTokenizer st = new StringTokenizer(f.readLine());
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				if(type == 'F')
					tree.update(left, right, 1);
				else if(type == 'E')
					tree.update(left, right, -1);
				else if(type == 'I')
					tree.update(left, right, 2);
				else
					System.out.printf("Q%d: %d\n", count++, tree.query(left, right));
			}
		}
	}
	
	static class SegmentTree
	{
		int[] tree;
		int[] lazy;
		int n;
		String state;
		public SegmentTree(String s)
		{
			state = s;
			tree = new int[4 * s.length()];
			lazy = new int[4 * s.length()];
			n = s.length();
			build(0, n - 1, 0);
		}
		
		public void update(int s, int e, int type)
		{
			update(0, n - 1, s, e, type, 0);
		}
		
		public int query(int s, int e)
		{
			return query(0, n - 1, s, e, 0);
		}
		
		public int build(int left, int right, int index)
		{
			if(left == right)
				return tree[index] = state.charAt(left) - '0';
			int mid = (left + right) / 2;
			int p1 = build(left, mid, (index << 1) + 1);
			int p2 = build(mid + 1, right, (index << 1) + 2);
			return tree[index] = p1 + p2;			
		}
		
		public void updateLazy(int s, int e, int index)
		{
			if(lazy[index] != 0)
			{
				int update = lazy[index];
				if(update == 1)
				{
					tree[index] = e - s + 1;
					if(s != e)
						lazy[(index << 1) + 1] = lazy[(index << 1) + 2] = 1;
				}
				else if(update == 2)
				{
					tree[index] = e - s + 1 - tree[index];
					if(s != e)
					{
						int left = (index << 1) + 1;
						int right = (index << 1) + 2;
						flipLazy(left);
						flipLazy(right);
					}
				}
				else
				{
					tree[index] = 0;
					if(s != e)
						lazy[(index << 1) + 1] = lazy[(index << 1) + 2] = -1;
				}
				lazy[index] = 0;
			}
		}
		public void flipLazy(int index)
		{
			if(lazy[index] == 2)
				lazy[index] = 0;
			else if(lazy[index] == 1)
				lazy[index] = -1;
			else if(lazy[index] == 0)
				lazy[index] = 2;
			else
				lazy[index] = 1;
		}
		public int query(int s, int e, int qs, int qe, int index)
		{
			updateLazy(s, e, index);
			if(s > e || e < qs || s > qe)
				return 0;
			if(s >= qs && e <= qe)
				return tree[index];
			int mid = (s + e) / 2;
			int p1 = query(s, mid, qs, qe, (index << 1) + 1);
			int p2 = query(mid + 1, e, qs, qe, (index << 1) + 2);
			return p1 + p2;
		}
		
		public void update(int s, int e, int qs, int qe, int type, int index)
		{
			updateLazy(s, e, index);
			if(s > e || s > qe || e < qs)
				return;
			if(s >= qs && e <= qe)
			{
				if(type == -1)
					tree[index] = 0;
				else if(type == 1)
					tree[index] = e - s + 1;
				else
					tree[index] = e - s + 1 - tree[index];
				if(s != e)
				{
					if(type == 2)
					{
						flipLazy((index << 1) + 1);
						flipLazy((index << 1) + 2);
					}
					else
						lazy[(index << 1) + 1] = lazy[(index << 1) + 2] = type;
				}
			}
			else if(s != e)
			{
				int mid = (s + e) / 2;
				update(s, mid, qs, qe, type, (index << 1) + 1);
				update(mid + 1, e, qs, qe, type, (index << 1) + 2);
				tree[index] = tree[(index << 1) + 1] + tree[(index << 1) + 2];
			}
		}
	}
}
