package com.example.demo.mapper;

import com.example.demo.dao.result.ProgramResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProgramMapper {
    List<ProgramResult> selectAll(Integer pageSize);

    List<ProgramResult> search(Integer typeId, Integer num, String name);

    void add(Integer typeId, String name, String point);

    void update(Integer programId,Integer typeId, String name, String point);

    Integer selectNewProgramId();

    void delete(Integer programId);

}
