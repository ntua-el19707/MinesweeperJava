import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * class timerlose
 * @author  Victoras Giannaki
 */
public class timerlose extends TimerTask
{
    private static  int i = 0 ;
    private Timer mytimer ;
    private timermain listener;

    /**
     *  constuctor
     * @param listener timermain
     * @param mytimer Timer
     * @param i int
     */
    timerlose(timermain listener,Timer mytimer,int i ){

        this.listener = listener;this.i=i;
        this.mytimer = mytimer ;
    }

    /**
     * method -run()
     */
    @Override
    public void run() {
        Platform.runLater(() -> {
            listener.losingScene(i);
        });

        ++i;
        if(i == 14){
            mytimer.cancel();

        }

    }
}
