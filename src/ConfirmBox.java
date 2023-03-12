import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * class ConfirmBox
 * 
 * @author Victoras Giannaki
 */
public class ConfirmBox {
    static boolean answer = false;

    /**
     * constructor
     * 
     * @param title   String
     * @param message String
     * @param path    String
     */
    public static boolean display(String title, String message, String path) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);// blck eyrything untill this window is handdle
        window.setTitle(title);
        window.setMinWidth(250);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(0);
        grid.setHgap(5);
        VBox buttons = new VBox(10);

        // buttons.getStylesheets().add()
        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("yes");
        Button noButton = new Button("no");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
        GridPane.setConstraints(yesButton, 1, 0);
        GridPane.setConstraints(noButton, 0, 0);
        grid.getChildren().addAll(yesButton, noButton);
        grid.setAlignment(Pos.CENTER);
        buttons.getChildren().add(grid);
        BorderPane layout = new BorderPane();
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.getChildren().add(label);
        layout.setCenter(buttons);
        layout.setTop(header);

        // System.out.println(path);
        // VBox layout = new VBox(10);
        // layout.getChildren().addAll(label,yesButton,noButton);
        // layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.getIcons().add(new Image(path));
        window.showAndWait();
        return answer;

    }
}