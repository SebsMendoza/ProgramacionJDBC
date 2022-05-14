package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Programador {
    private long idProg;
    private long idDept;
    private String nombre;
    private String lenguaje;
    private double salario;

    public Programador(long idDept, String nombre, String lenguaje, double salario) {
        this.idDept = idDept;
        this.nombre = nombre;
        this.lenguaje = lenguaje;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Programador{" +
                "idProg=" + idProg +
                ", idDept=" + idDept +
                ", nombre='" + nombre + '\'' +
                ", lenguajes=" + lenguaje +
                ", salario=" + salario +
                "}\n";
    }
}
