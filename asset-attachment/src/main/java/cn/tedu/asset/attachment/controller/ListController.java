package cn.tedu.asset.attachment.controller;

import cn.tedu.asset.attachment.pojo.dto.ListDTO;
import cn.tedu.asset.attachment.pojo.entity.AList;
import cn.tedu.asset.attachment.pojo.vo.ListAdminVO;
import cn.tedu.asset.attachment.pojo.vo.ListVO;
import cn.tedu.asset.attachment.service.ListService;
import cn.tedu.asset.commom.response.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j

@RestController
@RequestMapping("/v1/asset-attachment")
public class ListController {

    @Autowired
    ListService service;


    @PostMapping("create")
    public JsonResult insert(@RequestBody ListDTO listDTO){
        service.insert(listDTO);
        return JsonResult.ok();

    }

    @PostMapping("{id}/delete")
    public JsonResult deleteById(@PathVariable Long id){
        service.deleteById(id);
        return JsonResult.ok();
    }

    @GetMapping("admin")
    public JsonResult listForAdmin(){
        List<ListAdminVO> list = service.listForAdmin();
        return JsonResult.ok(list);
    }

    @PostMapping("/search")
    public JsonResult search(@RequestBody  ListVO listVO){
        log.info("listVO",listVO);
        List<List> list = service.search2(listVO);
        return JsonResult.ok(list);
    }


}

