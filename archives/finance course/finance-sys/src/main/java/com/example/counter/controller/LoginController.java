package com.example.counter.controller;

import com.example.counter.bean.res.Account;
import com.example.counter.bean.res.CaptchaRes;
import com.example.counter.bean.res.CounterRes;
import com.example.counter.cache.CacheType;
import com.example.counter.cache.RedisStringCache;
import com.example.counter.service.AccountService;
import com.example.counter.thirdParty.uuid.GudyUuid;
import com.example.counter.util.Captcha;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.counter.bean.res.CounterRes.FAIL;

@RestController
@RequestMapping("/login")
@Log4j2
public class LoginController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/captcha")
    public CounterRes captcha() throws Exception {
        // 1.生成验证码 120 40 4个字符 噪点+线条
        Captcha captcha = new Captcha(120, 40, 4, 10);

        // 2.将验证码<ID,验证码数值>放入缓存
        String uuid = String.valueOf(GudyUuid.getInstance().getUUID());

        try {
            RedisStringCache.cache(uuid, captcha.getCode(), CacheType.CAPTCHA);
        } catch (Exception e) {
            log.info(e);
        }

        // 3.使用base64编码图片，并返回给前台
        // uuid,base64
        CaptchaRes res = new CaptchaRes(uuid, captcha.getBase64ByteStr());
        return new CounterRes(res);
    }

    @RequestMapping(value = "/userLogin")
    public CounterRes login(@RequestParam long uid,
                            @RequestParam String password,
                            @RequestParam String captcha,
                            @RequestParam String captchaId) throws Exception {
        Account account = accountService.login(uid, password, captcha, captchaId);
        if (account== null){
            return new CounterRes(FAIL,
                    "用户名密码/验证码错误，登录失败",null);
        }else {
            return new CounterRes(account);
        }
    }

}
