package com.example.snake_game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Main extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private static final int  RADIUS = 6;

    private Pane root;
    private Text score;
    private Circle food;
    private Random random;
    private Snake snake;


    private void newFood(){
        food = new Circle(random.nextInt(WIDTH),random.nextInt(HEIGHT),RADIUS);
        food.setFill(Color.RED);
        root.getChildren().add(food);
    }

    private void newSnake(){
        snake = new Snake(WIDTH/2, HEIGHT/2, RADIUS +2);
        root.getChildren().add(snake);
    }

    private boolean hit(){
        return food.intersects(snake.getBoundsInLocal());
    }

    private void move(){
        Platform.runLater(()->{
            snake.step();
            adjustLocation();
            if (hit()){
                snake.eat(food);
                score.setText(""+snake.getLength());
                newFood();
            }
        });
    }

    private void adjustLocation(){
        if(snake.getCenterX()< 0){
            snake.setCenterX(WIDTH);
        }
        else if (snake.getCenterX() > WIDTH) {
            snake.setCenterX(0);
        }
        if(snake.getCenterY()< 0){
            snake.setCenterY(HEIGHT);
        }
        else if (snake.getCenterY() > HEIGHT) {
            snake.setCenterY(0);
        }
    }

 @Override
    public void start(Stage primaryStage){
        root = new Pane();
        root.setPrefSize(WIDTH,HEIGHT);
        random = new Random();
        score = new Text(0,32,"0");

        newFood();
        newSnake();

        Runnable r = ()-> {
            try{
                for (;;){
                    move();
                    sleep(200);
                }
            } catch (InterruptedException ie){

            }
        };

        Scene scene = new Scene(root, 600, 500);
        scene.setFill(Color.web("81c483"));
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event-> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP) {
                snake.setCurrentDirection(Direction.UP);
            } else if (code == KeyCode.DOWN) {
                snake.setCurrentDirection(Direction.DOWN);
            } else if (code == KeyCode.LEFT) {
                snake.setCurrentDirection(Direction.LEFT);
            } else if (code == KeyCode.RIGHT) {
                snake.setCurrentDirection(Direction.RIGHT);
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

    public static void main(String[] args) {
        launch(args);
    }

}