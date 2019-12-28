package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.Old;
import com.tongji.oldman.entity.OldExample;
import com.tongji.oldman.response.OldListResponse;
import com.tongji.oldman.service.OldService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetOldsController {

    private final OldService oldService;

    public GetOldsController(OldService oldService) {
        this.oldService = oldService;
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
}
