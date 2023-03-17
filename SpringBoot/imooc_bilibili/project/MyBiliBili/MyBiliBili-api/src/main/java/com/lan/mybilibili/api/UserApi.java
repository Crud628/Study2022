package com.lan.mybilibili.api;

import com.lan.mybilibili.api.support.UserSupport;
import com.lan.mybilibili.domain.JsonResponse;
import com.lan.mybilibili.domain.User;
import com.lan.mybilibili.domain.UserInfo;
import com.lan.mybilibili.service.UserService;
import com.lan.mybilibili.service.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApi {

    /**
     *
     */
    @Autowired
    private UserService userService;

    @Autowired
    private UserSupport userSupport;

    @GetMapping("/users")
    public JsonResponse<User> getUserInfo(){
        Long userId = userSupport.getCurrentUserId();
        User user = userService.getUserInfo(userId);
        return new JsonResponse<>(user);
    }

    /**
     *
     * @return
     */
    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey() {
        String pk = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);
    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/users")
    public JsonResponse<String> addUser(@RequestBody User user) {
        userService.addUser(user);

        return JsonResponse.success();
    }

    /**
     *
     * @param user 用户
     * @return 结果
     * @throws Exception
     */
    @PostMapping("/user-tokens")
    public JsonResponse<String> login(@RequestBody User user) throws Exception {
        String token = userService.login(user);
        return new JsonResponse<>(token);
    }

    @PutMapping("/user-infos")
    public JsonResponse<String> updateUserInfos(@RequestBody UserInfo userInfo){
        Long userId = userSupport.getCurrentUserId();
        userInfo.setUserId(userId);
        userService.updateUserInfos(userInfo);
        return JsonResponse.success();
    }
}
