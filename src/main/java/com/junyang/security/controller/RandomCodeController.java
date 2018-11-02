package com.junyang.security.controller;

import com.junyang.common.utils.ImageUtil;
import com.junyang.common.utils.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Controller
@RequestMapping(value = "/open")
public class RandomCodeController {

	public static final String SESSION_VALID_CODE = "SESSION_VALID_CODE";

	private static final Logger logger = LoggerFactory.getLogger(RandomCodeController.class);


	/**
	 * 登录验证码
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/randomCode")
	public void randomCode(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

		resp.addHeader("Cache-Control", "no-cache");
		resp.addHeader("Cache-Control", "no-store");
		resp.addHeader("Cache-Control", "must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", -1);
		resp.setCharacterEncoding("utf-8");

		String validCode = RandomUtil.randomCode(4);
		logger.info("验证码:{}",validCode);

		// 在内存中创建图象
		BufferedImage image = ImageUtil.createValidCodeImage(validCode);

		req.getSession().setAttribute(SESSION_VALID_CODE,validCode);

		// 输出图象到页面
		ImageIO.write(image, "JPEG", resp.getOutputStream());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();

	}

}
