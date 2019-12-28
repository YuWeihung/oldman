package com.tongji.oldman.service;

import com.tongji.oldman.entity.Old;
import com.tongji.oldman.entity.OldExample;
import com.tongji.oldman.mapper.OldMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OldService {
    private final OldMapper oldMapper;

    public OldService(OldMapper oldMapper) {
        this.oldMapper = oldMapper;
    }

    public List<Old> getOlds(OldExample oldExample) {
        return oldMapper.selectByExample(oldExample);
    }

    public int newOld(Old old) {
        return oldMapper.insertSelective(old);
    }

    public long countOlds(OldExample oldExample) {
        return oldMapper.countByExample(oldExample);
    }

    public int deleteOld(OldExample oldExample) {
        return oldMapper.deleteByExample(oldExample);
    }

    public int updateOld(Old old) {
        return oldMapper.updateByPrimaryKeySelective(old);
    }
}
