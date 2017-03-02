package com.hhh.fund.util;

import java.io.File;
import java.io.FilenameFilter;

public class MyFileFilter implements FilenameFilter{
	private String filterName;
	
	public MyFileFilter(String filename){
		if(filename.endsWith(".class")){
			filename = filename.replaceAll(",", "");
		}
		this.filterName = filename;
	}
	
	
	
	@Override
	public boolean accept(File file, String s) {
		if(s.startsWith(filterName)){
			return true;
		}
		return false;
	}

}
