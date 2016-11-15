import java.util.Iterator;

/**
* @file Graph.java
* basado en http://cs.boisestate.edu/~amit/teaching/342/examples/graphs/Graph.java
*/

/**
 * Interface for graphs, both directed and undirected.  The
 * implementation depends on whether the adjacency list or adjacency
 * matrix representation is used.
 */

public interface Graph
{
    /**
     * Adds a vertex to this graph.  Given the vertex's name, a
     * <code>Vertex</code> object is created and added.  The next
     * available index is used.
     *
     * @param name The vertex's name.
     * @return The new <code>Vertex</code> object added.
     */
    public Vertex addVertex(String name);

    /**
     * Adds a vertex to this graph.  Given the vertex's name and
     * index, a <code>Vertex</code> object is created and added.
     *
     * @param index The vertex's index.
     * @param name The vertex's name.
     * @return The new <code>Vertex</code> object added.
     */
    public Vertex addVertex(int index, String name);

    /**
     * Adds a vertex to this graph, given a <code>Vertex</object>.  If
     * the vertex's index is unknown, use the next available index.
     * Otherwise, use the index in the vertex.
     *
     * @param v The <code>Vertex</code> object to add.
     * @return <code>v</code>.
     */
    public Vertex addVertex(Vertex v);

    /**
     * Returns the vertex with a given index.
     *
     * @param index The index of the vertex.
     * @return The <code>Vertex</code> with the given index.
     */
    public Vertex getVertex(int index);

    /**
     * Adds an edge to this graph.  The edge is specified by a pair of
     * <code>Vertex</code> objects.
     *
     * @param u One vertex.
     * @param v The other vertex.
     */
    public void addEdge(Vertex u, Vertex v);

    /**
     * Adds an edge to this graph.  The edge is specified by a pair of
     * vertex indices.
     *
     * @param u The index of one vertex.
     * @param v The index of the other vertex.
     */
    public void addEdge(int u, int v);

    /** Returns an iterator that iterates though all the vertices in
     * the graph. */
    public Iterator vertexIterator();

    /**
     * Returns an iterator that iterates through the edges incident on
     * a given vertex.  Each incident edge is indicated by the
     * corresponding adjacent vertex.
     *
     * @param u The vertex whose incident edges are returned by the
     * iterator.
     */
    public Iterator edgeIterator(Vertex u);

    /**
     * Returns an iterator that iterates through the edges incident on
     * a given vertex.  Each incident edge is indicated by the
     * corresponding adjacent vertex.
     *
     * @param u The index of the vertex whose incident edges are
     * returned by the iterator.
     */
    public Iterator edgeIterator(int u);

    /** Returns the number of vertices in this graph. */
    public int getCardV();

    /** Returns the number of edges in this graph. */
    public int getCardE();

    /** Returns <code>true</code> if this graph is directed,
     * <code>false</code> if undirected. */
    public boolean isDirected();
}