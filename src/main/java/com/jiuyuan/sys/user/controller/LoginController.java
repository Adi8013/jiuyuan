package com.jiuyuan.sys.user.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiuyuan.utils.CaptchaUtil;
import com.jiuyuan.utils.SystemConstant;



@Controller
public class LoginController {
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class); 
	@RequestMapping("/login")
	public String login() {
		return "sys/login/login";
	}
	/**
	 * 验证码生成控制器
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/defaultCaptcha")
	public void defaultCaptcha(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		try {
			HttpSession session = request.getSession();
			CaptchaUtil tool = new CaptchaUtil();
			StringBuffer code = new StringBuffer();
			BufferedImage image = tool.genRandomCodeImage(code);
			session.removeAttribute(SystemConstant.KEY_CAPTCHA);
			session.setAttribute(SystemConstant.KEY_CAPTCHA, code.toString());
			LOGGER.debug("验证码生成");
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception e) {
			LOGGER.debug("验证码生成异常");
			e.printStackTrace();
		}
	}
}
