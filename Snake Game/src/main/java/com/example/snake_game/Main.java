package com.example.snake_game;

import javafx.application.Application;
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

    private void newFood(){
        food = new Circle(random.nextInt(WIDTH),random.nextInt(HEIGHT),RADIUS);
        food.setFill(Color.RED);
        root.getChildren().add(food);
    }

    private void newSnake(){
        snake = new Circle(WIDTH/2, HEIGHT/2, RADIUS +2);
        root.getChildren().add(snake);
    }

//        @Override
    public void start(Stage primaryStage){
        root = new Pane();
        root.setPrefSize(WIDTH,HEIGHT);
        random = new Random();

        newFood();
        newSnake();
        Scene scene = new Scene(root, 600, 500);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event-> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP) {
                snake.setCenterY(snake.getCenterY() - STEP);
            } else if (code == KeyCode.DOWN) {
                snake.setCenterY(snake.getCenterY() + STEP);
            } else if (code == KeyCode.LEFT) {
                snake.setCenterX(snake.getCenterX() - STEP);
            } else if (code == KeyCode.RIGHT) {
                snake.setCenterX(snake.getCenterX() + STEP);
            }
        });
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
