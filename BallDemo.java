
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private ArrayList<BoxBall> balls;
    private Random rand;
    private int randX;
    private int randY;
    private int r;
    private int g;
    private int b;
    private int xPos;
    private int yPos;
    private int diameter;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 600);
        balls = new ArrayList<BoxBall>();
        rand = new Random();
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * Simulates balls bouncing around a box
     */
     public void boxBounce()
     {
        //establishes the boundaries that the ball can move inside
        int ground = 550;
        int ceiling = 50;
        int leftWall = 50;
        int rightWall = 550;
        
        myCanvas.setVisible(true);//makes the canvas visible
        
        for (int i = 0; i < 30; i++)//establishes how many balls are being stored in the arraylist
        { 
            do
            {
                randX = rand.nextInt(21)-10;//randomizes x speed for each ball in arraylist
            }
            while(randX==0);
        
            do
            {
                randY = rand.nextInt(21)-10;//randomizes y speed for each ball in arraylist
            }
            while(randY==0);
            
            r = rand.nextInt(200);//random int between 0-200, passed as red value
            g = rand.nextInt(200);//random int between 0-200, passed as green value
            b = rand.nextInt(200);//random int between 0-200, passed as blue value
            
            do
            {
                xPos = rand.nextInt(rightWall-leftWall)+1+leftWall;//randomizes starting x position
            }
            while(xPos==0);
            
            do
            {
                yPos = rand.nextInt(ground-ceiling)+1+ceiling;//randomizes starting y position
            }
            while(yPos==0);
            
            do
            {
                diameter = rand.nextInt(35-5)+1+5;//randomizes diameter
            }
            while(diameter==0);
            
            /**
             * Adds new balls to the ArrayList with all of the randomly generated variables
             * random color produced by the new Color() constructor
             */
            balls.add(new BoxBall(xPos, yPos, diameter, new Color(r, g, b), ground, ceiling, leftWall, 
                                  rightWall, myCanvas, randX, randY));
        }
        
        /**
         * While loop makes it so the balls don't stop bouncing, as condition true is never changed
         * for loop sets all balls to move
         */
        while(true)
        {
            for(BoxBall b : balls)
            {
                b.move(); //begins movement of ball for duration of the loop
            }
            myCanvas.wait(50);
            
            /**
             * draws the lines of the boundaries
             * redraws lines if "chipped away"
             */
            myCanvas.drawLine(50, ground, 550, ground);
            myCanvas.drawLine(50, ceiling, 550, ceiling);
            myCanvas.drawLine(leftWall, 50, leftWall, 550);
            myCanvas.drawLine(rightWall, 50, rightWall, 550);
        }
    }
}