package cn.tedu.asset.attachment.controller;

import cn.tedu.asset.commom.response.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1")
public class UploadAttachmentController {
    @Value("${file-path}")
    private String dirPath;

    @PostMapping("/upload")
    public JsonResult upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffix;
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        File dirFile = new File(dirPath + datePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        file.transferTo(new File(dirPath + datePath + fileName));
        log.info("dirPath: {}", dirPath+datePath);


        return JsonResult.ok(datePath + fileName);

    }

    @PostMapping("remove")
    public JsonResult remove(String imgUrl){
        new File(dirPath+imgUrl).delete();
        return JsonResult.ok();
    }
}
