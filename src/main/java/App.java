import controller.DepartamentoController;
import controller.ProgramadorController;
import model.Departamento;
import model.Programador;

public class App {
    public static void main(String[] args) throws Exception {
        Empresa emp = Empresa.getInstance();
        DepartamentoController dc = DepartamentoController.getInstance();
        ProgramadorController pc = ProgramadorController.getInstance();

        //Comprobación de conexión
        emp.checkService();
        //Iniciamos datos(dropea y crea las tablas)
        emp.initData("datos.sql");

        //CRUD departamento
        dc.insertDepartamento(new Departamento(3, "Diseño", 10000));
        System.out.println(dc.findAllDepartamentos());
        System.out.println(dc.getDepartamentoById(2));
        dc.updateJefe(Departamento.builder().idDept(3).idJefe(4).build());
        dc.deleteDepartamento(Departamento.builder().idDept(3).build());

        //CRUD programador
        pc.insertProgramador(new Programador(3, "Jesús", "JavaScript", 2000));
        System.out.println(pc.findAllProgramadores());
        System.out.println(pc.getProgramadorById(3));
        pc.updateLenguaje(Programador.builder().idProg(5).lenguaje("CSharp").build());
        pc.deleteProgramador(Programador.builder().idProg(5).build());
    }
}
