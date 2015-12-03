package com.example.jorge.profileexample;

/**
 * Created by jorge on 1/12/15.
 */
public class Profile {
    private String photo;
    private String name;
    private String message;
    private String gps;

    private long id;

    public Profile(long id, String name, String photo, String message, String gps)  {
        this.photo=photo;
        this.name=name;
        this.message=message;
        this.gps=gps;
        this.id=id;
    }

    public Profile(long id, String name, String photo)  {
        this.photo=photo;
        this.name=name;
        this.id=id;
        this.gps=null;
        this.message=null;
    }

    public long getId() {
        return id;
    }

    public String getGps() {
        return gps;
    }

    public String getMessage() {
        return message;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
