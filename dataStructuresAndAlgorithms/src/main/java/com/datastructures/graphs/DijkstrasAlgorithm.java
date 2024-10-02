package com.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This algorithm is used to find the shortest path of all the nodes from source node.
 *
 * This is greedy algorithm which means at every step it compares previously stored values.
 *
 * It works on weighted graphs with +ve values. It fails in -ve values of weight.
 *
 * It uses BFS approach. Immediate nodes gets visited first.
 *
 * Algorithm:-
 * 	1. Create array to store distances of vertices;
 * 	2. Initialize distance array with infinite values.
 * 	3. Create array to store visited nodes.
 * 	4. Create priority queue to store nodes with weight. Priority will be decided based on weight. So if same node gets
 * 		added multiple times even then based on weight the shortest pair will get priority, and it will get pop first then when it will come 2nd time
 * 		then it will be visited node so do nothing.
 * 	5. Add source to priority queue with distance value 0.
 * 	6. Iterate while priority queue is not empty.
 * 		7. Pop node from priority queue. This will act as current node.
 * 		8. If current node not visited go ahead else next element.
 * 		8. Mark currentNode visited.
 * 		9. Find all edges of current node.
 * 			10. u = source of edge
 * 				v = destination of edge
 * 			    wt = wt of edge
 * 			11. if (dist[u] + wt < dist[v] then update dist[v] as dist[u] + wt
 * 				12. Add this destination node to priority queue with new updated weight.
 *
 *
 */
public class DijkstrasAlgorithm {

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

		private static class Pair implements Comparable<Pair> {
				int vertex;
				int weight;

				Pair(int vertex, int weight){
						this.vertex = vertex;
						this.weight = weight;
				}

				@Override public int compareTo(Pair o) {
						return this.weight - o.weight;
				}
		}


		public static void main(String[] args) {
				System.out.println("DijkstrasAlgorithm started");

				/**        '7
				 *     1------->3
				 * '2 ^|        ^ \ '1
				 *   / |        | v
				 * 	0  | '1   '2|  5
				 *   \ |        | ^
				 * '4 vv        |/ '5
				 *     2------->4
				 *         '3
				 *
				 *  note :- ' indicates weight
				 */
				int v = 6;

				List<Edge>[] graph = new List[v];

				createDirectedWeightedGraph(graph);

				dijkstras(graph, v);

		}


		private static void dijkstras(List<Edge>[] graph, int size){

				PriorityQueue<Pair> queue = new PriorityQueue<>();

				boolean[] visited = new boolean[size];

				int[] distance = new int[size];

				for (int i=0;i<size; i++){
						distance[i] = Integer.MAX_VALUE;
				}

				distance[0] =0;

				queue.add(new Pair(0, 0));

				while (!queue.isEmpty()){
						Pair currentPair = queue.poll();

						if(!visited[currentPair.vertex]){
								visited[currentPair.vertex] = true;

								int curr = currentPair.vertex;

								for(int i=0;i< graph[curr].size(); i++){
										Edge nextNode = graph[curr].get(i);

										int u = nextNode.source;
										int v = nextNode.destination;
										int wt = nextNode.weight;

										if(distance[u] + wt < distance[v]){
												distance[v] = distance[u] + wt;
												queue.add(new Pair(v, distance[v]));
										}
								}
						}

				}

				System.out.println("Shorted path from 0 are : ");
				for(int i=0;i<size;i++){
						System.out.println("vertex : "+i+", weight : "+distance[i]);
				}

		}


		private static void createDirectedWeightedGraph(List<Edge>[] graph){

				for(int i=0;i< graph.length;i++){
						graph[i] = new ArrayList<>();
				}

				graph[0].add(new Edge(0, 1,2));
				graph[0].add(new Edge(0, 2,4));
				graph[1].add(new Edge(1, 2,1));
				graph[1].add(new Edge(1, 3,7));
				graph[2].add(new Edge(2, 4,3));
				graph[3].add(new Edge(3, 5,1));
				graph[4].add(new Edge(4, 3,2));
				graph[4].add(new Edge(4, 5,5));
		}
}
