package com.KongJian.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;




public class ImageUtil {
private static Integer width=100;//定义图片的宽度
private static Integer height=40;//定义图片的高度
private static Integer codeCount=4;//定义图片上显示验证码的个数
private static Integer xx=15;
private static Integer fontHeight=32;//字体高度
private static Integer codeY=32;
private static char[] codeZiMu= {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
		'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
/*
 * 生成一个map集合
 * code为生成的验证码
 * codePic为验证码的图片，BufferImage对象
 * 
 */
public static Map<String,Object> getImage(){
	BufferedImage bufimg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	
	//画图对象Graphics
	Graphics gd=bufimg.getGraphics();
	//创建一个生成随机数的对象
	Random random=new Random();
	//将图片区域进行填充随便自己填充什么颜色这里我填充为白色
	gd.setColor(Color.WHITE);
	gd.fillRect(0, 0, width, height);
	//创建字体，字体的大小应该是根据图片高度来做，我们的图片高度是20所以我们设置为17比较合适,如果要修改修修改成员变量的值
	Font font =new Font("Fixedsys",Font.BOLD,fontHeight);
	//将字体的设置加入到画图对象gd中
	gd.setFont(font);
	//画出我们图片的边框
	gd.setColor(Color.RED);//这里我设置为红色边框，觉得丑可以改颜色值
	//画边框
	gd.drawRect(0, 0, width-1, height-1);
	//随机产生干扰线数量自己定我这默认写了15条，目的是让图片不容易被别的爬虫直接识别或者攻击程序直接识别
	//先设定干扰线的颜色
	gd.setColor(Color.BLACK);//黑色的干扰线
	//用循环随机画出干扰线
	for(int i=0;i<15;i++) {
		//两个点x,y-x1,y1作为划线的两个坐标点,利用random对象生成随机数
		Integer x=random.nextInt(width);
		Integer y=random.nextInt(height);
		//x,y为起始点，而x1,y1则为重点我们将它固定在某一个范围之内
		Integer x1=random.nextInt(width);
		Integer y1=random.nextInt(height);
		//画线gd.drawLine();
		gd.drawLine(x, y, x1, y1);
		
		
		
	
	}
	//用StringBuffer对象randomcode保存产生的验证码，以便用户登陆后可以进行验证
	StringBuffer randomcode=new StringBuffer();
	//定义三个变量保存颜色信息让我们的每个验证的信息颜色都不一样
	Integer red=0; Integer green=0; Integer blue=0;
	//用循环生成我们要的验证字符
	for(int i=0;i<codeCount;i++) {
		String code=String.valueOf(codeZiMu[random.nextInt(36)]);
		red=random.nextInt(255);
		 green = random.nextInt(255);
	     blue = random.nextInt(255);
	     //用随机产生的颜色将验证码绘制到图像中
	     gd.setColor(new Color(red,green,blue));
	     gd.drawString(code,(i+1)*xx,codeY);
	     //将产生的四个随机数组合在一起
	     randomcode.append(code);
	     
		
	}
	//循环结束后将验证码信息和绘制好的图片放入到Map中
	Map<String,Object> map=new HashMap<String,Object>();
	
	map.put("code", randomcode);
	map.put("codePic", bufimg);
	
	return map;
	
	
	
}


}
