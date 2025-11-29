//Joel Atkinson November 28, 2025, CSD420 Advanced Java Programming Assignment 7.2
/* The purpose of this assignment is to use JavaFX with a css stylesheet to display 4 circles with the first
 circle outlined with a black rectangle to match the output from the assignment word docx. This assignment demonstrates
 how to properly use JavaFX CSS as outlined in chapter 31 of our textbook*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;

public class Assignment_7_2 extends Application {

    @Override
    // creating circles and setting their radius
    public void start(Stage primaryStage) {
        Circle c1 = new Circle(70);
        Circle c2 = new Circle(70);
        Circle c3 = new Circle(70);
        Circle c4 = new Circle(70);

        //Using the existing IDs from the CSS file to get the styling for the circles
        c1.getStyleClass().addAll("plaincircle");
        c2.getStyleClass().add("plaincircle");
        c3.setId("redcircle");
        c4.setId("greencircle");

        // Using StackPane to wrap the first circle with a rectangle using the "border" styling from css file
        StackPane leftWrapper = new StackPane(c1);
        leftWrapper.getStyleClass().add("border");

        // Setting the HBox (horizontal box) to display the circles in a horizontal line centered with 10px between them
        HBox root = new HBox(10, leftWrapper, c2, c3, c4);
        root.setAlignment(Pos.CENTER);

        // Setting the scene, defining the window size, and linking the css style sheet
        Scene scene = new Scene(root, 700, 650);
        scene.getStylesheets().add("Assignment_7-2.css");

        primaryStage.setTitle("CSD420 Assignment 7.2 Circles Output"); //Window title to match course and assignment
        primaryStage.setScene(scene); //Placing the scene within the window
        primaryStage.show(); //Display the window
    }
}