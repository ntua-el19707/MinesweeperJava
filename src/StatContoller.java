import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.vic.file;
import com.vic.statics;
import javafx.util.Callback;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * class StatContoller
 * 
 * @author Victoras Giannaki
 */
public class StatContoller {
    private final TableView<StatsModel> StatsTable = new TableView<>();
    private final ObservableList<StatsModel> tvObservableList = FXCollections.observableArrayList();

    private TableColumn<StatsModel, Label> Rounds = new TableColumn<>("Rounds");

    private TableColumn<StatsModel, Label> Time = new TableColumn<>("Remaining Time");

    private TableColumn<StatsModel, Label> Bombs = new TableColumn<>("Total Bombs");

    private TableColumn<StatsModel, Label> Winner = new TableColumn<>("Winner");
    private TableColumn<StatsModel, Button> SeeBoard = new TableColumn<>("See Board");
    private TableColumn<StatsModel, Integer> latest = new TableColumn<>("latest");
    // private ObservableList<StatsModel> StatsModels ;

    private void initializetable() {
        StatsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        StatsTable.setPrefWidth(455);
        StatsTable.setPrefHeight(200);

        StatsTable.getColumns().addAll(latest, Rounds, Bombs, Time, Winner, SeeBoard);
        Rounds.setCellValueFactory(new PropertyValueFactory<StatsModel, Label>("rounds"));
        Time.setCellValueFactory(new PropertyValueFactory<StatsModel, Label>("Time"));
        Bombs.setCellValueFactory(new PropertyValueFactory<StatsModel, Label>("Bombs"));
        Winner.setCellValueFactory(new PropertyValueFactory<StatsModel, Label>("winner"));

        SeeBoard.setCellValueFactory(new PropertyValueFactory<StatsModel, Button>("seeBoard"));
        latest.setCellValueFactory(new PropertyValueFactory<StatsModel, Integer>("id"));
        SeeBoard.setMinWidth(70);
        SeeBoard.setMaxWidth(70);
        Winner.setMinWidth(63);
        Winner.setMaxWidth(63);
        ;

        Time.setMinWidth(100);
        Time.setMaxWidth(100);
        Bombs.setMinWidth(100);
        Bombs.setMaxWidth(100);
        Rounds.setMinWidth(70);
        Rounds.setMaxWidth(70);
        latest.setMaxWidth(50);
        latest.setMinWidth(50);
        latest.setStyle("-fx-alignment: CENTER");
    }

    /**
     * method -setTable()
     * 
     * @param statics statics[]
     * @param size    int
     */
    public void setTable(statics[] statics, int size) {
        for (int i = 0; i < size; i++) {

            // tvObservableList.add(new StatsModel(statics[i].getTrials(),
            // statics[i].getTime(),statics[i].getBombs(),statics[i].getWinner()));
            StatsTable.getItems()
                    .add(new StatsModel(statics[i].getTrials(), statics[i].getTime(), statics[i].getBombs(),
                            statics[i].getWinner(), statics[i].getId(), statics[i].getBoard(),
                            statics[i].getDimensions(), statics[i].getx(), statics[i].gety()));
           // System.out.println(StatsTable.getItems().get(i));

        }
        // System.out.println(size);
        // StatsTable.setItems(tvObservableList);

    }

    /**
     * constructor
     * 
     * @param statics statics []
     * @param size    int
     */
    StatContoller(statics[] statics, int size) {
        // file gamestats = new file("");

        this.initializetable();

        // StatsTable.setItems(StatsModels);
        this.setTable(statics, size);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new Group(StatsTable));
        HBox header = new HBox();
        Label headerLabel = new Label("Stats for the 5 past Games");
        // headerLabel.setMinWidth(400);
        headerLabel.setStyle("-fx-text-fill:green;-fx-font-size:20 ;-fx-font-style:italic ");
        header.getChildren().add(headerLabel);
        header.setAlignment(Pos.CENTER);
        borderPane.setTop(header);
        window.setScene(new Scene(borderPane, 470, 300));
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\sprites\\qmark.png";
        // ImageView qmark = new ImageView(new Image(s));
        window.getIcons().add(new Image(s));
        window.setMaxWidth(470);
        window.setMinWidth(470);
        window.setMinHeight(300);
        window.setMaxHeight(300);
        // qmark.setFitHeight(15);
        // qmark.setFitWidth(15);
        window.show();

    }

}
// ? what does this class do?
// & Create a table in a new window
// & for a statics array