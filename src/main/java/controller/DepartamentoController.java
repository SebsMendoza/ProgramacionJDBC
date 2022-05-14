package controller;

import database.DataBaseManager;
import model.Departamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoController {
    private static DepartamentoController instance;

    private DepartamentoController() {
    }

    public static DepartamentoController getInstance() {
        if (instance == null) {
            instance = new DepartamentoController();
        }
        return instance;
    }

    public Departamento insertDepartamento(Departamento dept) throws SQLException {
        System.out.println("--Insertando nuevo departamento--");
        String query = "INSERT INTO departamento VALUES (null, ?, ?, ?)";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        ResultSet result = db.insert(query, dept.getIdJefe(), dept.getNombre(), dept.getPresupuesto()).orElseThrow(() -> new SQLException("Error al insertar un nuevo departamento"));
        if (result.next()) {
            System.out.println("Departamento " + dept.getNombre() + " insertado");
            db.close();
            return dept;
        } else
            throw new SQLException("Error al insertar en la base de datos");
    }

    public List<Departamento> findAllDepartamentos() throws SQLException {
        System.out.println("--Mostrando todos los departamentos--");
        String query = "SELECT * FROM departamento";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar todos departamentos"));
        ArrayList<Departamento> list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Departamento(
                            result.getLong("id_departamento"),
                            result.getLong("id_jefe"),
                            result.getString("nombre"),
                            result.getDouble("presupuesto")
                    )
            );
        }
        db.close();
        return list;
    }

    public Departamento getDepartamentoById(Integer id) throws SQLException {
        System.out.println("--Buscando departamento mediante su ID--");
        String query = "SELECT * FROM departamento WHERE id_departamento = ?";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con ID " + id));
        if (result.next()) {
            Departamento dept = new Departamento(
                    result.getLong("id_departamento"),
                    result.getLong("id_jefe"),
                    result.getString("nombre"),
                    result.getDouble("presupuesto")
            );
            db.close();
            return dept;
        } else
            throw new SQLException("No existe el departamento con ID " + id);
    }

    public Departamento updateJefe(Departamento dept) throws SQLException {
        System.out.println("--Actualizando id del jefe del departamento seleccionado--");
        String query = "UPDATE departamento SET id_jefe = ? WHERE id_departamento = ?";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        int result = db.update(query, dept.getIdJefe(), dept.getIdDept());
        db.close();
        if (result > 0) {
            System.out.println("Departamento con ID " + dept.getIdDept() + " actualizado");
            return dept;
        } else
            throw new SQLException("Error al actualizar departamento con ID" + dept.getIdDept());
    }

    public Departamento deleteDepartamento(Departamento dept) throws SQLException {
        System.out.println("--Eliminando departamento--");
        String query = "DELETE FROM departamento WHERE id_departamento = ?";
        DataBaseManager db = DataBaseManager.getInstance();
        db.open();
        int result = db.delete(query, dept.getIdDept());
        db.close();
        if (result > 0) {
            System.out.println("Departamento con ID " + dept.getIdDept() + " ha sido eliminado");
            return dept;
        } else
            throw new SQLException("Error al eliminar el departamento con el ID " + dept.getIdDept());
    }
}
