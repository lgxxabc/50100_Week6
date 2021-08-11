package Week6;

public class TimeComplexity3 {
//	Find the time complexity of the following Java method:
	int bar(int N) {
		int result = 1;
		for (int i = 1; i < N; i *= 2)
			result += 2;
		return result;
	}

//	Solution:
//	Time complexity TC = O(N)
}
