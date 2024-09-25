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
    BST<Player> allPlayers;
    BST<Player> proPlayers;
    BST<Player> avgPlayers;
    BST<Player> novPlayers;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if (maxSucursales <= 3) { return Retorno.error1("El sistema debe tener más de 3 sucursales."); }
        UGraph<Branch> branches = new UGraph<>(maxSucursales);
        BST<Team> teams = new BST<>();
        BST<Player> allPlayers = new BST<>();
        BST<Player> ProPlayers = new BST<>();
        BST<Player> AvgPlayers = new BST<>();
        BST<Player> NewPlayers = new BST<>();
        return Retorno.ok("Sistema creado con éxito.");
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        if (alias == null || alias.isEmpty() || nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() || categoria == null) {
            return Retorno.error1("Los parámetros no pueden ser vacíos o nulos.");
        }
        Player newPlayer = new Player(alias, nombre, apellido, categoria);
        if (allPlayers.get(newPlayer) != null) return Retorno.error2("Ya existe el jugador.");
        if(newPlayer.getCat() == Categoria.PROFESIONAL) proPlayers.add(newPlayer);
        if(newPlayer.getCat() == Categoria.ESTANDARD) avgPlayers.add(newPlayer);
        if(newPlayer.getCat() == Categoria.PRINCIPIANTE) novPlayers.add(newPlayer);
        allPlayers.add(newPlayer);
        return Retorno.ok("Jugador registrado con éxito.");
    }

    @Override
    public Retorno buscarJugador(String alias) {
        if (alias == null || alias.isEmpty()) return Retorno.error1("El alias no puede ser nulo o vacío.");

        Player newPlayer = new Player(alias);
        if(allPlayers.get(newPlayer) == null) return Retorno.error2("No existe un jugador con ese alias.");

        return Retorno.ok(allPlayers.countRoute(), allPlayers.get(newPlayer).toString());
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.ok(allPlayers.listAscString());
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        if(unaCategoria == Categoria.PROFESIONAL) return Retorno.ok(proPlayers.listAscString());
        if(unaCategoria == Categoria.ESTANDARD) return Retorno.ok(avgPlayers.listAscString());
        if(unaCategoria == Categoria.PRINCIPIANTE) return Retorno.ok(novPlayers.listAscString());
        return null;
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        if(nombre == null || nombre.isEmpty() || manager == null || manager.isEmpty()) return Retorno.error1("Los parámetros no pueden ser vacíos o nulos.");
        Team newTeam = new Team(nombre, manager);
        if(teams.get(newTeam) != null) return Retorno.error2("Ya existe un team con ese nombre.");
        teams.add(newTeam);
        return Retorno.ok("Equipo creado con éxito.");
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
