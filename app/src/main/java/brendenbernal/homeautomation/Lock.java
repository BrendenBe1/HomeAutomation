package brendenbernal.homeautomation;

/**
 * Created by peter on 11/12/16.
 */

public class Lock {
    private int id;
    private String name;
    private int status;;

    public Lock(){

    }
    public Lock(int id, String name, int status){
        this.id = id;
        this.name = name;
        this.status = status;

    }
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

}
