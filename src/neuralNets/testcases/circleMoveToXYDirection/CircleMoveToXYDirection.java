package neuralNets.testcases.circleMoveToXYDirection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import neuralNets.lib.components.extended.networkFactories.SigmoidFactory;
import neuralNets.lib.graphics.entities.Line;
import neuralNets.lib.graphics.networks.DrawableSigmoidNetwork;
import neuralNets.lib.learning.GA.ChromoPool;
import neuralNets.lib.learning.GA.chromosomes.Chromosome;
import neuralNets.lib.learning.GA.chromosomes.DoublesChromosome;
import neuralNets.main.Renderer;
import neuralNets.testcases.TestCase;

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
    ArrayList<CircleEntity> circleEntities = new ArrayList<CircleEntity>();
    
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
     * The chromosome pool to use for the genetic algorithm
     */
    private ChromoPool chromoPool = new ChromoPool(0.7, 0.01);
    
    /**
     * Counts the amount of updates that have taken place.
     * After 100 updates the neural networks will be updated with new weights.
     */
    private int updateCount = 0;
    
    /**
     * The population size (the amount of circle entities)
     */
    private static int popSize = 100;
    
    /**
     * The amount of circles to visualize the network for
     */
    private static int visualizeAmount = 10;
    
    {
        networkFactory = new SigmoidFactory();
        networkFactory.setTopology(2, 2, 1, 6, 1, 1, 10);
    }

    /**
     * Initializes the test case, instantiating all circle entities.
     * Does not add any visualization, use for batch testing.
     */
    @Override
    public void initialize()
    {
        for(int i = 1; i <= popSize; i++)
        {
            CircleEntity circleEntity = new CircleEntity((int) (400), (int) (400), 5, networkFactory.create());
            circleEntities.add(circleEntity);
        }
    }

    /**
     * Initializes the test case, instantiating all circle entities and 
     * @param renderer The renderer instance
     */
    @Override
    public void initialize(Renderer renderer)
    {
        initialize();
        
        renderer.addDrawable(goalLineX);
        renderer.addDrawable(goalLineY);
        
        for(CircleEntity circleEntity : circleEntities)
        {
            renderer.addDrawable(circleEntity.getCircle());
        }
        
        Random random = new Random();
        
        for(int i = 0; i <= visualizeAmount - 1; i++)
        {
            int index = (int) random.nextInt(100);
            
            DrawableSigmoidNetwork.drawNetwork(circleEntities.get(index).getNetwork(), renderer, 300 * (i % 5), 200 * (int) (i / 5), 2, Color.black);
            circleEntities.get(index).getCircle().setColor(Color.red);
            circleEntities.get(index).getCircle().setFill(true);
        }
    }

    @Override
    public void reinitialize()
    {
        circleEntities.clear();
        updateCount = 0;
        generation = 0;
        chromoPool.reset();
        initialize();
    }
    
    /**
     * Reinitializes the test case
     * @param renderer The renderer instance
     */
    @Override 
    public void reinitialize(Renderer renderer)
    {
        renderer.clearDrawables();
        reinitialize();
        initialize(renderer);
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
            generation++;
            
            Chromosome workingChromo = null;
            for(CircleEntity circleEntity : circleEntities)
            {
                double fitness = 1 / (Math.abs(goalX - circleEntity.getX()) + Math.abs(goalY - circleEntity.getY()));
                Chromosome chromosome = new DoublesChromosome(fitness, circleEntity.getNetwork().getAdjustables());
                if(fitness == Double.POSITIVE_INFINITY)
                {
                    workingChromo = chromosome.clone();
                }
                chromoPool.addChromosome(chromosome);
                circleEntity.reset();
            }
            
            ArrayList<Chromosome> newChromosomes = chromoPool.update();
            
            if((workingChromo != null && chromoPool.getAvgFitness() != Double.POSITIVE_INFINITY) || workingChromo == null && chromoPool.getAvgFitness() == Double.POSITIVE_INFINITY)
            {
                throw new RuntimeException("Fitnesses are incorrect");
            }
            
            if(workingChromo != null)
            {
                for(int i = 0; i <= newChromosomes.size() - 1; i++)
                {
                    circleEntities.get(i).getNetwork().setAdjustables(((DoublesChromosome) workingChromo).getDoubles());
                }
            }
            else
            {
                for(int i = 0; i <= newChromosomes.size() - 1; i++)
                {
                    circleEntities.get(i).getNetwork().setAdjustables(((DoublesChromosome) newChromosomes.get(i)).getDoubles());
                }
            }
            
            updateCount = 0;
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
    
    @Override
    public CircleMoveToXYDirection clone()
    {
        CircleMoveToXYDirection clone = new CircleMoveToXYDirection();
        clone.initialize();
        return clone;
    }
}