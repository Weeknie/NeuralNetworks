package testcases.circleMoveToXYDirection;

import java.awt.Color;
import java.util.ArrayList;

import components.extended.networks.SigmoidNetwork;
import graphics.entities.Line;
import learning.GA.ChromoPool;
import learning.GA.chromosomes.Chromosome;
import learning.GA.chromosomes.DoublesChromosome;
import main.Renderer;
import testcases.TestCase;

/**
 * Testcase CircleMoveToXY
 * Goal: The circles must move to a certain X Y coordinate
 * Network: Sigmoid network
 * Learning: Genetic algorithm with doubles (the weights) as genes
 * @author Maarten Slenter
 */
public class CircleMoveToXYDirection extends TestCase
{
    /**
     * The circle entity instances
     */
    ArrayList<CircleEntity> circleEntities = new ArrayList<>();
    
    /**
     * The x coordinate the circles should move to
     */
    public static int goalX = 200;
    
    /**
     * The y coordinate the circles should move to
     */
    public static int goalY = 200;
    
    /**
     * The line that represents the x coordinate goal
     */
    private Line goalLineX = new Line(goalX, 0, goalX, 1500, Color.green);
    
    /**
     * The line that represents the y coordinate goal
     */
    private Line goalLineY = new Line(0, goalY, 1500, goalY, Color.green);
    
    /**
     * The length of the chromosomes (the amount of adjustable doubles)
     * The lenght is calculated with the following formula: (inputs + (hiddenLayers + 1) * neuronsPerLayer)
     */
    private int chromoLength = 2 + 2 * 6 + 2 * 6;
    
    /**
     * The chromosome pool to use for the genetic algoritm
     */
    private ChromoPool chromoPool = new ChromoPool(chromoLength, 0.7, 0.05);
    
    /**
     * Counts the amount of updates that have taken place.
     * After 100 updates the neural networks will be updated with new weights.
     */
    private int updateCount = 0;

    /**
     * Initializes the testcase, instantiating all cricle entities and 
     * @param renderer The renderer instance
     */
    @Override
    public void initialize(Renderer renderer)
    {
        renderer.addDrawable(goalLineX);
        renderer.addDrawable(goalLineY);
        
        for(int i = 1; i <= 100; i++)
        {
            //CircleEntity circleEntity = new CircleEntity((int) (800 * Math.random()), (int) (800 * Math.random()), 5, new SigmoidNetwork(2, 2, 1, 6, 1, 1, 10), renderer);
            CircleEntity circleEntity = new CircleEntity((int) (400 * Math.random()), (int) (400 * Math.random()), 5, new SigmoidNetwork(2, 2, 1, 6, 1, 1, 10), renderer);
            circleEntities.add(circleEntity);
            renderer.addDrawable(circleEntity.getCircle());
        }
        
        //DrawableDefaultNetwork.drawNetwork(circleEntities.get(0).getNetwork(), renderer);
    }

    /**
     * Updates the testcase.
     * Loops through all circle entities and updates them
     */
    @Override
    public void update()
    {
        updateCount++;
        
        for(CircleEntity circleEntity : circleEntities)
        {
            circleEntity.update();
        }
        
        if(updateCount == 100)
        {            
            Chromosome workingChromo = null;
            for(CircleEntity circleEntity : circleEntities)
            {
                double fitness = 1 / (Math.abs(goalX - circleEntity.getX()) + Math.abs(goalY - circleEntity.getY()));
                Chromosome chromosome = new DoublesChromosome(chromoLength, fitness, circleEntity.getNetwork().getWeights());
                if(fitness == Double.POSITIVE_INFINITY)
                {
                    workingChromo = chromosome.clone();
                }
                chromoPool.addChromosome(chromosome);
                circleEntity.reset();
            }
            
            ArrayList<Chromosome> newChromosomes = chromoPool.update();
            
            if(workingChromo != null)
            {
                for(int i = 0; i <= newChromosomes.size() - 1; i++)
                {
                    newChromosomes.set(i, workingChromo);
                }
            }
            
            for(int i = 0; i <= newChromosomes.size() - 1; i++)
            {
                circleEntities.get(i).getNetwork().setWeights(((DoublesChromosome) newChromosomes.get(i)).getDoubles());
            }
            
            System.out.println("Generation " + generation + ": " + chromoPool.getAvgFitness());
            
            updateCount = 0;
            generation++;
        }
    }
    
    /**
     * Proxy method for chromoPool.getAvgFitness
     * @return The average fitness of the chromosome pool
     */
    @Override
    public double getAvgFitness()
    {
        return chromoPool.getAvgFitness();
    }
}
