package com.incaier.integration.platform.request;

import java.io.Serializable;

/**
 * page 实体
 *
 * @author caiweijie
 * @date 2024/06/04
 */
public class PageDto implements Serializable {

    private static final long serialVersionUID = -5352253976323749335L;

    private Integer pageNum;

    private Integer pageSize;

    public PageDto() {
    }

    public PageDto(Integer pageNum, Integer pageSize) {
    }

    public Integer getPageNum() {
        if (this.pageNum == null || this.pageNum <= 0) {
            this.pageNum = 1;
        }
        return this.pageNum;
    }

    public Integer getPageSize() {
        if (this.pageSize == null || this.pageSize <= 0) {
            this.pageSize = 10;
        }
        return this.pageSize;
    }
}