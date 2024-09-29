package com.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

public class CycleDetectionInGraph {

		private static class Edge{
				int source;
				int destination;

				Edge(int source, int destination){
						this.source = source;
						this.destination = destination;
				}
		}


		public static void main(String[] args) {
				detectCycleInUndirectedGraph();
		}

		public static void detectCycleInUndirectedGraph(){
				/**
				 *
				 * 	1------0
				 * 		   |\
				 * 		   | \
				 * 		   2--3
				 */

				int v = 4;
				List<Edge>[] undirectedGraph = new ArrayList[v];

				 createUndirectedGraph(undirectedGraph);

				boolean isCycle = detectCycleInUndirectedGraph(undirectedGraph, 0, new boolean[v], -1);

				System.out.println("Cycle detected in undirected graph : "+ isCycle);
		}

		private static boolean detectCycleInUndirectedGraph(List<Edge>[] undirectedGraph, int currentNode, boolean[] visited, int parent){
				visited[currentNode] = true;

				boolean isCycle = false;
				for( int i=0;i<undirectedGraph[currentNode].size();i++){

						Edge nextNode = undirectedGraph[currentNode].get(i);

						if(visited[nextNode.destination] && parent !=nextNode.destination){
								return true;
						}

						if(!visited[nextNode.destination]){
								isCycle = detectCycleInUndirectedGraph(undirectedGraph, nextNode.destination, visited, currentNode);

								if(isCycle){
										break;
								}
						}


				}

				return isCycle;

		}

		private static void createUndirectedGraph(List<Edge>[] undirectedGraph){

				for (int i=0;i< undirectedGraph.length;i++){
						undirectedGraph[i] = new ArrayList<>();
				}

				undirectedGraph[0].add(new Edge(0,1));
//				undirectedGraph[0].add(new Edge(0,2));
				undirectedGraph[0].add(new Edge(0,3));
				undirectedGraph[1].add(new Edge(1,0));
//				undirectedGraph[2].add(new Edge(2,0));
				undirectedGraph[2].add(new Edge(2,3));
				undirectedGraph[3].add(new Edge(3,0));
				undirectedGraph[3].add(new Edge(3,2));
		}
}
