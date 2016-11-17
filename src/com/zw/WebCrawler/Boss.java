package com.zw.WebCrawler;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import com.zw.beans.excel.CreateExcel;

public class Boss implements Runnable {
	
	    private CountDownLatch downLatch;

	    public Boss(CountDownLatch downLatch)
	    {
	        this.downLatch = downLatch;
	    }
        @SuppressWarnings("unchecked")
		@Override
	    public void run()
	    {
	        System.out.println("正在等所有的线程完成......");
	        try
	        {
	            this.downLatch.await();
	        }
	        catch (InterruptedException e)
	        {
	        }
	        System.out.println("所有子线程已完成，开始排序以及排重工作");
	        
	        HashMap<String, Integer> map1 = InfoGetByWeb.map;
	        @SuppressWarnings("rawtypes")
			List excel = new ArrayList();
	        
	       	List<HashMap.Entry<String, Integer>> list = new ArrayList<HashMap.Entry<String, Integer>>(map1.entrySet());  
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
	            //降序排序  
	            @Override  
	            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	                //return o1.getValue().compareTo(o2.getValue());  
	                return o2.getValue().compareTo(o1.getValue());  
	            }  
	        });  
	       
	        //System.out.println(InfoGetByWeb.map.size());
	        for(Entry<String, Integer> map2 : list){
	        	//System.out.println( map2.getKey() + " and " + map2.getValue()+map2);	        	
	        	excel.add(map2.getKey());
	        }
	       
	        for(int i = 0;i<100;i++){
	        System.out.println(list.get(i));
	        }
	        
	        CreateExcel.Exp(excel);
	    }

	}

