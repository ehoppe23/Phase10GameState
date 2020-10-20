/**
* @author Kirsten Foster, Alexis Molina, Emily Hoppe, Grace Penunuri
 * Holds information about each card (particularly number and color)
 * Also includes getters/setters for card info
 * Includes a "to string" method to explain what the card is
*/

package com.example.phase10gamestate;

public class Card {
    private int number; //1-12 number
    private int color; // 1-4 to represent the 4 colors
    //wild cards are 0,0
    //skip cards are -1,-1

    /**
     * sets number and color of card
     * @param n holds number of card
     * @param c holds color of card
     */
    public Card(int n, int c){
        this.number = n;
        this.color = c;
    }


    public void setNumber(int n){
        this.number = n;
    }
    public int getNumber(){
        return this.number;
    }

    public void setColor(int c){
       this.color = c;
    }
    public int getColor(){
        return this.color;
    }


    public boolean isWild(){
        return this.number == 0 && this.color == 0;
    }
    public boolean isSkip(){
        return this.number == -1 && this.color == -1;
    }

    /**
     * checks if card is wild or skip, otherwise checks color and number
     * @return information about card
     */
    public String toString(){
        String color = null;
        if(this.isSkip()){
            return "skip card";
        }
        else if(this.isWild()){
            return "wild card";
        }
        else{
            if(this.getColor()==1){
                color = "yellow";
            }
            else if(this.getColor()==2){
                color = "green";
            }
            else if(this.getColor()==3){
                color = "blue";
            }
            else if(this.getColor()==4){
                color = "red";
            }
            return color + " " + this.getNumber();
        }

    }

}
