/******************************************************************************
 *  Compilation:  javac GraphGenerator.java
 *  Execution:    java GraphGenerator V E
 *  Dependencies: Graph.java
 *
 *  A graph generator.
 *
 *  For many more graph generators, see
 *  http://networkx.github.io/documentation/latest/reference/generators.html
 *
 ******************************************************************************/

package com.codeanalysis.algs4;

import com.codeanalysis.algs4.Graph;
import com.codeanalysis.algs4.MinPQ;
import com.codeanalysis.algs4.SET;
import com.codeanalysis.algs4.StdOut;
import com.codeanalysis.algs4.StdRandom;

/**
 *  The {@code GraphGenerator} class provides static methods for creating
 *  various graphs, including Erdos-Renyi random graphs, random bipartite
 *  graphs, random k-regular graphs, and random rooted trees.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class GraphGenerator {
    private static final class Edge implements Comparable<Edge> {
        private int v;
        private int w;

        private Edge(int v, int w) {
            if (v < w) {
                this.v = v;
                this.w = w;
            }
            else {
                this.v = w;
                this.w = v;
            }
        }

        public int compareTo(Edge that) {
            if (this.v < that.v) return -1;
            if (this.v > that.v) return +1;
            if (this.w < that.w) return -1;
            if (this.w > that.w) return +1;
            return 0;
        }
    }

    // this class cannot be instantiated
    private GraphGenerator() { }

    /**
     * Returns a random simple graph containing {@code V} vertices and {@code E} edges.
     * @param V the number of vertices
     * @param E the number of vertices
     * @return a random simple graph on {@code V} vertices, containing a total
     *     of {@code E} edges
     * @throws IllegalArgumentException if no such simple graph exists
     */
    public static com.codeanalysis.algs4.Graph simple(int V, int E) {
        if (E > (long) V*(V-1)/2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)                throw new IllegalArgumentException("Too few edges");
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);
        com.codeanalysis.algs4.SET<Edge> set = new com.codeanalysis.algs4.SET<Edge>();
        while (G.E() < E) {
            int v = com.codeanalysis.algs4.StdRandom.uniform(V);
            int w = com.codeanalysis.algs4.StdRandom.uniform(V);
            Edge e = new Edge(v, w);
            if ((v != w) && !set.contains(e)) {
                set.add(e);
                G.addEdge(v, w);
            }
        }
        return G;
    }

    /**
     * Returns a random simple graph on {@code V} vertices, with an 
     * edge between any two vertices with probability {@code p}. This is sometimes
     * referred to as the Erdos-Renyi random graph model.
     * @param V the number of vertices
     * @param p the probability of choosing an edge
     * @return a random simple graph on {@code V} vertices, with an edge between
     *     any two vertices with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static com.codeanalysis.algs4.Graph simple(int V, double p) {
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);
        for (int v = 0; v < V; v++)
            for (int w = v+1; w < V; w++)
                if (com.codeanalysis.algs4.StdRandom.bernoulli(p))
                    G.addEdge(v, w);
        return G;
    }

    /**
     * Returns the complete graph on {@code V} vertices.
     * @param V the number of vertices
     * @return the complete graph on {@code V} vertices
     */
    public static com.codeanalysis.algs4.Graph complete(int V) {
        return simple(V, 1.0);
    }

    /**
     * Returns a complete bipartite graph on {@code V1} and {@code V2} vertices.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @return a complete bipartite graph on {@code V1} and {@code V2} vertices
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static com.codeanalysis.algs4.Graph completeBipartite(int V1, int V2) {
        return bipartite(V1, V2, V1*V2);
    }

    /**
     * Returns a random simple bipartite graph on {@code V1} and {@code V2} vertices
     * with {@code E} edges.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param E the number of edges
     * @return a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     *    containing a total of {@code E} edges
     * @throws IllegalArgumentException if no such simple bipartite graph exists
     */
    public static com.codeanalysis.algs4.Graph bipartite(int V1, int V2, int E) {
        if (E > (long) V1*V2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)            throw new IllegalArgumentException("Too few edges");
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V1 + V2);

        int[] vertices = new int[V1 + V2];
        for (int i = 0; i < V1 + V2; i++)
            vertices[i] = i;
        com.codeanalysis.algs4.StdRandom.shuffle(vertices);

        com.codeanalysis.algs4.SET<Edge> set = new SET<Edge>();
        while (G.E() < E) {
            int i = com.codeanalysis.algs4.StdRandom.uniform(V1);
            int j = V1 + com.codeanalysis.algs4.StdRandom.uniform(V2);
            Edge e = new Edge(vertices[i], vertices[j]);
            if (!set.contains(e)) {
                set.add(e);
                G.addEdge(vertices[i], vertices[j]);
            }
        }
        return G;
    }

    /**
     * Returns a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     * containing each possible edge with probability {@code p}.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param p the probability that the graph contains an edge with one endpoint in either side
     * @return a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     *    containing each possible edge with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static com.codeanalysis.algs4.Graph bipartite(int V1, int V2, double p) {
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        int[] vertices = new int[V1 + V2];
        for (int i = 0; i < V1 + V2; i++)
            vertices[i] = i;
        com.codeanalysis.algs4.StdRandom.shuffle(vertices);
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V1 + V2);
        for (int i = 0; i < V1; i++)
            for (int j = 0; j < V2; j++)
                if (com.codeanalysis.algs4.StdRandom.bernoulli(p))
                    G.addEdge(vertices[i], vertices[V1+j]);
        return G;
    }

    /**
     * Returns a path graph on {@code V} vertices.
     * @param V the number of vertices in the path
     * @return a path graph on {@code V} vertices
     */
    public static com.codeanalysis.algs4.Graph path(int V) {
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        com.codeanalysis.algs4.StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        return G;
    }

    /**
     * Returns a complete binary tree graph on {@code V} vertices.
     * @param V the number of vertices in the binary tree
     * @return a complete binary tree graph on {@code V} vertices
     */
    public static com.codeanalysis.algs4.Graph binaryTree(int V) {
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        com.codeanalysis.algs4.StdRandom.shuffle(vertices);
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[i], vertices[(i-1)/2]);
        }
        return G;
    }

    /**
     * Returns a cycle graph on {@code V} vertices.
     * @param V the number of vertices in the cycle
     * @return a cycle graph on {@code V} vertices
     */
    public static com.codeanalysis.algs4.Graph cycle(int V) {
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        com.codeanalysis.algs4.StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[V-1], vertices[0]);
        return G;
    }

    /**
     * Returns an Eulerian cycle graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the cycle
     * @param  E the number of edges in the cycle
     * @return a graph that is an Eulerian cycle on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E <= 0}
     */
    public static com.codeanalysis.algs4.Graph eulerianCycle(int V, int E) {
        if (E <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one edge");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one vertex");
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);
        int[] vertices = new int[E];
        for (int i = 0; i < E; i++)
            vertices[i] = com.codeanalysis.algs4.StdRandom.uniform(V);
        for (int i = 0; i < E-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[E-1], vertices[0]);
        return G;
    }

    /**
     * Returns an Eulerian path graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the path
     * @param  E the number of edges in the path
     * @return a graph that is an Eulerian path on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E < 0}
     */
    public static com.codeanalysis.algs4.Graph eulerianPath(int V, int E) {
        if (E < 0)
            throw new IllegalArgumentException("negative number of edges");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian path must have at least one vertex");
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);
        int[] vertices = new int[E+1];
        for (int i = 0; i < E+1; i++)
            vertices[i] = com.codeanalysis.algs4.StdRandom.uniform(V);
        for (int i = 0; i < E; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        return G;
    }

    /**
     * Returns a wheel graph on {@code V} vertices.
     * @param V the number of vertices in the wheel
     * @return a wheel graph on {@code V} vertices: a single vertex connected to
     *     every vertex in a cycle on {@code V-1} vertices
     */
    public static com.codeanalysis.algs4.Graph wheel(int V) {
        if (V <= 1) throw new IllegalArgumentException("Number of vertices must be at least 2");
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        com.codeanalysis.algs4.StdRandom.shuffle(vertices);

        // simple cycle on V-1 vertices
        for (int i = 1; i < V-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[V-1], vertices[1]);

        // connect vertices[0] to every vertex on cycle
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[0], vertices[i]);
        }

        return G;
    }

    /**
     * Returns a star graph on {@code V} vertices.
     * @param V the number of vertices in the star
     * @return a star graph on {@code V} vertices: a single vertex connected to
     *     every other vertex
     */
    public static com.codeanalysis.algs4.Graph star(int V) {
        if (V <= 0) throw new IllegalArgumentException("Number of vertices must be at least 1");
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        com.codeanalysis.algs4.StdRandom.shuffle(vertices);

        // connect vertices[0] to every other vertex
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[0], vertices[i]);
        }

        return G;
    }

    /**
     * Returns a uniformly random {@code k}-regular graph on {@code V} vertices
     * (not necessarily simple). The graph is simple with probability only about e^(-k^2/4),
     * which is tiny when k = 14.
     *
     * @param V the number of vertices in the graph
     * @param k degree of each vertex
     * @return a uniformly random {@code k}-regular graph on {@code V} vertices.
     */
    public static com.codeanalysis.algs4.Graph regular(int V, int k) {
        if (V*k % 2 != 0) throw new IllegalArgumentException("Number of vertices * k must be even");
        com.codeanalysis.algs4.Graph G = new com.codeanalysis.algs4.Graph(V);

        // create k copies of each vertex
        int[] vertices = new int[V*k];
        for (int v = 0; v < V; v++) {
            for (int j = 0; j < k; j++) {
                vertices[v + V*j] = v;
            }
        }

        // pick a random perfect matching
        com.codeanalysis.algs4.StdRandom.shuffle(vertices);
        for (int i = 0; i < V*k/2; i++) {
            G.addEdge(vertices[2*i], vertices[2*i + 1]);
        }
        return G;
    }

    // http://www.proofwiki.org/wiki/Labeled_Tree_from_Prüfer_Sequence
    // http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.36.6484&rep=rep1&type=pdf
    /**
     * Returns a uniformly random tree on {@code V} vertices.
     * This algorithm uses a Prufer sequence and takes time proportional to <em>V log V</em>.
     * @param V the number of vertices in the tree
     * @return a uniformly random tree on {@code V} vertices
     */
    public static com.codeanalysis.algs4.Graph tree(int V) {
        com.codeanalysis.algs4.Graph G = new Graph(V);

        // special case
        if (V == 1) return G;

        // Cayley's theorem: there are V^(V-2) labeled trees on V vertices
        // Prufer sequence: sequence of V-2 values between 0 and V-1
        // Prufer's proof of Cayley's theorem: Prufer sequences are in 1-1
        // with labeled trees on V vertices
        int[] prufer = new int[V-2];
        for (int i = 0; i < V-2; i++)
            prufer[i] = StdRandom.uniform(V);

        // degree of vertex v = 1 + number of times it appers in Prufer sequence
        int[] degree = new int[V];
        for (int v = 0; v < V; v++)
            degree[v] = 1;
        for (int i = 0; i < V-2; i++)
            degree[prufer[i]]++;

        // pq contains all vertices of degree 1
        com.codeanalysis.algs4.MinPQ<Integer> pq = new MinPQ<Integer>();
        for (int v = 0; v < V; v++)
            if (degree[v] == 1) pq.insert(v);

        // repeatedly delMin() degree 1 vertex that has the minimum index
        for (int i = 0; i < V-2; i++) {
            int v = pq.delMin();
            G.addEdge(v, prufer[i]);
            degree[v]--;
            degree[prufer[i]]--;
            if (degree[prufer[i]] == 1) pq.insert(prufer[i]);
        }
        G.addEdge(pq.delMin(), pq.delMin());
        return G;
    }

    /**
     * Unit tests the {@code GraphGenerator} library.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int V1 = V/2;
        int V2 = V - V1;

        com.codeanalysis.algs4.StdOut.println("complete graph");
        com.codeanalysis.algs4.StdOut.println(complete(V));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("simple");
        com.codeanalysis.algs4.StdOut.println(simple(V, E));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("Erdos-Renyi");
        double p = (double) E / (V*(V-1)/2.0);
        com.codeanalysis.algs4.StdOut.println(simple(V, p));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("complete bipartite");
        com.codeanalysis.algs4.StdOut.println(completeBipartite(V1, V2));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("bipartite");
        com.codeanalysis.algs4.StdOut.println(bipartite(V1, V2, E));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("Erdos Renyi bipartite");
        double q = (double) E / (V1*V2);
        com.codeanalysis.algs4.StdOut.println(bipartite(V1, V2, q));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("path");
        com.codeanalysis.algs4.StdOut.println(path(V));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("cycle");
        com.codeanalysis.algs4.StdOut.println(cycle(V));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("binary tree");
        com.codeanalysis.algs4.StdOut.println(binaryTree(V));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("tree");
        com.codeanalysis.algs4.StdOut.println(tree(V));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("4-regular");
        com.codeanalysis.algs4.StdOut.println(regular(V, 4));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("star");
        com.codeanalysis.algs4.StdOut.println(star(V));
        com.codeanalysis.algs4.StdOut.println();

        com.codeanalysis.algs4.StdOut.println("wheel");
        com.codeanalysis.algs4.StdOut.println(wheel(V));
        StdOut.println();
    }

}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
