package com.datastructures.graphs;

import java.util.ArrayList;

/**
 * Here we are using Adjacency list to create graph structure. i.e. array of List.
 *
 * 	 0	    3
 * 	2 \  -1/ \0
 * 	   \  /	- \
 * 	    2------1
 * 	    	10
 */
public class UndirectedWeightedGraph {

		static class Edge {
				private int source;
				private int destination;

				private int weight;

				Edge(int source, int destination, int weight){
						this.source = source;
						this.destination = destination;
						this.weight = weight;
				}
		}


		public static void createGraph(ArrayList<Edge>[] graph){
				for (int i=0; i< graph.length; i++){
						graph[i] = new ArrayList<>();
				}

				graph[0].add(new Edge(0,2, 2));
				graph[1].add(new Edge(1,2, 10));
				graph[1].add(new Edge(1,3, 0));
				graph[2].add(new Edge(2,1, 10));
				graph[2].add(new Edge(2,3, -1));
				graph[3].add(new Edge(3,1, 0));
				graph[3].add(new Edge(3,2, -1));
		}

		public static void main(String[] args) {
				int v = 4;

				ArrayList<Edge>[] graph = new ArrayList[v];

				createGraph(graph);

				//Print neighbour's for all vertex
				for(int i=0;i< graph.length;i++){
						printNeighbourOfVertex(graph, i);
						System.out.println();
				}
		}

		public static void printNeighbourOfVertex(ArrayList<Edge>[] graph, int vertexIndex){
				System.out.print("Neighbours of "+vertexIndex+" : ");
				for(int i=0;i< graph[vertexIndex].size();i++){
						System.out.print("("+graph[vertexIndex].get(i).destination+",Weight:"+graph[vertexIndex].get(i).weight+")");
				}
		}

}
