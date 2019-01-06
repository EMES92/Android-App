package com.example.valerio.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class House {

    private String name;
    private String address;
    private String city;
    
    private boolean sensorTime;
    private boolean sensorMeteo;
    private boolean sensorServo;
    private boolean sensorTemp;
    private boolean sensorNoise;
    private boolean sensorLight;
    private boolean sensorSisma;

    private int icon;

    private static final String HOUSENAME = "housename";
    private static final String HOUSEADDRESS = "houseaddress";
    private static final String HOUSECITY = "housecity";

    private static final String HOUSESENSORTIME = "housesensortime";
    private static final String HOUSESENSORMETEO = "housesensormeteo";
    private static final String HOUSESENSORSERVO = "housesensorservo";
    private static final String HOUSESENSORTEMP = "housesensortemp";
    private static final String HOUSESENSORNOISE = "housesensornoise";
    private static final String HOUSESENSORLIGHT = "housesensorlight";
    private static final String HOUSESENSORSISMA = "housesensorsisma";
    private static final String ICON = "icon";



    public House(String name, String address, String city, boolean sensorTime, boolean sensorMeteo, boolean sensorServo, boolean sensorTemp, boolean sensorNoise, boolean sensorLight, boolean sensorSisma ){
        this.name=name;
        this.address=address;
        this.city=city;
        this.sensorTime=sensorTime;
        this.sensorMeteo=sensorMeteo;
        this.sensorServo=sensorServo;
        this.sensorTemp=sensorTemp;
        this.sensorNoise=sensorNoise;
        this.sensorLight=sensorLight;
        this.sensorSisma=sensorSisma;
    }

    public House(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString(HOUSENAME);
        address = jsonObject.getString(HOUSEADDRESS);
        city = jsonObject.getString(HOUSECITY);
        sensorTime = jsonObject.getBoolean(HOUSESENSORTIME);
        sensorMeteo = jsonObject.getBoolean(HOUSESENSORMETEO);
        sensorServo = jsonObject.getBoolean(HOUSESENSORSERVO);
        sensorTemp = jsonObject.getBoolean(HOUSESENSORTEMP);
        sensorNoise = jsonObject.getBoolean(HOUSESENSORNOISE);
        sensorLight = jsonObject.getBoolean(HOUSESENSORLIGHT);
        sensorSisma = jsonObject.getBoolean(HOUSESENSORSISMA);
        icon = jsonObject.getInt(ICON);

    }

    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }


    public boolean getSensorTime(){
        return sensorTime;
    }
    public boolean getSensorMeteo(){
        return sensorMeteo;
    }
    public boolean getSensorServo(){
        return sensorServo;
    }
    public boolean getSensorTemp(){
        return sensorTemp;
    }
    public boolean getSensorNoise(){
        return sensorNoise;
    }
    public boolean getSensorLight(){
        return sensorLight;
    }
    public boolean getSensorSisma(){
        return sensorSisma;
    }
    public boolean[] getSensors(){
        boolean[] sensors={sensorTime, sensorMeteo, sensorServo, sensorTemp, sensorNoise, sensorLight, sensorSisma};
        return sensors;
    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(HOUSENAME, name);
        jsonObject.put(HOUSEADDRESS, address);
        jsonObject.put(HOUSECITY, city);
        jsonObject.put(HOUSESENSORTIME, sensorTime);
        jsonObject.put(HOUSESENSORMETEO, sensorMeteo);
        jsonObject.put(HOUSESENSORSERVO, sensorServo);
        jsonObject.put(HOUSESENSORTEMP, sensorTemp);
        jsonObject.put(HOUSESENSORNOISE, sensorNoise);
        jsonObject.put(HOUSESENSORLIGHT, sensorLight);
        jsonObject.put(HOUSESENSORSISMA, sensorSisma);
        jsonObject.put(ICON, icon);

        return jsonObject;
    }
}
