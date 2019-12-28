package com.tongji.oldman.response;

import com.tongji.oldman.entity.Health;

import java.util.List;

public class HealthListResponse {
    private List<Health> healths;

    public HealthListResponse(List<Health> health) {
        this.healths = health;
    }

    public void setHealths(List<Health> healths) {
        this.healths = healths;
    }

    public List<Health> getHealths() {
        return healths;
    }
}
