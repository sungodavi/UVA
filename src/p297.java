import java.util.*;
import java.io.*;

public class p297 
{
	static class Tree
	{
		char[] tree;
		int index;
		public Tree(char[] a)
		{
			tree = a;
			index = 0;
		}
		public char curr()
		{
			return tree[index];
		}
		
		public int sum(int level)
		{
			if(tree[index] == 'e')
				return 0;
			if(tree[index] == 'f')
				return calc(level);
			int sum = 0;
			for(int i = 1; i <= 4; i++)
			{
				index++;
				sum += sum(level + 1);
			}
			return sum;
		}
		
		public static int calc(int level)
		{
			//System.out.println(level + " Adding " + (1024 / (1 << (level << 1))));
			return 1024 / (1 << (level << 1));
		}
	}
	static Tree a, b;
	static int index;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(int times = Integer.parseInt(f.readLine()); times > 0; times--)
		{
			a = new Tree(f.readLine().toCharArray());
			b = new Tree(f.readLine().toCharArray());
			System.out.printf("There are %d black pixels.\n", add(0));
			//System.out.println(a.index + " " + b.index);
		}
	}
	public static int add(int level)
	{
		if(a.curr() == 'f' || b.curr() == 'f')
		{
			if(a.curr() == 'p')
				a.sum(level);
			if(b.curr() == 'p')
				b.sum(level);
			return Tree.calc(level);
		}
		if(a.curr() == 'e' && b.curr() == 'p')
			return b.sum(level);
		if(a.curr() == 'p' && b.curr() == 'e')
			return a.sum(level);
		if(a.curr() == 'p' && b.curr() == 'p')
		{
			int sum = 0;
			for(int i = 1; i <= 4; i++)
			{
				a.index++;
				b.index++;
				sum += add(level + 1);
			}
			return sum;
		}
		return 0;
	}
}
