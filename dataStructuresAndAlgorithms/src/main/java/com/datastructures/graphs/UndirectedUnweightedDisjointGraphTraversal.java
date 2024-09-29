package com.datastructures.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * In this example we are going to use BFS to traverse graph.
 *
 * 		1---------3
 * 	   /		  |
 * 	  0           |  5----6
 * 	  \           |
 * 	   2----------4
 */

public class UndirectedUnweightedDisjointGraphTraversal {
		private static class Edge{
				int source;
				int destination;

				Edge(int source, int destination){
						this.source = source;
						this.destination = destination;
				}
		}

		public static void main(String[] args) {
				System.out.println("UndirectedUnweightedDisjointGraphTraversal started");

				int v = 7;
				List<Edge>[] graph = new ArrayList[v];

				createGraph(graph);

				boolean[] visitedNodes = new boolean[v];

				System.out.print("BFS traversal : ");
				for(int i=0;i<v;i++){
						if(!visitedNodes[i]){
								bfs(graph, visitedNodes, i);
						}
				}

				System.out.println();
				dfs(graph);

				System.out.println();
				System.out.println("UndirectedUnweightedDisjointGraphTraversal ended");

		}

		public static void createGraph(List<Edge>[] graph){
				for (int i=0;i<graph.length;i++){
						graph[i] = new ArrayList<>();
				}

				graph[0].add(new Edge(0,1));
				graph[0].add(new Edge(0,2));
				graph[1].add(new Edge(1,0));
				graph[1].add(new Edge(1,3));
				graph[2].add(new Edge(2,0));
				graph[2].add(new Edge(2,4));
				graph[3].add(new Edge(3,1));
				graph[3].add(new Edge(3,4));
				graph[4].add(new Edge(4,2));
				graph[4].add(new Edge(4,3));
				graph[5].add(new Edge(5,6));
				graph[6].add(new Edge(6,5));

		}

		public static void bfs(List<Edge>[] graph, boolean[] visitedNodes, int startPoint){
				Queue<Integer> queue = new LinkedList<>();
				queue.add(startPoint);

				while (!queue.isEmpty()){
						int currentNode = queue.poll();

						if(!visitedNodes[currentNode]){
								System.out.print(currentNode+",");
								visitedNodes[currentNode] = true;

								for(int i=0; i<graph[currentNode].size();i++){
										queue.add(graph[currentNode].get(i).destination);
								}
						}
				}

		}


		public static void dfs(List<Edge>[] graph){
				System.out.print("DFS traversal : ");
				boolean[] visitedNodes = new boolean[graph.length];

				for(int i=0;i< visitedNodes.length;i++){
						if(!visitedNodes[i]){
								dfs(graph, i, visitedNodes);
						}
				}
		}

		private static void dfs(List<Edge>[] graph, int currentNode, boolean[] visitedNodes){
				System.out.print(currentNode+",");

				visitedNodes[currentNode] = true;

				for (int i =0; i<graph[currentNode].size();i++){
						int nextNode = graph[currentNode].get(i).destination;

						if(!visitedNodes[nextNode]){
								dfs(graph, nextNode, visitedNodes);
						}
				}
		}
}
