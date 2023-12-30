import java.util.*;

public class CalderKatyalGraphTemplate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices in the graph:");
        int numVertices = scanner.nextInt();
        int[][] graph = new int[numVertices][numVertices];

        System.out.println("Enter the adjacency matrix of the graph (line by line):");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Check if a path exists between two vertices");
            System.out.println("2. Check if the graph is connected");
            System.out.println("3. Check if the graph is complete");
            System.out.println("4. Find a path between two vertices");
            System.out.println("5. Find the shortest path between two vertices");
            System.out.println("6. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            int start, end;
            switch (choice) {
                case 1:
                    System.out.print("Enter start and end vertices: ");
                    start = scanner.nextInt();
                    end = scanner.nextInt();
                    System.out.println("Path exists: " + doesPathExist(graph, start, end));
                    break;
                case 2:
                    System.out.println("Graph is connected: " + isConnected(graph));
                    break;
                case 3:
                    System.out.println("Graph is complete: " + isComplete(graph));
                    break;
                case 4:
                    System.out.print("Enter start and end vertices for path: ");
                    start = scanner.nextInt();
                    end = scanner.nextInt();
                    List<Integer> path = getAPath(graph, start, end);
                    if (path != null) {
                        System.out.println("Path: " + path);
                    } else {
                        System.out.println("No path exists.");
                    }
                    break;
                case 5:
                    System.out.print("Enter start and end vertices for shortest path: ");
                    start = scanner.nextInt();
                    end = scanner.nextInt();
                    List<Integer> shortestPath = getShortestPath(graph, start, end);
                    if (shortestPath != null) {
                        System.out.println("Shortest Path: " + shortestPath);
                    } else {
                        System.out.println("No path exists.");
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
    static boolean doesPathExist(int[][] graph, int start, int goal) {
        ArrayList<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == goal) {
                return true;
            }
            visited.add(node);
            for (int i = 0; i < graph.length; i++) {
                if (graph[node][i] != 0 && !visited.contains(i)) {
                    queue.add(i);
                }
            }
        }
        return false;
    }

    static boolean isConnected(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i != j && !doesPathExist(graph, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isComplete(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i != j && graph[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static ArrayList<Integer> getAPath(int[][] graph, int start, int goal) {
        Queue<ArrayList<Integer>> paths = new LinkedList<>();
        ArrayList<Integer> initialPath = new ArrayList<>();
        initialPath.add(start);
        paths.add(initialPath);

        while (!paths.isEmpty()) {
            ArrayList<Integer> path = paths.poll();
            int lastNode = path.get(path.size() - 1);

            if (lastNode == goal) {
                return path;
            }

            for (int i = 0; i < graph.length; i++) {
                if (graph[lastNode][i] != 0 && !path.contains(i)) {
                    ArrayList<Integer> newPath = new ArrayList<>(path);
                    newPath.add(i);
                    paths.add(newPath);
                }
            }
        }
        return null;
    }

    static ArrayList<Integer> getShortestPath(int[][] graph, int start, int goal) {
        return getAPath(graph, start, goal); // In this implementation, getAPath already finds the shortest path
    }
}
    