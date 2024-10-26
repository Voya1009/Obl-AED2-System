package sistema;

import ADTs.BST;
import ADTs.Graph;
import dominio.Branch;
import dominio.Connection;
import dominio.Player;
import dominio.Team;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    Graph<Branch> branches = new Graph<>(10, false);
    BST<Team> teams = new BST<>();
    BST<Player> allPlayers = new BST<>();
    BST<Player> proPlayers = new BST<>();
    BST<Player> avgPlayers = new BST<>();
    BST<Player> novPlayers = new BST<>();

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if (maxSucursales <= 3) { return Retorno.error1("El sistema debe tener más de 3 sucursales."); }
        Graph<Branch> branches = new Graph<>(maxSucursales, false);
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
        if(teams.get(newTeam) != null) return Retorno.error2("Ya existe un equipo con ese nombre.");
        teams.add(newTeam);
        return Retorno.ok("Equipo creado con éxito.");
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        if(nombreEquipo == null || nombreEquipo.isEmpty() || aliasJugador == null || aliasJugador.isEmpty())
            return Retorno.error1("Los parámetros no pueden ser vacíos o nulos.");
        Team newTeam = new Team(nombreEquipo);
        if(teams.get(newTeam) == null) return Retorno.error2("No existe un equipo con ese nombre.");
        Player newPlayer = new Player(aliasJugador);
        if(allPlayers.get(newPlayer) == null) return Retorno.error3("No existe un jugador con ese alias.");
        if(teams.get(newTeam).getPlayers().length() == 5) return Retorno.error4("El equipo ya está completo.");
        if(allPlayers.get(newPlayer).getCat() != Categoria.PROFESIONAL) return Retorno.error5("El jugador debe ser de categoría profesional.");
        if(allPlayers.get(newPlayer).getTeam() != null) return Retorno.error6("El jugador ya pertenece a un equipo.");
        teams.get(newTeam).getPlayers().add(newPlayer);
        return Retorno.ok("Jugador ingresado con éxito.");
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        if (nombreEquipo == null || nombreEquipo.isEmpty()) return Retorno.error1("El nombre del equipo no puede ser vacío.");
        Team newTeam = new Team(nombreEquipo);
        if(teams.get(newTeam) == null) return Retorno.error2("No existe un equipo con ese nombre.");
        return Retorno.ok(teams.get(newTeam).getPlayers().listAscString());
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.ok(teams.listDscString());
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        if(branches.getBranchesAmount() == branches.getMaxBranches()) return Retorno.error1("No se puede agregar mas sucursales.");
        if(codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) return Retorno.error2("El código y el nombre no pueden ser vacíos o nulos.");
        Branch newBranch = new Branch(codigo, nombre);
        if(branches.existBranch(newBranch)) return Retorno.error3("Ya existe un branch con ese codigo.");
        branches.addBranch(newBranch);
        return Retorno.ok("Sucursal registrada con éxito.");
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if(latencia < 0) return Retorno.error1("La latencia debe ser mayor a 0.");
        if(codigoSucursal1 == null || codigoSucursal1.isEmpty() || codigoSucursal2 == null || codigoSucursal2.isEmpty())
            return Retorno.error2("Los códigos no pueden ser vacíos o nulos.");
        Branch newBranch = new Branch(codigoSucursal1);
        Branch newBranch2 = new Branch(codigoSucursal2);
        if(!branches.existBranch(newBranch) || !branches.existBranch(newBranch2)) return Retorno.error3("Las sucursales deben existir para conectarlas.");
        if(branches.getCon(newBranch, newBranch2).doExist()) return Retorno.error4("Estas sucursales ya estan conectadas.");
        Connection newCon = new Connection(latencia);
        branches.addCon(newBranch, newBranch2, newCon);
        return Retorno.ok("Conexión registrada con éxito.");
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if(latencia < 0) return Retorno.error1("La latencia debe ser mayor a 0.");
        if(codigoSucursal1 == null || codigoSucursal1.isEmpty() || codigoSucursal2 == null || codigoSucursal2.isEmpty())
            return Retorno.error2("Los códigos no pueden ser vacíos o nulos.");
        Branch newBranch = new Branch(codigoSucursal1);
        Branch newBranch2 = new Branch(codigoSucursal2);
        if(!branches.existBranch(newBranch) || !branches.existBranch(newBranch2)) return Retorno.error3("Las sucursales deben existir para actualizar la conexión.");
        if(!branches.getCon(newBranch, newBranch2).doExist()) return Retorno.error4("No existe una conexión entre las sucursales.");
        branches.getCon(newBranch, newBranch2).setLat(latencia);
        return Retorno.ok("Conexión actualizada con éxito.");
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
