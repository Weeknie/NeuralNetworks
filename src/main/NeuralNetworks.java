package main;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import testcases.TestCase;
import testcases.circleMoveToXYCoordinates.CircleMoveToXYCoordinates;
import testcases.circleMoveToXYDirection.CircleMoveToXYDirection;

/**
 * 
 * @author s109367
 */
public class NeuralNetworks
{
    static Timer timer = new Timer();
    
    static int count = 0;
    
    /**
     * Initializes the renderer and test case, then sets up the window.
     * Runs testCase.update() every 100 milliseconds (updates the position of the testcase)
     * Runs renderer.repaint() every second (updates the window)
     * @param args 
     */
    public static void main(String[] args)
    {
        final Renderer renderer = new Renderer();
        
        final TestCase testCase = new CircleMoveToXYDirection();
        testCase.initialize(renderer);
        
        JFrame frame = new JFrame();
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(renderer);
        frame.setVisible(true);
        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run()
            {
                count++;
                
                if(count == (int) Math.min(50, Math.max((testCase.getGeneration() + testCase.getAvgFitness())/50, 1)))
                {
                    testCase.update();
                    count = 0;
                }
            }
        }, 0, 1);
        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run()
            {
                renderer.repaint();
            }
        }, 0, 50);
    }
}
