package com.jiuyuan.sys.user.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jiuyuan.sys.common.ResponseMsg;
import com.jiuyuan.sys.user.domain.User;
import com.jiuyuan.sys.user.service.UserService;
import com.jiuyuan.utils.CaptchaUtil;
import com.jiuyuan.utils.SystemConstant;
import com.jiuyuan.utils.security.EncryptUtil;



@Controller
public class LoginController {
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class); 
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	public String login() {
		return "sys/login/login";
	}
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("product")
	@ResponseBody
	public String product() {
		
		return "";
	}
	
	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(SystemConstant.SYS_USER);
		return "redirect:login";
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
			LOGGER.debug("验证码生成"+ session.getAttribute(SystemConstant.KEY_CAPTCHA));
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception e) {
			LOGGER.debug("验证码生成异常");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/doCheckLogin",method=RequestMethod.POST)
	@ResponseBody
	public ResponseMsg doCheckLogin(@RequestParam(name="userAccount") String userAccount, @RequestParam(name="password") String password,
									@RequestParam(name="captcha") String captcha, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (!session.getAttribute(SystemConstant.KEY_CAPTCHA).equals(captcha.trim())) {
			return ResponseMsg.failed("验证码错误");
		}
		User user = userService.loginUser(userAccount);
		if (user == null) {
			return ResponseMsg.failed("用户名不存在！");
		} else {
			// 解密
			String deEncrypt = "";
			try {
				deEncrypt = EncryptUtil.decrypt(user.getPassword());
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
				return ResponseMsg.error();
			}
			if (deEncrypt.equals(EncryptUtil.getSalt(userAccount, password))) {
				session.setAttribute(SystemConstant.SYS_USER, user);
				return ResponseMsg.success(SystemConstant.SUCCESS);
			} else {
				return ResponseMsg.failed("密码错误！");
			}
		}
	}
	
	
	
}
