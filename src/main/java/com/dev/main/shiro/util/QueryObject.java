package com.dev.main.shiro.util;

import java.util.HashMap;

/**
 * 查询对象
 */
public class QueryObject extends HashMap<String, Object> {

    private final static String PAGE_NUMBER = "pageNumber";
    private final static String PAGE_SIZE = "pageSize";
    private final static String SORT = "sort";
    private final static String ORDER = "order";

    public QueryObject() {
        super();
        super.put(PAGE_NUMBER, 1);
        super.put(PAGE_SIZE, 10);
    }

    /**
     * @param pageNumber 页码
     * @param pageSize 页大小
     */
    public QueryObject(Integer pageNumber, Integer pageSize) {
        super();
        super.put(PAGE_NUMBER, pageNumber);
        super.put(PAGE_SIZE, pageSize);
    }

    /**
     *
     * @param pageNumber 页码
     * @param pageSize 页大小
     * @param sort 排序列
     */
    public QueryObject(Integer pageNumber, Integer pageSize, String sort) {
        super();
        super.put(PAGE_NUMBER, pageNumber);
        super.put(PAGE_SIZE, pageSize);
        super.put(SORT, sort);
    }

    /**
     * @param pageNumber 页码
     * @param pageSize 页大小
     * @param sort 排序列
     * @param order 排序 升序或降序（不区分大小写），取值范围{"asc", "desc"}
     */
    public QueryObject(Integer pageNumber, Integer pageSize, String sort, String order) {
        super();
        super.put(PAGE_NUMBER, pageNumber);
        super.put(PAGE_SIZE, pageSize);
        super.put(SORT, sort);
        // oder值只能为asc|desc，否则不设置
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            super.put(ORDER, order.toLowerCase());
        }
    }

    @Override
    public QueryObject put(String key, Object value) {
        super.put(key, value);
        return this;
    }


}
