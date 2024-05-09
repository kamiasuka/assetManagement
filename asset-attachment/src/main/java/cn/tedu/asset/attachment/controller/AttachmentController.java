package cn.tedu.asset.attachment.controller;

import cn.tedu.asset.attachment.pojo.dto.AssetDTO;
import cn.tedu.asset.attachment.pojo.dto.AttachmentPO;
import cn.tedu.asset.attachment.pojo.vo.ListAdminVO;
import cn.tedu.asset.attachment.pojo.vo.ListVO;
import cn.tedu.asset.attachment.pojo.vo.PageData;
import cn.tedu.asset.attachment.service.AttachmentService;
import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.commom.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j

@RestController
@RequestMapping("/v1/asset-attachment")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("listAll/{page}")
    public JsonResult listAll(@PathVariable Integer page){
        log.info("查询全部附件数据");
        Integer pageNum = page == null ? 1 : page;
        PageData<AttachmentPO> pageData = attachmentService.listAll(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("check/{code}")
    public JsonResult checkAsset(@PathVariable String code) {
        log.info("检验资产是否存在"+code);
        String assetCode = attachmentService.checkAsset(code);
        if (assetCode != null) {
            return JsonResult.ok(assetCode);
        } else {
            return new JsonResult(StatusCode.OPERATION_FAILED, "未找到对应资产");
        }
    }

    @PostMapping("create")
    public JsonResult insert(AssetDTO assetDTO){
        log.info("保存附件数据："+ assetDTO);
        attachmentService.insert(assetDTO);
        return JsonResult.ok();
    }

    @PostMapping("{code}/delete")
    public JsonResult deleteById(@PathVariable String code){
        log.info("删除附件:"+code);
        attachmentService.deleteById(code);
        return JsonResult.ok();
    }



    @PostMapping("/search")
    public JsonResult search(@RequestBody  ListVO listVO){
        List<List> list = attachmentService.search2(listVO);
        return JsonResult.ok(list);
    }

}




