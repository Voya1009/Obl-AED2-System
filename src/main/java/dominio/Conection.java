package dominio;

public class Conection {
    private int lat;
    private boolean exist;

    public Conection() {
        exist = false;
    }

    public Conection(int peso) {
        exist = true;
        this.lat = peso;
    }

    public Conection(Conection c) {
        this.lat = c.getLat();
        this.exist = c.doExist();
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int peso) {
        this.lat = peso;
    }

    public boolean doExist() { return exist; }

    public void setExist(boolean existe) {
        this.exist = existe;
    }

    @Override
    public String toString() {
        return "Arista[" + lat + ", " + exist + ']';
    }
}
