public class WordLadder {
    public static void main(String[] args) {
        // get the words
        IndexSET<String> words = new IndexSet<String>();
        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String word = in.readString();
            words.add(word);
        }
        System.out.println("Finished reading words.");
        
        // construct a graph out of these words
        Graph G = new Graph(words.size());
        for (String word1 : words.keys()) {
            for (String word2 : words.keys()) {
                // verify they have the same length
                if (word1.length() != word2.length()) {
                    throw new RunTimeException("Words have different lengths");
                }
                if (word1.compareTo(word2) < 0 && areNeighbors(word1, word2)) {
                    G.addEdge(words.indexOf(word1), words.indexOf(word2));
                }
            }
        }
        
        // graph is constructed. Take source & target words
        String from = in.readString();
        String to = in.readString();
        if (!words.contains(from)) {
            throw new RunTimeException(from + " is not in list of words.");
        }
        if (!words.contains(to)) {
            throw new RunTimeException(to + " is not in list of words.");
        }
        
        // Perform BFS to get shortest route from source to dest
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, words.indexOf(from));
        if (bfs.hasPathTo(words.indexOf(to)) {
            // get the path
            for (int element : bfs.pathTo(words.indexOf(to))) {
                System.out.print(words.keyOf(element) + " ");
            }
            System.out.println();
        } else {
            System.out.println("NOT CONNECTED!");
        }
    }
    
    private static boolean areNeighbors(String p, String q) {
        if (p.length() != q.length()) {
            return false;
        }
        int differences = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != q.charAt(i)) {
                differences++;
            }
            if (differences > 1) {
                return false;
            }
        }
        return true;
    }
    
}