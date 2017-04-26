
package ballgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Balls_game_2 extends JPanel implements MouseMotionListener,KeyListener{
    
    int i, countBrick;
    private final int numBrick = 10;        //số lượng viên gạch, tối đa 50 viên
    private final int BOX_WIDTH2 = 1200;    //chiều rộng màn hình
    private final int BOX_HEIGHT2 = 700;    //chiều cao màn hình
    private final int BAR_WIDTH = 250;      //chiều rộng của thanh
    private final int BAR_HEIGHT = 30;      //chiều cao của thanh
    private final int BRICK_HEIGHT = 50;    //chiều cao của 1 viên gạch
    private final int BRICKLINE = 4;        //số viên gạch trên 1 hàng
    
    private final static int gameSpeed2 = 8;    //tốc độ game
    Ball ball2 = new Ball();
    BAR bar2 = new BAR();
    BRICK[] brick2 = new BRICK[50];
    
    String str1="GAME OVER";
    String str2="YOU WIN";
    String str3="YOU LOSE";
    
    public Balls_game_2(){ 
        addMouseMotionListener(this);
        this.setPreferredSize(new Dimension(BOX_WIDTH2, BOX_HEIGHT2));
                
        ball2.setRadius(25);
        ball2.setx(300);
        ball2.sety(400);
        ball2.setxSpeed(2);     //tốc độ của bóng
        ball2.setySpeed(3);
        
        bar2.setWidth(BAR_WIDTH);
        bar2.setHeight(BAR_HEIGHT);
        bar2.set_X(200);
        bar2.set_Y(BOX_HEIGHT2 - bar2.getHeight()/2);
        
        for(i=0; i<numBrick; i++)
        {
            brick2[i] = new BRICK();
            brick2[i].setHeight(BRICK_HEIGHT);
            brick2[i].setWidth(BOX_WIDTH2/BRICKLINE);
            brick2[i].set_X(( (i*brick2[i].getWidth()) % BOX_WIDTH2 ) + brick2[i].getWidth()/2   );
            brick2[i].set_Y( 25 + (i/BRICKLINE)*BRICK_HEIGHT );
//            brick2[i].setWidth(BOX_WIDTH2/numBrick);
//            brick2[i].set_X(brick2[i].getWidth()/2 + i*brick2[i].getWidth());
//            brick2[i].set_Y(brick2[i].getHeight()/2);
            brick2[i].setCheck(false);
        }

        Thread game2;
        game2 = new Thread(){
            public void run(){
                while(true){
                    ball2.move();
                    
                    ball2.overBound2(BOX_WIDTH2, BOX_HEIGHT2);      //bóng vs tường
                    bar2.barlimit(BOX_WIDTH2);                      //giới hạn thanh không ra khỏi màn hình
                    ball2.CollisionBar( bar2, BOX_HEIGHT2);         //bóng vs thanh
                    for(i=0; i<numBrick; i++)                       //bóng vs gạch
                        if(brick2[i].getCheck() == false)       
                            brick2[i].brickContactBall(ball2);
                    gameOver(ball2);                                //kiểm tra bóng chết
                                        
                    repaint();
                    try {
                        Thread.sleep(gameSpeed2);
                    } catch (Exception e) {
                    }
                }
            }
        };
        game2.start();
    }
    
    public void gameOver(Ball ball){        //nếu bóng lọt khỏi màn hình thì bóng dừng lại
        if( ball.gety() - ball.getRadius() > BOX_HEIGHT2 ){
            ball.stop();                    //bóng dừng lại, các thông số vận tốc về giá trị 0
        }
    }
    
    @Override
    public void paint(Graphics g){
        
        super.paint(g);     
        Font font =new Font("Verdana",Font.BOLD,50);
        g.setColor(Color.LIGHT_GRAY);    //vẽ nền
        g.fillRect(0, 0, BOX_WIDTH2, BOX_HEIGHT2);
        
        g.setColor(Color.blue);         //vẽ bóng
        g.fillOval(ball2.getx() - ball2.getRadius(), ball2.gety() - ball2.getRadius(), 2*ball2.getRadius(), 2*ball2.getRadius());
        
        g.setColor(Color.black);        //vẽ thanh di chuyển
        g.fillRect(bar2.get_X() - bar2.getWidth()/2, bar2.get_Y() - bar2.getHeight()/2, bar2.getWidth(), bar2.getHeight());
        
        //khởi tạo giá trị cho biến đếm số gạch còn lại
        countBrick =0;
        
        for(i=0; i<numBrick; i++){              //vẽ các viên gạch
            if(brick2[i].getCheck() == false){
                g.setColor(Color.ORANGE);
                g.fillRect(brick2[i].get_X() - brick2[i].getWidth()/2, brick2[i].get_Y() - brick2[i].getHeight()/2, brick2[i].getWidth(), brick2[i].getHeight());
                g.setColor(Color.black);        //vẽ viền cho gạch
                g.drawRect(brick2[i].get_X() - brick2[i].getWidth()/2, brick2[i].get_Y() - brick2[i].getHeight()/2, brick2[i].getWidth(), brick2[i].getHeight());
                countBrick++;
            }
        }
        
        if(countBrick == 0){
            g.setFont(font);
            g.setColor(Color.red);          
            g.drawString(str1, BOX_WIDTH2/2 - 140, BOX_HEIGHT2/2 - 30);
            g.setColor(Color.red);
            g.drawString(str2, BOX_WIDTH2/2 - 130, BOX_HEIGHT2/2 + 30);
        }
        
        if(ball2.isStop() && (countBrick > 0) ){
            g.setFont(font);
            g.drawString(str1, BOX_WIDTH2/2 - 140, BOX_HEIGHT2/2 - 30);
            g.drawString(str3, BOX_WIDTH2/2 - 125, BOX_HEIGHT2/2 + 30);
        }
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame abc = new JFrame("Balls Game version 2");
                abc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                abc.getContentPane().add(new Balls_game_2());
                abc.pack();
                abc.setVisible(true);
            }
        });      
    }

    @Override
    public void mouseDragged(MouseEvent m) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {         //lệnh gán cho tọa độ X tâm của thanh 
        bar2.set_X(me.getX());                      //bằng tọa độ X của chuột
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
//        int keyCode = ke.getKeyCode();
//        if(keyCode == KeyEvent.VK_LEFT)
//            bar2.set_X(bar2.get_X() - 3);
//        if(keyCode == KeyEvent.VK_RIGHT);
//            bar2.set_X(bar2.get_X() - 3);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
}