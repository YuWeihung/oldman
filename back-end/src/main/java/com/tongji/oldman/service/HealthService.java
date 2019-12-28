package com.tongji.oldman.service;

import com.tongji.oldman.entity.Health;
import com.tongji.oldman.entity.HealthExample;
import com.tongji.oldman.mapper.HealthMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthService {
    private final HealthMapper healthMapper;

    public HealthService(HealthMapper healthMapper) {
        this.healthMapper = healthMapper;
    }

    public List<Health> getHealth(HealthExample healthExample) {
        return healthMapper.selectByExample(healthExample);
    }

    public long countHealth(HealthExample healthExample) {
        return healthMapper.countByExample(healthExample);
    }

    public int newHealth(Health health) {
        return healthMapper.insertSelective(health);
    }

    public int deleteHealth(HealthExample healthExample) {
        return healthMapper.deleteByExample(healthExample);
    }

    public int updateHealth(Health health) {
        return healthMapper.updateByPrimaryKeySelective(health);
    }
}
