import java.util.*;
import java.io.*;

public class p1254
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int size = Integer.parseInt(f.readLine());
		Word[] words = new Word[size];
		for(int i = 0; i < size; i++)
			words[i] = new Word(i + 1, f.readLine());
		Arrays.sort(words);
		int queries = Integer.parseInt(f.readLine());
		while(queries-- > 0)
		{
			ArrayList<Integer> list = new ArrayList<Integer>(10);
			String p = f.readLine();
			for(int i = 0; i < size && list.size() < 10; i++)
				if(words[i].contains(p))
					list.add(words[i].index);
			if(list.isEmpty())
				out.println(-1);
			else
			{
				boolean flag = false;
				for(int num : list)
				{
					if(flag)
						out.print(" ");
					else
						flag = true;
					out.print(num);
				}
				out.println();
			}
		}
		out.close();
	}
	
	static class Word implements Comparable<Word>
	{
		String t;
		int index;
		int[] sa, ra;
		public Word(int index, String t)
		{
			this.t = t;
			this.index = index;
			build();
		}
		
		public boolean contains(String p)
		{
			int low = 0;
			int high = sa.length - 1;
			while(low <= high)
			{
				int mid = low + high >> 1;
				int c = compare(p, mid);
				if(c == 0)
					return true;
				if(c > 0)
					low = mid + 1;
				else
					high = mid - 1;
			}
			return false;
		}
		
		public int compare(String p, int index)
		{
			for(int i = 0; i < p.length(); i++, index++)
			{
				if(index == t.length())
					return 1;
				if(p.charAt(i) != t.charAt(index))
					return p.charAt(i) - t.charAt(index);
			}
			return 0;
		}
		
		public void build()
		{
			t += '$';
			int n = t.length();
			sa = new int[n];
			ra = new int[n];
			for(int i = 0; i < n; i++)
			{
				sa[i] = i;
				ra[i] = t.charAt(i);
			}
			for(int k = 1; k < n; k <<= 1)
			{
				sort(k);
				sort(0);
				int[] temp = new int[n];
				int r = 0;
				for(int i = 1; i < n; i++)
				{
					if(ra[sa[i]] == ra[sa[i - 1]] && ra[sa[i] + k] == ra[sa[i - 1] + k])
						temp[sa[i]] = r;
					else
						temp[sa[i]] = ++r;
				}
				System.arraycopy(temp, 0, ra, 0, n);
			}
		}
		
		public void sort(int k)
		{
			int n = sa.length;
			int[] counts = new int[Math.max(255, n)];
			for(int i = 0; i < n; i++)
			{
				int num = i + k;
				counts[num < n ? ra[num] : 0]++;
			}
			for(int i = 1; i < counts.length; i++)
				counts[i] += counts[i - 1];
			int[] temp = new int[n];
			for(int i = n - 1; i >= 0; i--)
			{
				int num = sa[i] + k;
				temp[--counts[num < n ? ra[num] : 0]] = sa[i];
			}
			System.arraycopy(temp, 0, sa, 0, n);
		}
		
		public int compareTo(Word w)
		{
			int c = t.compareTo(w.t);
			if(c != 0)
				return c;
			return index - w.index;
		}
		
		public void display()
		{
			for(int num : sa)
				System.out.println(t.substring(num));
		}
		public String toString()
		{
			return t;
		}
	}
}