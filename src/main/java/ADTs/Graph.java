package ADTs;

import dominio.Branch;
import dominio.Connection;

public class Graph<T> {
    private Branch[] branches;
    private Connection[][] connections;
    private final int maxBranches;
    private final boolean directed;
    private int countBranches;

    public Graph(int maxBranches, boolean isDirected) {
        this.maxBranches = maxBranches;
        directed = isDirected;
        branches = new Branch[maxBranches];
        connections = new Connection[maxBranches][maxBranches];

        if (directed) {
            for (int i = 0; i < connections.length; i++) {
                for (int j = 0; j < connections.length; j++) {
                    connections[i][j] = new Connection();
                }
            }
        } else {
            for (int i = 0; i < connections.length; i++) {
                for (int j = 0; j < connections.length; j++) {
                    Connection a = new Connection();
                    connections[i][j] = a;
                    connections[j][i] = a;
                }
            }
        }
    }

    public int getBranchesAmount(){ return countBranches; }

    public int getMaxBranches(){ return maxBranches; }

    public void addBranch(Branch b) {
        if (countBranches < maxBranches) {
            int freePos = getFreePos();
            branches[freePos] = b;
            countBranches++;
        }
    }

    public void deleteBranch(Branch b) {
        int posToDel = getPos(b);
        for (int i = 0; i < connections.length; i++) {
            connections[posToDel][i].setExist(false); //Fila: Aristas adyacentes
            if (directed) {
                connections[i][posToDel].setExist(false); //Columna: Aristas incidentes
            }
        }
        branches[posToDel] = null;
        countBranches--;
    }

    public void addCon(Branch bInicial, Branch bFinal, Connection connection) {
        int posBInitial = getPos(bInicial);
        int posBFinal = getPos(bFinal);

        Connection aux = connections[posBInitial][posBFinal];
        aux.setLat(connection.getLat());
        aux.setExist(true);
    }


    public void deleteCon(Branch bInitial, Branch bFinal) {
        int posBInitial = getPos(bInitial);
        int posBFinal = getPos(bFinal);

        Connection aux = connections[posBInitial][posBFinal];
        aux.setLat(0);
        aux.setExist(false);
    }

    public Connection getCon(Branch bInitial, Branch bFinal) {
        int posBInitial = getPos(bInitial);
        int posBFinal = getPos(bFinal);

        return connections[posBInitial][posBFinal];
    }

    public boolean existBranch(Branch b) {
        int searchPos = getPos(b);
        return searchPos >= 0;
    }

    public IList<Branch> adj(Branch b) {
        IList<Branch> adj = new List<>();
        int pos = getPos(b);

        for (int i = 0; i < connections.length; i++) {
            if (connections[pos][i].doExist()) {
                adj.add(branches[i]);
            }
        }
        return adj;
    }

    public void dfs(Branch b) {
        boolean[] visited = new boolean[maxBranches];
        int posB = getPos(b);
        dfs(posB, visited, connections);
    }

    private void dfs(int posB, boolean[] visited, Connection[][] connections) {
        System.out.print(branches[posB] + " ");
        visited[posB] = true;
        for (int i = 0; i < connections.length; i++) {
            if (connections[posB][i].doExist() && !visited[i]) {
                dfs(i, visited, connections);
            }
        }
        System.out.println();
    }

    public void bfs(Branch b) {
        int posB = getPos(b);
        boolean[] visited = new boolean[maxBranches];
        visited[posB] = true;
        IQueue<Integer> queue = new Queue<>();
        queue.enqueue(posB);
        while (!queue.isEmpty()) {
            int posUnqueued = queue.unqueue();
            System.out.print(branches[posUnqueued] + " ");
            for (int i = 0; i < connections.length; i++) {
                if (connections[posUnqueued][i].doExist() && !visited[i]) {
                    visited[i] = true;
                    queue.enqueue(i);
                }
            }
        }
    }

    public boolean isCritical(Branch b){
        int posB = getPos(b);
        boolean[] visitedWithB = new boolean[maxBranches];
        dfs(posB, visitedWithB, connections);

        boolean[] visitedWithoutB = new boolean[maxBranches];
        visitedWithoutB[posB] = true;

        int startB = -1;
        for (int i = 0; i < maxBranches; i++) {
            if (!visitedWithB[i] && i != posB) {
                startB = i;
                break;
            }
        }

        if (startB == -1) return false;

        dfs(startB, visitedWithoutB, connections);

        for (int i = 0; i < maxBranches; i++) {
            if (i != posB && visitedWithB[i] != visitedWithoutB[i]) {
                return true;
            }
        }
        return false;
    }

    private int getFreePos() {
        for (int i = 0; i < branches.length; i++) {
            if (branches[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int getPos(Branch b) {
        for (int i = 0; i < connections.length; i++) {
            if (branches[i] != null && branches[i].equals(b)) {
                return i;
            }
        }
        return -1;
    }
}

