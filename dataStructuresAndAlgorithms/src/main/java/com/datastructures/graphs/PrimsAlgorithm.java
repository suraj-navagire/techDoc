package com.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This algorithm is used to find minimum spanning tree (MST).
 *
 * It uses kind of BST approach
 *
 * Logic :-
 * 1. Create 2 sets, 1 for mst set and other for non mst set.
 * 2. Take 1 vertex from non mst set and put it in mst set and initialize sum as 0 (This sum will indicate mst weight).
 * 3. Then find minimum weight edge from mst vertices to non mst vertices.
 * 4. Put minimum weight vertex in mst set and add edge's weight in sum.
 * 5. Perform step 3 to 4 till non mst set becomes empty i.e. when all vertices are visited.
 *
 * Algorithm :-
 * 1. Create boolean array to store visited vertex.
 * 2. Create priority queue to store non mst vertices, and it's edge weights. Less weight high priority.
 * 3. First add 0 (source) to queue with 0 weight. and create mstWeight variable to hold total mst weight of graph. and initialize it with 0.
 * 4. Iterate while queue is not empty
 * 		5. Take out element from queue . [since its priority queue minimum weight vertex will come first]
 * 		6. Check if this popped element (vertex) is visited.
 * 		7. If its visited go to next element of priority queue i.e. step 4.
 * 		8. If its non visited add its weight to mstWeight, find all its edges and add it to priority queue one by one.
 *
 *
 */
public class PrimsAlgorithm {

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

				System.out.println("PrimsAlgorithm started");

				int v = 4;

				List<Edge>[] graph = new List[v];

				createGraph(graph);

				int mstWeight = mstWeight(graph, v);

				System.out.println("MST weight = "+mstWeight);

				System.out.println("PrimsAlgorithm ended");
		}

		private static int mstWeight(List<Edge>[] graph, int v){
				boolean[] visited = new boolean[v];

				PriorityQueue<Pair> queue = new PriorityQueue<>();

				queue.add(new Pair(0, 0));

				int mstWeight = 0;

				while (!queue.isEmpty()){
						Pair current = queue.poll();

						if(!visited[current.vertex]){
								visited[current.vertex] = true;

								mstWeight = mstWeight + current.weight;

								int minWeightEdge = Integer.MAX_VALUE;
								for(int i=0;i<graph[current.vertex].size();i++){
										Edge edge = graph[current.vertex].get(i);

										if(!visited[edge.destination]){
												queue.add(new Pair(edge.destination, edge.weight));
										}
								}
						}
				}

				return mstWeight;
		}

		private static void createGraph(List<Edge>[] graph){
				for (int i=0; i<graph.length;i++){
						graph[i] = new ArrayList<>();
				}

				graph[0].add(new Edge(0, 1, 10));
				graph[0].add(new Edge(0, 2, 15));
				graph[0].add(new Edge(0, 3, 30));

				graph[1].add(new Edge(1, 0, 10));
				graph[1].add(new Edge(1, 3, 40));

				graph[2].add(new Edge(2, 0, 15));
				graph[2].add(new Edge(2, 3, 50));

				graph[3].add(new Edge(3, 0, 30));
				graph[3].add(new Edge(3, 1, 40));
				graph[3].add(new Edge(3, 2, 50));
		}
}
