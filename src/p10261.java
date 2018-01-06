import java.util.*;
import java.io.*;

public class p10261 
{
	static TreeMap<State, Integer> dp = new TreeMap<State, Integer>();
	static int limit;
	static ArrayList<Integer> list;
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		boolean flag = false;
		while(times-- > 0)
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			limit = Integer.parseInt(f.readLine()) * 100;
			list = new ArrayList<Integer>();
			for(int length = Integer.parseInt(f.readLine()); length > 0; length = Integer.parseInt(f.readLine()))
				list.add(length);
			dp = new TreeMap<State, Integer>();
			System.out.println(recurse(0, 0, 0));
			//System.out.println(dp);
			display(0, 0, 0);
			f.readLine();
		}
	}
	
	public static void display(int index, int left, int right)
	{
		if(index == list.size())
			return;
		//System.out.println(index + " " + left + " " + right + " " + dp[index][left][right]);
		int length = list.get(index);
		int target = dp.get(new State(index, left, right));
		if(left + length <= limit && recurse(index + 1, left + length, right) + 1 == target)
		{
			System.out.println("port");
			display(index + 1, left + length, right);
		}
		else if(right + length <= limit && recurse(index + 1, left, right + length) + 1 == target)
		{
			System.out.println("starboard");
			display(index + 1, left, right + length);
		}
	}
	
	public static int recurse(int index, int left, int right)
	{
		if(index == list.size())
			return 0;
		
		State s = new State(index, left, right);
		
		if(dp.containsKey(s))
			return dp.get(s);
		
		int result = 0;
		int length = list.get(index);
		if(left + length <= limit)
			result = Math.max(result, recurse(index + 1, left + length, right) + 1);
		if(right + length <= limit)
			result = Math.max(result, recurse(index + 1, left, right + length) + 1);

		dp.put(s, result);
		return result;
	}
	
	static class State implements Comparable<State>
	{
		int index, left, right;
		public State(int index, int left, int right)
		{
			this.index = index;
			this.left = left;
			this.right = right;
		}
		
		public int compareTo(State s)
		{
			if(index != s.index)
				return index - s.index;
			if(left != s.left)
				return left - s.left;
			return right - s.right;
		}
		
		public String toString()
		{
			return index + " " + left + " " + right;
		}
	}
}