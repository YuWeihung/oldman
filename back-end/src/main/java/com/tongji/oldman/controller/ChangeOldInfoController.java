package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.Old;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.OldService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ChangeOldInfoController {

    private final OldService oldService;

    public ChangeOldInfoController(OldService oldService) {
        this.oldService = oldService;
    }

    @PostMapping("changeoldinfo")
    public String changeOldInfo(Integer oid, String name, String image, Date birthday, Integer gender, String identity,
                                String address, String nation, String education, String phone,
                                String single, String finance, String insurance, String dependant,
                                String dependantphone, String dependantindentity, Integer responsibility) {
        Old old = new Old();
        old.setResponsibility(responsibility);
        old.setDependantidentity(dependantindentity);
        old.setDependant(dependant);
        old.setDependantphone(dependantphone);
        old.setInsurance(insurance);
        old.setFinance(finance);
        old.setSingle(single);
        old.setPhone(phone);
        old.setEducation(education);
        old.setNation(nation);
        old.setAddress(address);
        old.setName(name);
        old.setOid(oid);
        old.setImage(image);
        old.setBirthday(birthday);
        old.setGender(gender);
        old.setIdentity(identity);
        int success = 0;
        int update = oldService.updateOld(old);
        if (update == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }
}
