package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.Old;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.OldService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CreateOldController {
    private final OldService oldService;

    public CreateOldController(OldService oldService) {
        this.oldService = oldService;
    }

    @PostMapping("/createold")
    public String createOld(String name, String image, Date birthday, Integer gender, String identity,
                            String address, String nation, String education, String phone,
                            String single, String finance, String insurance, String dependant,
                            String dependantphone, String dependantindentity, Integer responsibility) {
        Old old = new Old();
        old.setName(name);
        old.setImage(image);
        old.setBirthday(birthday);
        old.setGender(gender);
        old.setIdentity(identity);
        old.setAddress(address);
        old.setNation(nation);
        old.setEducation(education);
        old.setPhone(phone);
        old.setSingle(single);
        old.setFinance(finance);
        old.setInsurance(insurance);
        old.setDependant(dependant);
        old.setDependantphone(dependantphone);
        old.setDependantidentity(dependantindentity);
        old.setResponsibility(responsibility);
        int success = 0;
        int newOld = oldService.newOld(old);
        success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }
}
