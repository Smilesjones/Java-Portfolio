/*
Filename: SortingProgramMain.java
Author: Stephen Jones
Date: 02DEC2018
Purpose: Project 3 main class that defines the GUI, recieves user input, 
creates the object to perform sorting on input, and outputs the sorted input. 

References used to help create code:

    How to Use Borders. (n.d.). Retrieved November 23, 2018, from 
https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
 */

package sortingprogrammain;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class SortingProgramMain extends JFrame implements ActionListener {
    //Component Variables
    private JPanel mainPanel, inputPanel, inputNorth, outputCenter, 
            buttonPanel, radioMainPanel, radioLeftPanel, radioRightPanel;
    private JLabel originalLabel, sortedLabel, emptyLabel;
    private JTextField originalText, sortedText;
    private JButton performButton;
    private JRadioButton ascendingRB, descendingRB, integerRB, fractionRB;
    private ButtonGroup orderGroup, typeGroup;
    private Border blackLine;
    private TitledBorder sortTitle, numericTitle;
    private Box southBox;
    //String to highlight input if there is an exception thrown
    private String inputCheck = "";
    
    //Constructor
    public SortingProgramMain (){
        //Labels
        originalLabel = new JLabel("Original List");
        sortedLabel = new JLabel("Sorted List");
        emptyLabel = new JLabel("");
        
        //Textfields
        originalText = new JTextField(15);
        sortedText = new JTextField(15);
        sortedText.setEditable(false);
        sortedText.setBackground(Color.LIGHT_GRAY);
        
        //Button
        performButton = new JButton("Perform Sort");
        performButton.addActionListener(this);
        
        //RadioButtons
        ascendingRB = new JRadioButton("Ascending");
        descendingRB = new JRadioButton("Descending");
        integerRB = new JRadioButton("Integer     ");
        fractionRB = new JRadioButton("Fraction     ");
        ascendingRB.setSelected(true);
        integerRB.setSelected(true);
        
        //RadioButtons groups
        orderGroup = new ButtonGroup();
        typeGroup = new ButtonGroup();
        orderGroup.add(ascendingRB);
        orderGroup.add(descendingRB);
        typeGroup.add(integerRB);
        typeGroup.add(fractionRB);
        
        //Borders
        blackLine = BorderFactory.createLineBorder(Color.BLACK);
        sortTitle = BorderFactory.createTitledBorder(blackLine, "Sort Order");
        numericTitle = BorderFactory.createTitledBorder(blackLine, "Numeric Type");
        
        //Panels
        inputPanel = new JPanel(new BorderLayout()); 
        inputNorth = new JPanel();
        outputCenter = new JPanel(); 
        buttonPanel = new JPanel(new GridLayout(0,1));
        radioMainPanel = new JPanel(); 
        radioLeftPanel = new JPanel(new GridLayout(0,1));
        radioRightPanel = new JPanel(new GridLayout(0,1));
        
        //Borders around radio buttons
        radioLeftPanel.setBorder(sortTitle);
        radioRightPanel.setBorder(numericTitle);
        
        //Box to center button
        southBox = Box.createHorizontalBox();        
        
        //Add Components to Panels
        inputNorth.add(originalLabel);
        inputNorth.add(originalText);
        
        outputCenter.add(sortedLabel);
        outputCenter.add(sortedText);
        
        buttonPanel.add(performButton);
        buttonPanel.add(emptyLabel);
        
        radioMainPanel.add(radioLeftPanel);
        radioMainPanel.add(radioRightPanel);
        radioLeftPanel.add(ascendingRB);
        radioLeftPanel.add(descendingRB);
        radioRightPanel.add(integerRB);
        radioRightPanel.add(fractionRB);
        
        southBox.add(Box.createGlue());
        southBox.add(buttonPanel);
        southBox.add(Box.createGlue());
        
        inputPanel.add(inputNorth, BorderLayout.NORTH);
        inputPanel.add(outputCenter, BorderLayout.CENTER); 
        inputPanel.add(southBox, BorderLayout.SOUTH);
        
        //Add Panel sections to Frame
        add(inputPanel, BorderLayout.NORTH);
        add(radioMainPanel, BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String input = originalText.getText();
        //Remove excess spaces from beginning, end, and in between values
        input = input.trim().replaceAll(" +", " ");
        
        try{
            //Program flow based on Integer and Fraction selection first
            //Then on Ascending and Descending selection
            if(integerRB.isSelected()){
                //Seperate method for sorting to keep current method cleaner
                BinarySearchTree<Integer> integerTree = sortInteger(input);
                if (ascendingRB.isSelected()){
                    //Ascending sort
                    sortedText.setText(integerTree.inOrderTraverseTreeString(integerTree.getRoot()));
                }else{
                    //Descending sort
                    sortedText.setText(integerTree.reverseOrderTraverseTreeString(integerTree.getRoot()));
                }
            }else{
                //Like sortInteger method, but for the fraction input
                BinarySearchTree<Fraction> fractionTree = sortFraction(input);
                if (ascendingRB.isSelected()){
                    //Ascending sort
                    sortedText.setText(fractionTree.inOrderTraverseTreeString(fractionTree.getRoot()));
                }else{
                    //Descending sort
                    sortedText.setText(fractionTree.reverseOrderTraverseTreeString(fractionTree.getRoot()));
                }
            }
        }catch (NumberFormatException ne){
            //Tell user what should be input
            String correctInput = "";
            if (integerRB.isSelected()){
                correctInput = "Integers";
            }else{
                correctInput = "Fractions";
            }
                JOptionPane.showMessageDialog(null, 
                            "Inappropriate Numeric Input.\nPlease Correct Item: " 
                            + inputCheck +"\nPlease Input " + correctInput + 
                            " Only.", "Inappropriate Input", 
                            JOptionPane.ERROR_MESSAGE);
                    sortedText.setText("ERROR");
                    return;
            }
    }
    //Method that converts integer string to integers, then adds them 
    //to a binary search tree and returns that tree
    public BinarySearchTree<Integer> sortInteger(String integerInput){
        BinarySearchTree<Integer> integerTree = new BinarySearchTree<Integer>();
        //Seperate integers into an array based on space seperations
        String[] integerStringArray = integerInput.split(" ");
        int[] integerArray = new int[integerStringArray.length];
        for (int i = 0; i < integerStringArray.length; i++){
            inputCheck = integerStringArray[i];
            //Parse the string integers into int integers
            integerArray[i] = Integer.parseInt(integerStringArray[i]);
            //Recursively add the integers in the array to the BST
            integerTree.recursiveInsertNode(integerArray[i]);
        }
        return integerTree;
    } 
    //Method that creates fraction objects from fraction string, then adds them 
    //to a binary search tree and returns that tree
    public BinarySearchTree<Fraction> sortFraction(String fractionInput){
        BinarySearchTree<Fraction> fractionTree = new BinarySearchTree<Fraction>();
        //Seperate fractions into an array based on space seperations
        String[] fractionStringArray = fractionInput.split(" ");
        //Create a Fraction class array 
        Fraction[] fractionArray = new Fraction[fractionStringArray.length];
        for (int i = 0; i < fractionStringArray.length; i++){
            inputCheck = fractionStringArray[i];
            //Store new Fraction objects in fraction array
            fractionArray[i] = new Fraction(fractionStringArray[i]);
            //Method call to ensure proper fraction form
            fractionArray[i].checkForBadInput();
            //Recursively add the fractions to the BST 
            fractionTree.recursiveInsertNode(fractionArray[i]);
        }
        return fractionTree;
    }
    
    //Method to set the frame characteristics
    public static void setFrame(JFrame frame){
        frame.setName("Sorting Program");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);    
    }
    public static void main(String[] args) {
        SortingProgramMain sortWindow = new SortingProgramMain();
        setFrame(sortWindow);
    }

    
    
}
