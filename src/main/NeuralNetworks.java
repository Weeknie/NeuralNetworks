package main;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import testcases.TestCase;
import testcases.circleMoveToXY.CircleMoveToXY;

/**
 * 
 * @author s109367
 */
public class NeuralNetworks
{
    static Timer timer = new Timer();
    
    /**
     * Initializes the renderer and test case, then sets up the window.
     * Runs testCase.update() every 100 milliseconds (updates the position of the testcase)
     * Runs renderer.repaint() every second (updates the window)
     * @param args 
     */
    public static void main(String[] args)
    {
        final Renderer renderer = new Renderer();
        
        final TestCase testCase = new CircleMoveToXY();
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
                testCase.update();
            }
        }, 0, 50);
        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run()
            {
                renderer.repaint();
            }
        }, 0, 50);
    }
}
