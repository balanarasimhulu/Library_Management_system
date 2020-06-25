package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dxc.pojos.Librarysystem;

public class Admindao implements IAdmindao
{
	private int c1,bookid,quantity;
	Librarysystem bd;
	private String bookname,author,username;
	Scanner sc=new Scanner(System.in);
	private static Connection conn;
	private List<Librarysystem> l=new ArrayList<>();
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
	public void verifydetails(String a,String p)
	{
		try {
			Statement stmt =conn.createStatement();
			ResultSet rset=stmt.executeQuery("select * from admindetails");
			while(rset.next())
			{
				if(rset.getString(2).equals(a) && rset.getString(3).equals(p))
				  System.out.println("you are logged in");
				else {
					System.out.println("admindetails incorrect");
				System.exit(0);
				}
				break;
			}
			
		} catch (SQLException e) {
					e.printStackTrace();
		}
		
	}
	public void updateBooks()
	{
		try {
		
		System.out.println("1.adding a book ");
		System.out.println("2.remove a book ");
		c1=sc.nextInt();
		switch(c1)
		{
		case 1:
			PreparedStatement pstmt1 =conn.prepareStatement("insert into bookdetails values(?,?,?,?)");
	
			System.out.println("enter id");
			bookid=sc.nextInt();
			pstmt1.setInt(1, bookid);
			System.out.println("enter bookname");
			
			bookname=sc.next();
			pstmt1.setString(2, bookname);
			System.out.println("enter author");
			
			author=sc.next();
			pstmt1.setString(3, author);
			System.out.println("enter quantyties ");

			quantity=sc.nextInt();
			pstmt1.setInt(4, quantity);
			pstmt1.execute();	
			System.out.println("book added into bookdetails");
			break;
		case 2:
			Statement stmt =conn.createStatement();
			ResultSet rset=stmt.executeQuery("select * from bookdetails ");
			while(rset.next())
			{
				bd=new Librarysystem(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getInt(4));
				l.add(bd);
			}
			for(Librarysystem bd:l)
			{
				System.out.println(bd.getBook_id()+"  "+bd.getBook_name()+"  "+bd.getAuthor()+"  "+bd.getQuantity());
			}
			System.out.println();
			System.out.println("enter the bookid :");
			bookid=sc.nextInt();
			PreparedStatement pstmt =conn.prepareStatement("delete from bookdetails where book_id=?");
			pstmt.setInt(1, bookid);
			pstmt.execute();
			break;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void getListOfBooks()
	{
		try {
			System.out.println("enter the username:");
			username=sc.next();
			PreparedStatement pstmt1 =conn.prepareStatement("select book_id from userdetails where user_id=?");
			
			pstmt1.setString(1, username);
			ResultSet rset = pstmt1.executeQuery();
			int count=0;
			while(rset.next())
			{
				System.out.println(rset.getInt(1));
				count=count+1;
			}
			
			while(count>=1)
			{
			System.out.println("enter the bookid :");
			bookid=sc.nextInt();
			PreparedStatement pstmt =conn.prepareStatement("select * from bookdetails where book_id=?");
			pstmt.setInt(1, bookid);
			ResultSet rst=pstmt.executeQuery();
				while(rst.next())
				{
				bd=new Librarysystem(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4));
				l.add(bd);
				}
			
			for(Librarysystem bd:l)
			{
				System.out.println(bd.getBook_id()+"  "+bd.getBook_name()+"  "+bd.getAuthor()+"  "+bd.getQuantity());
			}
			System.out.println();
			count--;
			}
			
		}catch (Exception e) {
			e.printStackTrace();

		}
	}
	public void getBalance()
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
	public void closeUserAccount()
	{
		try {
		Statement stmt =conn.createStatement();
		ResultSet rset=stmt.executeQuery("select * from userdetails ");
		while(rset.next())
		{
			System.out.println(rset.getInt(1)+"  "+rset.getString(2)+"   "+rset.getString(3)+"   "+rset.getInt(4));
		}
		
		System.out.println("enter the userid :");
		username=sc.next();
		PreparedStatement pstmt =conn.prepareStatement("delete from userdetails where user_id=?");
		pstmt.setString(1, username);
		pstmt.execute();
		
		}
	 catch(Exception e) {
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