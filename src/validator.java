package com.vic ;

/**
 *  class  validator
 *  @author Victoras Giannaki
 */
public abstract class validator{
    protected int bombs_upper ;
    protected int bombs_lower ;

    protected  int time_upper ;
    protected  int time_lower ;

    protected  int spbomb ;
    protected  int type ;
    //private boolean validated = false ;

    /**
     *  constructor
     */
    validator(){

    }

    /**
     * method - validate
     * @param type int
     * @param bombs int
     * @param superbomb int
     * @param time int
     * @return true  "if a senario is valid" else false
     */
    public boolean validate(int type ,int bombs,int superbomb,int time){
        if(this.type != type){
            return false;
        }
        if(bombs < bombs_lower || bombs > bombs_upper){
            return false;
        }
        if(time < time_lower || time > time_upper){
            return false;
        }
        if(spbomb == 1 ){
            if(superbomb < 0 || superbomb >1){
                return false;
            }

        }else{
            if(superbomb != 0 ){
                return false ;
            }
        }
        return true ;

    }
}