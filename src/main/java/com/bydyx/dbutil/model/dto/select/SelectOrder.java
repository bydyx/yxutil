package com.bydyx.dbutil.model.dto.select;

import com.bydyx.dbutil.model.enums.SelectOrderEnum;
import lombok.Data;

/**
 * @author 风清扬
 * @date 2020/11/27 15:22
 */
@Data
public class SelectOrder {
	String columnName;
	SelectOrderEnum order;
}