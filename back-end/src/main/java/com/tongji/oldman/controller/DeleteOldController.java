package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.OldExample;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.OldService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteOldController {

    private final OldService oldService;

    public DeleteOldController(OldService oldService) {
        this.oldService = oldService;
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
}
