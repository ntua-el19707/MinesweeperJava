import javafx.scene.control.Button;
import com.vic.game ;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;
import java.util.List;
/**
 *  class playbutton
 *  @author Victoras  Giannaki
 */
public class playbutton {
    private boolean noevents = false;
    private bpress listener;
    private boolean block = false;
    private Button play_Button ;
    private int  x,y ;
    private boolean press = false ;
    private boolean black = false ;

    private static int bombs ;
    private  static  int marks   ;

    /**
     *  constructor
     * @param x int
     * @param y int  point  of button (x,y)
     * @param toAdd  bress (inteface) listener to communicate with Grid class
     */
    playbutton(int x ,int y,bpress toAdd){
        this.x = x ;
        this.y = y ;

        play_Button = new Button();
        play_Button.setMinSize(30,30);
        play_Button.setMaxSize(30,30);

        listener = toAdd;

    }

    /**
     *  method set if  a button is press  or not
     * @param var
     */
    public  void set(boolean var){
        press =var;
}

    /**
     *  method get_x()
     * @return x int cortibnate
     */
    public int get_x(){
        return x ;
    }

    /**
     *  method get_y()
     * @return y int cortibnate
     */
    public int get_y(){
        return y ;
    }

    /**
     *  method get_b()
     * @return Button
     */
    public Button get_b(){
        return play_Button ;
    }

    /**
     *  method setEvents
     *  set events for a button
     */
    public void setEvents(){
        play_Button.setOnMouseClicked(e->{
            if(!noevents){
            if(e.getButton() == MouseButton.PRIMARY){
            if(!press & !block){
                //System.out.printf("Push (%d,%d)",x,y);

                press = true ;
                listener.Someone_press(x,y);
            }}else if(e.getButton() == MouseButton.SECONDARY){

                if(!black){
                if(!block){
                    if(marks< bombs) {
                        mark();
                    }
                }
                else{
                    block = false;
                    play_Button.setText("");
                    listener.Someone_un_mark();
                    --marks;


                }}else{
                    if(marks< bombs){
                    block =  true ;
                    play_Button.setText("X");

                    play_Button.setStyle("-fx-text-fill:black;-fx-padding:  0");
                    listener.Someone_one_mark(x,y);
                }}}
            }

        });

    }

    /**
     * method mark()
     * mark  a button
     */
    public void mark(){
        block =  true ;
        play_Button.setText("X");

        play_Button.setStyle("-fx-text-fill:red;-fx-padding:  0");
        listener.Someone_one_mark(x,y);
         ++marks;
    }

    /**
     * method block()
     * block button
     */
    public void block(){
        play_Button.setText("x");
        play_Button.setStyle("-fx-text-fill:red");
        noevents = true ;
    }

    /**
     * method get_press
     * @return see if a button is pressed
     */
    public Boolean get_press(){
        return press;
    }

    /**
     * method setblackblock()
     * mark balck a button and block forever
     *
     */
    public  void setblackblock(){
       play_Button.setText("X");

       play_Button.setStyle("-fx-text-fill:black;-fx-padding:  0");
       listener.Someone_one_mark(x,y);
        black = true;
        ++marks;
   }

    /**
     *  method setBomb()
     * @param bomb int
     * set total bombs and marks initialization
     */
    public  void setBomb(int bomb){
      bombs = bomb ;
      marks = 0 ;

    }



}