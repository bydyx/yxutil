package com.bydyx.file.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 风清扬
 * @date 2021/3/18 09:50
 */
@Data
public class FileWriteDto {
	String fileName;
	String filePath;
	List<String> lineList;

	public FileWriteDto() {
		this.lineList = new ArrayList<>();
	}

	public FileWriteDto addLine(String line) {
		lineList.add(line);
		return this;
	}
}