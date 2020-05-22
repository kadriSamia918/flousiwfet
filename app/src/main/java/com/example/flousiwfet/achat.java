 package com.example.flousiwfet;



 import android.widget.Toast;

 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;

 public class achat{
     int id ;
     public static float total ;
     private String nom ;
     private Float prix ;
     String date ;

     public achat(String nom, Float prix) {
         this.nom = nom;
         this.prix = prix;
         String pattern = "MM/dd/yyyy";
         DateFormat df = new SimpleDateFormat(pattern);
         Date today = Calendar.getInstance().getTime();
         date = df.format(today);

     }

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getNom() {
         return nom;
     }

     public Float getPrix() {
         return prix;
     }

     public void setNom(String nom) {
         this.nom = nom;
     }

     public void setPrix(Float prix) {
         this.prix = prix;
     }

     public String getDate() {
         return date;
     }
     public void setDate(String date) {
         this.date = date;
     }

 }
