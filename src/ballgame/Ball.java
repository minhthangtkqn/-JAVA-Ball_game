
package ballgame;

public class Ball {
    
    private int x, y, xSpeed, ySpeed, radius, color;
       
    public int getColor(){
        return color;
    }
    public int getRadius(){
        return radius;
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public int getxSpeed(){
        return xSpeed;
    }
    public int getySpeed(){
        return ySpeed;
    }
    
    public void setColor(int color){
        this.color = color;
    }
    public void setRadius(int radius){
        this.radius = radius;
    }
    public void setx(int x){
        this.x = x;
    }
    public void sety(int y){
        this.y = y;
    }
    public void setxSpeed(int xSpeed){
        this.xSpeed = xSpeed;
    }
    public void setySpeed(int ySpeed){
        this.ySpeed = ySpeed;
    }
    
    public void move(){
        x+=xSpeed;
        y+=ySpeed;
    }
    
    public void stop(){
        this.setxSpeed(0);
        this.setySpeed(0);
    }
    public boolean isStop(){
        if(this.getxSpeed() == 0 && this.getySpeed() == 0)
            return true;
        return false;
    }
    
    public void overBound2( int width, int height) {    //kiểm tra va chạm với cạnh trên và 2 cạnh bên
        if ((x - radius) <= 0) {                        //của màn hình
            xSpeed = - xSpeed;                      //nếu bóng chạm cạnh dưới màn hình 
            x= radius;                              //thì người chơi thua
        } else if (x + radius >= width ) {
            xSpeed = - xSpeed;
            x = width - radius;
        }

        if ((y - radius) <= 0) {
            ySpeed = - ySpeed;
            y = radius;
        }
    }
    
    public void CollisionBar( BAR bar, int height){
        
        int xcor = bar.get_X() - bar.getWidth()/2;
        int ycor = bar.get_Y() - bar.getHeight()/2;
        
        if((x - y - xcor + ycor)>0 && (x+y-xcor-ycor-height)<0 && (y-radius)>=(ycor-2*radius) && ySpeed>0) ySpeed=-ySpeed; 
            else if((x-y-xcor+ycor)<0 && (y-radius)>=(ycor-2*radius) && (x-radius)>=(xcor-2*radius) && xSpeed>0) xSpeed=-xSpeed;
                else if((x+y-2*radius-xcor-ycor-height)>0 && y>=(ycor-2*radius) && (x-radius)<=(xcor+100) && xSpeed<0) xSpeed=-xSpeed;


//        //bên trên
//        if( (((bar.get_X() - bar.getWidth()/2) <= this.x) && (this.x <= (bar.get_X() + bar.getWidth()/2))) && ((this.y + this.radius) >= (height - bar.getHeight()) )){
//            this.y = height - bar.getHeight() - radius;
//            ySpeed = - ySpeed;
//            move();
//        }

//        //bên trái
//        else{ if( (BOX_HEIGHT2 - bar.getHeight() <= ball.gety()) && (ball.gety() <= BOX_HEIGHT2) && ((ball.getx() + ball.getRadius()) > (bar.get_X() - (bar.getWidth()/2))) ){
//                    ball.setx(bar.get_X() - bar.getWidth()/2 - ball.getRadius() );
//                    ball.setxSpeed(-ball.getxSpeed() );
//                    ball.move();
//                }
//        
//        //bên phải
//            else{ if( (BOX_HEIGHT2 - bar.getHeight() <= ball.gety()) && (ball.gety() <= BOX_HEIGHT2) && ((ball.getx() - ball.getRadius()) < (bar.get_X() + (bar.getWidth()/2))) ){
//                    ball.setx(bar.get_X() + bar.getWidth()/2 + ball.getRadius() ) ;
//                    ball.setxSpeed(-ball.getxSpeed() );
//                    ball.move();
//                    }
//            }
//        }
    }
}
