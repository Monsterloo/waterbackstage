package com.hhh.fund.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 打包工具
 * @author 3hzxp
 *
 */
public class MyPatchTools {

	static final String packageFileDir = "F:/description.txt";
	static final int BUFFER = 8192;
//	static final String contextName = "water_wx";
	static final String sourcesDirectory = "D:/3hzxp/workspace/water_wx/target/water_wx";//执行mvn 的命令package 后文件产生的路径
	static final String zipGenerDir = 	  "F:/zip/"+System.currentTimeMillis()+"/waterwx";  //打包后存放在路径
	
	public static void main(String[] args) throws Exception {
								//目标路径
		
//		BufferedReader br = IOUtils.getBufferedReader(packageFileDir, "");
//		String filePath = null;
//		String[] fileList = new String[1];
//		while((filePath = br.readLine())!=null){
//			fileList[fileList.length-1] = filePath;
//			fileList = Arrays.copyOf(fileList, fileList.length+1);
//			System.out.println(fileList.length);
//			System.out.println(Arrays.toString(fileList));
//		}
//		return;
		
		String[] fileList = new String[] {
				"/water_wx/src/main/webapp/assets",
				"/water_wx/src/main/java/com/hhh/fund/waterwx"
				};

		//执行之前长这样
		/**
		 * 		"/water_wx/src/main/java/com/hhh/fund/waterwx/controller/InsideNoticeController.java",
				"/water_wx/src/main/webapp/assets/jquery-1.12.2.js",
				"/water_wx/src/main/webapp/WEB-INF/views/initdata.jsp"
		 */
		for (int i = 0; i < fileList.length; i++) {
			fileList[i] = fileList[i].replaceFirst("/water_wx/src/main", "");
			fileList[i] = fileList[i].replaceFirst("/java/", "/WEB-INF/classes/");
			fileList[i] = fileList[i].replaceFirst("/webapp/","/");
			fileList[i] = fileList[i].replaceFirst(".java", ".class");
		}
		//执行之后长这样
		/**
		 * 	"/com/hhh/fund/waterwx/controller/InsideNoticeController.class",
			"/assets/jquery-1.12.2.js",
			"/WEB-INF/views/initdata.jsp"
		 */
		
		
		for (int i = 0; i < fileList.length; i++) {
			//最后需要长这样
			/**
		  		D:/3hzxp/workspace/water_wx/target/water_wx				 /WEB-INF/classes/com/hhh/fund/waterwx/controller/InsideNoticeController.class
				D:/3hzxp/workspace/water_wx/target/water_wx                 /assets/jquery-1.12.2.js
				D:/3hzxp/workspace/water_wx/target/water_wx                 /WEB-INF/views/initdata.jsp
			 */
			String souceFileStr = sourcesDirectory + fileList[i];
			
			copyFileOrDirectory(souceFileStr);
		}
		System.out.print(zipGenerDir + " --------" + zipGenerDir + ".zip");
		zipFile(zipGenerDir, zipGenerDir + ".zip");
		createLogFile(fileList, zipGenerDir);
	}

	private static void copyFileOrDirectory(String sourcefile) {

		File sourceFile = new File(sourcefile);
		String targetFile = "";
		if (sourceFile.exists()) {
			if(sourceFile.isDirectory()){
				File[]files = sourceFile.listFiles();
				for(File file:files){
					copyFileOrDirectory(file.getAbsolutePath().replace("\\", "/"));
				}
			}else{
				targetFile = sourcefile.replace(sourcesDirectory, zipGenerDir);
				
				if(!new File(zipGenerDir).exists()){
					new File(zipGenerDir).mkdirs();
				}
				copyFile(sourcefile,targetFile);
				/**
				 * service.impl  
				 */
				if(targetFile.endsWith(".class")&&targetFile.indexOf("/impl/")>=0){
					for(int index=0;index<50;index++){
						String targetFiletmp = "";
						targetFiletmp = targetFile.replace("Impl.class","Impl$"+index+".class");
						if(new File(sourcefile.replace("Impl.class","Impl$"+index+".class")).exists()){
							copyFile(sourcefile.replace("Impl.class","Impl$"+index+".class"),targetFiletmp);
						}
					}
				}
			}
		}
	}

	public static void createLogFile(String[] log, String srcPathName) {
		File newTextFile = new File(srcPathName + "/batchLog.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(newTextFile);
			for (int i = 0; i < log.length; i++) {
				fos.write((log[i] + "\n").getBytes());
			}
			fos.close();
		} catch (Exception e) {
			System.out.println("生成打包日志报错");
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("生成打包日志报错");
				e.printStackTrace();
			}

		}
	}

	public static void zipFile(String srcPathName, String zipFile) {
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "不存在！");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			out.setEncoding("utf-8");
			String basedir = "";
			compress(file, out, basedir);

			out.close();
			cos.close();

			fileOutputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			compressDirectory(file, out, basedir);
		} else {
			compressFile(file, out, basedir);
		}
	}

	/** 压缩一个目录 */
	private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/** 压缩一个文件 */
	private static void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fileInputStream);
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}

			bis.close();

			fileInputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			File newFile = new File(newPath);
			File parentFile = newFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory() && !temp.getName().endsWith(".svn")) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}

	}
}
