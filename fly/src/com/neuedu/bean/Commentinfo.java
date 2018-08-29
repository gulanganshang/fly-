package com.neuedu.bean;
import java.util.*;

public class Commentinfo {
  private int id;
  private int userid;
  private Date remarktime;
  private int goodnum;
  private int artorcommid;
  private int remarktype;
  private String content;
  private int isaccept;

  public int getId() {
    return id;
  }
  public void setId(int id){
    this.id = id;
  }
  public int getUserid() {
    return userid;
  }
  public void setUserid(int userid){
    this.userid = userid;
  }
  public Date getRemarktime() {
    return remarktime;
  }
  public void setRemarktime(Date remarktime){
    this.remarktime = remarktime;
  }
  public int getGoodnum() {
    return goodnum;
  }
  public void setGoodnum(int goodnum){
    this.goodnum = goodnum;
  }
  public int getArtorcommid() {
    return artorcommid;
  }
  public void setArtorcommid(int artorcommid){
    this.artorcommid = artorcommid;
  }
  public int getRemarktype() {
    return remarktype;
  }
  public void setRemarktype(int remarktype){
    this.remarktype = remarktype;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content){
    this.content = content;
  }
  public int getIsaccept() {
    return isaccept;
  }
  public void setIsaccept(int isaccept){
    this.isaccept = isaccept;
  }
}
