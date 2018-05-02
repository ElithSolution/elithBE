/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author ambk3201
 */
public class ConversionType {
    
    public static String getEncodedPassword(String key) {
        byte[] uniqueKey = key.getBytes();
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("no MD5 support in this VM");
        }
        StringBuilder hashString = new StringBuilder();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }
    
    
    
    public static int StringToInt(String entree) {
        int sortie = 0;
        if (entree != null && !entree.equals("")) {
            try{
                sortie = Integer.parseInt(entree);
            }catch(NumberFormatException e){
                sortie = 0 ;
            }
        }
        return sortie;
    }
    
    public static String DateToString(Date entree) {
        String sortie = "";
        String jourAff = "";
        String moisAff = "";

        if (entree != null && !entree.equals("")) {
            // format 2008-01-01 => 01-01-2008
            String dateentree = entree.toString();
            int annee = new Integer(dateentree.substring(0, 4)).intValue();
            int mois = new Integer(dateentree.substring(5, 7)).intValue();
            int jour = new Integer(dateentree.substring(8, 10)).intValue();

            jourAff = jour + "-";
            moisAff = mois + "-";
            if (jour < 10) {
                jourAff = "0" + jourAff;
            }
            if (mois < 10) {
                moisAff = "0" + moisAff;
            }
            sortie = jourAff + moisAff + annee;
        }
        return sortie;
    }
    
    // format 01-01-2008 => 2008-01-01
    public static String ReverseDateToString(String dateentree) {
        String sortie = "";
        String jourAff = "";
        String moisAff = "";

        if (dateentree != null && !dateentree.equals("")) {
         
            int annee = new Integer(dateentree.substring(0, 2)).intValue();
            int mois = new Integer(dateentree.substring(3, 5)).intValue();
            int jour = new Integer(dateentree.substring(6, 10)).intValue();

            jourAff = jour + "-";
            moisAff = mois + "-";
            if (jour < 10) {
                jourAff = "0" + jourAff;
            }
            if (mois < 10) {
                moisAff = "0" + moisAff;
            }
            sortie = jourAff + moisAff + annee;
            
        }
        return sortie;
    }
    
    public static Date StringToDate(String entree) {
        Date sortie = new Date();
        if (entree != null && !entree.equals("")) {
            // format 01/01/2008   
            if(entree.length() == 10){
                int jour = new Integer(entree.substring(0, 2)).intValue();
                int mois = new Integer(entree.substring(3, 5)).intValue();
                int annee = new Integer(entree.substring(6, 10)).intValue();               
                GregorianCalendar calendar = new java.util.GregorianCalendar();
                calendar.set(annee, mois - 1, jour);            
                sortie = calendar.getTime();
             }
        }

        return sortie;
    }
}
