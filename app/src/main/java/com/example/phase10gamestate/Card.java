package com.example.phase10gamestate;

public class Card {
    private int number; //1-12 number
    private int color; // 1-4 to represent the 4 colors
    //wild cards are 0,0
    //skip cards are -1,-1

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

    public String toString(){
        if(this.isSkip()){
            return "skip card";
        }
        else if(this.isWild()){
            return "wild card";
        }
        else{
            return this.getColor() + " " + this.getNumber();
        }
    }
}
