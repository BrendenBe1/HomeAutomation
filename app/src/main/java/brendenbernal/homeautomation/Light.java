package brendenbernal.homeautomation;

/**
 * Created by peter on 11/12/16.
 */

public class Light {

    private int id;
    private String name;
    private int status;;
    private String onTime;
    private String offTime;
    private int setStatus;

    public Light(){

    }
    public Light(int id, String name, int status, String onTime, String offTime, int setStatus){
        this.id = id;
        this.name = name;
        this.status = status;
        this.setStatus = setStatus;
        this.onTime = onTime;
        this.offTime = offTime;
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

    public void setSetStatus(int setStatus) {
        this.setStatus = setStatus;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
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

    public int getSetStatus() {
        return setStatus;
    }

    public String getOnTime() {
        return onTime;
    }

    public String getOffTime() {
        return offTime;
    }
}
