package com.dxc.services;

import com.dxc.dao.Admindao;
import com.dxc.dao.IAdmindao;

public class Adminservices implements IAdminservices
{
	IAdmindao iad=new Admindao();
	public void verifydetails(String a,String p)
	{
		iad.verifydetails(a,p);
		
	}
	public void updateBooks()
	{
		iad.updateBooks();
	}

	public void getListOfBooks()
	{
		iad.getListOfBooks();
	}
	public void getBalance()
	{
		iad.getBalance();
	}
	public void closeUserAccount() 
	{
		iad.closeUserAccount();		
	}

}
