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
//	Time complexity TC = O(logN)
//	The for loop says for(int i = 0, i < N, i *= 2) -- it isn't incrementing i normally, 
//	instead it doubles it with each iteration. 
//	That means that the loop will iterate fewer times, and leads to a time complexity of log base 2 of N, 
//	or lb(N), which is actually more efficient than O(N).
}
