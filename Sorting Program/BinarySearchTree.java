/*
Filename: BinarySearchTree.java
Author: Stephen Jones
Date: 02DEC2018
Purpose: Project 3 class that defines the Binary Search Tree and provides 
recursive methods for inserting a new node and traversing the tree both in
order and in reverse order.

References used to help create code:

    1. Banas, D. (2013, March 28). Binary Tree in Java. Retrieved November 28, 2018, 
from http://www.newthinktank.com/2013/03/binary-tree-in-java/


    2. Binary Search Tree | Set 1 (Search and Insertion). (2018, September 04). 
Retrieved from 
https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/

 */
package sortingprogrammain;

public class BinarySearchTree<T extends Comparable> {
    //Variables
    private Node rootNode;
    private String inOrderString = ""; 
    private String reverseOrderString = "" ;
    
    //Constructor
    public BinarySearchTree(){
        rootNode = null;
    }
    
    //Methods
    public Node getRoot(){
        return rootNode;
    }
    
    //Method that initiates the recursive insertion
    public void recursiveInsertNode(T value){
        rootNode = insertRecursivePart(rootNode, value);
    }
    public Node insertRecursivePart(Node newNode, T value){
        //Base case to stop recursion 
        if(newNode == null){
            newNode = new Node(value);
            return newNode;
        }
        //If the value is less than the value of the current node, go left
        if(value.compareTo(newNode.getValue()) < 0 ){
            newNode.setLeftNode(insertRecursivePart(newNode.getLeftNode(), value));
        }else {//Else the value is equal or greater, go right (allows for duplicates)
            newNode.setRightNode(insertRecursivePart(newNode.getRightNode(), value));
        }
        return newNode;
    }
    
    //Method that recursively traverses tree in order and outputs a 
    //String of all the values
    public String inOrderTraverseTreeString(Node currentNode){
        if (currentNode != null){
            inOrderTraverseTreeString(currentNode.getLeftNode());
            inOrderString = inOrderString + " " + currentNode;
            inOrderTraverseTreeString(currentNode.getRightNode());
        }
        return inOrderString;
    }
   
    //Method that recursively traverses tree in reverse order and outputs a 
    //String of all the values
    public String reverseOrderTraverseTreeString(Node currentNode){
        if (currentNode != null){
            reverseOrderTraverseTreeString(currentNode.getRightNode());
            reverseOrderString = reverseOrderString + " " + currentNode;
            reverseOrderTraverseTreeString(currentNode.getLeftNode());
        }
        return reverseOrderString;
    }
    
    public String toString(Node currentNode){
        return currentNode.getValue() +"";
    }
}
