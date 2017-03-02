package com.hhh.fund.waterwx.model;

import java.io.Serializable;
/** 
 * 菜单 
 *  
 * @author fws 
 * @date 2014-1-23
 */ 
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Button[] button;  
    
    public Button[] getButton() {  
        return button;  
    }  
  
    public void setButton(Button[] button) {  
        this.button = button;  
    } 
}
