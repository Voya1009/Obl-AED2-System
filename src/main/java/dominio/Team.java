package dominio;

import ADTs.BST;

import java.util.Objects;

public class Team implements Comparable<Team> {
    String name;
    String manager;
    BST<Player> players = new BST<>();

    public Team(String name, String manager) {
        this.name = name;
        this.manager = manager;
        BST<Player> players = new BST<>();
    }

    public Team(String name){ this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    public BST<Player> getPlayers() { return players; }
    public void setPlayers(BST<Player> players) { this.players = players; }

    @Override
    public int compareTo(Team t) { return name.compareTo(t.name); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(name, ((Team) o).name);
    }

    @Override
    public String toString() { return name + ";" + manager + ";" + players.countNodes(); }
}
