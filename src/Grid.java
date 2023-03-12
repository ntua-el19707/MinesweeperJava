import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * class Grid
 * 
 * @author Victoras Giannaki
 */
public abstract class Grid implements bpress {

    private boolean losingAnimationEnded = false;
    protected int size = 0;
    protected int ScreenWidth, ScreenHeight;
    private static  String audioPath ;

    /**
     * method getScreenWidth()
     * 
     * @return int
     */
    public int getScreenWidth() {
        return ScreenWidth;
    }

    /**
     * method getScreenHeight()
     * 
     * @return int
     */
    public int getScreenHeight() {
        return ScreenHeight;
    }

    private int rounds = 0;
    private boolean highlight = false;
    private int highx;
    private int highy;
    protected gchange listener;
    protected playbutton[][] play_buttons;
    protected int dimensions;
    protected int[][] board;
    private GridPane grid;
    // private int size;
    private boolean[] press;
    private String[] pics;
    private int index = 0;

    private int marks = 0;
    protected int totalbombs;

    /**
     * constructor
     */
    Grid() {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\audio";
        audioPath = s; //initialise audi path
        pics = new String[14];
        for (int i = 0; i < 10; i++) {
            pics[i] = "sprite_0" + Integer.toString(i) + ".png";
        }
        for (int i = 10; i < 14; i++) {
            pics[i] = "sprite_" + Integer.toString(i) + ".png";
        }
       // playMusic();
    }

    /**
     * method -getMarks()
     * 
     * @return int
     */
    public int getMarks() {
        return marks;
    }

    /**
     * method - getSize()
     * 
     * @return int
     */
    public int getSize() {
        return size;
    }

    /**
     * method - getgrid()
     * 
     * @return GridPane
     */
    public GridPane getgrid() {
        draw();
        return grid;
    }

    /**
     * method - get_dimension()
     * 
     * @return int
     */
    public int get_dimension() {
        return dimensions;
    }

    /**
     * method set_board_buttons
     * 
     * @param i int
     * @param j int
     *          "Point(j,i)"
     */
    public void set_board_buttons(int i, int j) {
        play_buttons[i][j].set(true);

    }

    protected SuperBombListener listener2;
    private int markBompx;
    private int markBompy;
    private int markLosex = -1;
    private int marklosey = -1;

    /**
     * method - serBomb() "set MarkBombx&y"
     * 
     * @param x
     * @param y
     */
    void setBomb(int x, int y) {
        markBompx = x;
        markBompy = y;
    }

    private boolean checkbomb(int i, int j) {
        if (markBompx == i && markBompy == j) {
            return true;
        }
        return false;
    }

    private void drawLose() {
        draws(index);
        if (index < 14) {
            ++index;
        }
    }

    /**
     * method - setHighlight
     * 
     * @param x int
     * @param y int
     */
    public void setHighlight(int x, int y) {
        highlight = true;
        highx = x;
        highy = y;
    }

