
import Airproject.dbConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gowshalini
 */
public class createreportReservation {
       public static void main(String args[]) throws SQLException, FileNotFoundException, DocumentException
    {
        
        int rowno=0;
       Connection con=null;
  PreparedStatement pst=null;
  ResultSet rs=null;
 
     dbConnection connect=new dbConnection();
     con=connect.ConnectDB();
    
   
     String p="select * from Reservation";
              
      pst =con.prepareStatement(p);
      rs=pst.executeQuery(p);
   ResultSetMetaData    rmd=rs.getMetaData();
      int colno=rmd.getColumnCount(); 
  
      while(rs.next())
      {
      rowno=rowno+1;
      }
      rs.first();
    
      Document d=new Document();
      PdfWriter.getInstance(d, new FileOutputStream("reportReservationt.pdf"));
      PdfPTable pt=new PdfPTable(colno);
      
      d.open();
       d.add(new Paragraph("Reservation Report"));
      for(int i=0;i<rowno;i++)
      {
       pt.addCell(""+rs.getString(1));
      pt.addCell(""+rs.getString(2));
      pt.addCell(""+rs.getString(3));
      pt.addCell(""+rs.getString(4));
      pt.addCell(""+rs.getString(5));
      pt.addCell(""+rs.getString(6));
      pt.addCell(""+rs.getString(7));
      pt.addCell(""+rs.getString(8));
      pt.addCell(""+rs.getString(9));
      pt.addCell(""+rs.getString(10));
      pt.addCell(""+rs.getString(11));
      pt.addCell(""+rs.getString(12));
      pt.addCell(""+rs.getString(13));
      pt.addCell(""+rs.getString(14));
      pt.addCell(""+rs.getString(15));
      pt.addCell(""+rs.getString(16));
     
     
      rs.next();
      }
     
     
      
      
      d.add(pt);
   
    d.close();
    
    }
    
}
