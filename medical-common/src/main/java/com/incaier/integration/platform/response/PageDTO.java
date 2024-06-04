
package com.incaier.integration.platform.response;

import java.io.Serializable;

/**
 * @Author Wangm
 */
public class PageDTO implements Serializable {
    private static final long serialVersionUID = -5352253976323749335L;

    private Integer pageNum;
    private Integer pageSize;

    public PageDTO() {
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
    public void setPageNum(Integer page) {
        this.pageNum = page;
    }

    public void setPageSize(Integer limit) {
        this.pageSize = limit;
    }
}
