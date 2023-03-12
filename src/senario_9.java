package  com.vic;
import com.vic.senario;

/**
 * class senario_9
 * @author  Victoras Giannaki
 */
public class senario_9 extends senario{
    public senario_9(int num,int startx,int starty){
        senario_tb = new int [9][9];
        rows  =  9 ;
        upperbound = 81 ;
        this.startx = startx;
        this.starty = starty ;
        gen_Bomb(num);



    }

}