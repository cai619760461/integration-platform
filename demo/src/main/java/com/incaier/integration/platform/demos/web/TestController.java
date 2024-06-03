package com.incaier.integration.platform.demos.web;

import com.incaier.integration.platform.annotation.DecryptMethod;
import com.incaier.integration.platform.annotation.EncryptMethod;
import com.incaier.integration.platform.entity.Info;
import com.incaier.integration.platform.mapper.PersonnelMapper;
import com.incaier.integration.platform.service.PersonnelService;
import com.incaier.integration.platform.util.AesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 测试控制器
 *
 * @author caiweijie
 * @date 2024/05/24
 */
@RestController
@RequestMapping("/demo/test")
public class TestController {

    @Autowired
    private PersonnelService personnelService;

    @PostMapping("/saveInfo")
    @DecryptMethod
    public HashMap<String,String> saveInfo(@RequestBody Info info) {
        HashMap<String,String> result = new HashMap<>();
        String name = info.getName();
        System.out.println(name);
        String sex= info.getSex();
        System.out.println(sex);
        result.put("flag","1");
        result.put("msg","操作成功");
        return result;
    }

    @GetMapping("/getInfo")
    @EncryptMethod
    public Info getInfo(HttpServletRequest request, HttpServletResponse response) {
        String cookieName = "custom_global_session_id";
        String encodeString = "UTF-8";


        Info info = new Info();
        info.setName("caiweijie");
        info.setSex("boy");
        return info;
    }

    @Autowired
    private PersonnelMapper personnelMapper;

    @PutMapping("/updatePassword")
    public Boolean updatePassword() {
        try {
            String decrypt = AesUtil.decrypt("Jv3O6cyu6lzYUHvvONpD3Q==");
        }catch (Exception e) {

        }
        return true;
    }

    @PutMapping("/resetPasswordToSM4")
    public Boolean resetPasswordToSM4() throws InterruptedException {
        personnelService.resetPasswordToSm4();
        return true;
    }

}
