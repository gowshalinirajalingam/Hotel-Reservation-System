
import Airproject.dbConnection;
import java.util.Arrays;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zzath
 */
public class Reservation {
    private int reserved;
    private String checkindate;
    private String checkoutdate;
    static private String identity;
    private String roomNo;
    private String reservedBy;
    private int breakfast;
    private int lunch;
    private int dinner;
    private double price;
    private double pricePerDay;
    private int days=1;
    private int cost=1;
    static private String reservationId;
    
    public double getPrice(){
        return this.price;
    }
    public void setResId(String reid)
    {
        this.reservationId=reid;
    }
    public String getResId()
    {
        return this.reservationId;
    }
      public void setIdentity(String identity)
    {
        this.identity=identity;
    }
    public String getIdentity()
    {
        return this.identity;
    }
     Reservation(){
     }
    Reservation(int ac, int masterbedroom, int breakfast, int lunch, int dinner,String checkindate,String checkoutdate,double pricePerDay){
      
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        this.pricePerDay = pricePerDay;
        calculateDays(checkindate, checkoutdate);
        calculateCost( ac,  masterbedroom,  breakfast,  lunch,  dinner);
        calculatePrice(this.cost);
        System.out.println(this.price);
        
    }
    public boolean contains(final int[] array, final int key) {
        return Arrays.asList(array).contains(key);
    }
     public int calculateDays(String checkindate,String checkoutdate){
        int checkinDay = Integer.parseInt(checkindate.substring(0, 2));
        int checkinMonth = Integer.parseInt(checkindate.substring(3, 5));
        int checkinYear = Integer.parseInt(checkindate.substring(5, 10));
        
        int checkoutDay = Integer.parseInt(checkoutdate.substring(0, 2));
        int checkoutMonth = Integer.parseInt(checkoutdate.substring(3, 5));
        int checkoutYear = Integer.parseInt(checkoutdate.substring(5, 10));
        
        
        try{
            int oddMonths[] ={1,3,5,7,8,10,12};//
           
            
            if(checkoutMonth-checkinMonth >=0 && checkoutYear-checkinYear>=0 && checkoutYear-checkinYear <=1 && checkoutDay-checkinDay>=1){
                this.days = checkoutDay-checkinDay;
            }
            else if (checkoutMonth-checkinMonth >=1 && checkoutYear-checkinYear>=0 && checkoutYear-checkinYear <=1 && checkoutDay-checkinDay<1 && contains(oddMonths,checkoutMonth)==true){
                
                checkoutDay=checkoutDay+(30*(checkoutMonth-checkinMonth));
                this.days = checkoutDay-checkinDay;
            }
             else if (checkoutMonth-checkinMonth >=1 && checkoutYear-checkinYear>=0 && checkoutYear-checkinYear <=1 && checkoutDay-checkinDay<1 && contains(oddMonths,checkoutMonth)==false){
                checkoutDay=checkoutDay+(31*(checkoutMonth-checkinMonth));
                this.days = checkoutDay-checkinDay;
               
            }
            else {
                System.out.print("Choose a Correct date");
            }
             
        }
        catch(Exception ex){
            ex.getStackTrace();
            
        }
        return this.days;

     }
    public int calculateCost(int ac, int masterbedroom, int breakfast, int lunch, int dinner){
        if(ac==1){
            this.cost++;
        }
        if(masterbedroom==1){
            this.cost++;
        }
        if(breakfast==1){
            this.cost++;
        }
        if(lunch==1){
            this.cost++;
        }
        if(dinner==1){
            this.cost++;
        }
        return this.cost;
    }
    public double calculatePrice(int cost){
        switch(cost){
            case 1 : this.price=20000+(this.days*this.pricePerDay);
            break;
            case 2 : this.price = 23000+(this.days*this.pricePerDay);
            break;
            case 3 : this.price = 26000+(this.days*this.pricePerDay);
            break;
            case 4 : this.price = 29000+(this.days*this.pricePerDay);
            break;
            case 5 : this.price = 35000+(this.days*this.pricePerDay);
            break;
            case 6 : this.price = 40000+(this.days*this.pricePerDay);
            break;
        }
       
        return this.price;
    }
    
