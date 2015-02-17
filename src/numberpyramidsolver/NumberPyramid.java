/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package numberpyramidsolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

/**
 *This is to store the numbernodes and allow easy quick access to the number at
 * specified coordinates and the parent and child nodes
 * @author jacobregenstein
 */
public class NumberPyramid {
    ArrayList<ArrayList<Integer>> intPyramid = new ArrayList<>();
    ArrayList<ArrayList<NumberNode>> nodePyramid = new ArrayList<>();//possibly not gonna use this
    //constructs the number pyramid given a text file with the numbers
    public NumberPyramid(File inputFile) throws FileNotFoundException{
        this.addIntsFromFile(inputFile);
        
      
    }
    
    //builds the pyramid of nodes from the intPyramid
    private void buildNumberPyramid(){
        for(int i = 0; i < intPyramid.size(); i++){
            
            for(int j = 0; j < intPyramid.get(i).size(); j++){
                
            }
        } 
    }
    
    //adds the numbers to the pyramid from the file
    private void addIntsFromFile(File inputFile) throws FileNotFoundException{
        intPyramid = new ArrayList<>();
        Scanner input = new Scanner(inputFile);
        while(input.hasNextLine()){
            intPyramid.add(toIntArray(input.nextLine()));
        }
    }
    
    public ArrayList<Integer> solve(){
        return solve(0);
    }
    
    private ArrayList<Integer> solve(int row){
        if(row == intPyramid.size()-2){
            return stepUp(intPyramid.get(row), intPyramid.get(row+1));
        }else{
            return stepUp(intPyramid.get(row), solve(row+1));
        }
    }
    
    private ArrayList<Integer> stepUp(ArrayList<Integer> row, ArrayList<Integer> sums){
        if(row.size() + 1 != sums.size()) System.out.println("Row sum size error");
        ArrayList<Integer> returnSums = new ArrayList<>();
        int newSum;
        for(int i = 0; i < row.size(); i++){
            if(sums.get(i)>sums.get(i+1)){
                newSum = row.get(i) + sums.get(i);
            }else{
                newSum = row.get(i) + sums.get(i+1);
            }
            returnSums.add(newSum);
        }
        return returnSums;
    }
    
    
    
    private int sumLeft(int x, int y){
        if(y == intPyramid.size()){
            return 0;
        }else{
            System.out.print(intPyramid.get(y).get(x) + " ");
            return intPyramid.get(y).get(x) + sumLeft(x,y+1);
        }
    }
    
    private int sumRight(int x, int y){
        if(y == intPyramid.size()){
            return 0;
        }else{
            System.out.print(intPyramid.get(y).get(x) + " ");
            return intPyramid.get(y).get(x) + sumLeft(x+1,y+1);
        }
    }
    
    //old approach, not sure why it doesn't work
    public int solveOld(int x, int y){
        System.out.print(intPyramid.get(y).get(x) + "(" + x + ", " + y + ")");
        System.out.println(" Leftsum: "+sumLeft(x,y)+" Rightsum: "+sumRight(x,y));
        if(y==intPyramid.size()-1){
            return intPyramid.get(y).get(x);
        }else if(sumRight(x,y) > sumLeft(x,y)){
            return intPyramid.get(y).get(x) + this.solveOld(x+1,y+1);
        }else{
            return intPyramid.get(y).get(x) + this.solveOld(x,y+1);
        }
    }
    //Takes a string of the form "21 43 2 12 48 2342" and converts it to an
    //array of integers. Currently all the numbers must have exaclty 2 digits
    private static ArrayList<Integer> toIntArray(String string){
        String s = string + " ";//adding the space to make sure it runs the right number of times
        ArrayList<Integer> ints = new ArrayList<>();
        for(int i = 0; s.contains(" "); i++){
            ints.add(Integer.parseInt(s.substring(0, s.indexOf(" "))));
            s = s.substring(s.indexOf(" ") + 1);
        }
        return ints;
    }
    
    
    
    
    
    
    
    
    
    
    
    
   /* 
    public NumberPyramid(ArrayList<Integer> inputNumbers){
        numbers = inputNumbers;
        nodes = new NumberNode[numbers.size()];
        for(int i = 0; i< numbers.size(); i++){
            nodes[i] = new NumberNode(numbers.get(i));
        }
        top = nodes[0];
        for(int y = 1; (y*y+y)/2 < numbers.size(); y++){
            for(int x = 1; x <= y; x++){
                nodeAt(x,y).setLeftChild(nodeAt(x,y+1));
                nodeAt(x,y).setRightChild(nodeAt(x+1,y+1));
            }
        }
    } 
    //returns the number at the specified coordinates, (1,1) is the peak node, 
    //(1,2) and (2,2) are it's children (counting from one makes mathematical
    //operations for lots of places in the code simpler, as it makes for easier 
    //use of the formula for triangular numbers
    public int numberAt(int x, int y){
        //(x^2+x)/2 is the formula for the xth triangular number
        return numbers.get((x*x+x)/2 + y - 1);
    }
    
    //same thing, just for nodes instead
    public NumberNode nodeAt(int x, int y){
        //(x^2+x)/2 is the formula for the xth triangular number
        return nodes[(x*x+x)/2 + y - 1];
    }
    
    //prints the pyramid, I used this to test if the pyramid was constructed
    //correctly
    public void print(){
        for(int y = 1; (y*y+y)/2 < numbers.size(); y++){
            for(int x = 1; x <= y; x++){
                System.out.print(nodeAt(x,y) + " ");
            }
            System.out.println();
        }
    }
    */
}
