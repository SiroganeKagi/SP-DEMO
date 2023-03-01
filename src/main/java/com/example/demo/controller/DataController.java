package com.example.demo.controller;

import com.example.demo.dao.CommonResult;
import com.example.demo.dao.param.AddParam;
import com.example.demo.dao.param.UpdateParam;
import com.example.demo.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@MapperScan("com/example/demo/mapper")
@RestController
public class DataController {

    final DataService dataService;
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/get/programList")
    public CommonResult getProgramList(@RequestParam Integer pageNum) {
        return dataService.getProgramList(pageNum);
    }

    @GetMapping("/search/program")
    public CommonResult search(
            @RequestParam(required = false,defaultValue = "0") Integer typeId,
            @RequestParam(required = false,defaultValue = "0") Integer num,
            @RequestParam(required = false,defaultValue = "") String name)
    {   if(typeId==0&&num==0&&name.equals("")){
            return dataService.getProgramList(1);
        }else {
            return dataService.search(typeId, num, name);
        }
    }

    @PostMapping("/add/program")
    public CommonResult addProgram(@RequestBody AddParam param) {
        return dataService.addProgram(param);
    }

    @PostMapping("/delete/program")
    public CommonResult deleteProgram(@RequestParam Integer programId) {
        return dataService.deleteProgram(programId);
    }

    @PostMapping("/update/program")
    public CommonResult updateProgram(@RequestBody UpdateParam param) {
        return dataService.updateProgram(param);
    }
}
