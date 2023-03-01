package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProgramActorListMapper {
    void addPal(Integer programId,Integer actorId);

    void deletePal(Integer programId);

    Integer selectId(Integer programId);


}
