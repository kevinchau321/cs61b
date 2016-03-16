class Primes {
	public static void printPrimes(int n) {
		boolean[] prime = new boolean[n+1];
		int i;
		for (i=2; i <= n; i++){
			prime[i]=true; ///Prime until proven composite
		}
		for (int divisor = 2; divisor*divisor <= n; divisor++){
			if (prime[divisor]){
				for (i = 2 * divisor; i<=n; i = i +divisor){
					prime[i]=false;
				}
			}
		}
		for (i=2; i <=n; i++){
			if (prime[i]){
				System.out.print(" "+i);
			}
		}
	}




}