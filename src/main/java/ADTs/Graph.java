package ADTs;

import dominio.Branch;
import dominio.Conection;

public class Graph<T> {
    private Branch[] branches;
    private Conection[][] conections;
    private final int maxBranches;
    private final boolean directed;
    private int countApex;

    public Graph(int maxBranches, boolean isDirected) {
        this.maxBranches = maxBranches;
        directed = isDirected;
        branches = new Branch[maxBranches];
        conections = new Conection[maxBranches][maxBranches];

        if (directed) {
            for (int i = 0; i < conections.length; i++) {
                for (int j = 0; j < conections.length; j++) {
                    conections[i][j] = new Conection();
                }
            }
        } else {
            for (int i = 0; i < conections.length; i++) {
                for (int j = 0; j < conections.length; j++) {
                    Conection a = new Conection();
                    conections[i][j] = a;
                    conections[j][i] = a;
                }
            }
        }
    }

    public void addBranch(Branch b) {
        if (countApex < maxBranches) {
            int freePos = getFreePos();
            branches[freePos] = b;
            countApex++;
        }
    }

    public void deleteBranch(Branch vert) {
        int posABorrar = getPos(vert);
        for (int i = 0; i < conections.length; i++) {
            conections[posABorrar][i].setExist(false); //Fila: Aristas adyacentes
            if (directed) {
                conections[i][posABorrar].setExist(false); //Columna: Aristas incidentes
            }
        }
        branches[posABorrar] = null;
        countApex--;
    }

    public void addCon(Branch vInicial, Branch vFinal, Conection arista) {
        int posVInicial = getPos(vInicial);
        int posVFinal = getPos(vFinal);

        Conection aux = conections[posVInicial][posVFinal];
        aux.setLat(arista.getLat());
        aux.setExist(true);
    }


    public void deleteCon(Branch bInicial, Branch bFinal) {
        int posBInicial = getPos(bInicial);
        int posBFinal = getPos(bFinal);

        Conection aux = conections[posBInicial][posBFinal];
        aux.setLat(0);
        aux.setExist(false);
    }

    public Conection getCon(Branch vInitial, Branch vFinal) {
        int posVInitial = getPos(vInitial);
        int posVFinal = getPos(vFinal);

        return conections[posVInitial][posVFinal];
    }

    public boolean existBranch(Branch b) {
        int posABuscar = getPos(b);
        return posABuscar >= 0;
    }

    public IList<Branch> adj(Branch b) {
        IList<Branch> adj = new List<>();
        int pos = getPos(b);

        for (int i = 0; i < conections.length; i++) {
            if (conections[pos][i].doExist()) {
                adj.add(branches[i]);
            }
        }
        return adj;
    }


    public void dfs(Branch b) {
        boolean[] visited = new boolean[maxBranches];
        int posB = getPos(b);
        dfs(posB, visited, conections);
    }

    private void dfs(int posB, boolean[] visited, Conection[][] conections) {
        System.out.print(branches[posB] + " ");
        visited[posB] = true;
        for (int i = 0; i < conections.length; i++) {
            if (conections[posB][i].doExist() && !visited[i]) {
                dfs(i, visited, conections);
            }
        }
        System.out.println();
    }

    public void bfs(Branch b) {
        int posV = getPos(b);
        boolean[] visited = new boolean[maxBranches];
        visited[posV] = true;
        IQueue<Integer> queue = new Queue<>();
        queue.enqueue(posV);
        while (!queue.isEmpty()) {
            int posUnqueued = queue.unqueue();
            System.out.print(branches[posUnqueued] + " ");
            for (int i = 0; i < conections.length; i++) {
                if (conections[posUnqueued][i].doExist() && !visited[i]) {
                    visited[i] = true;
                    queue.enqueue(i);
                }
            }
        }
    }

    public boolean esPuntoCritico(Branch b){
        /*
        - Obtener la posicion del Vertice vert.
        - Ejecutar dfs(el metodo privado), pasando un array de visitados y la posicion de vert, luego
        me quedo con el array de visitados que pasamos por parámetro para comparar luego.

        - Hacer una copia de la matriz de aristas y quitarle todas las aristas asociadas a vert.

        - Tengo que buscar el primer true del array de visitados anterior y me quedo con dicha posicion
        para usar como vertice de inicio para la próxima ejecución de dfs.

        - Ejecutar dfs con la posicion anterior, pero utilizando la copia de la matriz de aristas
        y me quedo con su array de visitados.

        - Comparo el array de visitados de ambas ejecuciones de dfs
        teniendo en cuenta el no comparar la posicion del vertice vert.

        - Si hay diferencias devuelvo true, ya que el vertice es un punto crítico.
         */
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
        for (int i = 0; i < conections.length; i++) {
            if (branches[i] != null && branches[i].equals(b)) {
                return i;
            }
        }
        return -1;
    }
}

