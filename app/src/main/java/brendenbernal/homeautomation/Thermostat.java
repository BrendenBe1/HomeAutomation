package brendenbernal.homeautomation;

/**
 * Created by peter on 11/10/16.
 */

public class Thermostat {
    private int id;
    private String name;
    private int status;;
    private String onTime;
    private String offTime;
    private int setTemp;

    public Thermostat(){

    }
    public Thermostat(int id, String name, int status, String onTime, String offTime, int setTemp){
        this.id = id;
        this.name = name;
        this.status = status;
        this.setTemp = setTemp;
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

    public void setSetTemp(int setTemp) {
        this.setTemp = setTemp;
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

    public int getSetTemp() {
        return setTemp;
    }

    public String getOnTime() {
        return onTime;
    }

    public String getOffTime() {
        return offTime;
    }
}
