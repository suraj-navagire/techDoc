package com.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Used on directed acyclic graph(DAG). It is a linear order of vertices such that for every directed edge u->v, vertex u comes before v in the order.
 *
 * Topological sorted order shows the dependency sequence.
 *
 * It uses DFS approach.
 *
 * Algorithm :-
 * 1. Create boolean array to store visited vertices.
 * 2. Iterate on all vertices
 * 		3. Iterate on all edges of vertex using DFS if destination vertex is unvisited.
 * 		4. Put current vertex value in stack while leaving out of recursion of DFS.
 * 5. Take out element of from stack and print. This will be Topological order of given graph.
 */
public class TopologicalSorting {

		private static class Edge{
				int source;
				int destination;

				Edge(int source, int destination){
						this.source = source;
						this.destination = destination;
				}
		}

		public static void main(String[] args) {
				System.out.println("TopologicalSorting started");

				/**
				 *     5       4
				 * 	   |\    / |
				 *     | v  v  |
				 *     |  0    |
				 *     v       v
				 *     2       1
				 *      \     ^
				 *       v   /
				 *         3
				 */
				int v = 6;

				List<Edge>[] graph = new List[v];

				createDirectedAcyclicGraph(graph);

				boolean[] visited = new boolean[v];

				Stack<Integer> stack = new Stack<>();

				for (int i=0;i<v;i++){
						if(!visited[i]){
								topologicalSorting(graph, i, visited, stack);
						}
				}

				System.out.print("Topological sorted order : ");
				while (!stack.isEmpty()){
						System.out.print(stack.pop());
				}

		}


		private static void topologicalSorting(List<Edge>[] graph, int currentNode, boolean[] visited, Stack<Integer> stack){
				visited[currentNode] = true;

				for (int i=0;i< graph[currentNode].size();i++){
						Edge nextNode = graph[currentNode].get(i);

						if (!visited[nextNode.destination]){
								topologicalSorting(graph, nextNode.destination, visited, stack);
						}
				}

				stack.add(currentNode);
		}


		private static void createDirectedAcyclicGraph(List<Edge>[] graph){
				for(int i=0; i< graph.length;i++) {
						graph[i] = new ArrayList<>();
				}


				graph[2].add(new Edge(2, 3));
				graph[3].add(new Edge(3, 2));
				graph[4].add(new Edge(4, 0));
				graph[4].add(new Edge(4, 1));
				graph[5].add(new Edge(5, 0));
				graph[5].add(new Edge(5, 2));
		}
}
