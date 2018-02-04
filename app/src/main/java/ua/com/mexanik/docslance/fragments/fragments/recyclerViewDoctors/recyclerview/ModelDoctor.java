package ua.com.mexanik.docslance.fragments.fragments.recyclerViewDoctors.recyclerview;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by root on 2/3/18.
 */

public class ModelDoctor {
    private String name;
    private String surname;
    private Bitmap profilePhoto;
    private String age;
    private String specialization;
    private String education;
    private ArrayList<String> skills;
    private double rating;
    private double price;
    private String experience;
    private int gas;

    public String getAvaUrl() {
        return avaUrl;
    }

    public void setAvaUrl(String avaUrl) {
        this.avaUrl = avaUrl;
    }

    private String avaUrl;


    public ModelDoctor(String name, String surname, Bitmap profilePhoto, String age, String specialization, String education,/* ArrayList<String> skills,*/ double rating, double price, String experience, int gas) {
        this.name = name;
        this.surname = surname;
        this.profilePhoto = profilePhoto;
        this.age = age;
        this.specialization = specialization;
        this.education = education;
        this.skills = skills;
        this.rating = rating;
        this.price = price;
        this.experience = experience;
    }
    public ModelDoctor(String name, String surname, String avaUrl, String age, String specialization, String education, /*ArrayList<String> skills,*/ double rating, double price, String experience, int gas) {
        this.name = name;
        this.surname = surname;
        this.avaUrl = avaUrl;
        this.age = age;
        this.specialization = specialization;
        this.education = education;
        this.skills = skills;
        this.rating = rating;
        this.price = price;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Bitmap getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Bitmap profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }
}
