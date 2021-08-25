package Week6;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Scanner;

/*
 * =========================================
 * Name:             Guanxiaoxiong Liu 
 * Date:             08/11/2021
 * Course name:      Programming Foundations 
 * Semester:         Summer 2 
 * Assignment Name:  Program 3
 * =========================================
 */

public class NearestNeighbor {

	public static void main(String[] args) throws FileNotFoundException {

		String fileNameTrain, fileNameTest;
		Scanner fileScanTrain, fileScanTest;
		String lineScanTrain, lineScanTest;

		double[][] trainAttrArray = new double[75][4];
		double[][] testAttrArray = new double[75][4];
		String[] trainClassArray = new String[75];
		String[] testClassArray = new String[75];

		int count = 0;

		// Parameters for training example
		double slx, swx, plx, pwx;
		// Parameters for testing example
		double sly, swy, ply, pwy;

		double globalMin = Integer.MAX_VALUE;

		Scanner scan = new Scanner(System.in);
		
		printInfo();
		
		// 1. Prompts the user to enter filenames for the training and the testing
		// dataset files.
		System.out.print("Enter the name of the training file: ");
		fileNameTrain = scan.nextLine();
		String f1 = getInputStream(fileNameTrain);
        	fileScanTrain = new Scanner(f1);

		System.out.print("Enter the name of the testing file: ");
		fileNameTest = scan.nextLine();
		String f2 = getInputStream(fileNameTest);
		fileScanTest = new Scanner(f2);

		// 2. Loads and parses the training and testing dataset files into separate
		// arrays.
		// 2.1 For iris-training-data.csv
		while (fileScanTrain.hasNext()) {
			// Reads each line in the file.
			lineScanTrain = fileScanTrain.nextLine(); // String

			// Put each line into a string array
			String[] stringArray = lineScanTrain.split(","); // String --> String Array

			for (int i = 0; i < stringArray.length - 1; i++) {
				String eleStrTrain = stringArray[i];
				double eleTrain = Double.parseDouble(eleStrTrain);
				trainAttrArray[count][i] = eleTrain;
			}

			trainClassArray[count] = stringArray[4];

			count++;
		}

		count = 0;

		// 2.2 For iris-testing-data.csv
		while (fileScanTest.hasNext()) {
			// Reads each line in the file.
			lineScanTest = fileScanTest.nextLine(); // String

			// Put each line into a string array
			String[] stringArray = lineScanTest.split(","); // String --> String[]

			for (int i = 0; i < stringArray.length - 1; i++) {
				String eleStrTest = stringArray[i];
				double eleTrain = Double.parseDouble(eleStrTest);
				testAttrArray[count][i] = eleTrain;
			}

			testClassArray[count] = stringArray[4];

			count++;
		}

		// 3. Classifies each testing example.
		System.out.println("\n" + "EX#: TRUE LABEL, PREDICTED LABEL");

		String[] minStringArray = new String[75];

		for (int i = 0; i < testAttrArray.length; i++) {
			sly = testAttrArray[i][0];
			swy = testAttrArray[i][1];
			ply = testAttrArray[i][2];
			pwy = testAttrArray[i][3];

			for (int j = 0; j < trainAttrArray.length; j++) {
				slx = trainAttrArray[j][0];
				swx = trainAttrArray[j][1];
				plx = trainAttrArray[j][2];
				pwx = trainAttrArray[j][3];

				double result = distance(slx, swx, plx, pwx, sly, swy, ply, pwy);
				if (result < globalMin) {
					globalMin = result;
					int minIndex = j;
					minStringArray[i] = trainClassArray[minIndex];
				}
			}
			
			globalMin = Integer.MAX_VALUE;
			System.out.println((i + 1) + ": " + trainClassArray[i] + " " + minStringArray[i]);
		}
		
		// 4. Computes the accuracy.
		int matchNum = caluMatch(trainClassArray, minStringArray);
		double accuracy = caluAccu(matchNum, testClassArray.length);
		System.out.println("ACCURACY: " + accuracy);

		// Close the scanner streams.
		fileScanTrain.close();
		fileScanTest.close();
		scan.close();
	}
	
	private static void printInfo() {
		System.out.println("Programming Fundamentals");
		System.out.println("NAME: Guanxiaoxiong Liu");
		System.out.println("PROGRAMMING ASSIGNMENT 3");
		System.out.println();
	}
	
	private static double distance(double x1, double x2, double x3, double x4, double y1, double y2, double y3,
			double y4) {
		double dist = Math.sqrt(
				Math.pow((x1 - y1), 2) + Math.pow((x2 - y2), 2) + Math.pow((x3 - y3), 2) + Math.pow((x4 - y4), 2));
		return dist;
	}

