package com.example.phamn.learningtoeic.Model;

import java.util.ArrayList;

public class Wronglist {

   private String eng;
   private String vn;

   public Wronglist(String eng)
   {
       this.eng=eng;
   }
    public Wronglist(String eng, String vn) {
        this.eng = eng;
        this.vn = vn;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }
}
