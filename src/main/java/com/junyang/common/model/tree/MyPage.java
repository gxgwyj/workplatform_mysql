package com.junyang.common.model.tree;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 类: MyPage <br>
 * 描述: 分页参数<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月22日 11:08
 */
public class MyPage<T> implements Serializable {

    private static final long serialVersionUID = -7351559593814784883L;

    private int pageNum = 1;//页码
    private int pageSize = 20;//每页多少数量
    private long total;//总条数
    private int pages;//总页数
    private List<T> records; //记录

    public MyPage() {
    }

    public MyPage(Page page) {
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.records = page.getResult();
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
