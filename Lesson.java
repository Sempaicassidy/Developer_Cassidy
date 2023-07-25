package com.cassidy.agromarket.models;

public class Lesson {
    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_name() {
        return Lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        Lesson_name = lesson_name;
    }

    public byte[] getLesson_video() {
        return lesson_video;
    }

    public void setLesson_video(byte[] lesson_video) {
        this.lesson_video = lesson_video;
    }

    private String lesson_id;
    private String Lesson_name;
    private byte [] lesson_video;

    public Lesson(String lesson_id, String lesson_name, byte[] lesson_video) {
        this.lesson_id = lesson_id;
        Lesson_name = lesson_name;
        this.lesson_video = lesson_video;
    }
}
