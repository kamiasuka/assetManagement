package cn.tedu.asset.manage.Util;

import java.util.UUID;

public class AssetCode {
    public String buildCode() {
        // 使用UUID生成唯一标识符，并去掉其中的“-”符号
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 在生成的UUID基础上截取前10位作为资产编码
        String assetCode = "AS" + uuid.substring(0, 10);
        return assetCode;
    }

}
