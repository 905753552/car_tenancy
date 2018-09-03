package com.dev.main.shiro.util;

import java.util.List;

public class Pagination<V> {
    //总记录数
    private int total;
    //每页记录数
    private int limit;
    //总页数
    private int totalPage;
    //当前页数
    private int currPage;
    //列表数据
    private List<V> rows;

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param currPage    当前页数
     */
    public Pagination(List<V> list, int totalCount, int pageSize, int currPage) {
        this.rows = list;
        this.total = totalCount;
        this.limit = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<V> rows) {
        this.rows = rows;
    }
}
