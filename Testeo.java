package Proyecto1Logging;

public class Testeo {

	public static void main(String []a) {
		Graph prueba = new Graph();
		prueba.addNode(1); // I
		prueba.addNode(2); // I
		prueba.addNode(3); // I
		prueba.addNode(1); // W
		prueba.addNode(15); // I
		
		prueba.addEdge(1,15); // I
		prueba.addEdge(1,15); // W
		prueba.addEdge(15, 1); // I
		
		prueba.removeEdge(2, 3); // W 
		prueba.removeEdge(0, 7); // W
		prueba.removeEdge(1, 7); // W
		prueba.removeEdge(7, 1); // W
		prueba.removeEdge(1, 15); // I
		prueba.removeEdge(15, 1); // I
		
		prueba.removeNode(2); // I
		prueba.removeNode(17); // W
		
	}
}
