package com.incaier.integration.platform.constant;

/**
 * 错误代码常数
 *
 * @author caiweijie
 * @date 2024/06/12
 */
public interface ErrorCodeConstant {

    /**
     * 未找到 Token
     */
    int TOKEN_NOT_FOUND = 1100502;

    /**
     * Token 无效
     */
    int TOKEN_INVALID = 1100503;

    int COMMON_ERROR = 1005000;

    int COMMON_READ_FILE_ERROR = 1005001;

    int COMMON_THIRD_PARTY_ERROR = 1005002;

    int COMMON_INVALID_PARAMETER = 1005003;

}
