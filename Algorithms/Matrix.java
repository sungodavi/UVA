import java.util.*;

public class Matrix 
{
	int[][] a;
	int n, m;
	public Matrix(int[][] a)
	{
		this.a = a;
		n = a.length;
		m = a[0].length;
	}
	
	public static Matrix I(int size)
	{
		int[][] a = new int[size][size];
		for(int r = 0; r < size; r++)
			a[r][r] = 1;
		return new Matrix(a);
	}
	
	public static int fib(int n)
	{
		Matrix m = new Matrix(new int[][] {{1, 1}, {1, 0}});
		return m.pow(n).a[0][1];
	}
	
	public Matrix matMul(Matrix mat)
	{
		if(m != mat.n)
			return null;
		int[][] result = new int[n][mat.m];
		for(int r = 0; r < result.length; r++)
			for(int c = 0; c < result[0].length; c++)
				for(int k = 0; k < m; k++)
					result[r][c] += a[r][k] * mat.a[k][c];
		return new Matrix(result);
	}
	
	public Matrix pow(int e)
	{
		Matrix base = new Matrix(a);
		Matrix result = I(n);
		while(e > 0)
		{
			if((e & 1) != 0)
				result = result.matMul(base);
			base = base.matMul(base);
			e >>= 1;
		}
		return result;
	}
	
	public String toString()
	{
		String s = "";
		for(int i = 0; i < a.length - 1; i++)
			s += Arrays.toString(a[i]) + "\n";
		return s + Arrays.toString(a[n - 1]);
	}
}

class Test
{
	public static void main(String[] args)
	{
		Matrix fib = new Matrix(new int[][]{{1, 1}, {1, 0}});
		for(int i = 1; i <= 40; i++)
		{
			System.out.println(Matrix.fib(i));
		}
	}
}
