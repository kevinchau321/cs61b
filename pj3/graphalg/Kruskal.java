/* Kruskal.java */

package graphalg;

import graph.*;
import set.*;
import list.*;
import dict.*;
import sort.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {
  protected HashTableChained vertexHash;

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */
  public static WUGraph minSpanTree(WUGraph g) {
  	WUGraph tree = new WUGRAPH();
  	Object[] gVertices = g.getVertices();
    Edge[] edgeList = new Edge[2*g.edgeCount()];

  	for (int i = 0; i<gVertices.length;i++){
      Object gVertex = gVertices[i];
      vertexHash.insert(gVertex, i);
  		tree.addVertex(gVertex);
      Neighbors gVertNeighbors = gVertices[i].getNeighbors();
      for (int j = 0; j<gVertNeighbors.neighborList.length; j++){
        Object endVertex = gVertNeighbors.neighborList[j];
        int eWeight = gVertNeighbors.weightList[j];
        edgeList[i] = new Edge(gVertex, endVertex , eWeight);
      }
  	}

    LinkedQueue sortQueue = new LinkedQueue();
    Edge currEdge = edgeList[0];
    for (int k = 1; currEdge != null; k++) {
      sortQueue.enqueue(currEdge);
      currEdge = edgeList[k];
    }
    quickSort(sortQueue);

    DisjointSets treeDisjoint = new DisjointSets(sortQueue.size());
    int sortLim = sortQueue.size();
    for (int l = 0; i< sortLim; l++) {
      Edge e = (Edge) sortQueue.dequeue();
      if (treeDisjoint.find(vertexHash.find(e.vert1))!=treeDisjoint.find(vertexHash.find(e.vert2))) {
        treeDisjoint.union(treeDisjoint.find(vertexHash.find(e.vert1)),treeDisjoint.find(vertexHash.find(e.vert2)));
        tree.addEdge(e.vert1,e.vert2,e.weight);
      }
    }
    return tree;
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
  private static void partition(LinkedQueue qIn, Comparable pivot, 
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
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  private static void quickSort(LinkedQueue q) {
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





}
