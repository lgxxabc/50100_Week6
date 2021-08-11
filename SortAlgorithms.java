package Week6;

public class SortAlgorithms {
	/*
	Trace the execution of the insertion and selection sort algorithms when executed on the following array of integers:
	{1, 29, 14, 15, 94}
	Show how the array will look like after each iteration of the outer loop.
	 
	Solution:
	Selection sort:
	Iteration 1:
	Scan right starting with 1. 1 is the smallest, exchange 1 and 1.
	{1, 29, 14, 15, 94}
	Iteration 2:
	Scan right starting with 29. 14 is the smallest, exchange 29 and 14.
	{1, 14, 29, 15, 94}
	Iteration 3:
	Scan right starting with 29. 15 is the smallest, exchange 29 and 15.
	{1, 14, 15, 29, 94}
	Iteration 4:
	Scan right starting with 29. 29 is the smallest, exchange 29 and 29.
	{1, 14, 15, 29, 94}

	Insertion sort:
	Iteration 1:
	1 is sorted. Shift nothing, insert 29.
	{1, 29}
	Iteration 2:
	1 and 29 are sorted. Shift 29 to the right, insert 14.
	{1, 14, 29}
	Iteration 3:
	1, 14 and 29 are sorted. Shift 29 to the right, insert 15.
	{1, 14, 15, 29}
	Iteration 4:
	1, 14, 15 and 29 are sorted. Shift nothing, insert 94.
	{1, 14, 15, 29, 94}
	*/
}
