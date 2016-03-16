def printPrimes(n):
	primes = []
	for i in range(0,n+1):
		primes.append(True)
	divisor = 2
	while divisor*divisor<=n:
		if primes[divisor]==True:
			i = 2*divisor
			while i<=n:
				primes[i]=False
				i= i + divisor
	for i in range(2,n):
		if primes[i]==True:
			print(i)



