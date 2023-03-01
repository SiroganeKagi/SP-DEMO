package com.example.demo.service.impl;

import com.example.demo.dao.CommonResult;
import com.example.demo.dao.param.AddParam;
import com.example.demo.dao.param.UpdateParam;
import com.example.demo.dao.result.ProgramResult;
import com.example.demo.mapper.ActorMapper;
import com.example.demo.mapper.ProgramMapper;
import com.example.demo.mapper.ProgramActorListMapper;
import com.example.demo.mapper.TypeMapper;
import com.example.demo.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {


    @Resource
    ProgramMapper programMapper;
    @Resource
    TypeMapper typeMapper;
    @Resource
    ActorMapper actorMapper;
    @Resource
    ProgramActorListMapper programActorListMapper;


    @Override
    public CommonResult getProgramList(Integer pageNum){
        int pageSize = (pageNum-1)*5;
        List<ProgramResult> programResults = programMapper.selectAll(pageSize);
        return CommonResult.success(programResults);
    }

    @Override
    public CommonResult search(Integer typeId, Integer num, String name) {
        List<ProgramResult> programResults = programMapper.search(typeId,num,name);
        return CommonResult.success(programResults);
    }
    @Override
    public CommonResult addProgram(AddParam param) {
        String name = param.getName();
        Integer typeId = param.getTypeId();
        String actorName = param.getActorName();
        String point = param.getPoint();
        programMapper.add(typeId, name, point);
        actorMapper.addActor(actorName);
        Integer programId = programMapper.selectNewProgramId();
        Integer actorId = actorMapper.selectNewActorId();
        programActorListMapper.addPal(programId,actorId);
        return CommonResult.success("添加成功");
    }

    @Override
    public CommonResult deleteProgram(Integer programId) {
        Integer actorId = programActorListMapper.selectId(programId);
        actorMapper.deleteActor(actorId);
        programActorListMapper.deletePal(programId);
        programMapper.delete(programId);
        return CommonResult.success("删除成功");
    }

    @Override
    public CommonResult updateProgram(UpdateParam param) {
        Integer programId = param.getProgramId();
        String name = param.getName();
        Integer typeId = param.getTypeId();
        String actorName = param.getActorName();
        String point = param.getPoint();
        programMapper.update(programId,typeId, name, point);
        Integer actorId = programActorListMapper.selectId(programId);
        actorMapper.updateActor(actorId,actorName);
        return CommonResult.success("编辑成功");
    }
}
