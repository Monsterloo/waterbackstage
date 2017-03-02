package com.hhh.fund.waterwx.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.servlet.http.HttpServletResponse;

import com.hhh.fund.waterwx.entity.SwjAttachment;

import net.coobird.thumbnailator.Thumbnails;

public class IOUtils {
	
	/**
	 * 将流转换成byte[]
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public byte[] changeInputStreamToByteArray(InputStream in){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[]bytearray = null;
		byte[]buf = new byte[1024*3];
		int len = 0;
		try{
			while((len=in.read(buf))!=-1){
				out.write(buf, 0, len);
			}
			bytearray = out.toByteArray();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			IOUtils.close(in);
			IOUtils.close(out);
		}
		return bytearray;
	}
	
	
	/**
	 * 
	 * @param data
	 * @param out
	 */
	 public static void byte2image(byte[] data,OutputStream out){
		    if(data.length<3) return;
		    try{
		    	out.write(data, 0, data.length);
		    	out.flush();
		    } catch(Exception ex) {
		      ex.printStackTrace();
		    }finally{
		    	try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
	}
	 
	 /**
	  * 将图片输出到磁盘
	  */
	 public static void outputImageToDisk(String name,byte[] bufs,String path,HttpServletResponse response){
			System.out.println("path>>>>>>>>>"+path);
			File file = new File(path+File.separator+name+".jpeg");
			 OutputStream out = null;
			if(file.exists()){
				try {
				InputStream in = new BufferedInputStream(new FileInputStream(file));
				int size = in.available();
				byte[]fileBytes = new byte[size];
				int endflag = 0;
				while((endflag=in.read(fileBytes))!=-1){
			        response.setHeader("Pragma","No-cache");     
			        response.setHeader("Cache-Control","no-cache");     
			        response.setDateHeader("Expires", 0);     
			        response.setContentType("image/jpeg"); 
					out = response.getOutputStream();
					IOUtils.byte2image(fileBytes,out);
				}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				byte2image(new byte[]{},out);
			}
	 }
	 
	/**
	 * 压缩图片
	 * @param b
	 * @param quality
	 * @return
	 * @throws IOException
	 */
	public static byte[] changeImageSize(byte[]b, float quality) throws IOException {  
		ByteArrayInputStream in = new ByteArrayInputStream(b);    //将b作为输入流；
		BufferedImage image = ImageIO.read(in);		
        // 如果图片空，返回空  
        if (image == null) {  
            return null;  
        }     
        // 得到指定Format图片的writer  
        Iterator<ImageWriter> iter = ImageIO  
                .getImageWritersByFormatName("jpeg");// 得到迭代器  
        ImageWriter writer = (ImageWriter) iter.next(); // 得到writer  
  
        // 得到指定writer的输出参数设置(ImageWriteParam )  
        ImageWriteParam iwp = writer.getDefaultWriteParam();  
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩  
        iwp.setCompressionQuality(quality); // 设置压缩质量参数  
  
        iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);  
  
        ColorModel colorModel = ColorModel.getRGBdefault();  
        // 指定压缩时使用的色彩模式  
        iwp.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,  
                colorModel.createCompatibleSampleModel(16, 16)));  
  
        // 开始打包图片，写入byte[]  
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流  
        IIOImage iIamge = new IIOImage(image, null, null);  
        try {  
            // 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput  
            // 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput  
            writer.setOutput(ImageIO  
                    .createImageOutputStream(byteArrayOutputStream));  
            writer.write(null, iIamge, iwp);  
        } catch (IOException e) {  
            System.out.println("write errro");  
            e.printStackTrace();  
        }  
        System.out.print("jpeg" + quality + "质量完成打包-----lenth----" + byteArrayOutputStream.toByteArray().length+">>>>>>>>>>");  
        return byteArrayOutputStream.toByteArray();  
    }
	
	/**
	 * 获取PrintWriter
	 * @param filePath
	 * @param charset
	 * @param append
	 * @return
	 */
	public static PrintWriter getPrintWriter(String filePath,String charset,boolean append){
		File file = new File(filePath);
		
		PrintWriter out = null;
		try{
			if(!file.exists()){
				file.createNewFile();
			}
			
			out = new PrintWriter(
					new OutputStreamWriter(
							new FileOutputStream(filePath,append),charset));
		}catch(Exception e){
		}
		return out;
	}
	
	/**
	 * 获取BufferedReader
	 * @return
	 */
	public static BufferedReader getBufferedReader(String filePath,String charset){
		BufferedReader br = null;
		File file = new File(filePath);
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			br = new BufferedReader(
					new InputStreamReader(
							new BufferedInputStream(
									new FileInputStream(filePath)
							),charset));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return br;
	}

	/**
	 * 关闭流
	 * @param br
	 */
	public static void close(InputStream br) {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭流
	 * @param br
	 */
	public static void close(Reader br) {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
	public static void close(OutputStream out){
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void closeResponseWriter(HttpServletResponse response) {
		try {
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成缩略图
	 * @param bufs
	 * @param path  路径
	 * @param attaId 图片Id
	 */
	public static void generateSmallImage(byte[] bufs, String path,String attaId) {
        	generateSmallImage(bufs, path, attaId,90);
	}
	public static void generateSmallImage(byte[] bufs, String path,String attaId,int size) {
        try {
			Thumbnails.of(ImageIO.read(new ByteArrayInputStream(bufs))).outputQuality(0.8f).size(size, size).toFile(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
