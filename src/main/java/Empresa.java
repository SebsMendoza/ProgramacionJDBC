import database.DataBaseManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Empresa {
    private static Empresa instance;

    private Empresa() {
    }

    public static Empresa getInstance() {
        if (instance == null) {
            instance = new Empresa();
        }
        return instance;
    }

    /**
     * Método que comprueba la conexión del programa con la BBDD
     */
    public void checkService() {
        DataBaseManager controller = DataBaseManager.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello World'");
            if (rs.isPresent()) {
                rs.get().next();
                controller.close();
                System.out.println("Conexión correcta");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Método para iniciar un script con información
     *
     * @param script parámetro de tipo String para señalar el script que queremos ejecutar
     */
    public void initData(String script) {
        System.out.println("Iniciamos los datos");
        DataBaseManager controller = DataBaseManager.getInstance();
        String sqlFile = System.getProperty("user.dir") + File.separator + "sql" + File.separator + script;
        System.out.println(sqlFile);
        try {
            controller.open();
            controller.initData(sqlFile);
            controller.close();
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el fichero de datos iniciales: " + e.getMessage());
            System.exit(1);
        }
    }

}
