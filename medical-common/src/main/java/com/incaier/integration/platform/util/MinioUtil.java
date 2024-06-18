//package com.incaier.integration.platform.util;
//
//import com.baomidou.mybatisplus.core.toolkit.Constants;
//import io.minio.*;
//import org.apache.commons.compress.utils.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * Minio 工具栏
// *
// * @author caiweijie
// * @date 2024/06/18
// */
//@Component
//public class MinioUtil {
//
//    private final static Logger logger = LoggerFactory.getLogger(CookieUtils.class);
//
//    @Value("${minio.bucketName}")
//    private String bucketName;
//
//    @Autowired
//    private MinioClient minioClient;
//
//    /**
//     * description: 判断bucket是否存在，不存在则创建
//     *
//     * @param name 名称
//     */
//    public void existBucket(String name) {
//        try {
//            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
//            if (!exists) {
//                minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }
//
//    /**
//     * description: 上传文件
//     *
//     * @param multipartFile 多部件文件
//     * @return {@link List}<{@link String}>
//     */
//    public List<String> upload(MultipartFile[] multipartFile) {
//        List<String> names = new ArrayList<>(multipartFile.length);
//        for (MultipartFile file : multipartFile) {
//            String fileName = file.getOriginalFilename();
//            String[] split = fileName.split("\\.");
//            if (split.length > 1) {
//                fileName = split[0] + "_" + System.currentTimeMillis() + "." + split[1];
//            } else {
//                fileName = fileName + System.currentTimeMillis();
//            }
//            InputStream in = null;
//            try {
//                in = file.getInputStream();
//                minioClient.putObject(PutObjectArgs.builder()
//                        .bucket(bucketName)
//                        .object(fileName)
//                        .stream(in, in.available(), -1)
//                        .contentType(file.getContentType())
//                        .build()
//                );
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            } finally {
//                if (in != null) {
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        logger.error(e.getMessage(), e);
//                    }
//                }
//            }
//            names.add(fileName);
//        }
//        return names;
//    }
//
//    /**
//     * description: 下载文件
//     *
//     * @param fileName 文件名
//     * @return {@link ResponseEntity}<{@link byte[]}>
//     */
//    public ResponseEntity<byte[]> download(String fileName) {
//        ResponseEntity<byte[]> responseEntity = null;
//        InputStream in = null;
//        ByteArrayOutputStream out = null;
//        try {
//            in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
//            out = new ByteArrayOutputStream();
//            IOUtils.copy(in, out);
//            //封装返回值
//            byte[] bytes = out.toByteArray();
//            HttpHeaders headers = new HttpHeaders();
//            try {
//                headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, Constants.UTF_8));
//            } catch (UnsupportedEncodingException e) {
//                logger.error(e.getMessage(), e);
//            }
//            headers.setContentLength(bytes.length);
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.setAccessControlExposeHeaders(Collections.singletonList("*"));
//            responseEntity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            try {
//                if (in != null) {
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        logger.error(e.getMessage(), e);
//                    }
//                }
//                if (out != null) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
//        }
//        return responseEntity;
//    }
//}