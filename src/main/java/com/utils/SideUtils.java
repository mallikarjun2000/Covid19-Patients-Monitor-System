package com.utils;

public class SideUtils {
	public static String getAlphaNumericString() 
    { 
		int n = 20;
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        }
  
        return sb.toString(); 
    }
	
	public static String generateOTP()  
    {  //int randomPin declared to store the otp 
        //since we using Math.random() hence we have to type cast it int 
        //because Math.random() returns decimal value 
        int randomPin   =(int) (Math.random()*9000)+1000; 
        String otp  = String.valueOf(randomPin); 
        return otp; //returning value of otp 
    } 
	
}
