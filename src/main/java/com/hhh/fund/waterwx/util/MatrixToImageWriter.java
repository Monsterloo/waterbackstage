package com.hhh.fund.waterwx.util;


//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Date;
//import java.util.Hashtable;

//import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//import com.glodon.gjc.util.DateFormatUtil;
//import com.glodon.gjc.util.WebUtil;
//import com.glodon.util.GLodonSessionFactory;
//import com.glodon.util.GlodonSession;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;

/*
 * 二维码生成类
 */
public class MatrixToImageWriter {

/*   private static final int width = 300;        //默认高
   private static final int height = 300;       //默认宽
   private static final int BLACK = 0xFF000000; 
   private static final int WHITE = 0xFFFFFFFF; 
    
   private MatrixToImageWriter() {} 
    
      
   public static BufferedImage toBufferedImage(BitMatrix matrix) { 
     int width = matrix.getWidth(); 
     int height = matrix.getHeight(); 
     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
     for (int x = 0; x < width; x++) { 
       for (int y = 0; y < height; y++) { 
         image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE); 
       } 
     } 
     return image; 
   } 
   
    * format :图片格式
    * file ：生成路径
    
   
     public static void writeToFile(String format, File file,String text) 
         throws IOException { 
       BitMatrix bitMatrix = getBitMatrix(text);
       BufferedImage image = toBufferedImage(bitMatrix); 
       if (!ImageIO.write(image, format, file)) { 
              throw new IOException("Could not write an image of format " + format + " to " + file); 
  	     } 
     }
     
 
  * format :图片格式
  * file ：生成路径
  
 
   public static void writeToFile(BitMatrix matrix, String format, File file) 
       throws IOException { 
     BufferedImage image = toBufferedImage(matrix); 
     if (!ImageIO.write(image, format, file)) { 
            throw new IOException("Could not write an image of format " + format + " to " + file); 
	     } 
   } 
 
  * 以流的形式生成
  	    
      
   public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) 
       throws IOException { 
     BufferedImage image = toBufferedImage(matrix); 
     if (!ImageIO.write(image, format, stream)) { 
       throw new IOException("Could not write an image of format " + format); 
     } 
   } 
   
   public static BitMatrix getBitMatrix(String text){
       //二维码的图片格式 
       String format = "gif"; 
       Hashtable hints = new Hashtable(); 
       //内容所使用编码 
       hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
       BitMatrix bitMatrix =null;
       try{
    	  bitMatrix = new MultiFormatWriter().encode(text, 
    			  BarcodeFormat.QR_CODE, width, height, hints);
       }catch(WriterException e){
    	   System.out.println("二维码生成异常!");
    	   e.printStackTrace();
       }
                
	   return bitMatrix;
   }
   //生成微信扫描二维码
   public static String createWechatTwoCode(HttpServletRequest request,HttpServletResponse response){
	    String format="gif";
		String month=DateFormatUtil.toStr(new Date(), "yyyyMM");
	    String basePath=request.getSession().getServletContext().getRealPath("/")+"uploadData/img/twoCode/"+month;
	    String sessionId=GLodonSessionFactory.getSessionID(request, response);
	    String id = sessionId.replaceAll("-","");
		String agent=request.getHeader("User-Agent");
		
		ClientInfo clientInfo = new ClientInfo(agent);
		String browserName=clientInfo.getBrowserName();
		String osName=clientInfo.getOSName();
		GlodonSession session=GLodonSessionFactory.getGlodonSessionById(sessionId);
		session.setAttribute("browserName", browserName);
		session.setAttribute("osName", osName);
		
		File BaseFile = new File(basePath);
		if(!BaseFile.exists()){
			BaseFile.mkdirs();
		}
		String imgPath=basePath+"/"+id+".gif";
		File imgFile = new File(imgPath);
		if(imgFile.exists()){
			imgPath =imgPath.replaceAll("\\\\", "/");
			return imgPath;
		}
	    File file = new File(imgPath);
	    String httpUrl=Constants.PC_AUTH_URL.replace("STATE", sessionId);
	    try{
	    	MatrixToImageWriter.writeToFile(format,file,httpUrl);
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	    imgPath =imgPath.replaceAll("\\\\", "/");
	    return imgPath;
   }  */
}
