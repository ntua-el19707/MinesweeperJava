import com.vic.game;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;
import com.vic.file;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Callback;
import com.vic.statics;

/**
 * class Program
 * 
 * @author Victoras Giannaki
 *         & Main Class
 */

public class Program extends Application implements formCreate, gchange, timermain, timerN, SuperBombListener,
        mainlistener /* implements EventHandler<ActionEvent> */ {
    private game g;
    private MediaPlayer player;
    private Media bomb;
    private static  String audioPath ;
    private     Stage windowcreate ;
    /**
     * method - close() close window
     */
    @Override
    public void close() {
        window.close();
    }

    /**
     * method - statsStage() "load stats page"
     */
    @Override
    public void statsStage() {
        this.Stats();
    }

    private timermain lis = this;
    static mainCotroller mainHandler;
    private boolean pause = false;
    private int time = 0;
    private timerNormal timer;
    private int x, y = 0;
    private file senario_file;
    private Timer timer_game;
    private boolean start = false;
    private Stage window;
    private Grid grid;

    // Button alert ;
    /**
     * method - main
     * 
     * @param args String []
     */
    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\audio";
        audioPath = s;

        launch(args);

    }

    /**
     * method -loadfile()
     */
    @Override
    public void loadfile() {
        this.load();

    }

    /**
     * method - loseduetime()
     */
    @Override
    public void loseduetime() {
        g.setGameLose();
        // senario_file.createStat(grid.getRounds(),time,0,g.getgame(),
        // grid.get_dimension(), -1,-1);
        // losegame();
        loser(-1, -1);
        // to later
    }

    /**
     * method - normarefresh() "refresh game"
     */
    @Override
    public void normalrefresh(int time) {
        if (!pause) {
            drawmain(time);
        }
    }

    /**
     * method -startfile "start game"
     */
    @Override
    public void startfile() {
        start();
    }

    /**
     *  method - createseanrio "create a file for the senario"
     * @param type int "0 or 1"
     * @param bombs int
     * @param time int
     * @param superBomb int
     * @param filename String
     */
    @Override
    public void createsenartio(int type, int bombs, int time, int superBomb, String filename) {
        senario_file = new file(filename);
        // System.out.printf("%d %d %d %d",type,bombs,superBomb,time);
        senario_file.create(type, bombs, time, superBomb, filename);
        mainHandler.disable();
        windowcreate.close();

    }

    /**
     * method - start() "primary method"
     * @param primaryStage
     * @throws Exception
     */
    private MediaPlayer musicplayer ;
    @Override
    public void start(Stage primaryStage) throws Exception {
        /// mainCotroller useless = new mainCotroller();
        // useless.setListener(this);

        String wallpapermusic = audioPath + "\\Wallpaper.mp3";

        Media Score = new Media(new File(wallpapermusic).toURI().toString());
        musicplayer = new MediaPlayer(Score);

        musicplayer.setAutoPlay(true);
        musicplayer.setRate(1);
        musicplayer.play();

        window = primaryStage;
        window.setOnCloseRequest(e -> {
            closeProgram();
            e.consume();// WE GONE TAKSE CARE FROM HERE
        });
        // window.setMaximized(true);

        // window.show();
        // System.out.println(window.getWidth() + " "+ window.getHeight());
        // winner();
        mainScene();

        // window.show();
        // Stats();
        // when ever use rewust to close the window

    }

    private void Stats() {
        try {
            if (senario_file == null) {
                senario_file = new file("");
            }
            StatContoller statwindow = new StatContoller(senario_file.readStatics(), senario_file.getStatssize());

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private void load() {
        // to do later ;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\medialab";
        fileChooser.setInitialDirectory(new File(s));
        File selectedFile = fileChooser.showOpenDialog(window);
        if (selectedFile != null) {

            senario_file = new file(selectedFile.getPath());
            senario_file.read();
            if (senario_file.getValid()) {
                mainHandler.enable();
            } else {
                mainHandler.disable();
            }

        }

    }

    private void closeProgram() {
        // System.out.println("file is Saved");
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\sprites\\exit.png";
        Boolean answer = ConfirmBox.display("Exit", "Are you sure you  want to exit?", s);
        if (answer) {
            window.close();
        }
    }

    private void init(int x, int y) {
        g = new game(y, x);

        g.new_game(senario_file.getType(), senario_file.getBombs(), senario_file.getTime(),
                senario_file.getSuperbomb());
        start = true;
        senario_file.createMinestxt(g.getgame(), grid.get_dimension());
        grid.setBombS(senario_file.getBombs());

    }

    private void start() {
        int mode = senario_file.getType();
        switch (mode) {
            case 0:
                grid = new Grid_9(this, this, senario_file.getBombs());

                window.setScene(grid.get_scene());
                window.setTitle("MineSweeper");
                window.show();
              //  musicplayer.stop();
                break;
            case 1:
                grid = new Grid_16(this, this, senario_file.getBombs());

                window.setScene(grid.get_scene());
                window.setTitle("MineSweeper");
                window.show();
             //   musicplayer.stop();
                break;
            default:

                break;
        }

        if (grid != null) {

            window.setMinWidth(grid.getScreenWidth() + 10);
            window.setMaxWidth(grid.getScreenWidth() + 10);
            window.setMaxHeight(grid.getScreenHeight());
            window.setMinHeight(grid.getScreenHeight());
        }
        window.show();
        timer = new timerNormal(this, senario_file.getTime());
        // grid = new Grid_16(this);

    }

    private void drawmain(int time) {
        this.time = time;
        HBox htop = new HBox();
        VBox center = new VBox();
        BorderPane finalboard = new BorderPane();

        if (start) {
            for (int i = 0; i < grid.get_dimension(); i++) {
                for (int j = 0; j < grid.get_dimension(); j++) {
                    if (g.gethidden(i, j) == true) {
                        grid.set_board_buttons(j, i);
                    }
                }

            }
        }

        htop.getChildren().addAll(returnBackButton());
        // htop.setAlignment(Pos.CENTER);
        BorderPane headerBox = new BorderPane();
        HBox boardheader = new HBox();
        boardheader.getChildren().addAll(set_timer(time, grid.getMarks(), senario_file.getBombs()));
        // boardheader.setAlignment(Pos.CENTER);
        // boardheader.setAlignment(Pos.CENTER);
        boardheader.setPadding(new Insets(10, 10, 10, 10));
        // boardheader.setAlignment(Pos.CENTER_LEFT);
        // htop.setAlignment(Pos.CENTER);
        headerBox.setTop(htop);
        headerBox.setCenter(boardheader);

        // centerBox.setCenter(grid.getgrid());
        center.getChildren().add(grid.getgrid());
        finalboard.setTop(headerBox);
        finalboard.setCenter(center);
        window.setScene(new Scene(finalboard, grid.getScreenWidth(), grid.getScreenHeight()));
        window.setTitle("MineSweeper");

        window.show();

    }

    private void winner() {
        // layouts
        HBox htop = new HBox();
        VBox center = new VBox();
        /// VBox bottom = new VBox();

        BorderPane Final = new BorderPane();
        Final.setTop(htop);// here Conrgralugulation you have win
        Final.setCenter(center);// game
        // Final.setBottom(bottom);//2 buttons (new gamesame rules ) ,back to main)
        htop.getChildren().add(set_header("You have win the game", "green"));
        // htop.setAlignment(Pos.CENTER);
        grid.block();

        BorderPane inside = new BorderPane();
        HBox buttons = set_Buttons();
        inside.setTop(buttons);
        inside.setCenter(grid.win_grid());
        center.getChildren().add(inside);

        Scene scene = new Scene(Final, window.getWidth(), window.getHeight() + 50);
        senario_file.createStat(grid.getRounds(), time, 1, g.getgame(), grid.get_dimension(), -1, -1);
        window.setScene(scene);

        ;

    }

    private HBox returnBackButton() {
        HBox backLayout = new HBox();
        Path currentRelativePath = Paths.get("");
        Button Back = new Button();
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\sprites";
        ImageView backbutton = new ImageView(new Image(s + "\\back.png"));

        backbutton.setFitWidth(20);
        backbutton.setFitHeight(20);

        Back.setGraphic(backbutton);
        Back.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
        Back.getStyleClass().add("Back");
        backLayout.getChildren().add(Back);
        Back.setOnAction(e -> {
            timer.forceStop();
            start = false;
           // grid.stopMusic();
            mainScene();

        });
        return backLayout;
    }

    private BorderPane set_timer(int time, int marks, int bombs) {
        BorderPane board = new BorderPane();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label textName = new Label("Time:");
        Label MarkName = new Label("Marks:");
        Label BombsName = new Label("Bombs:");
        Label text = new Label(Integer.toString(time));

        Label Marks = new Label(Integer.toString(marks));
        Label Bombs = new Label(Integer.toString(bombs));
        // GridPane.setConstraints(Back,0,0);
        GridPane.setConstraints(text, 0, 2);
        GridPane.setConstraints(Marks, 2, 2);
        GridPane.setConstraints(Bombs, 1, 2);
        GridPane.setConstraints(textName, 0, 1);
        GridPane.setConstraints(MarkName, 2, 1);
        GridPane.setConstraints(BombsName, 1, 1);
        if (time < 10) {
            text.setStyle("-fx-text-fill:red");
        } else {
            text.setStyle("-fx-text-fill:green");
        }
        // GridPane grid2 =new GridPane();
        // grid2.getChildren().addAll(text,Bombs,Marks,textName,MarkName,BombsName);
        grid.getChildren().addAll(text, Bombs, Marks, textName, MarkName, BombsName);

        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-border-color:black; -fx-border-style:dashed");

        board.setCenter(grid);
        return board;

    }

    private HBox set_Buttons() {
        // System.out.println("I can come here");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        Button new_game = new Button("new game");
        Button back_main = new Button("Back to main menu");
        new_game.setOnAction(e -> {
          //  this.grid.stopMusic();
            start();

        });
        start = false;
        back_main.setOnAction(e -> {
           // this.grid.stopMusic();
            mainScene();
        });
        GridPane.setConstraints(new_game, 1, 0);
        GridPane.setConstraints(back_main, 0, 0);
        grid.getChildren().addAll(new_game, back_main);
        HBox footter = new HBox();
        footter.getChildren().add(grid);
        return footter;
    }

    private void loser(int x, int y) {
        senario_file.createStat(grid.getRounds(), time, 0, g.getgame(), grid.get_dimension(), x, y);
        // layouts
        HBox htop = new HBox();
        VBox center = new VBox();
        // VBox bottom = new VBox();

        BorderPane Final = new BorderPane();
        Final.setTop(htop);// lose
        Final.setCenter(center);// game
        // Final.setBottom(bottom);//2 buttons (new gamesame rules ) ,back to main)

        htop.getChildren().add(set_header("You have lost the game", "red"));

        // loser =>show table
        for (int i = 0; i < grid.get_dimension(); i++) {
            for (int j = 0; j < grid.get_dimension(); j++) {

                grid.set_board_buttons(j, i);
            }

        }
        BorderPane inside = new BorderPane();
        HBox buttons = set_Buttons();
        inside.setTop(buttons);
        inside.setCenter(grid.win_grid());
        center.getChildren().add(inside);
        grid.setHighlight(x, y);
        // grid.drawLose();
        // center.getChildren().add(grid.win_grid());

        // bottom.getChildren().add(set_footer(2));
        Scene scene = new Scene(Final, window.getWidth(), window.getHeight() + 50);
        window.setScene(scene);
    }

    /**
     * method - losindScene "display losing scene "
     * @param i int
     */
    // ? why there  is an  i
    // & use timer  and call it as many time show different bomb create an animation  od bomb exploding
    @Override
    public void losingScene(int i) {

        if (i == 1) {

            String bombpath = audioPath + "\\Bomb.mp3";

            bomb = new Media(new File(bombpath).toURI().toString());
            player = new MediaPlayer(bomb);
            player.play();
        }
        if (i < 14) {
            window.setScene(new Scene(grid.lose_grid(i), grid.getSize(), grid.getSize()));
            window.setTitle("Minesweeper");
            window.show();
        } else {

            grid.endLose();
            loser(x, y);
        }
    }

    private void losegame() {
        Timer timer = new Timer();
        timerlose lose = new timerlose(this, timer, 0);

        timer.schedule(lose, 0, 50);

    }

    private double mainWidth = 662;
    private double mainHeight = 521;

    private void mainWindowinit() {
        window.setMinHeight(mainHeight);
        window.setMaxHeight(mainHeight);
        window.setMinWidth(mainWidth);
        window.setMaxWidth(mainWidth);
    }

    private void mainScene() {
        mainWindowinit();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = (Parent) loader.load();
            mainHandler = (mainCotroller) loader.getController();
            mainHandler.setListener(this);
            Scene scene = new Scene(root, mainWidth, mainHeight);
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\sprites\\sprite_05.png";
            window.setTitle("MineSweeper");
            window.getIcons().add(new Image(s));
            window.setScene(scene);
            window.show();
            mainHandler.setAnimation();


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    /**
     * method - create() "open create form window"
     */
    @Override
    public void create() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("createForm.fxml"));

            Parent root = (Parent) loader.load();
            windowcreate = new Stage();
            windowcreate.initModality(Modality.APPLICATION_MODAL);// blck eyrything untill this window is handdle
            Scene scene = new Scene(root, 300, 300);
            windowcreate.setMinWidth(310);
            windowcreate.setMinHeight(310);
            windowcreate.setMaxHeight(310);
            windowcreate.setMaxWidth(310);
            windowcreate.setScene(scene);
            windowcreate.setTitle("Create a new Senario");
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\sprites\\edit.jpg";
            windowcreate.getIcons().add(new Image(s));
            formControlCreate handler = (formControlCreate) loader.getController();
            handler.initForm(this);
            windowcreate.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private GridPane set_header(String msg, String color) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        Label text = new Label(msg);
        text.setStyle(
                "-fx-text-fill:" + color + ";-fx-alignment: CENTER-RIGHT;-fx-font-style:italic;-fx-font-size:20px");
        GridPane.setConstraints(text, 0, 0);

        grid.getChildren().add(text);
        return grid;
    }

    /**
     * method - change_on_grid() "handles changes check if win or lose"
     * @param x int
     * @param y int
     */
    @Override
    public void change_on_grid(int x, int y) {
        if (!start) {
            init(x, y);
        }
        // g.show_board();
        g.play(y, x, false);
        // g.show_board();
        // System.out.printf("(%d,%d) Press\n",y,x);

        for (int i = 0; i < grid.get_dimension(); i++) {
            for (int j = 0; j < grid.get_dimension(); j++) {
                if (g.gethidden(i, j) == true) {
                    grid.set_board_buttons(j, i);
                }
            }

        }

        grid.setBoard(g.getgame());
        drawmain(time);
        // window.setScene(grid.get_scene());

        // window.setTitle("What ever");
        // window.show();
        if (g.get_game_finished()) {
            timer.forceStop();
            if (g.win_or_loss() == true) {
                winner();
            } else {

                grid.setLose(x, y);
                losegame();
                this.x = x;
                this.y = y;

            }
        }
    }

    // * the following 2 methods are not implemented  on gui
    /**
     * method - pause() "pause game"
     */
    @Override
    public void pause() {
        pause = true;
    }

    /**
     * method - unpause() "unpause the game"
     */
    @Override
    public void unpause() {
        pause = false;
    }

    /**
     * method  - SuperBombAnimation  "play a position of the super bomb animation "
     * @param x int
     * @param y int
     */
    @Override
    public void SuperBombAnimation(int x, int y) {

        int game[][] = g.getgame();

        if (game[y][x] != -1 && game[y][x] != -2) {

            g.play(y, x, true);
        } else {

            grid.black(x, y);
        }

    }
}
