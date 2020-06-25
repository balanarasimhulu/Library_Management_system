package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.dxc.pojos.Librarysystem;

public class Userdao implements IUserdao 
{
	private int c1,bookid,quantity,days;
	Librarysystem bd=new Librarysystem();
	private String bookname,author,username;
	Scanner sc=new Scanner(System.in);
	private static Connection conn;
	static
	{
		try {
			Class.forName("org.postgresql.Driver");
			
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/LibrarySystem","postgres","password");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void verifydetails(String a, String p)
	{
		try {
			Statement stmt =conn.createStatement();
			ResultSet rset=stmt.executeQuery("select * from userdetails");
			while(rset.next())
			{
				if(rset.getString(2).equals(a) && rset.getString(3).equals(p))
				  System.out.println("you are logged in");
				else
				{
					System.out.println("userdetails incorrect");
					System.exit(0);
				
				}
				 break;
			}
			
		} catch (SQLException e) {
					e.printStackTrace();
		}
		
	}

	public void getBooksLibrary()
	{
		try {
			Statement stmt=conn.createStatement();
			ResultSet rst=stmt.executeQuery("select * from bookdetails");
			while(rst.next())
			{
				System.out.println(rst.getInt(1)+"  "+rst.getString(2)+"   "+rst.getString(3)+"   "+rst.getInt(4));
			}
			System.out.println();
			System.out.println("enter bookid to issue :");
			bookid=sc.nextInt();
			PreparedStatement pstmt1 =conn.prepareStatement("insert into userdetails values(?,'userid','password',300)");
			pstmt1.setInt(1, bookid);
			pstmt1.execute();
			System.out.println("book issued ");
			System.out.println("how many books needed :");
			c1=sc.nextInt();
			PreparedStatement pstmt=conn.prepareStatement("update bookdetails set quantity=quantity-? where book_id =?");
			pstmt.setInt(1, c1);
			pstmt.setInt(2,bookid);			
			pstmt.execute();
			System.out.println("how many days ");
			days=sc.nextInt();
			System.out.println("enter userid");
			username=sc.next();
			PreparedStatement pstmt2=conn.prepareStatement("update userdetails set Library_bal=Library_bal-(?*5) where user_id=?");
			pstmt2.setInt(1, days);
			pstmt2.setString(2, username);
			pstmt2.execute();
			
			
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void getListOfbooks()
	{
		try {
			System.out.println("enter author");
			author=sc.next();
			PreparedStatement pstmt=conn.prepareStatement("select * from bookdetails where book_author =?");
			pstmt.setString(1,author);
			ResultSet rst=pstmt.executeQuery();
			while(rst.next())
			{
				System.out.println(rst.getInt(1)+"  "+rst.getString(2)+"   "+rst.getString(3)+"   "+rst.getInt(4));
			}
			
			System.out.println();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void availableBalance()
	{
		try {
			PreparedStatement pstmt =conn.prepareStatement("select Library_bal from userdetails where user_id=?");
			System.out.println("enter username");
			username=sc.next();
			pstmt.setString(1, username);
			ResultSet rst=pstmt.executeQuery();
			while(rst.next())
			{
			System.out.println(rst.getInt(1));
			break;
			}
			System.out.println();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void closeSystem()
	{

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


}
