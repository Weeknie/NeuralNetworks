package neuralNets.lib.components.basic;

/**
 * Factory for neural networks
 * @author Maarten Slenter
 */
public interface NetworkFactory 
{   
    /**
     * Create the network with the set topology
     */
    public Network create();
    
    /**
     * Sets the topology of the network
     * @param args The topology arguments
     */
    public void setTopology(int ... args);
}
