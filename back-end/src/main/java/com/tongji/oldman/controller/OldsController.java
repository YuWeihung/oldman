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
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/olds")
public class OldsController {

    private final OldService oldService;
    private final HealthService healthService;

    public OldsController(OldService oldService, HealthService healthService) {
        this.oldService = oldService;
        this.healthService = healthService;
    }

    private static class Req {
        private String type;
        private Integer oid;
        private String name;
        private String address;
        private String image;
        private Date birthday;
        private Integer gender;
        private String identity;
        private String nation;
        private String education;
        private String phone;
        private String single;
        private String finance;
        private String insurance;
        private String dependant;
        private String dependantphone;
        private String dependantindentity;
        private Integer responsibility;
        private Double weight;
        private String bloodpressure;
        private Integer heartbeat;
        private String leftsight;
        private String rightsight;
        private String recognize;
        private String mental;

        public void setOid(Integer oid) {
            this.oid = oid;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public void setBloodpressure(String bloodpressure) {
            this.bloodpressure = bloodpressure;
        }

        public void setDependant(String dependant) {
            this.dependant = dependant;
        }

        public void setDependantindentity(String dependantindentity) {
            this.dependantindentity = dependantindentity;
        }

        public void setDependantphone(String dependantphone) {
            this.dependantphone = dependantphone;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public void setFinance(String finance) {
            this.finance = finance;
        }

        public void setHeartbeat(Integer heartbeat) {
            this.heartbeat = heartbeat;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public void setLeftsight(String leftsight) {
            this.leftsight = leftsight;
        }

        public void setMental(String mental) {
            this.mental = mental;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setRecognize(String recognize) {
            this.recognize = recognize;
        }

        public void setResponsibility(Integer responsibility) {
            this.responsibility = responsibility;
        }

        public void setRightsight(String rightsight) {
            this.rightsight = rightsight;
        }

        public void setSingle(String single) {
            this.single = single;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }
    }

    @PostMapping("/getolds")
    public String getOlds(@RequestBody Req req) {
        List<Old> olds;
        OldExample oldExample = new OldExample();
        OldExample.Criteria criteria = oldExample.createCriteria();
        if (req.type.equals("oid")) {
            criteria.andOidEqualTo(req.oid);
        }
        else if (req.type.equals("name")) {
            criteria.andNameLike("%"+req.name+"%");
        }
        else if (req.type.equals("address")) {
            criteria.andAddressLike("%"+req.address+"%");
        }
        olds = oldService.getOlds(oldExample);
        OldListResponse oldListResponse = new OldListResponse(olds);
        return JSON.toJSONString(oldListResponse);
    }

    @PostMapping("/createold")
    public String createOld(@RequestBody Req req) {
        Old old = new Old();
        old.setName(req.name);
        old.setImage(req.image);
        old.setBirthday(req.birthday);
        old.setGender(req.gender);
        old.setIdentity(req.identity);
        old.setAddress(req.address);
        old.setNation(req.nation);
        old.setEducation(req.education);
        old.setPhone(req.phone);
        old.setSingle(req.single);
        old.setFinance(req.finance);
        old.setInsurance(req.insurance);
        old.setDependant(req.dependant);
        old.setDependantphone(req.dependantphone);
        old.setDependantidentity(req.dependantindentity);
        old.setResponsibility(req.responsibility);
        int success = 0;
        int newOld = oldService.newOld(old);
        success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/deleteold")
    public String deleteOld(@RequestBody Req req) {
        OldExample oldExample = new OldExample();
        OldExample.Criteria criteria = oldExample.createCriteria();
        criteria.andOidEqualTo(req.oid);
        int delete = oldService.deleteOld(oldExample);
        int success = 0;
        if (delete == 1) {
            success = 1;
        }
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/changeoldinfo")
    public String changeOldInfo(@RequestBody Req req) {
        Old old = new Old();
//        old.setResponsibility(req.responsibility);
        old.setDependantidentity(req.dependantindentity);
        old.setDependant(req.dependant);
        old.setDependantphone(req.dependantphone);
        old.setInsurance(req.insurance);
        old.setFinance(req.finance);
        old.setSingle(req.single);
        old.setPhone(req.phone);
        old.setEducation(req.education);
        old.setNation(req.nation);
        old.setAddress(req.address);
        old.setName(req.name);
        old.setOid(req.oid);
        old.setImage(req.image);
        old.setBirthday(req.birthday);
        old.setGender(req.gender);
        old.setIdentity(req.identity);
        int success = 0;
        int update = oldService.updateOld(old);
        if (update == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/puthealthinfo")
    public String putHealthInfo(@RequestBody Req req) {
        Health health = new Health();
        health.setOid(req.oid);
        health.setWeight(req.weight);
        health.setBloodpressure(req.bloodpressure);
        health.setHeartbeat(req.heartbeat);
        health.setLeftsight(req.leftsight);
        health.setRightsight(req.rightsight);
        health.setRecognize(req.recognize);
        health.setMental(req.mental);
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

    @PostMapping("/gethealthinfo")
    public String getHealthInfo(@RequestBody Req req) {
        HealthExample healthExample = new HealthExample();
        HealthExample.Criteria criteria = healthExample.createCriteria();
        criteria.andOidEqualTo(req.oid);
        List<Health> health = healthService.getHealth(healthExample);
        HealthListResponse healthListResponse = new HealthListResponse(health);
        return JSON.toJSONString(healthListResponse);
    }

    @PostMapping("/putresponsibility")
    public String putResponsibility(@RequestBody Req req) {
        Old old = new Old();
        old.setOid(req.oid);
        old.setResponsibility(req.responsibility);
        int success = 0;
        int update = oldService.updateOld(old);
        if (update == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }
}
