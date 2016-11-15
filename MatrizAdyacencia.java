import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

/**
* @file NodeClass.java
* basado en http://cs.boisestate.edu/~amit/teaching/342/examples/graphs/MatrizAdyacenciah.java 
* 
*/

public class MatrizAdyacencia implements Graph{
	
	// Comienzo Atributos
	
    protected boolean direccionDeNodo;
    protected int lastAdded;
    protected int e;
    protected verticeMatriz[] vertices;
    protected boolean[][] a;
    protected double[][] mat;
    protected boolean cicloNegativo;
    protected Set<String> recorrido;

	// Fin de Atributos
	
	// Comienzo metodos

    /**
     * @param poscicionMatriz 
     * @param direccionDeNodo 
     */
    public MatrizAdyacencia(int poscicionMatriz, boolean direccionDeNodo)
    {
        this.direccionDeNodo = direccionDeNodo;
        lastAdded = -1;
        vertices = new verticeMatriz[poscicionMatriz];
        a = new boolean[poscicionMatriz][poscicionMatriz];
        mat = new double[poscicionMatriz][poscicionMatriz];
        recorrido = new HashSet<String>();

        for (int i = 0; i < poscicionMatriz; i++)
            for (int j = 0; j < poscicionMatriz; j++)
                a[i][j] = false; // no edge (i,j) yet
        
        for (int i = 0; i < poscicionMatriz; i++) {
        	for (int j = 0; j < poscicionMatriz; j++) {
        		if(i==j) {
        			mat[i][j] = 0.0;
        		}else{
        			mat[i][j] = Double.POSITIVE_INFINITY; // valor infinito
        		}
        	}
        }

        e = 0;
    }
    
    /**
     * @param columnNames
     * @return null
     */
    public JTable showMatrix(Object[] columnNames){

    	Object[][] data = new Object[mat.length][mat.length];
    	for(int i = 0; i < data.length; i++) {
    		for(int j = 0; j < data.length; j++) {
    			data[i][j] = mat[i][j];
    		}
    	}

        return new JTable(data, columnNames);
    }
	
    /**
     * @param index The verticeMatriz's index.
     * @param name The verticeMatriz's name.
     * @return .
     */
    public verticeMatriz addverticeMatriz(int index, String name)
    {
        lastAdded = index;
        vertices[lastAdded] = new verticeMatriz(lastAdded, name);
        return vertices[lastAdded];
    }

    /**
     * @param name The verticeMatriz's name.
     * @return 
     */
    public verticeMatriz addverticeMatriz(String name)
    {
        lastAdded++;        // the index for this verticeMatriz
        vertices[lastAdded] = new verticeMatriz(lastAdded, name);
        return vertices[lastAdded];
    }


    /**
     * @param v 
     * @return 
     */
    public verticeMatriz addverticeMatriz(verticeMatriz v)
    {
        if (v.getIndex() == verticeMatriz.UNKNOWN_INDEX) {
            lastAdded++;
            v.setIndex(lastAdded);
        }
        else
            lastAdded = v.getIndex();

            vertices[lastAdded] = v;
        return v;
    }   
    
    /**
     * @param name
     * @return el vertice.
     */
    public verticeMatriz getverticeMatriz(String name) {
    	for (int i = 0; i < vertices.length; i ++) {
    		if (vertices[i].getName().equalsIgnoreCase(name)){
    			return vertices[i];
    		}
    	}
    	return null;
    }

    /**
     * @param index The index of the verticeMatriz.
     * @return The <code>verticeMatriz</code> with the given index.
     */
    public verticeMatriz getverticeMatriz(int index)
    {
        return vertices[index];
    }

    /**
     * @param u One verticeMatriz.
     * @param v The other verticeMatriz.
     */
    public void addEdge(verticeMatriz u, verticeMatriz v){
        addEdge(u.getIndex(), v.getIndex());
    }

    /**
     * @param u The index of one verticeMatriz.
     * @param v The index of the other verticeMatriz.
     */
    public void addEdge(int u, int v){
        a[u][v] = true;
        if (!direccionDeNodo)
            a[v][u] = true;

        e++;
    }
    
    /**
     * @param u vertice origen
     * @param v vertice destino
     * @param weight el peso
     */
    public void addEdge(verticeMatriz u, verticeMatriz v, int weight) {
    	addEdge(u.getIndex(), v.getIndex(), weight);
    }
    
    /**
     * @param u el indice del vertice origen
     * @param v el indice del vertice destino
     * @param weight el peso
     */
    public void addEdge(int u, int v, int weight) {
    	mat[u][v] = weight;
    	addEdge(u,v);
    }

    public Iterator verticeMatrizIterator(){
        return new verticeMatrizIterator();
    }    

    /** 
     * Inner class for a verticeMatriz iterator.
     */
    public class verticeMatrizIterator implements Iterator{
        protected int lastVisited;

        /**
        * @throws UnsupportedOperationException
        */
        public void remove(){
            throw new UnsupportedOperationException();
        }

        public verticeMatrizIterator(){
            lastVisited = -1;
        }

        public boolean hasNext(){
            return lastVisited < vertices.length-1;
        }

        public Object next(){
            return vertices[++lastVisited];
        }
    } 
	//verticeMatrizIterator

    /**
     * @param u The index of the verticeMatriz whose incident edges are
     * returned by the iterator.
     */
    public Iterator edgeIterator(int u){
        return new EdgeIterator(u);
    }

    /**
     * @param u The verticeMatriz whose incident edges are returned by the
     * iterator.
     */
    public Iterator edgeIterator(verticeMatriz u){
        return new EdgeIterator(u.getIndex());
    }

    /**
     * Inner class 
     */
    public class EdgeIterator implements Iterator{

    /**
     * Floyd-Warshall 
     * @return
     */
    public double [][] floydWarshall() {
    	double [][] distancias;
    	int n = mat.length;
    	distancias = Arrays.copyOf(mat, n);
    	for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distancias[i][j] = Math.min(distancias[i][j], distancias[i][k] + distancias[k][j]);
                }
            }

            if (distancias[k][k] < 0.0) {
                this.cicloNegativo = true;
            }
        }
    	
        return distancias;
    }
    
    /**
     * @return centroDelGrafo
     */
    public int centroDelGrafo() {
    	double [][] dist = floydWarshall();
    	
    	double [] verticeMatrizEccentricities = new double [dist.length];
    	for(int i = 0; i < dist.length; i++) {
    		double maxColValue = dist[0][i];
    		for(int j = 0; j < dist.length; j++) {
    			if(dist[j][i] > maxColValue) {
    				maxColValue = dist[j][i];
    			}
    		}
    		verticeMatrizEccentricities[i] = maxColValue;
    	}
    	double min = verticeMatrizEccentricities[0];
    	int city = 0;
    	for(int i = 0; i < verticeMatrizEccentricities.length; i++) {
    		if(verticeMatrizEccentricities[i] <= min) {
    			min = verticeMatrizEccentricities[i];
    			city = i; 			
    		}
    	}
    	return city;
    }
	
    /**
     * Floyd
     * @param u el vertice origen
     * @param v el vertice destino
     * @return devuelve la distancia minima
     */
    public double minDistance(verticeMatriz u, verticeMatriz v) {
    	int origen = u.getIndex();
    	int destino = v.getIndex();
    	double [][] dist = floydWarshall();
    	return dist [origen][destino];
    }
    
}
