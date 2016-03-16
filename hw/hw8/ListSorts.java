/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    LinkedQueue outQueue = new LinkedQueue();
    try {
      while (!q.isEmpty()){
        LinkedQueue inQueue = new LinkedQueue();
        inQueue.enqueue(q.dequeue());
        outQueue.enqueue(inQueue);
      }
    } catch (QueueEmptyException e) {
      System.out.println(e);
    }
    return outQueue;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue merged = new LinkedQueue();
    try {
      while (!q1.isEmpty() && !q2.isEmpty()){
        Comparable q1front = (Comparable) q1.front();
        Comparable q2front = (Comparable) q2.front();
        if (q1front.compareTo(q2front)<0){
          merged.enqueue(q1.dequeue());
        } else if(q1front.compareTo(q2front)>0) {
          merged.enqueue(q2.dequeue());
        } else {
          merged.enqueue(q1.dequeue());
          merged.enqueue(q2.dequeue());
        }
      }
      if (q1.isEmpty()&&!q2.isEmpty()) {
        while (!q2.isEmpty()){
          merged.enqueue(q2.dequeue());
        }
      } else if (q2.isEmpty()&&!q1.isEmpty()) {
        while (!q1.isEmpty()) {
          merged.enqueue(q1.dequeue());
        }
      }
    } catch (QueueEmptyException e) {
      System.out.println(e);
    }
    return merged;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    try {
      while (!qIn.isEmpty()) {
        Comparable comp = (Comparable) qIn.dequeue();
        if (comp.compareTo(pivot)<0) {
          qSmall.enqueue(comp);
        } else if (comp.compareTo(pivot)>0) {
          qLarge.enqueue(comp);
        } else {
          qEquals.enqueue(comp);
        }
      }
    } catch (QueueEmptyException e) {
      System.out.println(e);
    }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    LinkedQueue queueception = makeQueueOfQueues(q);
    try {
      while (queueception.size()>1) {
        LinkedQueue one = (LinkedQueue) queueception.dequeue();
        LinkedQueue two = (LinkedQueue) queueception.dequeue();
        queueception.enqueue(mergeSortedQueues(one,two));
      }
      q.append((LinkedQueue) queueception.dequeue());
    } catch (QueueEmptyException e) {
      System.out.println(e);
    }
  }
  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
    if (q.size()>1) {
      int nPivot = new Integer((int) (q.size() * Math.random())) + 1;
      Comparable pivot = (Comparable) q.nth(nPivot);
      LinkedQueue small = new LinkedQueue();
      LinkedQueue equals = new LinkedQueue();
      LinkedQueue large = new LinkedQueue();
      partition(q,pivot,small,equals,large);
      quickSort(small);
      quickSort(large);
      q.append(small);
      q.append(equals);
      q.append(large);
    }
  }
  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }
  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {
    LinkedQueue q1 = new LinkedQueue();
    q1.enqueue(3);
    q1.enqueue(5);
    q1.enqueue(2);
    System.out.println("Queue q1: "+q1);
    System.out.println("After makeQueueOfQueues(): "+makeQueueOfQueues(q1));
    System.out.println("q1: "+q1);

    LinkedQueue q2 = new LinkedQueue();
    q2.enqueue(1);
    q2.enqueue(2);
    q2.enqueue(5);
    q2.enqueue(6);
    q2.enqueue(7);
    q2.enqueue(9);
    LinkedQueue q3 = new LinkedQueue();
    q3.enqueue(3);
    q3.enqueue(5);
    q3.enqueue(8);
    q3.enqueue(8);
    q3.enqueue(10);
    q3.enqueue(15);
    System.out.println("Queue q2: "+q2);
    System.out.println("Queue q3: "+q3);
    System.out.println("After mergeSortedQueues(): "+mergeSortedQueues(q2,q3));
    System.out.println("Queue q2: "+q2);
    System.out.println("Queue q3: "+q3);

    System.out.println("MergeSort");
    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    LinkedQueue q4 = new LinkedQueue();
    q4.enqueue(4);
    q4.enqueue(7);
    q4.enqueue(1);
    q4.enqueue(5);
    q4.enqueue(9);
    q4.enqueue(3);
    q4.enqueue(0);
    System.out.println("Queue q4: "+q4);
    System.out.println("Partitioning q4");
    LinkedQueue q4Small = new LinkedQueue();
    LinkedQueue q4Equals = new LinkedQueue();
    LinkedQueue q4Large = new LinkedQueue();
    partition(q4, 4, q4Small, q4Equals, q4Large);
    System.out.println("Queue q4Small: "+q4Small);
    System.out.println("Queue q4Equals: "+q4Equals);
    System.out.println("Queue q4Large: "+q4Large);
    System.out.println("Queue q4: "+q4);

    System.out.println("QuickSort");
    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    System.out.println("Sorting empty queue");
    LinkedQueue emptyQueue = new LinkedQueue();
    System.out.println("Empty queue: "+emptyQueue);
    quickSort(emptyQueue);
    System.out.println("after sort: "+emptyQueue);

    System.out.println("Sorting one item queue");
    LinkedQueue oneQueue = new LinkedQueue();
    oneQueue.enqueue(1);
    System.out.println("one item queue: "+oneQueue);
    quickSort(oneQueue);
    System.out.println("after sort: "+oneQueue);


    // Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
  
  }

}
