/*
Filename: Node.java
Author: Stephen Jones
Date: 02DEC2018
Purpose: Project 3 class that defines the Node used in the BinarySearchTree.java.
Since all the variables are private, included appropriate getter and setter 
methods.

References used to help create code:

    Banas, D. (2013, March 28). Binary Tree in Java. Retrieved November 28, 2018, 
from http://www.newthinktank.com/2013/03/binary-tree-in-java/

 */
package sortingprogrammain;

//Generic class
public class Node<T> {
    
    //Variables
    private T value;
    private Node leftNode;
    private Node rightNode;
    
    //Constructor
    public Node(T value){
        this.value = value;
    }
    
    //Methods
    //Getter and Setter
    public T getValue(){
        return value;
    }
    public Node getLeftNode(){
        return leftNode;
    }
    public Node getRightNode(){
        return rightNode;
    }
    public void setLeftNode(Node newNode){
        leftNode = newNode;
    }
    public void setRightNode(Node newNode){
        rightNode = newNode;
    }
    @Override
    public String toString(){
        return value + "";
    }
    
}
