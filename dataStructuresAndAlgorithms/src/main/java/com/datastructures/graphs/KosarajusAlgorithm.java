package com.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This algorithm is used to find strongly connected component's.
 *
 * It works on directed graph only.
 *
 * Logic :-
 * 1. Populate stack using topological sorting.
 * 2. Take transpose of graph i.e. reverse direction of every edge.
 * 3. Perform DFS on transposed graph using stack values.
 */
public class KosarajusAlgorithm {

		private static class Edge{
				int source;
				int destination;

				Edge(int source, int destination){
						this.source = source;
						this.destination = destination;
				}
		}

		public static void main(String[] args) {
				System.out.println("KosarajusAlgorithm started");

				/**
				 *
				 *	1----->0------>3
				 *  ^     /        |
				 *  |   /          |
				 *  |  v           v
				 * 	 2             4
				 */

				try{
						int v = 5;
						List<Edge>[] graph = new List[v];
						createGraph(graph, v);

						kosarujusAlgo(graph, v);
				} catch (Throwable e){
						e.printStackTrace();
				}


				System.out.println("KosarajusAlgorithm ended");
		}

		private static void kosarujusAlgo(List<Edge>[] graph, int v){

				//Step 1 : Populate stack using topological sorting.
				boolean[] visited = new boolean[v];
				Stack<Integer> stack = new Stack<>();
				for(int i=0;i<v;i++){
						if(!visited[i]){
								topologicalSort(graph, i, visited, stack);
						}
				}


				//Step 2 : Take transpose of graph [Note: While taking transpose clearing visited array]
				List<Edge>[] transposeGraph = new List[v];

				for(int i=0;i<v;i++){
						visited[i] = false;
						transposeGraph[i] = new ArrayList<>();
				}

				for (int i=0;i<v;i++){
						for (int j=0;j<graph[i].size();j++){
								Edge edge = graph[i].get(j);

								transposeGraph[edge.destination].add(new Edge(edge.destination, edge.source));
						}
				}


				//Step 3 : Perform DFS using stack

				System.out.println("Strongly connected component list : ");
				while(!stack.isEmpty()){
						int vertex = stack.pop();

						if(!visited[vertex]){
								dfs(transposeGraph, vertex, visited);
						}

						System.out.println();
				}
		}

		private static void topologicalSort(List<Edge>[] graph, int currentVertex, boolean[] visited, Stack<Integer> stack){
				visited[currentVertex] = true;

				for (int i=0;i<graph[currentVertex].size();i++){
						Edge nextEdge = graph[currentVertex].get(i);

						if(!visited[nextEdge.destination]){
								topologicalSort(graph, nextEdge.destination, visited, stack);
						}
				}

				stack.add(currentVertex);
		}

		private static void dfs(List<Edge>[] graph, int currentVertex, boolean[] visited){
				visited[currentVertex] = true;

				System.out.print(currentVertex+" ");

				for (int i=0;i<graph[currentVertex].size();i++){
						Edge nextEdge = graph[currentVertex].get(i);

						if(!visited[nextEdge.destination]){
								dfs(graph, nextEdge.destination, visited);
						}
				}
		}

		private static void createGraph(List<Edge>[] graph, int v){
				for (int i=0;i<v;i++){
						graph[i] = new ArrayList<>();
				}

				graph[0].add(new Edge(0, 2));
				graph[0].add(new Edge(0, 3));
				graph[1].add(new Edge(1, 0));
				graph[2].add(new Edge(2, 1));
				graph[3].add(new Edge(3, 4));
		}
}
