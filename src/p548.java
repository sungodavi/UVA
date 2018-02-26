import java.util.*;
import java.io.*;

public class p548 
{
	static int[] in, post;
	static int index;
	static int best;
	static HashMap<Node, Integer> map;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			in = convert(input);
			post = convert(f.readLine());
			Node root = build(0, index = in.length - 1);
			map = new HashMap<Node, Integer>();
			System.out.println(minPath(root));
		}
	}
	
	public static int[] convert(String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		int[] a = new int[st.countTokens()];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(st.nextToken());
		return a;
	}
	
	public static void inOrder(Node n)
	{
		if(n == null)
			return;
		inOrder(n.left);
		System.out.print(n.val + " ");
		inOrder(n.right);
	}
	
	public static int minPath(Node n)
	{
		if(n == null)
			return Integer.MAX_VALUE;
		if(n.left == null && n.right == null)
		{
			map.put(n, n.val);
			return n.val;
		}
		int left = minPath(n.left);
		int right = minPath(n.right);
		int d1 = n.left == null ? Integer.MAX_VALUE : map.get(n.left);
		int d2 = n.right == null ? Integer.MAX_VALUE : map.get(n.right);
		if(d1 < d2)
		{
			map.put(n, d1 + n.val);
			return left;
		}
		else
		{
			map.put(n, d2 + n.val);
			return right;
		}
	}
	
	public static Node build(int s, int e)
	{
		if(s > e)
			return null;
		int val = post[index--];
		if(s == e)
			return new Node(val);
		int pos = indexOf(s, e, val);
		Node right = build(pos + 1, e);
		Node left = build(s, pos - 1);
		return new Node(val, left, right);
	}
	
	public static int indexOf(int s, int e, int val)
	{
		for(int i = s; i <= e; i++)
			if(in[i] == val)
				return i;
		return -1;
	}
	
	static class Node
	{
		int val;
		Node left, right;
		public Node(int val)
		{
			this.val = val;
		}
		
		public Node(int val, Node left, Node right)
		{
			this.val = val;
			this.left = left;
			this.right = right;
		}
		
		public int hashCode()
		{
			return val;
		}
	}
}