    public void MakeReservation(int reserved,String checkindate,String checkoutdate,String identity,String roomNo,String reservedBy,double price,double pricePerDay,int days,int breakfast, int lunch, int dinner) {
        this.reserved = reserved;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        this.identity ="1";
        this.roomNo = roomNo;
        this.reservedBy = reservedBy;
        this.price = price;
        this.pricePerDay = pricePerDay;
        this.days = days;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        
        try{
            dbConnection connect = new dbConnection();
            connect.ConnectDB();

            String reservationQuery="INSERT INTO reservation (reserved,checkindate,checkoutdate,identity,roomNo,reservedBy,days,pricePerDay,price,breakfast,lunch,dinner,reservedDate) VALUES ('"+this.reserved+"','"+this.checkindate+"','"+this.checkoutdate+"','"+this.identity+"','"+this.roomNo+"','"+this.reservedBy+"','"+this.days+"','"+this.pricePerDay+"','"+this.price+"',','"+this.breakfast+"','"+this.breakfast+"','"+this.lunch+"','"+this.dinner+"',NOW()')";
            String roomQuery = "UPADTE room SET STATUS =2 WHERE roomNo = '"+this.roomNo+"'";
            connect.Excecute(reservationQuery, "insert");
            connect.Excecute(roomQuery, "update");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
        public void tmpReserve(String checkindate,String checkoutdate,String roomNo,String reservedBy,int ac, int masterbedroom,double price,double pricePerDay,int days,int breakfast, int lunch, int dinner) {
        this.reserved = reserved;
        this.roomNo = roomNo;
        this.reservedBy = reservedBy;
        this.price = price;
        this.cost = 1;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        
        try{
            this.checkindate = checkindate;
            this.checkoutdate = checkoutdate;
            this.pricePerDay = pricePerDay;
            calculateDays(checkindate, checkoutdate);
            calculateCost(ac,masterbedroom,breakfast,lunch,dinner);
            calculatePrice(this.cost);
            
            
            try{
                dbConnection connect = new dbConnection();
                connect.ConnectDB();
                try {
                    String reservationQuery="INSERT INTO reservation (reserved,checkindate,checkoutdate,roomNo,reservedBy,ac,masterbedroom,price,pricePerDay,days,breakfast,lunch, dinner,reservedDate) VALUES (2,'"+this.checkindate+"','"+this.checkoutdate+"','"+this.roomNo+"','"+this.reservedBy+"','"+ac+"','"+masterbedroom+"','"+this.price+"','"+this.pricePerDay+"','"+this.days+"','"+this.breakfast+"','"+this.lunch+"', '"+this.dinner+"',NOW())";
                    String roomQuery = "UPDATE room SET reserved =2 WHERE roomNo = '"+this.roomNo+"'";
                    connect.Excecute(reservationQuery, "insert");
                    connect.Excecute(roomQuery, "update");
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
        
        

    }
        public void getPrice(String checkindate,String checkoutdate,String roomNo,String reservedBy,int ac, int masterbedroom,double price,double pricePerDay,int days,int breakfast, int lunch, int dinner) {
        this.reserved = reserved;
        this.roomNo = roomNo;
        this.reservedBy = reservedBy;
        this.price = price;
        this.cost = 1;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        
        try{
            this.checkindate = checkindate;
            this.checkoutdate = checkoutdate;
            this.pricePerDay = pricePerDay;
            calculateDays(checkindate, checkoutdate);
            calculateCost(ac,masterbedroom,breakfast,lunch,dinner);
            calculatePrice(this.cost);
            
            
            try{
                dbConnection connect = new dbConnection();
                connect.ConnectDB();
                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
        
        

    }

}
