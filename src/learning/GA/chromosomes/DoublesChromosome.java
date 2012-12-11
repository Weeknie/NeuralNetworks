package learning.GA.chromosomes;

import java.util.ArrayList;

/**
 * The default implementation of a chromosome
 * @author Maarten Slenter
 */
public class DoublesChromosome extends Chromosome<Double>
{    
    /**
     * The list of doubles this chromosome consists of
     */
    ArrayList<Double> doubles;
    
    public DoublesChromosome(int length, double fitness, ArrayList<Double> doubles)
    {
        super(length, fitness);
        
        if(doubles.size() != length)
        {
            throw new IllegalArgumentException("The lenght of the array and the supplied length are unequal");
        }
        
        this.doubles = doubles;
    }
    
    /**
     * 
     * @return The list of doubles that this chromosome consists of
     */
    public ArrayList<Double> getDoubles()
    {
        return doubles;
    }
    
    /**
     * Mutate this chromosome at the specified index
     * @param index The index to mutate at
     */
    @Override
    public void mutate(int index)
    {
        doubles.set(index, doubles.get(index) + ((Math.random() >= 0.5) ? 1 : -1) * doubles.get(index) * Math.random());
    }
    
    @Override
    public double getValue()
    {
        double value = 0;
        
        for(double gene : doubles)
        {
            value += gene;
        }
        
        return value;
    }
    
    /**
     * Cross over this chromosome with the supplied other chromosome from the supplied index
     * @param indexFrom The index to start the cross over from
     * @param otherChromo The other doubles chromosome to cross over with
     */
    @Override
    public void crossOver(int indexFrom, Chromosome otherChromo)
    {
        if(!(otherChromo instanceof DoublesChromosome))
        {
            throw new IllegalArgumentException("Gene types are not compatible");
        }
        
        crossOver(indexFrom, (DoublesChromosome) otherChromo);
    }
    
    private void crossOver(int indexFrom, DoublesChromosome otherChromo)
    {
        for(int i = indexFrom; i <= doubles.size() - 1; i++)
        {
            double tmp = otherChromo.doubles.get(i);
            otherChromo.doubles.set(i, doubles.get(i));
            doubles.set(i, tmp);
        }
    }
    
    @Override
    public DoublesChromosome clone()
    {
        DoublesChromosome chromo = new DoublesChromosome(length, fitness, doubles);
        return chromo;
    }
}
