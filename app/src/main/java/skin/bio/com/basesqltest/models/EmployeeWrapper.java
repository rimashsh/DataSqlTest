package skin.bio.com.basesqltest.models;

import java.io.Serializable;

/**
 * Created by adnenhamdouni on 23/03/2016.
 */

public class EmployeeWrapper implements Serializable {

    private int id;
    private String name;
    private String designation;



    public EmployeeWrapper(int id, String name, String designation) {
        this.id = id;
        this.name = name;
        this.designation = designation;

    }

    public EmployeeWrapper(int id, String name) {
        this.id = id;
        this.name = name;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    @Override
    public String toString() {
        return "Prisoner{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }

}
