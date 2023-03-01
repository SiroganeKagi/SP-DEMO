package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActorMapper {
    void addActor(String actorName);

    Integer selectNewActorId();

    void deleteActor(Integer actorId);

    void updateActor(Integer actorId,String actorName);
}
