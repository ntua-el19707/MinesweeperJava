import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * class StatsModel
 * 
 * @author Victoras Giannaki
 *
 */

public class StatsModel {
    private Label rounds, Time, Bombs;
    private int id;
    private Label winner;
    private Button seeBoard = new Button("?");

    public Button getSeeBoard() {
        return seeBoard;
    }

    private void init(Pos x) {
        this.winner.setMinWidth(60);
        this.Time.setMinWidth(100);
        this.Bombs.setMinWidth(100);
        this.rounds.setMinWidth(70);

        rounds.setAlignment(x);
        Time.setAlignment(x);
        Bombs.setAlignment(x);
        winner.setAlignment(x);

    }

    /**
     * constuctor
     * 
     * @param Rounds    int
     * @param Time      int
     * @param Bombs     int
     * @param winner    int "1->winner" "0->loser"
     * @param id        int
     * @param board     int [][] "dimension * dimension"
     * @param dimension int
     * @param x         int
     * @param y         int "losing point"
     */
    StatsModel(int Rounds, int Time, int Bombs, int winner, int id, int board[][], int dimension, int x, int y) {
        // Path currentRelativePath = Paths.get("");
        // String s = currentRelativePath.toAbsolutePath().toString() +
        // "\\src\\sprites\\qmark2.png";
        // ImageView qmark = new ImageView(new Image(s));
        // seeBoard.setGraphic(qmark);
        // qmark.setFitHeight(15);
        // qmark.setFitWidth(15);
        seeBoard.setMinWidth(70);
        // seeBoard.setStyle("-fx-background-color:aqua;-fx-text-fill:black;-fx-font-weight:bolder;-fx-font-size:13");
        seeBoard.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
        seeBoard.getStyleClass().add("Stats");
        this.rounds = new Label(Integer.toString(Rounds));
        this.Time = new Label(Integer.toString(Time));
        this.Bombs = new Label(Integer.toString(Bombs));
        this.id = id;
        seeBoard.setOnAction(e -> {
            windowForBoard(board, dimension, x, y);
        });
        if (winner == 1) {
            this.winner = new Label("Player");
            this.winner.setStyle("-fx-text-fill:green;");
            seeBoard.setVisible(false);
        } else {
            this.winner = new Label("PC");
            this.winner.setStyle("-fx-text-fill:red");
            ;

        }

        init(Pos.CENTER);
        // this.winner.setTextAlignment(TextAlignment.RIGHT);

    }

    private void windowForBoard(int[][] board, int dimensions, int x, int y) {
        BorderPane borderpane = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(0);
        grid.setHgap(0);
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {

                if (board[j][i] != -1 && board[j][i] != -2) {
                    Label label = new Label();
                    label.setMaxSize(30, 30);
                    label.setMinSize(30, 30);
                    String s = String.valueOf(board[j][i]);// Now it will return "10"
                    if (board[j][i] == 0) {
                        s = " ";
                    }

                    label.setText(s);
                    if (board[j][i] == 1) {
                        label.setStyle("-fx-text-fill:blue;-fx-alignment: CENTER;-fx-border-color: white;");
                    } else if (board[j][i] == 2) {
                        label.setStyle("-fx-text-fill:green;-fx-alignment: CENTER;-fx-border-color: white;");
                    } else if (board[j][i] == 3) {
                        label.setStyle("-fx-text-fill:red;-fx-alignment: CENTER;-fx-border-color: white;");
                    } else {
                        label.setStyle("-fx-text-fill:purple;-fx-alignment: CENTER;-fx-border-color: white;");
                    }

                    GridPane.setConstraints(label, i, j);
                    grid.getChildren().add(label);

                } else {
                    try {
                        Path currentRelativePath = Paths.get("");

                        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\sprites\\";
                        // System.out.println("Current absolute path is: " + s);
                        if (i == x && j == y) {
                            s += "sprite_13.png";
                        } else {
                            s += "sprite_09.png";
                        }
                        // Creating an image
                        Image image = new Image(new FileInputStream(s));

                        // Seting the image view
                        ImageView imageView = new ImageView(image);
                        imageView.setFitHeight(30);
                        imageView.setFitWidth(30);

                        GridPane.setConstraints(imageView, i, j);
                        grid.getChildren().add(imageView);
                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                        Label label = new Label();
                        label.setMaxSize(30, 30);
                        label.setMinSize(30, 30);
                        label.setText("B");
                        GridPane.setConstraints(label, i, j);
                        grid.getChildren().add(label);

                    }
                }
            }
        }

        VBox center = new VBox();
        center.getChildren().add(grid);

        borderpane.setCenter(center);
        int width, height;
        if (dimensions == 16) {
            width = 500;
            height = 520;
        } else {
            width = 290;
            height = 310;
        }
        HBox header = new HBox();
        Label header_label = new Label("Analytic Lost");
        header.getChildren().add(header_label);
        header.setAlignment(Pos.CENTER);
        header_label.setStyle("-fx-text-fill:green");
        borderpane.setTop(header);

        Stage windowNew = new Stage();
        windowNew.initModality(Modality.APPLICATION_MODAL);
        windowNew.setMaxHeight(height + 10);
        windowNew.setMinHeight(height + 30);
        windowNew.setMaxWidth(width + 10);
        windowNew.setMinWidth(width + 30);
        windowNew.setScene(new Scene(borderpane, width, height));
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\sprites\\qmark.png";

        windowNew.getIcons().add(new Image(s));
        windowNew.showAndWait();

        // Scene game_scene = new Scene(grid,300,300);
        // window.setScene(game_scene);
    }

    /**
     * method - getWinner()
     * 
     * @return Label
     */
    public Label getWinner() {
        return winner;
    }

    /**
     * method - setWinner()
     * 
     * @param winner String
     *               set winner label
     */
    public void setWinner(String winner) {
        this.winner = new Label(winner);
    }

    /**
     * method - getRounds()
     * 
     * @return Label
     */
    public Label getRounds() {
        return rounds;
    }

    /**
     * method - setRounds()
     * 
     * @param rounds Label
     *               set Label rounds
     */
    public void setRounds(Label rounds) {
        this.rounds = rounds;
    }

    /**
     * method - getTime()
     * 
     * @return Label
     */
    public Label getTime() {
        return Time;
    }

    /**
     * method - seTime()
     * 
     * @param time Label
     */
    public void setTime(Label time) {
        Time = time;
    }

    /**
     * method - getBombs()
     * 
     * @return Label
     */
    public Label getBombs() {
        return Bombs;
    }

    /**
     * method - setBombs()
     * 
     * @param bombs Label
     */
    public void setBombs(Label bombs) {
        Bombs = bombs;
    }

    /**
     * method - getId()
     * 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * method - setId()
     * 
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }
}
