import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

/**
 * class mainController
 * 
 * @author Victoras Giannaki
 */
public class mainCotroller {
    @FXML
    private Button LoadButton;
    @FXML
    private Button StartButton;
    @FXML
    private Button StatsButton;
    @FXML
    private Button CloseButton;
    @FXML
    private Button createButton;
    Timer timer = new Timer();
    private String[] pics;
    private int counter = 0;
    private Path currentRelativePath = Paths.get("");
    @FXML
    public ImageView image;
    // mainCotroller(){
    // Path currentRelativePath = Paths.get("");
    // String s = currentRelativePath.toAbsolutePath().toString() +
    // "\\src\\sprites\\sprite_05.png";
    // image.setImage(new Image(s));
    // }
    private mainlistener listener;

    /**
     * method -setListener
     * 
     * @param listener mainListener
     */
    public void setListener(mainlistener listener) {
        this.listener = listener;
        ini();
    }

    private void ini() {
        StatsButton.setOnAction(e -> {
            listener.statsStage();
        });
        disable();
    }

    private void animation() {
        image.setImage(new Image(currentRelativePath.toAbsolutePath().toString() + "\\src\\sprites\\" + pics[counter]));
        ++counter;
        if (counter == 14) {
            counter = 0;
        }
    }

    /**
     * method - kiilAnimation()
     * "kils animation "
     */
    public void killAnimation() {
        timer.cancel();
        counter = 0;
        animation();

    }

    /**
     * method -setAnimation()
     * "set an starts animation"
     */
    public void setAnimation() {
        inipics();
        TimerTask animation = new TimerTask() {
            @Override
            public void run() {
                animation();
            }
        };
        timer.schedule(animation, 0, 100);
    }

    private void inipics() {
        pics = new String[14];
        for (int i = 0; i < 10; i++) {
            pics[i] = "sprite_0" + Integer.toString(i) + ".png";
        }
        for (int i = 10; i < 14; i++) {
            pics[i] = "sprite_" + Integer.toString(i) + ".png";
        }
    }

    /**
     * method - load()
     * "FXML method "
     */
    @FXML
    void load() {
        Platform.runLater(() -> {
            listener.loadfile();
        });
        // System.out.println("load");
    }

    /**
     * method -start()
     * "FXML method "
     */
    @FXML
    void start() {
        listener.startfile();
    }

    /**
     * method - create()
     * "FXML method "
     */
    @FXML
    void create() {
        listener.create();
    }

    /**
     * method - close()
     * 
     * @param actionEvent
     *                    "call listener.close()"
     */
    public void close(ActionEvent actionEvent) {
        listener.close();
    }

    /**
     * method - enable()
     * "enable start Button"
     */
    public void enable() {
        StartButton.setDisable(false);
    }

    /**
     * method - disable()
     * "disable start Button"
     */
    public void disable() {
        StartButton.setDisable(true);
    }
}