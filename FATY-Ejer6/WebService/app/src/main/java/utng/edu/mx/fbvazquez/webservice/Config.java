package utng.edu.mx.fbvazquez.webservice;

/**
 * Created by FatyPC on 17/08/2016.
 */
public class Config {
    //Address of our scripts of the CRUD
    //192.168.0.13 ip de mi maquina para acceder a xamp desde el dispositivo movil
    public static final String URL_ADD="http://192.168.0.13/Proyectos_PHP/addFrase.php";
    public static final String URL_GET_ALL = "http://192.168.0.13/Proyectos_PHP/getAllFrase.php";
    public static final String URL_GET_FRASE = "http://192.168.0.13/Proyectos_PHP/getFrase.php?id=";
    public static final String URL_UPDATE_FRASE = "http://192.168.0.13/Proyectos_PHP/updateFrase.php";
    public static final String URL_DELETE_FRASE = "http://192.168.0.13/Proyectos_PHP/deleteFrase.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_FRASE_ID = "id";
    public static final String KEY_FRASE_AUTOR = "autor";
    public static final String KEY_FRASE_FRASE = "frase";
    public static final String KEY_FRASE_TIPOFRASE = "tipo";
    public static final String KEY_FRASE_RATING = "rating";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_AUTOR = "autor";
    public static final String TAG_FRASE = "frase";
    public static final String TAG_TIPOFRASE = "tipo";
    public static final String TAG_RATING = "rating";

    //frase id to pass with intent
    public static final String FRASE_IDFRASE = "frase_id";
}
