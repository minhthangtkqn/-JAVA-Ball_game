/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballgame;

/**
 *
 * @author MINH-THANG
 */
public class BAR {
    private int x, y, width, height;
    
    public int get_X(){
        return x;
    }
    public int get_Y(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    
    public void set_X(int x){
        this.x = x;
    }
    public void set_Y(int y){
        this.y = y;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    
    public void barlimit(int width){
        if( x <= this.width/2 )
            x = this.width/2;
        else if(x >= width - this.width/2)
            x = width - this.width/2;
    }
    
    
}
