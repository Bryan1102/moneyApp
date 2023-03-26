    
package com.anthorra.moneyapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anthorra
 */
public class DBHandler
{
    public static void insertIntoDB(FinancialRecord fr, StatusMessage sm) 
    {
        double amount = fr.getAmount();
        int isExpense = fr.isIsExpense() ? 1 : 0;
        int type = fr.getType();
        int subType = fr.getSubtype();
        String comment = fr.getComment();
        String datum = fr.getRealizedDate();
        String status;
        
        //status = " Connecting DB..."; 
        //sm.updateStatus(status);
        
        Connection con;
        try
        {
            //con = DriverManager.getConnection(url, username, password);
            con = retrieveConn();

            // Obtain a statement
            //Statement st = con.createStatement();
            var st = con.prepareStatement("INSERT INTO financialrecord (ISEXPENSE, AMOUNT, TYPE, SUBTYPE, COMMENT,REALIZED_DATE) VALUES (?,?,?,?,?,STR_TO_DATE(?,\"%Y-%m-%d\"));");
            st.setInt(1, isExpense);
            st.setDouble(2, amount);
            st.setInt(3, type);
            st.setInt(4, subType);
            st.setString(5, comment);
            st.setString(6, datum);
            
            int count = st.executeUpdate();
            sm.updateStatus(count + " " + (fr.isIsExpense()?" Kiadés":" Bevétel")  + " rögzítve!");
            // Execute the query
            
            // Closing the connection as per the requirement with connection is completed
            con.close();
        } 
        catch (SQLException ex)
        {
            /*Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);*/
            System.out.println(ex);
        }
        
        //sm.updateStatus(status);
    }
    
    public static void insertTypeIntoDB(String type, StatusMessage sm) 
    {
        String type_desc = type;
        String status;
        
        // Obtain a connection
        Connection con;
        try
        {
            //con = DriverManager.getConnection(url, username, password);
            con = retrieveConn();
            // Obtain a statement
            var st = con.prepareStatement("INSERT INTO fintypes (TYPE_DESC) VALUES (?);");
            st.setString(1, type_desc);
            
            int count = st.executeUpdate();
            // Execute the query
            // Closing the connection as per the requirement with connection is completed
            con.close();
            
            sm.updateStatus(count + " Típus rögzítve!");
            
        } 
        catch (SQLException ex)
        {
            /*Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);*/
            System.out.println(ex);
            //status += " Connection FAILED!"; 
            //sm.updateStatus(status);
        }
        //status += " OK"; 
        //sm.updateStatus(status);
    }
    
    public static ArrayList<FinancialRecordTypes> queryTypeList()
    {
        ArrayList<FinancialRecordTypes> typeList = new ArrayList<>();
        String sqlQuery = "Select f.ID, f.TYPE_DESC, fs.ID, fs.PARENT_ID, fs.TYPE_DESC FROM fintypes f  LEFT JOIN finsubtypes fs ON f.ID = fs.PARENT_ID\n" +
                            "WHERE f.IS_DELETED is null  AND fs.IS_DELETED is null\n" +
                            "ORDER BY f.ID, fs.ID";
        Connection con;
        
        try
        {
            con = retrieveConn();
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while(rs.next())
            {
                FinancialRecordTypes frt = new FinancialRecordTypes(rs.getInt(1), 
                                                                    rs.getString(2), 
                                                                    rs.getInt(3), 
                                                                    /*rs.getInt(4),*/
                                                                    rs.getString(5));
                typeList.add(frt);
            }
            
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return typeList;
    }
    
    /* CONNECTION SUB ROUTINE */
    private static Connection retrieveConn()
    {
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/moneyapp"; //"jdbc:odbc:XE";
        String username = "javaDev";
        String password = "java1234_";
        
        try
        {
            // Load driver class
            Class.forName(driverClassName);
            //status = "JDBC driver found..."; 
            //sm.updateStatus(status);
        } 
        catch (ClassNotFoundException ex)
        {
            /*Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);*/
            System.out.println(ex);
            //status = "JDBC driver NOT FOUND!"; 
            //sm.updateStatus(status);
        }
        
        Connection con;
        try
        {
            con = DriverManager.getConnection(url, username, password);
            return con;
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    
}
