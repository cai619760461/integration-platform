package com.incaier.integration.platform.constant;

public interface BYConstant {

    /**
     * sql限制1条
     */
    String SQL_LIMIT_1 = "LIMIT 1";

    /**
     * excel 单表最大条数
     */
    int EXCEL_SHEET_MAX = 500000;

    /**
     * excel 单次任务插入条数
     */
    int EXCEL_INSERT_NUM = EXCEL_SHEET_MAX / 2;

    /**
     * excel 单表前缀
     */
    String EXCEL_SHEET_PRFIX = "sheet-";

    Integer INT_FALSE = 0;
    Integer INT_TRUE = 1;
}
