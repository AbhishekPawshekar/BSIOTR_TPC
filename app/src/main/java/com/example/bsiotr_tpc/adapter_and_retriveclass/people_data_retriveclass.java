package com.example.bsiotr_tpc.adapter_and_retriveclass;

public class people_data_retriveclass {
    String first_name;
    String description;
    String last_name;
    String education;
    String hobbies;
    String email;
    String username;
    String mobile;
    String gender;
    String department;
    String classes;
    String dob;
    String technical;
    String profile_pic;
    String message;
    String uid;
    long timestamp;


    public people_data_retriveclass() {

    }

    public people_data_retriveclass(String message, String uid) {
        this.message = message;
        this.uid = uid;
    }

    public people_data_retriveclass(String first_name, String description, String last_name, String education, String hobbies, String email, String username, String mobile, String gender, String department, String classes, String dob, String technical, String profile_pic, String message, String uid, long timestamp) {
        this.first_name = first_name;
        this.description = description;
        this.last_name = last_name;
        this.education = education;
        this.hobbies = hobbies;
        this.email = email;
        this.username = username;
        this.mobile = mobile;
        this.gender = gender;
        this.department = department;
        this.classes = classes;
        this.dob = dob;
        this.technical = technical;
        this.profile_pic = profile_pic;
        this.message = message;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
