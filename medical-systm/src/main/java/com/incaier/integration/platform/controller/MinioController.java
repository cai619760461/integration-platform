package com.incaier.integration.platform.controller;

import com.incaier.integration.platform.config.MinioPropertiesConfig;
import com.incaier.integration.platform.constant.ErrorCodeConstant;
import com.incaier.integration.platform.exception.CommonBusinessException;
import com.incaier.integration.platform.response.FileEntity;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.util.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传
 *
 * @author caiweijie
 * @date 2024/06/18
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class MinioController {

    @Resource
    private MinioUtils minioUtils;

    @Resource
    private MinioPropertiesConfig minioPropertiesConfig;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link Result}<{@link String}> objectName
     */
    @PostMapping("/upload")
    public Result<FileEntity> upload(@RequestParam("file") MultipartFile file) {
        FileEntity fileVo = new FileEntity();
        if (ObjectUtils.isEmpty(file)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_INVALID_PARAMETER, "文件错误");
        }
        // 文件名处理
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            fileName = UUID.randomUUID().toString().replace("-", "");
        }
        String objectName = new SimpleDateFormat("yyyy/MM/dd/").format(new Date())
                + fileName.substring(0, fileName.indexOf('.'))
                + "-" + UUID.randomUUID().toString().replaceAll("-", "")
                + fileName.substring(fileName.lastIndexOf("."));
        String contentType = file.getContentType();
        try {
            // 如果桶不存在,则创建桶
            minioUtils.createBucket(minioPropertiesConfig.getBucketName());
            minioUtils.uploadFile(minioPropertiesConfig.getBucketName(), file, objectName, contentType);
            fileVo.setFileType(contentType);
            fileVo.setFileName(fileName);
            fileVo.setFilePath(minioUtils.getFileUrl(objectName));
            // 获取元数据
            // StatObjectResponse statObjectResponse = minioUtils.statObject(objectName);
            return Result.success(fileVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(ErrorCodeConstant.COMMON_ERROR, "文件上传失败，" + e.getMessage()) ;
        }
    }
}