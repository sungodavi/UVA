
public class SegmentTree 
{
	int[] tree;
	int[] lazy;
	int n;
	
	int[] a;
	public SegmentTree(int[] a)
	{
		this.a = a;
		int n = a.length;
		tree = new int[4 * n];
		lazy = new int[4 * n];
		build(0, n - 1, 0);
	}
	
	public void build(int s, int e, int index)
	{
		if(s == e)
			tree[index] = a[s];
		else
		{
			int mid = (s + e) / 2;
			build(s, mid, l(index));
			build(mid + 1, e, r(index));
			tree[index] = tree[l(index)] + tree[r(index)];
		}
	}
	
	public int query(int s, int e, int qs, int qe, int index)
	{
		if(s > e || s > qe || e < qs)
			return 0;
		if(s >= qs && e <= qe)
			return tree[index];
		
		int mid = (s + e) / 2;
		int p1 = query(s, mid, qs, qe, l(index));
		int p2 = query(mid + 1, e, qs, qe, r(index));
		return p1 + p2;
	}
	
	public void update(int s, int e, int i, int diff, int index)
	{
		if(i >= s && i <= e)
		{
			tree[index] += diff;
			int mid = (s + e) / 2;
			update(s, mid, i, diff, l(index));
			update(mid + 1, e, i , diff, r(index));
		}
	}
	
	public int l(int index)
	{
		return (index << 1) + 1;
	}
	
	public int r(int index)
	{
		return (index << 1) + 2;
	}
}
