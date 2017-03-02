package com.hhh.fund.waterwx.service;

import com.hhh.fund.waterwx.model.Menu;



public interface MenuService {
	public  Menu getMenu();
	public  int createMenu(Menu menu, String accessToken);
}
