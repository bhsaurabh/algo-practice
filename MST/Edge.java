import java.lang.Comparable;
import java.lang.String;

/**
 * A weighted edge!
 */
public class Edge implements Comparable<Edge> {
    private final int v, w;  // connecting vertices
    private final double weight;    // weight of Edge
    
    /**
     * Constructor initialises the Edge
     * 
     * @param v, w: vertices to be connected
     * @param weight: weight of Edge
     */
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    
    /**
     * Arbitrarily return a vertex. Good for starting Edge processing
     * 
     * @return v: a vertex connected by the said Edge
     */
    public int either() {
        return v;
    }
    
    /**
     * Given a vetex, fetch the other vertex connected by the Edge
     * 
     * @param vertex: vertex that you know
     * 
     * @return w: the other vertex
     */
    public int other(int vertex) {
        if (v == this.v) {
            return this.w;
        } else {
            return this.v;
        }
    }
    
    /**
     * @return weight: the weight of this Edge
     */
    public double weight() {
        return this.weight; 
    }
    
    /**
     * Compares this Edge with another one
     * 
     * @param that: Edge to be compared with
     * 
     * @return val:
     *      val > 0 => this edge has bigger weight
     *      val < 0 => the other edge has bigger weight
     *      val == 0 => both edges have equal weights
     */
    public int compareTo(Edge that) {
        return this.weight.compareTo(that.weight);
    }
    
    /**
     * @return edge: String representation of the Edge
     */
    public String toString() {
        return String.valueOf(v) + " -> " + String.valueOf(w);
    }
}