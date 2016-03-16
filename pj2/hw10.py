from __future__ import division # for python versions < 3, so that 1/2 == 0.5
import numpy as np
from scipy.misc import comb # comb(n,k) computes "n choose k"
from matplotlib import pyplot as plt # for plotting

def dryer(n, k):
    """Returns the probability of at least two matching socks 
    when k socks drawn from n pairs of socks in the dryer (part c)"""
    return (comb(2*n,k)-comb(n,k)*math.pow(2,k))/comb(2*n,k)

def birthday(n, k):
    """Returns the probability of picking the same sock twice
    when drawing with replacement among n distinct socks (part d)"""
    return (math.pow(n,k)-permute(n,k))/math.pow(n,k)

def fact(x):
    if x == 0:
        return 1
    if x == 1:
        return 1:
    else:
        return x*fact(x-1)

def permute(n,k):
    return fact(n)/fact(n-k)

def plot_dryer_birthday_probabilities(n):
    """Generate plot for (part e) and saves it under socks.png"""
    plt.plot(range(1, n+1), [dryer(n, i) for i in range(1, n+1)], 'o--', label="Dryer")
    plt.plot(range(1, n+1), [birthday(n, i) for i in range(1, n+1)], 'x--', label="Birthday")
    plt.xlim(1, n)
    plt.ylim(0, 1)
    plt.yticks(np.arange(11)/10)
    plt.grid()
    plt.ylabel("P")
    plt.xlabel("k")
    plt.legend(loc=0)
    plt.savefig("socks.png", bbox_inches='tight', dpi=200)

plot_dryer_birthday_probabilities(15)
