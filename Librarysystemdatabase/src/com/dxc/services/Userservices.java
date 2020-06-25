package com.dxc.services;

import com.dxc.dao.IUserdao;
import com.dxc.dao.Userdao;

public class Userservices implements IUserservices
{
	IUserdao iua=new Userdao();
	public void verifydetails(String a, String p)
	{
		iua.verifydetails(a,p);
	}

	public void getBooksLibrary()
	{
		iua.getBooksLibrary();
	}
	public void getListOfbooks()
	{
		iua.getListOfbooks();
	}
	public void availableBalance()
	{
		iua.availableBalance();
	}
	public void closeSystem()
	{
		iua.closeSystem();
	}

}
