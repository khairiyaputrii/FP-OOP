package com.example.snake_game;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Rectangle> body;

    private String move; //NOTHING, UP, DOWN, LEFT, RIGHT
    public Snake() {
        body = new ArrayList<>();

        Rectangle temp= new Rectangle(Game.dimension, Game.dimension);
        temp.setLocation(Game.width /2 * Game.dimension, Game.height / 2 * Game.dimension);
        body.add(temp);

        temp = new Rectangle(Game.dimension, Game.dimension);
        temp.setLocation((Game.width /2 - 2) * Game.dimension, (Game.height / 2 - 2) * Game.dimension);
        body.add(temp);

        move = "NOTHING";
    }
    public ArrayList<Rectangle> getBody() {
        return body;
    }
    public void setBody (ArrayList<Rectangle> body) {
        this.body = body;
    }
    public void up(){
        move = "UP";
    }
    public void down(){
        move = "DOWN";
    }
    public void left(){
        move = "LEFT";
    }
    public void right(){
        move = "RIGHT";
    }

}
