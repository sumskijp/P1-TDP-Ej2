package Proyecto1Logging;

import java.util.logging.*;
public class Graph {
	
	protected int[] nodos; // 1 si hay nodo i, 0 caso contrario
	protected int[][] arcos; // 1 si hay arco de i a j, 0 contrario
	protected static Logger logger;
	
	/**
	 * Crea un grafo vacio
	 */
	public Graph() {
		nodos = new int[10];
		arcos = new int[10][10];
		
		if (logger == null) {
			
			logger = Logger.getLogger(Graph.class.getName());
			
			Handler hnd = new ConsoleHandler();
			hnd.setLevel(Level.FINE);
			logger.addHandler(hnd);
			
			logger.setLevel(Level.FINE);
			
			Logger rootlogger = logger.getParent();
			for (Handler h : rootlogger.getHandlers()) {
				h.setLevel(Level.OFF);
			}
			
		}
	}
	
	/**
	 * Agrega el nodo “node” al grafo, si aún no pertenecía a la
	 * estructura.
	 * @param node
	 */
	public void addNode(int node) {
		if (!existsNode(node)) {	// Si el nodo no esta
			if (nodos.length <= node) { // Si no entra en el arreglo, lo resizea
				resize(node); 
				nodos[node] = 1; // Lo agrego
			}
			else {
				nodos[node] = 1; // Lo agrego
			}
			logger.info("El nodo "+node+" fue creado con éxito");
		}
		else logger.warning("El nodo "+node+" ya existía");
	}
	
	/**
	 * agrega un arco entre el nodo “node1” y el nodo
     * “node2”, si aún no existía el arco y ambos parámetros son nodos pertenecientes a la
     * estructura.
	 * @param node1
	 * @param node2
	 */
	public void addEdge(int node1, int node2) {
		if (existsNode(node1) && existsNode(node2)) // Si los nodos existen
			if (!existsEdge(node1,node2)) { // Si no existe el arco
				arcos[node1][node2] = 1; // lo agrego
				logger.info("El arco entre "+node1+" y "+node2+" fue creado con éxito");
			}
			else logger.warning("El arco entre "+node1+" y "+node2+" ya existía");
		else {
			if (existsNode(node1) && !existsNode(node2))
				logger.warning("El arco no se puede crear, no existe "+node2);
			else if (!existsNode(node1) && existsNode(node2))
					logger.warning("El arco no se puede crear, no existe "+node1);
				 else logger.warning("El arco no se puede crear, no existe "+node1+" ni "+node2);
		}
	}
	
	/**
	 * remueve el nodo “node” del grafo, si el parámetro es un
     * nodo de la estructura.
	 * @param node
	 */
	public void removeNode(int node) {
		if (existsNode(node)) { // si existe el nodo
			nodos[node]=0; // lo elimino
			logger.info("El nodo "+node+" fue eliminado con exito");
		}
		else logger.warning("No se puede eliminar el nodo "+node+", no existe.");
	}
	
	/**
	 * Remueve el arco entre el nodo “node1” y el
	 * nodo “node2”, si el arco formado por los parámetros pertenecen a la estructura.
	 * @param node1
	 * @param node2
	 */
	public void removeEdge(int node1, int node2) {
		if (existsNode(node1) && existsNode(node2)) // si existen los nodos
			if (existsEdge(node1,node2)) { // si existe el arco
				arcos[node1][node2] = 0; // lo elimino
				logger.info("El arco entre "+node1+" y "+node2+" fue eliminado con éxito");
			}
			else logger.warning("No se pudo eliminar, el arco entre "+node1+" y "+node2+" no existía");
		else {
			if (existsNode(node1) && !existsNode(node2))
				logger.warning("No existe arco para eliminar entre "+node1+" y "+node2+ ", porque no existe "+node2);
			else if (!existsNode(node1) && existsNode(node2))
					logger.warning("No existe arco para eliminar entre "+node1+" y "+node2+" porque no existe "+node1);
				 else logger.warning("No existe arco para eliminar entre "+node1+" y "+node2+" porque no existe "+node1+" ni "+node2);
			}
	}

	// Metodo para verificar existencia de arco
	private boolean existsEdge(int node1, int node2) {
		if (arcos[node1][node2] == 1)
			return true;
		else return false;
	}
	
	// Metodo para verificarl existencia de nodo
	private boolean existsNode(int node) {
		
		if (nodos.length <= node) 
			return false;
		else if (nodos[node]==1)
				return true;
			 else return false;
		
	}
	
	// Metodo que agranda la lista de nodos y la matriz de arcos.
	private void resize(int tamaño) {
		
		int[] aux = new int[tamaño*2];
		int[][] aux2 = new int[tamaño*2][tamaño*2];
		
		for (int i = 0; i < nodos.length; i++) {
			aux[i] = nodos[i];
		}
		nodos = aux;
		
		for (int i = 0; i < arcos.length; i++) {
			for (int j=0 ; j < arcos[0].length; j++) {
				aux2[i][j] = arcos[i][j];
			}
		}
		arcos = aux2;
	}
	
}
