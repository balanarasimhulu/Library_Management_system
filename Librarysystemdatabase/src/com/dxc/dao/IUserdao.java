package com.dxc.dao;

public interface IUserdao
{
	public void verifydetails(String a, String p);

	public void getBooksLibrary();
	public void getListOfbooks();
	public void availableBalance();
	public void closeSystem();
}
