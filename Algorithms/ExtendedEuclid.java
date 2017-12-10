
public class ExtendedEuclid 
{
	static int x, y, d;
	
	static void extendedEuclid(int a, int b)
	{
		if(b == 0)
		{
			x = 1;
			y = 0;
			d = a;
			return;
		}
		extendedEuclid(b, a % b);
		int x1 = y;
		int y1 = x - (a / b) * y;
		x = x1;
		y = y1;
	}
}
