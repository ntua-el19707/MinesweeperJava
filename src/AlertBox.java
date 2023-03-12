import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * class AlertBox
 * 
 * @author Victoras Giannaki
 */
public class AlertBox {
    /**
     * constructor
     * @param title   String
     * @param message String
     * @param path    String
     */
    public static void display(String title, String message, String path) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);// blck eyrything untill this window is handdle
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button();
        closeButton.setText("close");
        closeButton.setOnAction(e -> window.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}