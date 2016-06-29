package com.dotel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Funcoes {	
	
	public static boolean isNumber(String str) {  
	       Pattern p = Pattern.compile("[0-9]+");  
	       Matcher m = p.matcher(str);  
	 
	       return m.find();     
	}  

}
