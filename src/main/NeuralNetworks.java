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
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        final Renderer renderer = new Renderer();
        
        final TestCase testCase = new CircleMoveToXY();
        testCase.initialize(renderer);
        
        JFrame frame = new JFrame();
        frame.setSize(1600, 1050);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(renderer);
        frame.setVisible(true);
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run()
            {
                testCase.update();
            }
        }, 0, 100);
        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run()
            {
                renderer.repaint();
            }
        }, 0, 1000);
    }
}
