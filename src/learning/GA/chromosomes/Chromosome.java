package learning.GA.chromosomes;

/**
 *
 * @author Maarten Slenter
 */
public abstract class Chromosome<T> implements Comparable<Chromosome>
{
    /**
     * The fitness of this chromosome (how effective it is)
     */
    protected double fitness = 0;
    
    /**
     * The length of this chromosome
     */
    protected int length;
    
    /**
     * 
     * @param length The length of this chromosome
     */
    public Chromosome(int length, double fitness)
    {
        this.length = length;
        this.fitness = fitness;
    }
    
    /**
     * Mutate this chromosome at the specified index
     * @param index The index to mutate at
     */
    abstract public void mutate(int index);
    
    /**
     * Cross over this chromosome with the supplied other chromosome from the supplied index
     * @param indexFrom The index to start the cross over from
     * @param otherGene The other chromosome to cross over with
     */
    abstract public void crossOver(int indexFrom, Chromosome otherGene);
    
    /**
     * Compares this chromosome to another one and determines which one is greater
     * @param chromosome The chromosome to compare to
     * @return -1 if this chromosome is smaller, 0 if the chromosomes are equal and 1 if this chromosome is bigger
     */
    @Override
    public int compareTo(Chromosome chromosome)
    {
        if(chromosome.equals(this))
        {
            return 0;
        }
        
        int doubleComp = Double.compare(fitness, chromosome.fitness);
        
        return doubleComp == 0 ? (this.hashCode() < chromosome.hashCode() ? -1 : 1) : doubleComp;
    }
    
    public abstract double getValue();
    
    /**
     * 
     * @return The length of this chromosome
     */
    public int getLength()
    {
        return length;
    }
    
    /**
     * 
     * @return The fitness of this chromosome
     */
    public double getFitness()
    {
        return fitness;
    }
    
    /**
     * Clones this object
     * @return A clone of this object
     */
    @Override
    public abstract Chromosome clone();
}