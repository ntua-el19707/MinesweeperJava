package com.vic;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.NoSuchElementException ;
import java.util.Scanner; // Import the Scanner class to read text files

import com.vic.*;
import com.vic.statics;
import com.vic.validator;
import com.vic.validator_16;
import com.vic.validator_9;

/**
 * class  file handles read and wirite to files
 * @author  Victoras Giannaki
 */


public class file{
    private  int statsSize = 0;
    private String filename ;
    private int type ;
    private int bombs ;
    private int time ;
    private  int superbomb ;
    private validator validdata ;
    private boolean valid = false;


    public int getStatssize(){
        return  statsSize ;
    }

    /**
     *  constructor
     * @param filename String "fileName that write a senario"
     */
    public file(String filename){
        this.filename = filename;
    }

    /**
     *  method getValid() --validate input of the loadded  senario
     * @return
     */
    public boolean getValid(){
        return valid ;
    }

    /**
     *  method  getType of game (0,1) levels
     * @return
     */
    public int getType(){
        return type ;
    }

    /**
     *  method getBombs()
     * @return number of bombs ;
     */
    public int getBombs(){
        return bombs;
    }

    /**
     *  method getTime()
     * @return get Time of  game secs
     */
    public int getTime(){
        return time;
    }

    /**
     *  method getSuperbomb()
     * @return 1,0 either is or not
     *
     */
    public int getSuperbomb(){
        return superbomb;
    }

    /**
     *  method read()
     *  read a senario(FileName of  contructor)
     */
    public void read(){

        try{
            File myFile = new File(filename);
            Scanner myReader = new Scanner(myFile);
            type = myReader.nextInt();
            bombs = myReader.nextInt();
            time = myReader.nextInt();
            superbomb = myReader.nextInt();
            if(type == 0){
                validdata = new com.vic.validator_9();
            }else if(type == 1){
                validdata = new validator_16();
            }else{
                //wrong input
                //System.out.printf("Wrong input\n");
                throw  new com.vic.InvalidDescriptionException();
            }
            if(type ==1 || type == 0){
                if(!validdata.validate(type,bombs,superbomb,time)){
                    //wrong data
                  //  System.out.printf("Wrong input\n");
                    valid  = false;
                    throw new  com.vic.InvalidValueException();

                }else{
                    valid = true ;
                }
            }

            // if(myReader.hasNext()){
            //   throw  new Exception("more input") ;
            //}
            //System.out.println(myReader.nextInt());
            //System.out.printf("type %d,bomb %d,time %d,superbomb %d",type,bombs,time,superbomb);

        }catch(FileNotFoundException e){
            System.out.print(e);
        }
        catch(NoSuchElementException e){
            System.out.print("not such element \n");
        }
        catch (com.vic.InvalidValueException e){
            System.out.print("\nWrong Values exeption\n");
        }
        catch (com.vic.InvalidDescriptionException e){
            System.out.println("\nDescritpion exception");
        }

    }

