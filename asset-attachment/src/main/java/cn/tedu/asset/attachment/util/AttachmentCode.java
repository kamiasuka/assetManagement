package cn.tedu.asset.attachment.util;

import java.util.UUID;

public class AttachmentCode {
    public static String buildCode() {
        // 使用UUID生成唯一标识符，并去掉其中的“-”符号
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 在生成的UUID基础上截取前10位作为资产编码
        String assetCode = "FJ" + uuid.substring(0, 10);
        return assetCode;
    }

}
