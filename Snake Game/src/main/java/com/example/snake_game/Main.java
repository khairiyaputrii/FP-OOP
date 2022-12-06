package com.example.snake_game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private static final int  RADIUS = 6;
    private static final int STEP = 8;
    private Pane root;
    private Circle food;
    private Random random;
    private Circle snake;

    private Direction curretDirection;

    private void newFood(){
        food = new Circle(random.nextInt(WIDTH),random.nextInt(HEIGHT),RADIUS);
        food.setFill(Color.RED);
        root.getChildren().add(food);
    }

    private void newSnake(){
        snake = new Circle(WIDTH/2, HEIGHT/2, RADIUS +2);
        root.getChildren().add(snake);
    }

    private void step(){
        if (curretDirection == Direction.UP) {
            snake.setCenterY(snake.getCenterY() - STEP);
        } else if (curretDirection == Direction.DOWN) {
            snake.setCenterY(snake.getCenterY() + STEP);
        } else if (curretDirection == Direction.LEFT) {
            snake.setCenterX(snake.getCenterX() - STEP);
        } else if (curretDirection == Direction.RIGHT) {
            snake.setCenterX(snake.getCenterX() + STEP);
        }
    }
    private void move(){
        Platform.runLater(()->{
            step();
        });
    }

 @Override
    public void start(Stage primaryStage){
        root = new Pane();
        root.setPrefSize(WIDTH,HEIGHT);
        random = new Random();
        curretDirection = Direction.UP;

        newFood();
        newSnake();

        Runnable r = ()-> {
            try{
                for (;;){
                    move();
                    Thread.sleep(200);
                }
            } catch (InterruptedException ie){

            }
        };

        Scene scene = new Scene(root, 600, 500);
        scene.setFill(Color.web("81c483"));
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event-> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP) {
                curretDirection = Direction.UP;
            } else if (code == KeyCode.DOWN) {
                curretDirection = Direction.DOWN;
            } else if (code == KeyCode.LEFT) {
                curretDirection = Direction.LEFT;
            } else if (code == KeyCode.RIGHT) {
                curretDirection = Direction.RIGHT;
            }
        });
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        Thread th = new Thread(r);
        th.setDaemon(true);
        th.start();
    }

}