    /**
     * method  createMinestxt
     * @param board int [] []
     * @param dimension int
     * stores the game  in file called mines.txt  in Folder  Mines
     */
    public  void createMinestxt(int  board [] [] ,int dimension){
        try{
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\Mines\\mines.txt";
        FileWriter myFile = new FileWriter(s);
            for(int  i = 0 ;i<dimension ;i++){
                for(int  j = 0 ;j<dimension ;j++){
                    if(board[i][j] == -1){
                        myFile.write(Integer.toString(j)+","+Integer.toString(i) +",0\n" );
                    } else if (board[i][j] == -2) {
                        myFile.write(Integer.toString(j)+","+Integer.toString(i) +",1\n" );
                    }
                }
            }
            myFile.close();
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     *  method create => create a senario (Input must be correct (in our case MineSweeper  form does not allow user to put  inavlid values))
     * @param type int (0,1)
     * @param bombs int
     * @param time int secs
     * @param superbomb int (0,1) exist or not
     * @param filename string
     */
    public void create(int type,int bombs ,int time , int superbomb ,String filename){
        try {

            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\medialab\\" +filename + ".txt";
            FileWriter myFile = new FileWriter(s);
            System.out.println(type);
            myFile.write(Integer.toString(type) +"\n"+Integer.toString(bombs)+"\n"+Integer.toString(time)+
                    "\n"+Integer.toString(superbomb));
           // myFile.write(bombs);
            myFile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * method createStat (stores in a file last 5(1 new 4 old ) called Stats.txt in a folder Stats)
     * @param trials int  "rounds"
     * @param time  int "remainig time"
     * @param winner int
     * @param gameboard int [][] "game board"
     * @param dimensions int  "dimensions  of game Board"
     * @param x int   point of lose (x,y)
     * @param y int
     */
    public void  createStat(int  trials,int  time ,int winner,int gameboard[][],int dimensions,int x,int y){
        statics [] statistics  = new statics[5];
        statistics[0] = new statics(winner,trials,bombs,time,1,gameboard,dimensions,x,y);
        int size =1 ;
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\Stats\\Stats.txt";
        File myFile = new File(s);
        if(myFile.exists()){
            try {
                Scanner myReader = new Scanner(myFile);
                while(myReader.hasNext()){
                    int  id = myReader.nextInt() + 1 ;
                    if(id == 6){
                        break;
                    }
                    int  r = myReader.nextInt() ;
                    int  b = myReader.nextInt() ;
                    int t = myReader.nextInt();
                    int d = myReader.nextInt();
                    int board[][] = new int [d][d];
                    for(int i  =0 ; i<d ; i++){
                        for(int  j = 0;  j<d ;j++){
                            board[i][j] = myReader.nextInt();
                        }
                    }
                    int w = myReader.nextInt();
                    int x1 =0 ;
                    int y1 = 0 ;
                    if(w==0){
                        x1=myReader.nextInt();
                        y1=myReader.nextInt();
                    }
                    statistics[id-1] = new statics(w,r,b,t,id,board,d,x1,y1);
                    ++size;
                  //  System.out.println("Create one");
                }
                myFile.delete();
            }
            catch (Exception e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        try{

            FileWriter mynewFile = new FileWriter(s);
            for(int i = 0 ;i<size;i++){
                mynewFile.write(Integer.toString(statistics[i].getId()) + "\n");
                mynewFile.write(Integer.toString(statistics[i].getTrials()) + "\n");
                mynewFile.write(Integer.toString(statistics[i].getBombs()) + "\n");
                mynewFile.write(Integer.toString(statistics[i].getTime()) + "\n");
                mynewFile.write(Integer.toString(statistics[i].getDimensions()) + "\n");
                int newBoard[][] = statistics[i].getBoard();
                for(int j = 0 ; j<statistics[i].getDimensions();j++){
                    for(int k = 0 ; k<statistics[i].getDimensions();k++){
                        mynewFile.write(Integer.toString(newBoard[j][k]) + " ");

                    }
                    mynewFile.write("\n");
                }
                mynewFile.write(Integer.toString(statistics[i].getWinner()) + "\n");
                if(statistics[i].getWinner() == 0){
                    mynewFile.write(Integer.toString(statistics[i].getx()) + "\n");
                    mynewFile.write(Integer.toString(statistics[i].gety()) + "\n");
                }
            }
            mynewFile.close();
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     *  method readStatics ;
     *
     * @return last 5  game statics  in array of model statics
     */
    public statics[] readStatics(){
        statics statistics[] = new statics[5];
        int size = 0;
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + "\\src\\Stats\\Stats.txt";
        File myFile = new File(s);
        if(myFile.exists()){
            try {
                Scanner myReader = new Scanner(myFile);
                while(myReader.hasNext()){
                    int  id = myReader.nextInt();//+ 1 ;
                  //  if(id == 6){
                   //     break;
                 //   }
                    int  r = myReader.nextInt() ;
                    int  b = myReader.nextInt() ;
                    int t = myReader.nextInt();
                    int d = myReader.nextInt();
                    int board[][] = new int [d][d];
                    for(int i  =0 ; i<d ; i++){
                        for(int  j = 0;  j<d ;j++){
                            board[i][j] = myReader.nextInt();
                        }
                    }
                    int w = myReader.nextInt();

                    int x=0;
                    int  y=0;
                    if(w == 0) {
                    x=myReader.nextInt();
                    y= myReader.nextInt();
                    }
                    statistics[id-1] = new statics(w,r,b,t,id,board,d,x,y);
                    ++size;
                    //  System.out.println("Create one");
                }
                statsSize = size;
          //      myFile.delete();

            }catch (Exception e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return  statistics;

    }
    }
