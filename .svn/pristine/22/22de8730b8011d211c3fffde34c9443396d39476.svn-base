package com.hhh.fund.flowdemo.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.waterwx.service.SwjUcenterRoleService;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.waterwx.util.JsSDKConfig;
import com.hhh.fund.waterwx.util.MatrixToImageWriter;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.webmodel.SysUcenterRoleBean;
import com.hhh.fund.web.model.LoginLogBean;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.ShiroUtils;
import com.hhh.weixin.util.QiyehaoConst;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {
	@Autowired
	private UserCenterService uconterService;
	
	@Autowired
	private SwjUcenterRoleService swjUcenterRoleService;
	
	@Autowired
	private SwjUserService userService;
	
	/**
	 * 跳转到登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		return "admin_login";
	}
	
	@RequestMapping(value = "/getEweima", method = RequestMethod.GET)
	public void getEweima(HttpServletRequest request,HttpServletResponse response) {
		try {
			String codeText = request.getParameter("CodeText");
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

	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public String adminLogin(UserBean user, HttpSession session, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		token.setRememberMe(user.isRememberMe());
		try {
			subject.login(token);
			user = ShiroUtils.getUser();
			SysUcenterRoleBean roleBean = swjUcenterRoleService.findRoleByUserId(user.getUserId());
			SwjUserBean userBean = userService.findByUserId(user.getUserId());
			session.setAttribute(QiyehaoConst.Weixin_UserId, user.getUserId());
			session.setAttribute(StringUtil.CUSTOMER_ID, user.getCustomerId());
			session.setAttribute("area", userBean.getArea());
			session.setAttribute("name", userBean.getName());
			session.setAttribute("roleName", roleBean.getName());
			session.setAttribute("roleid", roleBean.getId());
			session.setAttribute("loginSuccess", "1");
			LoginLogBean log = new LoginLogBean();
			log.setCustomerId(user.getCustomerId());
			log.setIp(StringUtil.getIpAddress(request));
			log.setLoginName(user.getUsername());
			log.setLoginTime(StringUtil.dateFormat(new Date()));
			uconterService.saveLoginLog(log);
			return "redirect:/admin/main";
		}catch (AuthenticationException e) {
			e.printStackTrace();
			token.clear();
			return "redirect:/admin/login";
		}
	}
	
	
	@RequestMapping(value = "/keyToLogin", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public String keyToLogin(String key, HttpSession session, HttpServletRequest request) {
		if(key == null || key.equals("")){
			return "redirect:/admin/login";
		}
		System.out.println(key+"key");
		UserBean user = uconterService.findUserByKeyId(key);
		if(user == null){//未找见匹配key的用户
			return "redirect:/admin/login?code=1";
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getKeyId()+";;;key");
		token.setRememberMe(user.isRememberMe()) ;
		try {
			subject.login(token);
			user = ShiroUtils.getUser();
			session.setAttribute(StringUtil.CUSTOMER_ID, user.getCustomerId());
			LoginLogBean log = new LoginLogBean();
			log.setCustomerId(user.getCustomerId());
			log.setIp(StringUtil.getIpAddress(request));
			log.setLoginName(user.getUsername());
			log.setLoginTime(StringUtil.dateFormat(new Date()));
			uconterService.saveLoginLog(log);
			return "redirect:/admin/main";
		}catch (AuthenticationException e) {
			e.printStackTrace();
			token.clear();
			return "redirect:/admin/login";
		}
	}
}
