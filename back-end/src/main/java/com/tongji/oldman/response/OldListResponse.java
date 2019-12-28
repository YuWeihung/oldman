package com.tongji.oldman.response;

import com.tongji.oldman.entity.Old;

import java.util.List;

public class OldListResponse {
    private List<Old> olds;

    public OldListResponse(List<Old> olds) {
        this.olds = olds;
    }

    public List<Old> getOlds() {
        return olds;
    }

    public void setOlds(List<Old> olds) {
        this.olds = olds;
    }
}
