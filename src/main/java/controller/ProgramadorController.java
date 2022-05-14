package controller;

import database.DataBaseManager;
import model.Programador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramadorController {
    private static ProgramadorController instance;

    private ProgramadorController() {
    }

    public static ProgramadorController getInstance() {
        if (instance == null) {
            instance = new ProgramadorController();
        }
        return instance;
    }

    public Programador insertProgramador(Programador prog) throws SQLException {
        System.out.println("--Insertando nuevo programador--");
        String query = "INSERT INTO programador VALUES (null, ?, ?, ?, ?)";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        ResultSet result = db.insert(query, prog.getIdDept(), prog.getNombre(), prog.getLenguaje(), prog.getSalario()).orElseThrow(() -> new SQLException("Error al insertar un nuevo programador"));
        if (result.next()) {
            System.out.println("Programador " + prog.getNombre() + " insertado");
            db.close();
            return prog;
        } else
            throw new SQLException("Error al insertar en la base de datos");
    }

    public List<Programador> findAllProgramadores() throws SQLException {
        System.out.println("--Mostrando todos los programadores--");
        String query = "SELECT * FROM programador";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar todos programadores"));
        ArrayList<Programador> list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Programador(
                            result.getLong("id_programador"),
                            result.getLong("id_departamento"),
                            result.getString("nombre"),
                            result.getString("lenguaje"),
                            result.getDouble("salario")
                    )
            );
        }
        db.close();
        return list;
    }

    public Programador getProgramadorById(Integer id) throws SQLException {
        System.out.println("--Buscando programador mediante su ID--");
        String query = "SELECT * FROM programador WHERE id_programador = ?";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar programador con ID " + id));
        if (result.next()) {
            Programador prog = new Programador(
                    result.getLong("id_programador"),
                    result.getLong("id_departamento"),
                    result.getString("nombre"),
                    result.getString("lenguaje"),
                    result.getDouble("salario")
            );
            db.close();
            return prog;
        } else
            throw new SQLException("No existe el programador con ID " + id);
    }

    public Programador updateLenguaje(Programador prog) throws SQLException {
        System.out.println("--Actualizando lenguaje del programador seleccionado--");
        String query = "UPDATE programador SET lenguaje = ? WHERE id_programador = ?";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        int result = db.update(query, prog.getLenguaje(), prog.getIdProg());
        db.close();
        if (result > 0) {
            System.out.println("Programador con ID " + prog.getIdProg() + " actualizado");
            return prog;
        } else
            throw new SQLException("Error al actualizar programador con ID" + prog.getIdDept());
    }

    public Programador deleteProgramador(Programador prog) throws SQLException {
        System.out.println("--Eliminando programador--");
        String query = "DELETE FROM programador WHERE id_programador = ?";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        int result = db.delete(query, prog.getIdProg());
        db.close();
        if (result > 0) {
            System.out.println("Programador con ID " + prog.getIdProg() + " ha sido eliminado");
            return prog;
        } else
            throw new SQLException("Error al eliminar el programador con el ID " + prog.getIdProg());
    }
}
