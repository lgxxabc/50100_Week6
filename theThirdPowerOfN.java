package Week6;

public class theThirdPowerOfN {
	int N;
	
	public int Test(int N) {
		this.N = N;
		int x = 0;
		for (int l = 1; l <= N; l++) {
			for (int m = 1; m <= N; m++) {
				for (int n = 1; n <= N; n++) {
					x++;
				}
			}
		}
		return x;
	}
	
	public static void main(String[] args) {
		int N = 3;
		theThirdPowerOfN test = new theThirdPowerOfN();
		int result = test.Test(N);
		System.out.println(result);		// Output: 27
	}

}
