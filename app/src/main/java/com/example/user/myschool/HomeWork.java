package com.example.user.myschool;

public class HomeWork {

    private String name;
    private String time;
    private String date;
    private String subject;
    private String notes;
    private String teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public HomeWork(String name, String time, String date, String subject, String notes, String teacher) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.subject = subject;
        this.notes = notes;
        this.teacher = teacher;
    }

    public HomeWork() {
    }
}
