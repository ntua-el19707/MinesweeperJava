import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * class  timerNormal
 * @author  Victoras Giannaki
 */
public class timerNormal
{
    private int durration ;
    private static  int time;
    private int delay ;
    private Timer timer = new Timer() ;
    private timerN listener ;

    /**
     * construtor
     * @param listener timerN  "class  you want to act"
     * @param durration int  "seconds until stop"
     */
    timerNormal(timerN listener,int durration){
        this.listener = listener ;
        this.durration = durration ;
        time = durration ;
        TimerTask game_timer = new TimerTask() {
            @Override
            public void run() {

                Platform.runLater(() ->{
                        listener.normalrefresh(time);
                        --time ;

                });
                if(time == 0){
                    //normal lose
                    Platform.runLater(() ->{
                        listener.loseduetime(); // player losee due time


                    });
                    timer.cancel();
                }
                // timer.cancel();
            }

        };
        timer.schedule(game_timer,0,1000);


    }

    /**
     * method  forceStop() "stop timer"
     */
   void  forceStop(){
        timer.cancel();
   }



}
