package com.cdgs.worktime.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SideWorkPutTimeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8886815346590834119L;
	
	private Long id;
	private String startTime;
	private String endTime;
	private Boolean workAnyWhere;
	private String remark;
	
}