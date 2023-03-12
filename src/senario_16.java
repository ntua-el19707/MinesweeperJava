package  com.vic;
import com.vic.senario;
import  com.vic.randomNum;

/**
 *  class senario_16
 * @author  Victoras Giannaki
 */
public class senario_16 extends senario{
    private int superBomb ;//fix later

    /**
     *  constructor
     * @param num int "total bombs"
     * @param startx int
     * @param starty int  "starting point"
     */
    public senario_16(int num,int startx ,int starty){
        senario_tb = new int [16][16];
        rows  =  16 ;
        upperbound = 256 ;
        this.startx = startx;
        this.starty = starty ;
        gen_Bomb(num);
        genarateSuberBomb(num);
     //   System.out.println(startx + starty );

      //  this.startx = startx ;
        //this.starty = starty ;
    }
    //genarateSuberBomb(num) => select one bomb as super bomb(-2)
    private void genarateSuberBomb(int num){
       randomNum genarator = new randomNum(num);//vrate a genarator of random
        int count = 0 ;

        int suberBomb= genarator.get_Num() ;
        for(int i = 0 ; i<rows;i++){
            for(int j =0; j<rows;j++){
                if(senario_tb[i][j]==-1) {
                    if(count == suberBomb){
                        senario_tb[i][j] = -2;
                    }
                    ++count;
                }
                }
            }
        }
    }

