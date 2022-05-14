package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Departamento {
    private long idDept;
    private long idJefe;
    private String nombre;
    private double presupuesto;

    public Departamento(long idJefe, String nombre, double presupuesto) {
        this.idJefe = idJefe;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "idDept=" + idDept +
                ", idJefe=" + idJefe +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                "}\n";
    }
}
