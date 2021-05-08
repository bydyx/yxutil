package com.bydyx.file.test;

import com.bydyx.file.FileWriteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 风清扬
 * @date 2021/3/18 10:26
 */
public class FileWriteUtilTest {
	public static void main(String[] args) {
		String filePath = "/Users/fqy/dev/project/java/backstage/src/test/java/lazyCode/com/fqy/model/entity/lc/UsersEnt.java";
		String fileName = "xx.txt";
		List<String> list = new ArrayList<String>() {{
			add("httt");
			add("httt");
			add("httt");
		}};
		FileWriteUtil.write(filePath, fileName, list);
	}
}