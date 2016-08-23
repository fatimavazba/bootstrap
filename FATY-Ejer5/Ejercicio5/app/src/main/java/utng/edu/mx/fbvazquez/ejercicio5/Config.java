package utng.edu.mx.fbvazquez.ejercicio5;

/**
 * Created by FatyPC on 17/08/2016.
 */
public class Config {
    //Address of our scripts of the CRUD
    //192.168.0.13 ip de mi maquina para acceder a xamp desde el dispositivo movil
    public static final String URL_ADD="http://192.168.0.13/Proyectos_PHP/insertArchivo.php";
    public static final String URL_GET_ALL = "http://192.168.0.13/Proyectos_PHP/getAllArchivos.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_LIBRO_NOMBRE = "nombre";
    public static final String KEY_LIBRO_APELLIDOS = "apellidos";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NOMBRE = "nombre";
    public static final String TAG_APELLIDOS = "apellidos";
}
