import java.util.*;
import java.io.*;

public class p10635
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 1; t <= times; t++)
		{
			st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			int[] a = new int[s1 + 1];
			int[] indices = new int[n * n + 1];
			Arrays.fill(indices, -1);
			st = new StringTokenizer(f.readLine());
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j <= s2; j++)
				indices[Integer.parseInt(st.nextToken())] = j;
			//System.out.println(Arrays.toString(indices));
			
			ArrayList<Integer> lcs = new ArrayList<Integer>();
			lcs.add(0);
			for(int i = 1; i < a.length; i++)
			{
				int val = a[i];
				if(indices[val] >= 0)
				{
					if(indices[val] > lcs.get(lcs.size() - 1))
						lcs.add(indices[val]);
					else
					{
						int index = search(lcs, indices[val]);
						lcs.set(index + 1, indices[val]);
					}
				}
				//System.out.println(lcs);
			}
			out.printf("Case %d: %d\n", t, lcs.size());
		}
		out.close();
	}
	
	public static int search(ArrayList<Integer> list, int val)
	{
		int low = 0;
		int high = list.size() - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(list.get(mid) < val && (mid == 0 || list.get(mid + 1) > val))
				return mid;
			if(list.get(mid) < val)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return -1;
	}
}