/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package numberpyramidsolver;

/**
 *This is to make a structure to store the number pyramid that will make the 
 * recursive structure of the main program much easier. 
 * @author jacobregenstein
 */
public class NumberNode {
    private int number;
    private NumberNode leftChild;
    private NumberNode rightChild;
    private NumberNode leftParent;
    private NumberNode rightParent;
    
    public NumberNode(int number){
        this.number = number;
    }
    
    public void setLeftChild(int number){
        leftChild = new NumberNode(number);
        leftChild.setRightParent(this);
    }
    
    public void setRightChild(int number){
        rightChild = new NumberNode(number);
        rightChild.setLeftParent(this);
    }
    
    public void setLeftChild(NumberNode node){
        leftChild = node;
        leftChild.setRightParent(this);
    }
    
    public void setRightChild(NumberNode node){
        rightChild = node;
        rightChild.setLeftParent(this);
    }
    
    private void setLeftParent(NumberNode node){
        leftParent = node;
    }
    
    private void setRightParent(NumberNode node){
        rightParent = node;
    }
    
    public int getNumber(){
        return number;
    }
    
    public int sumLeftChildren(){
        if(leftChild == null){
            return 0;
        }else{
            return number + leftChild.sumLeftChildren();
        }
    }

    public int sumRightChildren(){
        if(rightChild == null){
            return 0;
        }else{
            return number + rightChild.sumRightChildren();
        }
    }
    
    public String toString(){
        return String.valueOf(number);
    }
}
