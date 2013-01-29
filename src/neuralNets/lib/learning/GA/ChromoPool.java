package neuralNets.lib.learning.GA;

import java.util.ArrayList;
import java.util.Collection;

import neuralNets.lib.learning.GA.chromosomes.Chromosome;
import neuralNets.lib.learning.GA.chromosomes.DoublesChromosome;

/**
 * 
 * @author Maarten Slenter
 */
public class ChromoPool<T extends DoublesChromosome>
{    
    /**
     * The mutation rate of this chromosome pool
     */
    private double mutationRate;
    
    /**
     * The cross over rate of this chromosome pool
     */
    private double crossOverRate;
    
    /**
     * The pool of chromosomes
     */
    ArrayList<Chromosome<T>> chromoPool = new ArrayList<Chromosome<T>>();
    
    /**
     * The average fitness of this pool, set at each update
     */
    private double avgFitness = 0;
    
    /**
     * 
     * @param mutationRate The mutation rate of this pool
     * @param crossOverRate The cross over rate of this pool
     */
    public ChromoPool(double crossOverRate, double mutationRate)
    {
        this.crossOverRate = crossOverRate;
        this.mutationRate = mutationRate;
    }
    
    /**
     * Adds all chromosomes from the supplied list to this pool
     * @param chromosomes The list of chromosomes to add
     */
    public void addChromosomes(Collection<Chromosome<T>> chromosomes)
    {
        chromoPool.addAll(chromosomes);
    }
    
    /**
     * Adds the supplied chromosome to this pool
     * @param chromosome The chromosome to add
     */
    public void addChromosome(Chromosome<T> chromosome)
    {
        chromoPool.add(chromosome);
    }
    
    /**
     * 
     * @return The average fitness of this chromosome pool
     */
    public double getAvgFitness()
    {
        return avgFitness;
    }
    
    /**
     * Updates the chromosome pool
     * @return A list with the new chromosomes
     */
    public ArrayList<Chromosome<T>> update()
    {
        if(chromoPool.size() % 2 != 0)
        {
            throw new RuntimeException("Gene pool size is not even");
        }
        
        if(chromoPool.size() < 4)
        {
            throw new RuntimeException("Gene pool size is too small");
        }
        
        ArrayList<Chromosome<T>> newChromoList = new ArrayList<Chromosome<T>>();
        
        double totalFitness = 0;
        for(Chromosome<T> chromosome : chromoPool)
        {
            totalFitness += chromosome.getFitness();
        }
        
        avgFitness = totalFitness/chromoPool.size();
        
        for(int i = chromoPool.size() / 2; i > 0; i -= 2)
        {            
            Chromosome<T> c1 = pickChromosome(chromoPool);
            Chromosome<T> c2 = pickChromosome(chromoPool);

            if(Math.random() <= crossOverRate)
            {
                if(c1.getLength() != c2.getLength())
                {
                    throw new RuntimeException("Chromosome lengths do not match, can not crossover");
                }
                int crossOverPoint = (int) Math.floor(Math.random() * c1.getLength());
                c1.crossOver(crossOverPoint, c2);
            }
            
            newChromoList.add(c1);
            newChromoList.add(c2);
            newChromoList.add(c1);
            newChromoList.add(c2);
        }
        
        chromoPool.clear();
        
        for(Chromosome<T> chromosome : newChromoList)
        {
            for(int i = 0; i <= chromosome.getLength() - 1; i++)
            {
                if(Math.random() <= mutationRate)
                {
                    chromosome.mutate(i);
                }
            }
        }
        
        return newChromoList;
    }
    
    /**
     * Resets the chromosome pool
     */
    public void reset()
    {
        chromoPool.clear();
        avgFitness = 0;
    }
    
    /**
     * Picks a chromosome from the supplied pool.
     * Chromosomes with a relatively higher fitness have more chance to be picked than those with a relatively lower fitness.
     * The picked chromosome will also directly be removed from the pool, to ensure the same chromosome can't be picked twice.
     * @param chromoPool The pool to pick the chromosome from
     * @return The picked chromosome
     */
    private Chromosome<T> pickChromosome(ArrayList<Chromosome<T>> chromoPool)
    {
        double totalFitness = 0;
        for(Chromosome<T> chromosome : chromoPool)
        {
            totalFitness += chromosome.getFitness();
        }
        
        double targetFitness = totalFitness * Math.random();
        
        totalFitness = 0;
        for(Chromosome<T> chromosome : chromoPool)
        {
            totalFitness += chromosome.getFitness();
            if(totalFitness >= targetFitness)
            {
                chromoPool.remove(chromosome);
                return chromosome;
            }
        }
        
        throw new RuntimeException("Could not pick a chromosome");
    }

    @Override
    public ChromoPool clone()
    {
        ChromoPool clone = new ChromoPool(crossOverRate, mutationRate);
        clone.chromoPool = (ArrayList<Chromosome>) chromoPool.clone();
        return clone;
    }
}
