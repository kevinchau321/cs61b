class discussion3 {
	public int sum(int[] A) {
		int sum = 0;
		for (int i=0; i<A.length; i++){
			sum = sum + A[i];
		}
	}
	public void ShiftRight(int[] A){
		int last = A[A.length-1];
		for (int i=A.length-1; i>=A.length;i--) {
			A[i]=A[i-1];
		}
		A[0]= last;
	}
	public int[] resize(int[] A, int newSize, int init) {
		if (newSize == A.size) {
			return A;
		} else  if (newSize > A.size) {
			int[] newSizeArray = new int[newSize];
			int counter = 0;
			while (counter< A.length){
				newSizeArray[counter]=A[counter];
				counter++;
			}
			while (counter < newSize){
				newSizeArray[counter]=init;
				counter++;
			}
			return newSizeArray;
		} else {
			int[] smallArray = new int[newSize];
			for (int i =0; i<smallArray.size;i++) {
				smallArray[i]=A[i];
			}
			return smallArray;
		}
	}

	/*2D ARRAYS
	int[][] 2D = new init[3][];
	for (int i=0; i < 3, i++){
		2D[i] = new int[3];
	}
	int[][] twoD = new int[3][3];	
	int[] A = {1,3,5}
	int[][] 2D = {{1,3,5},{2,4,6}}
	*/
	public int[][] sum(int[][] A, int[][] B) {
		for (i=0; i<A.length; i++) {
			for(j=0; j<A.length;j++){
				A[i][j]=A[i][j]+B[i][j];
			}
		}
		return A;
	}
	public int[][] matrixProduct(int[][] A, int[][] B) {
		int[][] result = new int[A.length][A.length];
		for (i=0; i<A.length; i++) {
			int dotProduct = 0;
			for (j=0; j<A.length; j++){
				dotProduct = dotProduct +A[i][j]*B[j][i];
			result[]
			}
		}
	}

}
