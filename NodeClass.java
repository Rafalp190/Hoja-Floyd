/**
* @file NodeClass.java
*/

public class NodeClass{

	// Comienzo Atributos 
    public static final int errorEnIndice = -1;   
    private int indice;
    private String nombreReferencia;
	
	// Fin de Atributos 
	
	// Comienzo metodos
	
    /**
     * @param indice New value for this NodeClass's indice.
     */
    public void setindice(int indice){
        this.indice = indice;
    }
	
    /**
     * @param indice
     * @param nombreReferencia
     */
    public NodeClass(String nombreReferencia, int indice){
        this.indice = indice;
        this.nombreReferencia = nombreReferencia;
    }
	
    /**
     * @param nombreReferencia This NodeClass's nombreReferencia.
     */
    public NodeClass(String nombreReferencia)
    {
        indice = errorEnIndice;
        this.nombreReferencia = nombreReferencia;
    }
	
    // return indice 
    public int getindice()
    {
        return indice;
    }
	
    // gets nombre 
    public String getnombreReferencia(){
        return nombreReferencia;
    }
	
    /**
     * @param nombreReferencia New value for this NodeClass's nombreReferencia.
     */
    public void setnombreReferencia(String nombreReferencia){
        this.nombreReferencia = nombreReferencia;
    }
	
	// To string 
    public String toString(){
        return nombreReferencia + " el indice es " + indice + " . "; 
    }
	
	// Find de metodos
}