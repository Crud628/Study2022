package com.lan.mybilibili.api;

import com.lan.mybilibili.domain.JsonResponse;
import com.lan.mybilibili.domain.User;
import com.lan.mybilibili.service.UserService;
import com.lan.mybilibili.service.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey() {
        String pk = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);
    }

    @PostMapping("/users")
    public JsonResponse<String> addUser(@RequestBody User user) {
        userService.addUser(user);

        return new JsonResponse<>(null);
    }
}
