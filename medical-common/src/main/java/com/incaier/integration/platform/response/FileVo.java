package com.incaier.integration.platform.response;

import lombok.Data;


/**
 * 文件 vo
 *
 * @author caiweijie
 * @date 2024/06/14
 */
@Data
public class FileVo {

    /**
     * 文件类别 doc,pdf,xls等
     */
    private String fileType;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;
}
