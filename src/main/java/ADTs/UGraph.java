package ADTs;

public class UGraph<T> {
    private AList<T>[] ads;
    private int v;

    public UGraph(int v) {
        this.v = v;
        ads = new AList[v];
        for (int i = 0; i < v; i++) {
            ads[i] = new AList<>();
        }
    }

    public void conect(int index1, T data1, int index2, T data2) {
        ads[index1].add(data2);
        ads[index2].add(data1);
    }
}
