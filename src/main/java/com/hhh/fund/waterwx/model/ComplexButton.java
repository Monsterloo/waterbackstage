package com.hhh.fund.waterwx.model;

/** 
 * 复杂按钮（父按钮） 
 *  
 * @author fws 
 * @date 2014-1-23 
 */  
public class ComplexButton extends Button {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Button[] sub_button;     //子类
  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}
