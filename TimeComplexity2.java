package Week6;

public class TimeComplexity2 {
//	Find the time complexity of the following Java method:
	int foo(int N) {
		int result = 0;
		for (int i = 0; i < N; i++)
			result++;
		for (int j = 0; j < 1000000; j++)
			result += j;
		return result;
	}

//	Solution:
//	Time complexity TC = O(N)

}
