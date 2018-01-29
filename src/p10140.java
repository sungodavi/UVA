import java.util.*;
import java.io.*;

public class p10140 //UNSOLVED
{
	public static void main(String[] args)
	{
		BitSet a = new BitSet(Integer.MAX_VALUE);
		a.set(2, Integer.MAX_VALUE - 1);
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean flag = true;
		for(int i = 2; i < Integer.MAX_VALUE; i++)
		{
			if(a.get(i))
			{
				list.add(i);
				if(flag && i * i < 0)
					flag = false;
				if(flag)
					for(int j = i * i; j > 0 && j < Integer.MAX_VALUE; j += i)
						a.clear(j);
			}
		}
		System.out.println(list);
	}
}
