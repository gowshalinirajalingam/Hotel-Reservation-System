
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zzath
 */
public class Room {
    private String roomNo;
    private String specialName;
    private int ac;
    private int masterBedroom;
    private int maxAdlut;
    private int maxMinors;
    private double pricePerDay;

    
    
    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public int isAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int isMasterBedroom() {
        return masterBedroom;
    }

    public void setMasterBedroom(int masterBedroom) {
        this.masterBedroom = masterBedroom;
    }


    public int getMaxAdlut() {
        return maxAdlut;
    }

    public void setMaxAdlut(int maxAdlut) {
        this.maxAdlut = maxAdlut;
    }

    public int getMaxMinors() {
        return maxMinors;
    }

    public void setMaxMinors(int maxMinors) {
        this.maxMinors = maxMinors;
    }
    public double getpricePerDay() {
        return pricePerDay;
    }

    public void setpricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
   
    
    
}
