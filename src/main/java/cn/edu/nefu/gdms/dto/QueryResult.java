package cn.edu.nefu.gdms.dto;

import lombok.Data;

/**
 * Created by dingyunxiang on 16/12/15.
 */
@Data
public class QueryResult {
    private int limit;
    private int offset;
    private Object data;
}
