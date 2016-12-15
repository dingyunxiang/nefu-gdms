package cn.edu.nefu.gdms.dto;

import lombok.Data;

/**
 * Created by dingyunxiang on 16/12/15.
 */

/**
 * 分页的数据结构
 */
@Data
public class QueryResult {
    private int limit;
    private int offset;
    private int total;
    private Object data;
}