    protected void draw_init() {

        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {

                play_buttons[i][j] = new playbutton(i, j, this);
                play_buttons[i][j].setEvents();
                // play_buttons[i][j].get_b().setOnAction(e->{

                // draw();
                // });
            }
        }
        draw();
    }

    /**
     * method - draw() "draw grid"
     */
    public void draw() {
        draws(1);
    }

    /**
     * method - setBoard()
     * 
     * @param board int [][] "dimenions*dimenions"
     */
    public void setBoard(int[][] board) {
        this.board = board;
    }

    private void draws(int index) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(0);
        grid.setHgap(0);
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                if (!play_buttons[i][j].get_press()) {
                    // System.out.printf("%d %d ",i,j);
                    /// mygame.show_board();
                    GridPane.setConstraints(play_buttons[i][j].get_b(), i, j);
                    grid.getChildren().add(play_buttons[i][j].get_b());
                } else {
                    if (board[j][i] != -1 && board[j][i] != -2) {
                        Label label = new Label();
                        label.setMaxSize(30, 30);
                        label.setMinSize(30, 30);
                        String s = String.valueOf(board[j][i]);// Now it will return "10"
                        if (board[j][i] == 0) {
                            s = " ";
                        }
                        if (highlight && highx == j && highy == i) {
                            label.setStyle("-fx-background-color: red;-fx-border-color: white;");

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
                            int inex = 7;

                            if (markLosex == i && marklosey == j) {
                                inex = index;
                                if (losingAnimationEnded) {
                                    inex = 13;
                                }
                            }

                            String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\sprites\\"
                                    + pics[inex];
                            // System.out.println("Current absolute path is: " + s);

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
        }
        this.grid = grid;
        // Scene game_scene = new Scene(grid,300,300);
        // window.setScene(game_scene);
    }

    /**
     * method - mark() "mark a button"
     * 
     * @param x int
     * @param y int
     */
    public void mark(int x, int y) {
        play_buttons[x][y].mark();
    }

    /**
     * method -block() "block pressed button"
     * 
     */
    public void block() {
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                if (!play_buttons[i][j].get_press()) {
                    play_buttons[i][j].block();
                }
            }
        }
    }

    /**
     * method - win_grid() "return game grid not only win"
     * 
     * @return GridPane
     */
    public GridPane win_grid() {
        draw();
        return grid;
    }

    /**
     * method -setLose() "set lossing point"
     * 
     * @param x int
     * @param y int
     */
    public void setLose(int x, int y) {
        markLosex = x;
        marklosey = y;
    }

    /**
     * method - lose_grid() "return game grid lose "
     * 
     * @param index int
     * @return GridPane
     */
    public GridPane lose_grid(int index) {
        draws(index);

        return grid;
    }

    /**
     * method -get_scene() "game scene"
     * 
     * @return Scene
     */
    public Scene get_scene() {
        draw();
        return new Scene(grid, 510, 740);
    }

    /**
     * method -setbombs
     * 
     * @param bombs int
     */
    public void setbombs(int bombs) {
        totalbombs = bombs;
    }

    /**
     * method -Someone_pess()
     * 
     * @param x int
     * @param y int
     */
    @Override
    public void Someone_press(int x, int y) {

        ++rounds;
        listener.change_on_grid(x, y);

    }

    /**
     * method - Someone_one_mark()
     * 
     * @param x int
     * @param y int
     */
    @Override
    public void Someone_one_mark(int x, int y) {
        // System.out.println("Someone has mark ");
        ++marks;
        if (board[y][x] == -2 && marks <= 4) {
            // System.out.println("x,y :"+ x+ " "+y);
            String scorepath = audioPath +"\\score.mp3";

            Media Score = new Media(new File(scorepath).toURI().toString());
            MediaPlayer player = new MediaPlayer(Score);
            player.play();
            SuberBombTimer animation = new SuberBombTimer(listener2, x, y, dimensions);

        }
    }

    /**
     * method -endLose()
     * "mark losing animation Ended"
     */
    public void endLose() {
        losingAnimationEnded = true;
        // stopMusic();
    }

    /**
     * method - Someone_un_mark()
     */
    @Override
    public void Someone_un_mark() {
        --marks;
    }

    /**
     * method - getRounds()
     * 
     * @return int
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * method -black() "bombs mark find from super bomb animation"
     * 
     * @param x int
     * @param y int
     */
    public void black(int x, int y) {
        this.play_buttons[x][y].setblackblock();
    }

    /**
     * method - setBombS "set total bombs in play buttons"
     * 
     * @param bomb int
     */
    public void setBombS(int bomb) {
        play_buttons[0][0].setBomb(bomb);
    }

    private MediaPlayer muscipl;

    private void playMusic() {
        String scorepath = audioPath + "\\main.mp3";

        Media Score = new Media(new File(scorepath).toURI().toString());
        muscipl = new MediaPlayer(Score);

        muscipl.setAutoPlay(true);
        muscipl.setRate(1);

        muscipl.setCycleCount(MediaPlayer.INDEFINITE);

        muscipl.play();
    }

    /**
     * method - stopMusic()
     */
    public void stopMusic() {
        muscipl.stop();
    }
}