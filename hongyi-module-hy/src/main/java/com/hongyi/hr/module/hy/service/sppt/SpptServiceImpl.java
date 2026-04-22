package com.hongyi.hr.module.hy.service.sppt;// ... existing code ...
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.codec.Base64Decoder;


/**
 * 我的日记 Service 实现类
 *
 * @author zxl
 */
@Service
@Validated
public class SpptServiceImpl implements SpptService {


    @Override
    public String jiemi(String pwd) {
//        UzMi03MTc=MD
        if (StrUtil.isNotEmpty(pwd) && pwd.length() > 2) {
            String moved = pwd.substring(pwd.length() - 2) + pwd.substring(0, pwd.length() - 2);
            return Base64Decoder.decodeStr(moved);
        }
        return "999";
    }
}
