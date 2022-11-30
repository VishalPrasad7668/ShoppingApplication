package com.niit.UserProductService.proxy;

import com.niit.UserProductService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "authentication-service",url = "http://authentication-service:8089")
public interface UserProxy {
    @PostMapping("/userservice/register")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
