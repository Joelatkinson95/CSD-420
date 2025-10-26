// Joel Atkinson October 24, 2025 CSD420 Advanced Java Programming Assignment 1.2
/* The purpose of this assignment is to write a JavaFX program that randomly displays 4 playing cards with a refresh
button underneath the display and using Lambda Expressions */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CardDisplay extends Application {
    private Pane pane = new Pane();
    private ArrayList<Integer> usedCards = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        displayRandomCards();

        Button refreshButton = new Button("Refresh");
        refreshButton.setLayoutX(150);
        refreshButton.setLayoutY(250);
        refreshButton.setOnAction(event -> {
            pane.getChildren().clear();
            usedCards.clear();
            displayRandomCards();
            pane.getChildren().add(refreshButton);
        });

        pane.getChildren().add(refreshButton);

        Scene scene = new Scene(pane, 500, 350);
        primaryStage.setTitle("Random Card Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayRandomCards() {
        for (int i = 0; i < 4; i++) {
            int cardNumber;
            do {
                cardNumber = (int) (Math.random() * 52) + 1;
            } while (usedCards.contains(cardNumber));
            usedCards.add(cardNumber);

            Image cardImage = new Image("AssignmentCards/" + cardNumber + ".png");
            ImageView imageView = new ImageView(cardImage);
            imageView.setX(50 + i * 110);
            imageView.setY(50);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            pane.getChildren().add(imageView);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}