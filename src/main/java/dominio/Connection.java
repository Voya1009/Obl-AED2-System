package dominio;

public class Connection {
    private int lat;
    private boolean exist;

    public Connection() {
        exist = false;
    }

    public Connection(int lat) {
        exist = true;
        this.lat = lat;
    }

    public Connection(Connection c) {
        this.lat = c.getLat();
        this.exist = c.doExist();
    }

    public int getLat() { return lat; }

    public void setLat(int lat) { this.lat = lat; }

    public boolean doExist() { return exist; }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "Conexion[" + lat + ", " + exist + ']';
    }
}
