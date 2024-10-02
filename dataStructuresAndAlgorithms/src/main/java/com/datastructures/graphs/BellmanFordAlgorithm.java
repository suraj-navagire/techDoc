package com.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 *  Used to find shortest path for all the nodes from source vertex. It used in +ve as well as-ve weights.
 *  It takes more time than Dijkstra's algorithm. So use this only for -ve values weights.
 *
 *  BellmanFord algorithm don't work if negative cycle present in graph. i.e. if cycle is there and sum of their weight is -ve then this algorithm won't work.
 *
 *  It uses BFS approach. Immediate nodes gets visited first.
 *
 *  Algorithm :-
 *  1. Create array with size equal to vertices to store shortest distance of each vertex from source. Store initial values as MAX.
 *     Store 0 for source.
 *  2. Iterate over graph V-1 times, where v is number of vertices.
 *  	3. Iterate over all the edges.
 *  	4. Get edge of current vertex.
 *		5. u = currentEdge.source
 *		   v = currentEdge.destination
 *		   wt = currentEdge.wt
 *		6. If distance[u] = wt < distance[v] then update distance[v] as distance[u] + wt. Note distance[u] should not be Integer.Max means
 *		   this u has to be visited. In case of Dijkstra's algorithm this condition is not needed as u i.e. current node is visited only and
 *		   it is coming from queue.
 *
 *  7. Loop again anf check if value updates. If value of distance updates then negative cycle must be present. Then cannot find shirted path.
 *
 */
public class BellmanFordAlgorithm {

		private static class Edge{
				int source;
				int destination;

				int weight;

				Edge(int source, int destination, int weight){
						this.source = source;
						this.destination = destination;
						this.weight = weight;
				}
		}

		public static void main(String[] args) {
				System.out.println("BellmanFordAlgorithm started");

				/**
				 *          1
				 *        ^/ ^
				 *      / /    \'-1
				 * '2 / /       \
				 *   / |         \
				 * 	0  | '-4  '2  4
				 *   \ |         ^
				 * '4 vv        / '4
				 *     2------>3
				 *         '2
				 *
				 *  note :- ' indicates weight
				 */

				int v = 5;

				List<Edge>[] graph= new List[v];

				createDirectedWeightedGraph(graph);

				bellmanFordAlgo(graph, 0, v);

		}

		private static void bellmanFordAlgo(List<Edge>[] graph, int source, int size){
				int[] distance = new int[size];

				for(int i=0;i<size;i++){
						distance[i] = Integer.MAX_VALUE;
				}

				distance[source] = 0;


				for (int i=0;i<size-1;i++){
						//Iterating on all edges. For that need to iterate on every vertex.
						for(int j=0;j<size;j++){
								for(int k=0;k<graph[j].size();k++){
										Edge edge = graph[j].get(k);
										int u = edge.source;
										int v = edge.destination;
										int wt = edge.weight;

										if(distance[u] != Integer.MAX_VALUE && (distance[u] + wt < distance[v])){
												distance[v] = distance[u] + wt;
										}
								}
						}

				}

				//To check -ve cycle
				for (int i=0;i<size-1;i++){
						for(int j=0;j<size;j++){
								for(int k=0;k<graph[j].size();k++){
										Edge edge = graph[j].get(k);
										int u = edge.source;
										int v = edge.destination;
										int wt = edge.weight;

										if(distance[u] != Integer.MAX_VALUE && (distance[u] + wt < distance[v])){
												System.out.println("Negative cycle is present. Cannot find shorted distance");
												return;
										}
								}
						}

				}

				System.out.println("Shorted distance calculated using Bellman Ford Algorithm :");
				for(int i=0;i<size;i++){
						System.out.println("Vertex : "+i+", Weight : "+distance[i]);
				}
		}

		private static void createDirectedWeightedGraph(List<Edge>[] graph){
				for (int i=0;i<graph.length;i++){
						graph[i] = new ArrayList<>();
				}

				graph[0].add(new Edge(0,1, 2));
				graph[0].add(new Edge(0,2, 4));
				graph[1].add(new Edge(1,2, -4));
				graph[2].add(new Edge(2,3, 2));
				graph[3].add(new Edge(3,4, 4));
				graph[4].add(new Edge(4,1, -10));

		}
}
