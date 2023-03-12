import java.util.TimerTask;

import com.vic.CordinatesCrossBoard;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class SuberBombTimer {
    private SuperBombListener listener ;
    private Timer timer = new Timer();
    private CordinatesCrossBoard cross ;
    private TimerTask superBombAnimation = new TimerTask() {
        @Override
        public void run() {

            Platform.runLater(()->{

                //System.out.println(cross.getDown());
                listener.SuperBombAnimation(cross.getDown().x,cross.getDown().y);
               // System.out.println(cross.getleft());
                listener.SuperBombAnimation(cross.getleft().x,cross.getleft().y);
                listener.SuperBombAnimation(cross.getUp().x,cross.getUp().y);
               // System.out.println(cross.getUp());
                listener.SuperBombAnimation(cross.getRight().x,cross.getRight().y);
                if(cross.stop()){
                  //  listener.unpause();
                    timer.cancel();
                }
                else{
                    cross.move();
                }
            });
        }
    };
    SuberBombTimer(SuperBombListener listener,int x ,int y ,int dimensions){
        cross = new CordinatesCrossBoard(x,y,dimensions);
        cross.move();
        this.listener = listener;

        timer.schedule(superBombAnimation,0,700);

    }


    public void forceStop(){
        timer.cancel();
    }
}
