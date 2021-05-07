package com.bydyx.file;

import com.bydyx.file.model.FileWriteDto;
import com.bydyx.string.PrintUtil;
import com.bydyx.string.StringUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * @author 风清扬
 * @date 2021/3/18 09:49
 */
public class FileWriteUtil {

	public static void write(FileWriteDto dto) {
		write(dto.getFilePath(), dto.getFileName(), dto.getLineList());
	}

	public static void write(String filePath, String fileName, List<String> lineList) {
		String path = StringUtil.concatPath(filePath, fileName);
		try (
			BufferedWriter writer = new BufferedWriter(new FileWriter(path))
		) {
			for (final String line : lineList) {
				writer.write(line);
				writer.newLine();
			}
		} catch (Exception e) {
			PrintUtil.print("写入文件失败 文件目录:{} 错误日志:{}", path, e);
		}
	}
}