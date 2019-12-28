package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.Health;
import com.tongji.oldman.entity.HealthExample;
import com.tongji.oldman.entity.Old;
import com.tongji.oldman.entity.OldExample;
import com.tongji.oldman.response.HealthListResponse;
import com.tongji.oldman.response.OldListResponse;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.HealthService;
import com.tongji.oldman.service.OldService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/olds")
public class OldsController {

    private final OldService oldService;
    private final HealthService healthService;

    public OldsController(OldService oldService, HealthService healthService) {
        this.oldService = oldService;
        this.healthService = healthService;
    }

    @PostMapping("/getolds")
    public String getOlds(String type, Integer oid, String name, String address) {
        List<Old> olds;
        OldExample oldExample = new OldExample();
        OldExample.Criteria criteria = oldExample.createCriteria();
        if (type.equals("oid")) {
            criteria.andOidEqualTo(oid);
        }
        else if (type.equals("name")) {
            criteria.andNameLike("%"+name+"%");
        }
        else if (type.equals("address")) {
            criteria.andAddressLike("%"+address+"%");
        }
        olds = oldService.getOlds(oldExample);
        OldListResponse oldListResponse = new OldListResponse(olds);
        return JSON.toJSONString(oldListResponse);
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

    @PostMapping("/deleteold")
    public String deleteOld(Integer oid) {
        OldExample oldExample = new OldExample();
        OldExample.Criteria criteria = oldExample.createCriteria();
        criteria.andOidEqualTo(oid);
        int delete = oldService.deleteOld(oldExample);
        int success = 0;
        if (delete == 1) {
            success = 1;
        }
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
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

    @PostMapping("/puthealthinfo")
    public String putHealthInfo(Integer oid, Double weight, String bloodpressure, Integer heartbeat,
                                String leftsight, String rightsight, String recognize, String mental) {
        Health health = new Health();
        health.setOid(oid);
        health.setWeight(weight);
        health.setBloodpressure(bloodpressure);
        health.setHeartbeat(heartbeat);
        health.setLeftsight(leftsight);
        health.setRightsight(rightsight);
        health.setRecognize(recognize);
        health.setMental(mental);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        health.setDate(date);
        int success = 0;
        int insert = healthService.newHealth(health);
        if (insert == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("gethealthinfo")
    public String getHealthInfo(Integer oid) {
        HealthExample healthExample = new HealthExample();
        HealthExample.Criteria criteria = healthExample.createCriteria();
        criteria.andOidEqualTo(oid);
        List<Health> health = healthService.getHealth(healthExample);
        HealthListResponse healthListResponse = new HealthListResponse(health);
        return JSON.toJSONString(healthListResponse);
    }

    @PostMapping("/putresponsibility")
    public String putResponsibility(Integer oid, Integer responsibility) {
        Old old = new Old();
        old.setOid(oid);
        old.setResponsibility(responsibility);
        int success = 0;
        int update = oldService.updateOld(old);
        if (update == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }
}
