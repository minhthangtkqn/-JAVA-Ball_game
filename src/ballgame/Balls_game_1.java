package ballgame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Balls_game_1 extends Frame implements Runnable{
            
    int i, j;
    final int numBalls = 10;
    private final int BOX_WIDTH = 800;
    private final int BOX_HEIGHT = 600;
    private final static int gameSpeed = 10;
    Random r = new Random();
    Ball[] ball = new Ball[200];

    public Balls_game_1() {
        this.addWindowListener(new WindowAdapter()
	{
            @Override
            public void windowClosing(WindowEvent e)    //nút X đóng cửa sổ
            {
		System.exit(0);
            }
	});
        setTitle("The Bouncing Balls Game");
        setBackground(Color.black);
        setSize(BOX_WIDTH, BOX_HEIGHT);
        setVisible(true);
        //khởi tạo giá trị các thông số của ball
        for (i = 0; i < numBalls; i++) {
            ball[i] = new Ball();
            ball[i].setx(50 + 100 * r.nextInt(10));
            ball[i].sety(50 + 100 * r.nextInt(6));
            ball[i].setRadius(10 + 10 * r.nextInt(2));
            ball[i].setxSpeed(-4 + r.nextInt(8));
            ball[i].setySpeed(-4 + r.nextInt(8));
            ball[i].setColor(r.nextInt(6));
        }
        Thread game = new Thread(this);
        game.start();                       //Khi Thread game bắt đầu chạy bằng lệnh start thì hàm run() được thực hiện
    }

    @Override
    public void run() {
        while (true) {
            for (i = 0; i < numBalls; i++) {
                ball[i].move();
            }

            //2 ball va nhau đôi một, mỗi ball va với 99 bal kia
            for (i = 0; i < numBalls - 1; i++) //ball thứ i
                for (j = i + 1; j < numBalls; j++) //các ball kia
                    Collision(ball[i], ball[j]);
            
            //Các balls va vào tường
            for (i = 0; i < numBalls; i++) {
                overBound(ball[i]);
            }
            repaint();
            try {
                Thread.sleep(gameSpeed);
            } catch (Exception e) {
            }
        }
    }
    //Tách 2 ball bị dính
    public void Separate(Ball a, Ball b){
        //khoảng cách giữa tâm 2 bóng
        double dist = Math.sqrt(Math.pow(a.getx() - b.getx(), 2) + Math.pow(a.gety() - b.gety(), 2));
        //giới hạn khoảng cách để xảy ra va chạm
        double range = a.getRadius() + b.getRadius();
        while( dist <= range )
        {
            a.move();
            b.move();
            dist = Math.sqrt(Math.pow(a.getx() - b.getx(), 2) + Math.pow(a.gety() - b.gety(), 2));
        }
    }
    
    public void Collision(Ball a, Ball b) {
        int tam;
        double dist = Math.sqrt(Math.pow(a.getx() - b.getx(), 2) + Math.pow(a.gety() - b.gety(), 2));
        double range = a.getRadius() + b.getRadius();
        if ( dist <= range ) {

            //hoán đổi tốc độ cho nhau
            tam = a.getxSpeed();
            a.setxSpeed(b.getxSpeed());
            b.setxSpeed(tam);

            tam = a.getySpeed();
            a.setySpeed(b.getySpeed());
            b.setySpeed(tam);
            
            Separate(a, b);
        }    
    }

    public void overBound(Ball a) {
        if ((a.getx() - a.getRadius()) <= 15) {
            a.setxSpeed(-a.getxSpeed());
            a.setx(a.getRadius()+15);
        } else if (a.getx() >= this.getWidth() - a.getRadius()) {
            a.setxSpeed(-a.getxSpeed());
            a.setx(this.getWidth() - a.getRadius());
        }

        if ((a.gety() - a.getRadius()) <= 13) {
            a.setySpeed(-a.getySpeed());
            a.sety(a.getRadius() + 13);
        } else if (a.gety() >= this.getHeight() - a.getRadius()) {
            a.setySpeed(-a.getySpeed());
            a.sety(this.getHeight() - a.getRadius());
        }
    }

    @Override
    public void paint(Graphics g) {
        //paint balls
        for (i = 0; i < numBalls; i++) {
            switch (ball[i].getColor()) {   //random color of balls
                case 0: {
                    g.setColor(Color.red);
                    break;
                }
                case 1: {
                    g.setColor(Color.blue);
                    break;
                }
                case 2: {
                    g.setColor(Color.pink);
                    break;
                }
                case 3: {
                    g.setColor(Color.green);
                    break;
                }
                case 4: {
                    g.setColor(Color.cyan);
                    break;
                }
                case 5: {
                    g.setColor(Color.yellow);
                    break;
                }
                case 6: {
                    g.setColor(Color.ORANGE);
                    break;
                }
            }
            g.fillOval(ball[i].getx() - ball[i].getRadius(), ball[i].gety() - ball[i].getRadius(), 2 * ball[i].getRadius(), 2 * ball[i].getRadius());
        }
    }

    public static void main(String[] args) {
        Balls_game_1 balls_game = new Balls_game_1();
    }
}