package com.zw.WebCrawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InfoGetByWeb implements Runnable{
         //爬取方法
	   private String str;

		private CountDownLatch downLatch;
	   
	   public InfoGetByWeb(CountDownLatch downLatch,String str){
		   this.downLatch = downLatch;
		   this.str=str;
	   }
	   private Object obj= new  Object();
	   
	   public volatile boolean exit = false; 
       
	   //将抓取的数据转化为数组
       //利用HashMap的特性完成去重
	   static HashMap<String,Integer> map = new HashMap<String,Integer>();
	   
	 
	   
	/*public static List toArray(String str,int num,int star){
		    List coll = new ArrayList(); 
		    coll.add(str);
		    coll.add(num);
		    coll.add(star);
		  // System.out.println(coll);
		   return coll;
	   }*/
	   
	   @Override
	   public void run() {
		   synchronized (obj) {
	       //豆瓣上按照书籍分类来做在第1000个是会返回304错误，于是如此操作
		   for(int i=0;i<1000;i=i+20){
		    
		   //1.创建连接
			String url="https://book.douban.com/tag/"+str+"?start="+i+"&type=T"; 
        try {
        	//2.加载网页
			Document doc = Jsoup.connect(url).timeout(1000000000).get();
			//3.解析网页
			Elements eles=doc.select("li");  
			
			//4.遍历数组
			for(int j=15;j<eles.size()-5;j++){
				Element li = eles.get(j);
				
				//获取图片
				Element img = li.select("img").get(0);
				String imgName= img.attr("src");
				
				//获取title
				Element tit1 = li.select("a").get(1);
				Element tit2 = li.select("span").get(0);
				String bookName=tit1.text()+tit2.text();
                
				//其它信息				
				String author = li.select("div.pub").get(0).text();
				
				
				//存在少标签的情况出现，判断来规避空指针异常
				Elements de =li.select("p");
				String detail;
				if(null==de){
			
				    detail= "";
					}
				
				else{
				     detail = de.text();
				}
				
				String price;
				Elements pr = li.select("span.buy-info");
				if(null== pr){
					
					price ="";
				}
				else {
				    price = pr.select("a").text();
				}
				
				//获取人数
				String n = li.select("span.pl").get(0).text();
				String reg = "[^0-9]";
				Pattern p = Pattern.compile(reg);
				Matcher m =p.matcher(n);
				int num = Integer.valueOf(m.replaceAll("").trim());
			
				//获取评价
				String level;
				Elements le = li.select("span.rating_nums");
				
				   level = le.text()+"0";
				   
				   /*if(level.matches("\\s+")){
					   level="0";}*/
				 
				   // System.out.println(level);
				
				 Matcher ms =p.matcher(level);  
			     int star = Integer.valueOf(ms.replaceAll("").trim());
				
				//System.out.println(level);
				
				//存入数组
				String ss=imgName+","+bookName+","+detail+","+price+","+author+","+n+","+level;
				
				
				
                
				if(num>200){
				//@SuppressWarnings("rawtypes")
				//List coll = toArray(ss, num, star);
				
					map.put(ss,star);
				//System.out.println(map.size());	
				}
				
				//System.out.println(str+imgName+","+bookName+detail+price+author+n+"******"+star);
                //System.out.println(coll);
				
				
				
				
					
				   // TopList.insert(coll);
						
					
										
				try {
					Thread.sleep((int) Math.random() * 10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
				
			 
				
			}
			
			
			
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   }
	
        
		
		
			
	}		

	   this.downLatch.countDown();
	   }}


	

