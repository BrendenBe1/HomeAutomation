package brendenbernal.homeautomation;

/**
 * Created by peter on 11/10/16.
 */

public class Thermostat {
    private int id;
    private String name;
    private int status;
    private String onTime;
    private String offTime;

    public Thermostat(){

    }
    public Thermostat(int id, String name, int status, String onTime, String offTime){
        this.id = id;
        this.name = name;
        this.status = status;
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

    public String getOnTime() {
        return onTime;
    }

    public String getOffTime() {
        return offTime;
    }
}
