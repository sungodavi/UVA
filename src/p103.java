import java.util.*;
import java.io.*;

public class p103
{
	static int[] dist;
	static boolean[][] matrix;
	static int[][] parent;
	static boolean[] visited;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int num = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			Box[] boxes = new Box[num];
			matrix = new boolean[num][num];
			for(int i = 0; i < boxes.length; i++)
			{
				int[] temp = new int[size];
				st = new StringTokenizer(f.readLine());
				for(int k = 0; k < temp.length; k++)
					temp[k] = Integer.parseInt(st.nextToken());
				Arrays.sort(temp);
				boxes[i] = new Box(temp, i + 1);
			}
			Arrays.sort(boxes);
			
			Box current = null;
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < num; i++)
			{
				if(current == null || current.canFit(boxes[i]))
				{
					list.add(boxes[i].index);
					current = boxes[i];
				}
			}
			System.out.println(list.size());
			for(int i = 0; i < list.size(); i++)
			{
				System.out.print(list.get(i) + (i == list.size() - 1 ? "" : " "));
			}
			System.out.println();
		}
	}
	
	static class Box implements Comparable<Box>
	{
		int[] a;
		int index;
		public Box(int[] a, int index)
		{
			this.a = a;
			this.index = index;
		}
		
		public int compareTo(Box b)
		{
			int i= 0;
			while(i < a.length && a[i] == b.a[i])
				i++;
			if(i == a.length)
				return 0;
			return a[i] - b.a[i];
		}
		
		public boolean canFit(Box b)
		{
			for(int i = 0; i < a.length; i++)
				if(a[i] >= b.a[i])
					return false;
			return true;
		}
		
		public String toString()
		{
			return index + " " + Arrays.toString(a);
		}
	}
}