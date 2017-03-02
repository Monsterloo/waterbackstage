package com.hhh.fund.waterwx.controller;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.hhh.fund.waterwx.entity.OutfallPolluateResource;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.SwjOutfallPolluateResourceService;
import com.hhh.fund.waterwx.service.SwjUcenterRoleService;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.waterwx.util.Constants;
import com.hhh.fund.waterwx.util.IOUtils;
import com.hhh.fund.waterwx.webmodel.OutfallPolluateResourceBean;
import com.hhh.fund.waterwx.webmodel.PollutantSourceBean;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.webmodel.SysUcenterRoleBean;
import com.hhh.fund.web.model.DataTablesResult;
import com.hhh.security.util.ShiroUtils;
import com.hhh.weixin.util.QiyehaoConst;

@Controller
@RequestMapping("/outFall")
public class OutFallController {
	
	
	@Autowired
	private SwjUserService userService;
	
	@Autowired
	private SwjUcenterRoleService swjUcenterRoleService;
	
	@Autowired
	private SwjOutfallPolluateResourceService outfallService;
	
	@Autowired
	private ResponsibilityService responseService;
	
	private Map<String,String>dictMap = new HashMap<String,String>();
	
	@RequestMapping("/toOutFallList")
	public String toOutFallList(){
		return "waterwx/outfall_list";
	}
	
	private int getPage(int start, int pageSize) {
		return (int)Math.floor((double)start/pageSize);
	}
	
