/* Sorts.java */

package sort;

public class Sorts {

  /**
   *  Place any final static fields you would like to have here.
   **/


  /**
   *  countingSort() sorts an array of int keys according to the
   *  values of _one_ of the base-16 digits of each key.  "whichDigit"
   *  indicates which digit is the sort key.  A zero means sort on the least
   *  significant (ones) digit; a one means sort on the second least
   *  significant (sixteens) digit; and so on, up to a seven, which means
   *  sort on the most significant digit.
   *  @param key is an array of ints.  Assume no key is negative.
   *  @param whichDigit is a number in 0...7 specifying which base-16 digit
   *    is the sort key.
   *  @return an array of type int, having the same length as "keys"
   *    and containing the same keys sorted according to the chosen digit.
   *
   *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
   **/
  public static int[] countingSort(int[] keys, int whichDigit) {
    // Replace the following line with your solution.
    int[] counts = new int[16];
    for (int i = 0; i<keys.length; i++){
      int val = keys[i];
      int hexDigit = val >> whichDigit*4;
      hexDigit = hexDigit & 15;
      counts[hexDigit]++;
    }
    int total = 0;
    for (int j = 0; j<counts.length; j++){
      int c = counts[j];
      counts[j] = total;
      total = total + c;
    }
    int[] y = new int[keys.length];
    for (int i = 0; i<keys.length; i++){
      int val = keys[i];
      int hexDigit = val >> whichDigit*4;
      hexDigit = hexDigit & 15;
      y[counts[hexDigit]] = keys[i];
      counts[hexDigit]++;
    }
    return y;
  }

  /**
   *  radixSort() sorts an array of int keys (using all 32 bits
   *  of each key to determine the ordering).
   *  @param key is an array of ints.  Assume no key is negative.
   *  @return an array of type int, having the same length as "keys"
   *    and containing the same keys in sorted order.
   *
   *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
   **/
  public static int[] radixSort(int[] keys) {
    // Replace the following line with your solution.
    int[] sorted = keys;
    for (int i = 0; i<=7; i++) {
      sorted = countingSort(sorted,i);
    }
    return sorted;
  }

  /**
   *  yell() prints an array of int keys.  Each key is printed in hexadecimal
   *  (base 16).
   *  @param key is an array of ints.
   **/
  public static void yell(int[] keys) {
    System.out.print("keys are [ ");
    for (int i = 0; i < keys.length; i++) {
      System.out.print(Integer.toString(keys[i], 16) + " ");
    }
    System.out.println("]");
  }

  public static void showInts(int[] keys) {
    System.out.print("keys are [ ");
    for (int i = 0; i < keys.length; i++) {
      System.out.print(keys[i] + " ");
    }
    System.out.println("]");
  }

  /**
   *  main() creates and sorts a sample array.
   *  We recommend you add more tests of your own.
   *  Your test code will not be graded.
   **/
  public static void main(String[] args) {
    int[] keys = { Integer.parseInt("60013879", 16),
                   Integer.parseInt("11111119", 16),
                   Integer.parseInt("2c735010", 16),
                   Integer.parseInt("2c732010", 16),
                   Integer.parseInt("7fffffff", 16),
                   Integer.parseInt("4001387c", 16),
                   Integer.parseInt("10111119", 16),
                   Integer.parseInt("529a7385", 16),
                   Integer.parseInt("1e635010", 16),
                   Integer.parseInt("28905879", 16),
                   Integer.parseInt("00011119", 16),
                   Integer.parseInt("00000000", 16),
                   Integer.parseInt("7c725010", 16),
                   Integer.parseInt("1e630010", 16),
                   Integer.parseInt("111111e5", 16),
                   Integer.parseInt("61feed0c", 16),
                   Integer.parseInt("3bba7387", 16),
                   Integer.parseInt("52953fdb", 16),
                   Integer.parseInt("40013879", 16) };
    yell(keys);
    for (int i = 0; i<=7; i++)  {
      System.out.println("Sorting on digit "+i);
      keys = countingSort(keys,i);
      yell(keys);
    }
    System.out.println("Radix Sorting");
    keys = radixSort(keys);
    yell(keys);
    showInts(keys);

    System.out.println("Sorting new keys");
    System.out.println("keys in hex are");
    int[] keys2 = {12,134,3,235,345,6,45,6,5,6,54,234,75675,675645634,534635,657,568678,67856,75467,4563,4563,453,4532,42,342,34,23423,424,2342,3423,4234,234,34234,23,4,234,23,4,234,23,4,234,23423,4234,123,2,3,2,32,32,34986,248785,38749,2374,986,4893,2848,34,5,7656,3487,2057,1975,450836,140752,160748,23947,347826,43475,24837,348,97,5,4,54325,543};
    yell(keys2);
    System.out.println("keys in dec are");
    showInts(keys2);
    System.out.println("Radix Sorting");
    keys2 = radixSort(keys2);
    yell(keys2);
    showInts(keys2);
  }

}
