package dominio;

import interfaz.Categoria;

import java.util.Objects;

public class Player implements Comparable<Player> {
    String alias;
    String name;
    String lastName;
    Categoria cat;


    public Player(String alias, String name, String lastName, Categoria cat) {
        this.alias = alias;
        this.name = name;
        this.lastName = lastName;
        this.cat = cat;
    }

    public Player(String alias) {
        this.alias = alias;
    }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Categoria getCat() { return cat; }
    public void setCat(Categoria cat) { this.cat = cat; }

    @Override
    public int compareTo(Player p) { return alias.compareTo(p.alias); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(alias, ((Player) o).alias);
    }

    @Override
    public String toString() { return alias + ";" + name + ";" + lastName + ";" + cat; }
}
