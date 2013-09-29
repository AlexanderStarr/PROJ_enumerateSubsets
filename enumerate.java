// In eclipse, class name and file name must be the same.
// Each class has a separate file.

public class enumerate {

    // all runs start at "main"
    public static void main(String[] args) {
        enumerateSubsets(5);
        //enumerateCombinations(2, 5);
        //enumerate5Permutations();
    }


    // enumerate all subsets of the set { 1, 2, ..., n }, where n < 64.
    public static void enumerateSubsets (int n) {   
         // Pre: n < 64
         System.out.println("All subsets of " + n + " numbers:"); 
         for (int x = 0; x < (1L << n); x++) {
             System.out.print("{"); 
             for (int j = 1; j <= n; j++) {
            	 if ((x & (1L << (j-1))) != 0) System.out.print(j + ", ");
             }
             System.out.print("}\n"); 
         }
    }

    // print the first n elements of the array x as a set.
    public static void printArray(int x[], int n) {
    	System.out.print("{"); 
    	System.out.print(x[0]);
    	for (int i = 1; i < n; i++)
	    System.out.print(", " + x[i]);
    	System.out.print("}\n"); 
    }
    
    // print all k-combinations of n elements.
    public static void enumerateCombinations (int k, int n) {   
        int x[] = new int[100];    // k <= 100
	System.out.println("All " + k + "-combinations of " + n + " numbers:"); 
        for (int j = 0; j < k; j++) x[j] = j+1;
        while (true) {
             printArray(x, k);
             if (nextCombination(x, k, n) == false) break;
        }
    }

    // Recursively find the next k-combination of x.
    // Takes an index j in addition to all the other arguments nextCombination takes.
    static boolean rNextCombo (int x[], int k, int n, int j) {
    	if (j < 0) return false;
    	// If the element is under that index's maximum, increment it and 'reset' all following elements.
    	if (x[j] <= (n - k + j)) {
    		x[j]++;
    		for (int i = 1; i < k - j; i++) x[i+j] = x[j]+i;
    		return true;
    	}
    	// Otherwise, if the element is at its maximum, then check the proceeding index.
    	else {
    		return rNextCombo(x, k, n, j-1);
    	}
    }

    // modify the array x to generate the next k-combination from x.
    // In general, the first k-combination of n elements is { 1, 2, ..., k } 
    // and the last k-combination is { n-k+1, n-k+2, ..., n }.
    public static boolean nextCombination (int x[], int k, int n) {
    	// j starts at k-1
    	return rNextCombo(x, k, n, k-1);
    }
    
    
    // Helper function for nextPermutation, reverses the list x from indices l to r.
    static void reverse(int x[], int l, int r) {
    	while (l < r) {
    		int temp = x[l];
    		x[l] = x[r];
    		x[r] = temp;
    		l += 1;
    		r -= 1;
    	}
    }
    
    
    public static boolean nextPermutation (int x[], int n) {
    	int k = -1;
    	for (int i = n-2; i >= 0; i--) {
    		if (x[i] < x[i+1]) {
    			k = i;
    			break;
    		}
    	}
    	if (k < 0) return false;
   		for (int l = n-1; l > k; l--) {
    		if (x[k] < x[l]) {
    			int temp = x[k];
    			x[k] = x[l];
    			x[l] = temp;
    			reverse(x, k+1, n-1);
    		}
    	}
    	return true;
    }

    // This is an awkward method to print all 5! permutations of 5 elements.
    public static void enumerate5Permutations () {   
        // Pre: n = 5
	System.out.println("All permutations of 5 numbers:"); 
        for (int x1 = 1; x1 <= 5; x1++) 
             for (int x2 = 1; x2 <= 5; x2++) if (x1 != x2)
                  for (int x3 = 1; x3 <= 5; x3++) if (x3 != x1 && x3 != x2)
                      for (int x4 = 1; x4 <= 5; x4++) if (x4 != x1 && x4 != x2 && x4 != x3)
                           for (int x5 = 1; x5 <= 5; x5++) 
                               if (x5 != x1 && x5 != x2 && x5 != x3 && x5 != x4) {
				   System.out.print("[ ");
				   System.out.print(x1+", "+x2+", "+x3+", "+x4+", "+x5); 
				   System.out.print("]\n"); 
			       }
    }

}