	private static int caluMatch(String[] array1, String[] array2) {
		int match = 0;
		if (array1.length == array2.length) {
			for (int i = 0; i < array1.length; i++) {
				if (array1[i].equals(array2[i])) {
					match++;
				}
			}
		}
		return match;
	}

	private static double caluAccu(int num, int totalNum) {
//		DecimalFormat fmt = new DecimalFormat("0.################");

		double accuracy = (double) num / totalNum;
//		String s = fmt.format(accuracy);
//		return Double.parseDouble(s);	// string --> double
		return accuracy;
	}
	
	private static String getInputStream(String file) {
		InputStream inputStream = PrintMessage.class.getResourceAsStream("/" + file);
		if (inputStream == null) {
			System.out.println("Input Stream is null");
		}
		String f1 = null;
		try {
			f1 = inputStreamToString(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f1;
	}
	
	private static String inputStreamToString(InputStream aInInputStream) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = aInInputStream.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, length);
		}
		return byteArrayOutputStream.toString(StandardCharsets.UTF_8.name());
	}
}



// Output:
/*
Programming Fundamentals
NAME: Guanxiaoxiong Liu
PROGRAMMING ASSIGNMENT 3

Enter the name of the training file: iris-training-data.csv
Enter the name of the testing file: iris-testing-data.csv

EX#: TRUE LABEL, PREDICTED LABEL
1: Iris-setosa Iris-setosa
2: Iris-setosa Iris-setosa
3: Iris-setosa Iris-setosa
4: Iris-setosa Iris-setosa
5: Iris-setosa Iris-setosa
6: Iris-setosa Iris-setosa
7: Iris-setosa Iris-setosa
8: Iris-setosa Iris-setosa
9: Iris-setosa Iris-setosa
10: Iris-setosa Iris-setosa
11: Iris-setosa Iris-setosa
12: Iris-setosa Iris-setosa
13: Iris-setosa Iris-setosa
14: Iris-setosa Iris-setosa
15: Iris-setosa Iris-setosa
16: Iris-setosa Iris-setosa
17: Iris-setosa Iris-setosa
18: Iris-setosa Iris-setosa
19: Iris-setosa Iris-setosa
20: Iris-setosa Iris-setosa
21: Iris-setosa Iris-setosa
22: Iris-setosa Iris-setosa
23: Iris-setosa Iris-setosa
24: Iris-setosa Iris-setosa
25: Iris-setosa Iris-setosa
26: Iris-versicolor Iris-versicolor
27: Iris-versicolor Iris-versicolor
28: Iris-versicolor Iris-versicolor
29: Iris-versicolor Iris-versicolor
30: Iris-versicolor Iris-versicolor
31: Iris-versicolor Iris-versicolor
32: Iris-versicolor Iris-versicolor
33: Iris-versicolor Iris-versicolor
34: Iris-versicolor Iris-versicolor
35: Iris-versicolor Iris-versicolor
36: Iris-versicolor Iris-versicolor
37: Iris-versicolor Iris-versicolor
38: Iris-versicolor Iris-versicolor
39: Iris-versicolor Iris-versicolor
40: Iris-versicolor Iris-versicolor
41: Iris-versicolor Iris-versicolor
42: Iris-versicolor Iris-versicolor
43: Iris-versicolor Iris-versicolor
44: Iris-versicolor Iris-versicolor
45: Iris-versicolor Iris-versicolor
46: Iris-versicolor Iris-virginica
47: Iris-versicolor Iris-versicolor
48: Iris-versicolor Iris-virginica
49: Iris-versicolor Iris-versicolor
50: Iris-versicolor Iris-versicolor
51: Iris-virginica Iris-virginica
52: Iris-virginica Iris-virginica
53: Iris-virginica Iris-virginica
54: Iris-virginica Iris-virginica
55: Iris-virginica Iris-virginica
56: Iris-virginica Iris-virginica
57: Iris-virginica Iris-versicolor
58: Iris-virginica Iris-virginica
59: Iris-virginica Iris-virginica
60: Iris-virginica Iris-virginica
61: Iris-virginica Iris-virginica
62: Iris-virginica Iris-virginica
63: Iris-virginica Iris-virginica
64: Iris-virginica Iris-virginica
65: Iris-virginica Iris-virginica
66: Iris-virginica Iris-virginica
67: Iris-virginica Iris-virginica
68: Iris-virginica Iris-virginica
69: Iris-virginica Iris-virginica
70: Iris-virginica Iris-versicolor
71: Iris-virginica Iris-virginica
72: Iris-virginica Iris-virginica
73: Iris-virginica Iris-virginica
74: Iris-virginica Iris-virginica
75: Iris-virginica Iris-virginica
ACCURACY: 0.9466666666666667
*/
