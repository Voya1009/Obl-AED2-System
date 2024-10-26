package ADTs;

public class BST<T extends Comparable<T>> {
    private BSTnode<T> root;
    private int count;

    public int length(){
        return length(root);
    }

    private int length(BSTnode<T> node) {
        if (node == null) return 0;
        return 1 + length(node.left) + length(node.right);
    }

    public void add(T data) {
        if (root == null) root = new BSTnode<>(data);
        add(data, root);
    }

    private void add(T data, BSTnode<T> node) {
        if (node.data.compareTo(data) > 0) {
            if (node.left == null)
                node.left = new BSTnode<>(data);
            else
                add(data, node.left);
        } else if (node.data.compareTo(data) < 0) {
            if (node.right == null)
                node.right = new BSTnode<>(data);
            else
                add(data, node.right);
        }
    }

    public T get(T data) {
        count = 0;
        return get(data, root);
    }

    private T get(T data, BSTnode<T> node) {
        count++;
        if (node != null) {
            if (node.data.equals(data))
                return node.data;
            else if (node.data.compareTo(data) > 0) {
                return get(data, node.left);
            } else if (node.data.compareTo(data) < 0) {
                return get(data, node.right);
            }
        }
        return null;
    }

    public int countRoute() {
        return count;
    }

    public int countNodes() {
        return countNodes(root);
    }

    public int countNodes(BSTnode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public String listAscString() {
        return listAscString(root);
    }

    private String listAscString(BSTnode<T> node) {
        if (node == null) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(listAscString(node.left));

        if (!sb.toString().isEmpty()) sb.append("|");

        sb.append(node.data);

        String rightSubtree = listAscString(node.right);

        if (!rightSubtree.isEmpty()) { sb.append("|").append(rightSubtree); }

        return sb.toString();
    }

    public String listDscString() {
        return listAscString(root);
    }

    private String listDscString(BSTnode<T> node) {
        if (node == null) { return ""; }

        StringBuilder sb = new StringBuilder();
        String rightSubtree = listDscString(node.right);
        if (!rightSubtree.isEmpty()) {
            sb.append(rightSubtree).append("|");
        }

        sb.append(node.data);

        String leftSubtree = listDscString(node.left);
        if (!leftSubtree.isEmpty()) {
            sb.append("|").append(leftSubtree);
        }

        return sb.toString();
    }

    private static class BSTnode<T> {
        private T data;
        private BSTnode<T> left;
        private BSTnode<T> right;

        public BSTnode(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "BSTnode[" + data + "]";
        }
    }
}
