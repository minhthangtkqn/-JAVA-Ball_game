
package ballgame;

public class BRICK {
    private int x,y,width, height;
    boolean check;
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
    public boolean getCheck(){
        return check;
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
    public void setCheck(boolean check){
        this.check = check;
    }
    
//    public void brickContactBall(Ball ball){
////        if((ball.getx()+ball.gety()+2*ball.getRadius()-this.x-this.y-this.height)>0 && (ball.getx()-ball.gety()-this.x-this.width+this.y+this.height)<0  && ball.gety()<=(this.y+this.height) && ball.getySpeed()<0) 
////            {ball.setySpeed(-ball.getySpeed());check=true;}
////	else if( (ball.getx()-ball.gety()-this.x-this.width+this.y+this.height)>0 && ball.gety()<(this.y+this.height) && ball.getx()<=(this.x+this.width) && ball.getxSpeed()<0) 
////                {ball.setxSpeed(-ball.getxSpeed());check=true;}
////                    else if( (ball.getx()+ball.gety()+2*ball.getRadius()-this.x-this.y-this.height)<0 && ball.gety()<(this.y+this.height) && ball.getx()>=(this.x-2*ball.getRadius()) && ball.getxSpeed()>0) 
////                        {ball.setxSpeed(-ball.getxSpeed());check=true;}
//            //mặt dưới viên gạch
//            if( (((x - width/2) <= ball.getx()) && (ball.getx() <= (x + width/2))) && ((ball.gety() - ball.getRadius()) <= ( y + height/2 ) ) && (ball.getySpeed()<0) ){
//                ball.sety(y + height/2 + ball.getRadius());
//                ball.setySpeed(-ball.getySpeed());
//                ball.move();
//                check = true;
//            }
//                //mặt bên trái viên gạch
//            else    if( (ball.gety() >= y - height) && (ball.gety() <= y+height) && (ball.getx() + ball.getRadius() >= x - width) && (ball.getxSpeed() > 0)  ){
//                        ball.setx(x - width/2 - ball.getRadius());
//                        ball.setxSpeed(-ball.getxSpeed());
//                        ball.move();
//                        check = true;
//                    }
//                    //mặt bên phải viên gạch
//                    else    if( (ball.gety() >= y - height) && (ball.gety() <= y+height) && ( ball.getx() - ball.getRadius() <= x + width) && (ball.getxSpeed() < 0) ){
//                                ball.setx(x + width/2 + ball.getRadius());
//                                ball.setxSpeed(-ball.getxSpeed());
//                                ball.move();
//                            }
//    }
    //b.getX()+b.getY()+2*b.getR()-x-y-height)>0 && (b.getX()-b.getY()-x-width+y+height)<0  && b.getY()<=(y+height) && b.getySpeed()<0
    //b.getX()-b.getY()-x-width+y+height)>0 && b.getY()<(y+height) && b.getX()<=(x+width) && b.getxSpeed()<0
    //b.getX()+b.getY()+2*b.getR()-x-y-height)<0 && b.getY()<(y+height) && b.getX()>=(x-2*b.getR()) && b.getxSpeed()>0
    public void brickContactBall(Ball ball){
        if((ball.getx() + ball.gety() -(x - width/2 )-(y - height/2)-height)>0 && (ball.getx()-ball.gety()-(x-width/2)-width+(y-height/2)+height)<0  && (ball.gety()-ball.getRadius())<=((y-height/2)+height) && ball.getySpeed()<0) 
            {ball.setySpeed(-ball.getySpeed());check=true;}
	else if( (ball.getx()-ball.gety()-(x-width/2)-width+(y-height/2)+height)>0 && (ball.gety() - ball.getRadius())<((y-height/2)+height) && (ball.getx() - ball.getRadius())<=((x-width/2)+width) && ball.getxSpeed()<0) 
                {ball.setxSpeed(-ball.getxSpeed());check=true;}
                    else if( (ball.getx()+ball.gety()-(x-width/2)-(y-height/2)-height)<0 && (ball.gety() - ball.getRadius())<((y-height/2)+height) && (ball.getx() - ball.getRadius())>=((x-width/2)-2*ball.getRadius()) && ball.getxSpeed()>0) 
                        {ball.setxSpeed(-ball.getxSpeed());check=true;}
    }
}
