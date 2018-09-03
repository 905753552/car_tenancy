package com.dev.main.kaptcha.service.impl;

import com.dev.main.common.exception.DataNotFoundException;
import com.dev.main.common.exception.IllegalParameterException;
import com.dev.main.common.util.CommonUtil;
import com.dev.main.common.util.CookieUtils;
import com.dev.main.common.util.ResultMap;
import com.dev.main.kaptcha.service.ICaptchaService;
import com.dev.main.kaptcha.util.CaptchaConstant;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaServiceImpl implements ICaptchaService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DefaultKaptcha captchaProducer;


    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setCaptchaProducer(DefaultKaptcha captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    @Override
    public void generate(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        Integer cookieExp = 60 * 5; // 过期时间 3 分钟
        String uuid = CommonUtil.createUUID(); // 作为Redis中的验证码的key，同时存储在Cookie中用于
        redisTemplate.opsForValue().set(CaptchaConstant.CAPTCHA_CODE_PREFIX + uuid, capText, cookieExp, TimeUnit.SECONDS);
        CookieUtils.addCookie(response, CaptchaConstant.COOKIE_NAME, CaptchaConstant.CAPTCHA_CODE_PREFIX + uuid, cookieExp, CaptchaConstant.COOKIE_PATH);

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @Override
    public boolean verify(String uuidInCookie, String codeInput, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(uuidInCookie)) {
            throw new DataNotFoundException("验证码已失效，请重新获取");
        }
        if (StringUtils.isBlank(codeInput)) {
            throw new IllegalParameterException("请输入验证码");
        }
        String codeInCache = (String) redisTemplate.opsForValue().get(uuidInCookie);

        CookieUtils.clearCookie(request, response, CaptchaConstant.COOKIE_NAME, CaptchaConstant.COOKIE_PATH);

        redisTemplate.delete(uuidInCookie);
        if (codeInCache == null) {
            throw new DataNotFoundException("验证码已失效，请重新获取");
        }
        if (codeInCache.equalsIgnoreCase(codeInput)) {
            return true;
        }
        return false;
    }
}
