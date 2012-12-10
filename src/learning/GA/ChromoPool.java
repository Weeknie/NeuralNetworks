package learning.GA;

import java.util.ArrayList;
import java.util.Collection;

import learning.GA.chromosomes.Chromosome;
import learning.GA.chromosomes.DoublesChromosome;

/**
 * 
 * @author Maarten Slenter
 */
public class ChromoPool<T extends DoublesChromosome>
{    
    /**
     * The mutation rate of this chromosome pool
     */
    private double mutationRate = 0.001;
    
    /**
     * The cross over rate of this chromosome pool
     */
    private double crossOverRate = 0.7;
    
    /**
     * The pool of chromosomes
     */
    ArrayList<Chromosome<T>> chromoPool = new ArrayList<>();
    
    /**
     * The length each chromosome in this pool should have
     */
    public final int chromoLength;
    
    /**
     * 
     * @param chromoLength The length of chromosomes in this pool
     */
    public ChromoPool(int chromoLength)
    {
        this.chromoLength = chromoLength;
    }
    
    /**
     * 
     * @param mutationRate The mutation rate of this pool
     * @param crossOverRate The cross over rate of this pool
     * @param chromoLength The length of chromosomes in this pool
     */
    public ChromoPool(int chromoLength, double crossOverRate, double mutationRate)
    {
        this(chromoLength);
        
        this.crossOverRate = crossOverRate;
        this.mutationRate = mutationRate;
    }
    
    /**
     * Adds all chromosomes from the supplied list to this pool
     * @param chromosomes The list of chromosomes to add
     */
    public void addChromosomes(Collection<Chromosome<T>> chromosomes)
    {
        for(Chromosome<T> chromosome : chromosomes)
        {
            if(chromosome.getLength() != chromoLength)
            {
                throw new IllegalArgumentException("Chromosome length is incorrect");
            }
        }
        
        chromoPool.addAll(chromosomes);
    }
    
    /**
     * Adds the supplied chromosome to this pool
     * @param chromosome The chromosome to add
     */
    public void addChromosome(Chromosome<T> chromosome)
    {
        if(chromosome.getLength() != chromoLength)
        {
            throw new IllegalArgumentException("Chromosome length is incorrect");
        }
        
        chromoPool.add(chromosome);
    }
    
    /**
     * Updates the chromosome pool
     * @return A list with the new chromosomes
     */
    public ArrayList<Chromosome<T>> update()
    {
        if(chromoPool.size() % 2 != 0)
        {
            throw new RuntimeException("Gene pool length is not even");
        }
        
        ArrayList<Chromosome<T>> newChromoList = new ArrayList<>();
        
        double totalFitness = 0;
        for(Chromosome<T> chromosome : chromoPool)
        {
            totalFitness += chromosome.getFitness();
        }
        
        System.out.println("Avg fitness: " + totalFitness/chromoPool.size());
        
        for(int i = chromoPool.size() / 2; i > 0; i -= 2)
        {            
            Chromosome<T> c1 = pickChromosome(chromoPool);
            Chromosome<T> c2 = pickChromosome(chromoPool);

            if(Math.random() <= crossOverRate)
            {
                int crossOverPoint = (int) Math.floor(Math.random() * chromoLength);
                c1.crossOver(crossOverPoint, c2);
            }
            
            newChromoList.add(c1);
            newChromoList.add(c2);
            newChromoList.add(c1);
            newChromoList.add(c2);
        }
        
        chromoPool.clear();
        
        totalFitness = 0;
        for(Chromosome<T> chromosome : newChromoList)
        {
            totalFitness += chromosome.getFitness();
        }
        
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
        
        return null;
    }
}
