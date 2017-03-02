package com.hhh.fund.waterwx.model;

import java.io.Serializable;
/** 
 * 按钮的基类 
 *  
 * @author fws 
 * @date 2013-08-08 
 */ 
public class Button implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    private String name;  //按钮名
    
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    } 
}
