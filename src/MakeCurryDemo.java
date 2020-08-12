import java.util.Arrays;

public class MakeCurryDemo {
	
	static boolean check(int[] a, int selection, int target)
	{
		int sum = 0;
		int len = a.length;
		for(int i=0;i<len;i++)
		{
			if(((selection>>i) & 1) == 1)
				sum += a[i];
		}
		return sum==target;
	}

	
	static int[] exclude(int[] a, int selection)
	{
		int[] res = new int[a.length];
		int j = 0;
		for(int i=0;i<a.length;i++)
		{
			if(((selection>>i) & 1) == 0)
				res[j++] = a[i];
		}
		return Arrays.copyOf(res, j);
	}

	static String makeCurry(int[] a)
	{
		int sum = 0;
		for(int x : a)
			sum += x;
		if(sum%3 > 0)
			return "noLuck";
		int target = sum/3;
		int max1 = 1<<a.length;
		for(int i=0;i<max1;i++)
		{
			if(check(a, i, target))
			{
				int[] b = exclude(a, i);
				int max2 = 1<<b.length; 
				for(int j=0;j<max2;j++)
				{
					if(check(b, j, target))
						return formatSolution(i, j, a.length);
				}
			}
		}
		return "noLuck";
	}

	static String formatSolution(int p, int q, int len)
	{
		char[] res = new char[len];
		Arrays.fill(res, 'R');
		int j = 0;
		for(int i=0;i<len;i++)
		{
			if(((p>>i) & 1) == 1)
				res[i] = 'P';
			else
			{
				if(((q>>j) & 1) == 1)
					res[i] = 'Q';
				j++;
			}
		}
		return new String(res);
	}

	public static void main(String[] args)
	{
		int[] a = new int[]{5, 4, 3, 3, 3, 3, 3, 3};
		System.out.println(makeCurry(a));
	}

}
