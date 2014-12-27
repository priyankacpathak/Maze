import java.io.*;
import java.util.*;

/**
 * Priyanka Pathak
 * COMS 3134 - Fall 2010
 * Homework Assignment Four - Maze Assignment
 * December 16, 2010
 * 
 */

public class DisjointedSet implements Serializable{
	//Tree-based implementation of disjointed set
  
	private int numElements;
	public int[] parent;
 
  public DisjointedSet(int numElements) {
    this.numElements = numElements;
    this.parent = new int[numElements];
		for (int i = 0; i < numElements; i++)
			parent[i] = i;
  }

	//Find root of particular node in set
	public int find(int i){
    if (parent[i] == i) return i;
    return find(parent[i]);
	}
	
	//Join two sets, using their roots and based on height
	public void union(int root1, int root2){
		if(parent[root2] < parent[root1]){
			parent[root1] = root2;
		}else{
			if(parent[root1] == parent[root2]){
				parent[root1]--;
			}
		parent[root2] = root1;	
		}
	}
	
}

