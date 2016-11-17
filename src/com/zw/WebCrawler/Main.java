package com.zw.WebCrawler;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	//需要的书籍类型
	static String str1= "互联网";
	static String str2= "编程";
	static String str3= "算法";
	
    
	
	//容量容器   
    
	ArrayList<InfoGetByWeb> list=new ArrayList<InfoGetByWeb>();
	
	
	
	
	public static void main(String[] args){
		 ExecutorService executor = Executors.newCachedThreadPool(); 
		 CountDownLatch latch = new CountDownLatch(3);
		 
    	    /*Thread t1 = new Thread(new InfoGetByWeb(str1));
    	    Thread t2 = new Thread(new InfoGetByWeb(str2));
    	    Thread t3 = new Thread(new InfoGetByWeb(str3));
    	    
    	    
    	    t1.start();
    	    t2.start();
    	    t3.start();*/
		 
		 
		 InfoGetByWeb w1 = new InfoGetByWeb(latch, str1);
		 InfoGetByWeb w2 = new InfoGetByWeb(latch, str2);
		 InfoGetByWeb w3 = new InfoGetByWeb(latch, str3);
	    
	        Boss boss = new Boss(latch);
	        
	        executor.execute(boss);
	        executor.execute(w3);
	        executor.execute(w2);
	        executor.execute(w1);
	        
	        executor.shutdown();
	    }
    	   
    	    //线程结束后开始执行判断
    	// if(!(t1.isAlive()||t2.isAlive()||t3.isAlive())){
    	 
    		 //  System.out.println(InfoGetByWeb.map);
    	    	
    	   
    	     
    	     
    	     
    	    
    	   
       }

