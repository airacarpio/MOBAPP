package ph.edu.apc.mytenant.model;

/**
 * Created by Gail on 12/3/2016.
 */

public class Tenant {

    private String select_date;
    private String electricity_meter;
    private String name;
    private String rental_fee;
    private String room_number;
    private String water_meter;


    public Tenant(){

    }

    public String getSelect_date() {
        return select_date;
    }

    public void setSelect_date(String select_date) {
        this.select_date = select_date;
    }

    public String getWater_meter() {
        return water_meter;
    }

    public void setWater_meter(String water_meter) {
        this.water_meter = water_meter;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getRental_fee() {
        return rental_fee;
    }

    public void setRental_fee(String rental_fee) {
        this.rental_fee = rental_fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElectricity_meter() {
        return electricity_meter;
    }

    public void setElectricity_meter(String electricity_meter) {
        this.electricity_meter = electricity_meter;
    }

}
