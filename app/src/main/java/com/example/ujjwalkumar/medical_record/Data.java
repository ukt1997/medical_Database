package com.example.ujjwalkumar.medical_record;

/**
 * Created by Ujjwal Kumar on 12-03-2018.
 */

public class Data {

    private int id;
    private String name;
    private String address;
    private  String mobile;
    private  String  age;
    private byte[] image;

    public Data(int id, String name, String address, String mobile, String age, byte[] image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.age = age;
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
