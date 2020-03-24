package com.example.customerlibrary.beans;

import java.util.ArrayList;

public class Messageitem {
    public ArrayList<String> id=new ArrayList<>();
    public ArrayList<String> admin=new ArrayList<>();
    public ArrayList<String> message=new ArrayList<>();
    public ArrayList<String> time=new ArrayList<>();
    public ArrayList<String> head=new ArrayList<>();
    public ArrayList<String> pick=new ArrayList<>();
    public ArrayList<String> file=new ArrayList<>();
    public ArrayList<String> reader=new ArrayList<>();
    public boolean button=true;
    public boolean success=false;
    public void add(String id,String admin,String message,String time,String head,String pick,String reader){
        this.id.add(id);
        this.admin.add(admin);
        this.message.add(message);
        this.time.add(time);
        this.head.add(head);
        this.pick.add(pick);
        this.reader.add(reader);
    }
    public void add2(String id,String admin,String file,String message,String time,String head,String pick){
        this.id.add(id);
        this.admin.add(admin);
        this.message.add(message);
        this.time.add(time);
        this.head.add(head);
        this.pick.add(pick);
        this.file.add(file);
    }
    public void insert(String id,String admin,String file,String message,String time,String head,String pick){
        this.id.add(0,id);
        this.admin.add(0,admin);
        this.message.add(0,message);
        this.time.add(0,time);
        this.head.add(0,head);
        this.pick.add(0,pick);
        this.file.add(0,file);
    }
}
