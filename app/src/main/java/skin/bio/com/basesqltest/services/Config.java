package skin.bio.com.basesqltest.services;

/**
 * Created by adnenhamdouni on 10/05/2016.
 */
public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://192.168.1.5/Mdev/addEmp.php";
    public static final String URL_GET_ALL = "http://192.168.1.5/Mdev/getAllEmp.php";
    public static final String URL_GET_EMP = "http://192.168.1.5/Mdev/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.1.5/Mdev/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://192.168.1.5/Mdev/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_DESG = "designation";


    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESG = "designation";


    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";

    public static final int PICK_ADD_UPDATE_EMPLOYEE_REQUEST = 1;
    public static final int PICK_ADD_UPDATE_EMPLOYEE_RESULT = 2;
}