	@RequestMapping(value = "/ScanLogin", method = RequestMethod.GET)
	public String ScanLogin(HttpSession session, HttpServletRequest request) {
		String UKey=request.getParameter("UKey");
		SwjUserBean user=(SwjUserBean)request.getServletContext().getAttribute("UKey-"+UKey);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginname(),user.getPassword());
		//token.setRememberMe(user.isRememberMe());
		subject.login(token);
		//user = ShiroUtils.getUser();
		
		
		try {
			SysUcenterRoleBean roleBean = swjUcenterRoleService.findRoleByUserId(user.getUserid());
			session.setAttribute(QiyehaoConst.Weixin_UserId, user.getUserid());
			session.setAttribute("area", user.getArea());
			session.setAttribute("name", user.getName());
			session.setAttribute("roleName", roleBean.getName());
			session.setAttribute("roleid", roleBean.getId());
			session.setAttribute("loginSuccess", "1");
			request.getServletContext().removeAttribute("IsScan-"+UKey);
			request.getServletContext().removeAttribute("UKey-"+UKey);
			return "redirect:/admin/main";
		}catch (AuthenticationException e) {
			e.printStackTrace();
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping(value = "/CheckIsScan", method = RequestMethod.GET)
	public void CheckIsScan(HttpServletResponse response,HttpServletRequest request) {
		String res="0";
		String UKey=request.getParameter("UKey");
		String IsScan=(String)request.getServletContext().getAttribute("IsScan-"+UKey);
		if("0".equals(IsScan)){
			SwjUserBean user=(SwjUserBean)request.getServletContext().getAttribute("UKey-"+UKey);
			res="HasScan"+user.getName()+"["+user.getLoginname()+"]";
		}if ("1".equals(IsScan)){
			res="1";
		}
		
		outPrintResult(response,res);
	}

	
	@RequestMapping(value = "/ConfirmLogin", method = RequestMethod.GET)
	public void ConfirmLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String res="";
		response.setHeader("Access-Control-Allow-Origin", "*");
		String userId=request.getParameter("UserId");
		String Ukey=request.getParameter("UKey");
		String IsScan=request.getParameter("IsConfirm");
		request.getServletContext().setAttribute("IsScan-"+Ukey, IsScan);
		SwjUserBean userBean=userService.findByUserId(userId);
		System.out.println("UKey-"+Ukey+":"+userBean);
		
		request.getServletContext().setAttribute("UKey-"+Ukey, userBean);
		res=IsScan;
		outPrintResult(response,res);
	}
	
	
	/**
	 * 输出文本
	 * @param response
	 * @param returnStr
	 */
	private void outPrintResult(HttpServletResponse response,String returnStr) {
		try {
			response.setContentType("text/plain; charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().print(returnStr);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeResponseWriter(response);
		}
	}
	
	protected SwjUserBean FetchUserInfoByCode(String code) throws Exception
    {
		String corpId ="wx7d9176de98109ba1";
		String secret ="f6QHGzTnqt5oQijGAS0WLf2JVawjtwgK5yykcL2BoT1I4dBwf_67oGb8twVYxX-3";
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpId+"&corpsecret="+secret;
		String result = getResponseFromUrl(url);
		System.out.println("access_token------"+result);
		System.out.println("code------"+code);
		JSONObject object = JSONObject.fromObject(result);
		AccessTokenJson token = new AccessTokenJson();
		token.setAccess_token(object.getString("access_token"));
		token.setExpires_in(object.getInt("expires_in"));

		url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+token.getAccess_token()+"&code="+code+"&agenti=0";
        
        result =getResponseFromUrl(url);
        System.out.println("userInfo------"+result);
        if(result.toLowerCase().indexOf("userid")>=0){
	        object = JSONObject.fromObject(result);
	        String  userId =  object.getString("UserId");
	        SwjUserBean userBean = userService.findByUserId(userId);
	        return userBean;
        }
        else {
        	return new SwjUserBean();
        }
    }
	
	private String getResponseFromUrl(String url) throws Exception {
		String returnStr = "";
		URL urlRequest = new URL(url);
        URLConnection conn = urlRequest.openConnection();
        conn.addRequestProperty("Content-Type", "text/html;charset=UTF-8");
        InputStream inputStream = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String tmp = "";
    	while((tmp=br.readLine())!=null){
        	returnStr += tmp;
        }
		inputStream.close();
		
		
		return returnStr;
	}

	class AccessTokenJson
    {
        private String access_token;
        private int expires_in;
		public String getAccess_token() {
			return access_token;
		}
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
		public int getExpires_in() {
			return expires_in;
		}
		public void setExpires_in(int expires_in) {
			this.expires_in = expires_in;
		}
    }
	
	@RequestMapping(value = "/getwxEweima", method = RequestMethod.GET)
	public void getEweima(HttpServletRequest request,HttpServletResponse response) {
		try {
			String ukey = request.getParameter("CodeText");
			String codeText="";
			if(StringUtils.isBlank( ukey)==false){
				String domain="http://wx1.ccqm.cn/water_qy/weixinqy/authorize";
				codeText="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7d9176de98109ba1&redirect_uri="+URLEncoder.encode(domain,"utf-8")+"&response_type=code&scope=SCOPE&state=/water_qy/weixinRiverMaster/WeixinConfirm"+URLEncoder.encode("?UKey="+ukey,"utf-8")+"#wechat_redirect";
			}
			String height = request.getParameter("Height");
			String width = request.getParameter("Width");
	        String qrcodeFormat = "png";
	        HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(codeText, BarcodeFormat.QR_CODE, Integer.parseInt(width), Integer.parseInt(height), hints);
	        BufferedImage image = new BufferedImage(Integer.parseInt(width), Integer.parseInt(height), BufferedImage.TYPE_INT_RGB);

			ImageIO.write(toBufferedImage(bitMatrix), qrcodeFormat, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static BufferedImage toBufferedImage(BitMatrix matrix) {  
        int width = matrix.getWidth();  
        int height = matrix.getHeight();  
        int BLACK = 0xFF000000;  
        int WHITE = 0xFFFFFFFF;
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);  
            }  
        }  
        return image;  
    }  
	
	/**
	 * 修改河道，返回数据
	 * */
	@RequestMapping(value="/findById")
	public @ResponseBody OutfallPolluateResourceBean findById(String id){
		OutfallPolluateResourceBean bean = outfallService.findById(id);
		bean.setTherectificationresponsibilityunitStr(dictMap.get(bean.getTherectificationresponsibilityunit()));
		return bean;
	}	
	
	/**
	 * 查询所有的河道
	 * */
	@RequestMapping(value="/searchAllOutFallList")
	public @ResponseBody DataTablesResult<OutfallPolluateResourceBean> searchAllResponse(@RequestParam(value = "start",defaultValue="0") int start,
			@RequestParam(value = "length",defaultValue="10") int pageSize,
			OutfallPolluateResourceBean bean){
		int page = getPage(start, pageSize);
		PageRequest pr = new PageRequest(page, pageSize);
		int t= pr.getPageNumber();
		SmsPage<OutfallPolluateResourceBean> records = outfallService.findAll(pr,bean);
		
		List<SysUcenterDict> unitList=responseService.findDict("responsibilityUnit");
		changeToMap(unitList,dictMap);
		changeDictCodeToNameToShow(records,dictMap);
		
		DataTablesResult<OutfallPolluateResourceBean> dtr = new DataTablesResult<OutfallPolluateResourceBean>();
		dtr.setData(records.getContent());
		dtr.setRecordsFiltered(new Long(records.getTotalElements()).intValue());
		dtr.setRecordsTotal(records.getTotalPages());
		return dtr;
	}
	
	private void changeToMap(List<SysUcenterDict> list, Map<String, String> map) {
		for(SysUcenterDict dict:list){
			map.put(dict.getCode(), dict.getName());
		}
	}
	
	private void changeDictCodeToNameToShow(SmsPage<OutfallPolluateResourceBean> records, Map<String, String> dictMap2) {
		List<OutfallPolluateResourceBean> data = records.getContent();
		for(OutfallPolluateResourceBean bean:data){
			bean.setTherectificationresponsibilityunitStr(dictMap2.get(bean.getTherectificationresponsibilityunit()));
		}
	}
	
	@RequestMapping("/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response){
		try {
		    
			ServletContext sc = request.getServletContext();
			String realPath = sc.getRealPath("/template")+File.separator+"outfall-pollutant-template.xls";
			System.out.println(realPath);
			InputStream in = new FileInputStream(realPath);
			
			response.addHeader("Content-Disposition", "attachment;filename=" + new String("outfall-pollutant-template.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Length", "" + in.available());
			OutputStream os = response.getOutputStream();
			byte[]buff = new byte[1024];
			int i = 0;
			while((i=in.read(buff))!=-1){
				os.write(buff);// 输出文件
				os.flush();
			}
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
