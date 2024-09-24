package sistema;

import ADTs.BST;
import ADTs.UGraph;
import dominio.Branch;
import dominio.Player;
import dominio.Team;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    UGraph<Branch> branches;
    BST<Team> teams;
    BST<Player> players;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if (maxSucursales <= 3) { return Retorno.error1("El sistema debe tener más de 3 sucursales."); }
        UGraph<Branch> branches = new UGraph<>(maxSucursales);
        BST<Team> teams = new BST<>();
        BST<Player> players = new BST<>();
        return Retorno.ok("Sistema creado con éxito.");
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        if (alias == null || alias.isEmpty() || nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() || categoria == null) {
            return Retorno.error1("Los parámetros no pueden ser vacíos o nulos.");
        }
        Player newPlayer = new Player(alias, nombre, apellido, categoria);
        if (players.get(newPlayer) != null) return Retorno.error2("Ya existe el jugador.");
        players.add(newPlayer);
        return Retorno.ok("Jugador registrado con éxito.");
    }

    @Override
    public Retorno buscarJugador(String alias) {
        if (alias == null || alias.isEmpty()) return Retorno.error1("El alias no puede ser nulo o vacío.");
        Player newPlayer = new Player(alias);
        if(players.get(newPlayer) == null) return Retorno.error2("No existe un jugador con ese alias.");
        return Retorno.ok(players.countRoute(), players.get(newPlayer).toString());
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.ok(players.listAscString());
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }
}
