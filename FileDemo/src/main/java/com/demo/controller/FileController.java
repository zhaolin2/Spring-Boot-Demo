package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author zl
 */
@Slf4j
@Controller
public class FileController {

    @GetMapping("/file")
    public ResponseEntity<byte[]> getFile() throws IOException {

        String fileName = "中文.txt";


//        URL resourceUrl = FileController.class.getClassLoader().getResource("test.txt");
        URL resourceUrl = this.getClass().getResource("/");
        assert resourceUrl != null;
//        File file = new File(resourceUrl.getPath() + fileName);
//        String fileName = new String(file.getName().getBytes(StandardCharsets.UTF_8),
//                StandardCharsets.ISO_8859_1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 设置文件名称

        headers.setContentDispositionFormData("attachment", "test.txt");
//        headers.setContentDispositionFormData("attachment", fileName);
        // 返回数据

        return new ResponseEntity<byte[]>(
                "test".getBytes(StandardCharsets.UTF_8), headers,
                HttpStatus.CREATED);
    }

    @GetMapping("/excelDownload")
    public ResponseEntity<byte[]> getExcelFile() throws IOException {

        //表头数据
        String[] header = {"ID", "姓名", "性别", "年龄", "地址", "分数"};

        //数据内容
        String[] student1 = {"1", "小红", "女", "23", "成都青羊区", "96"};
        String[] student2 = {"2", "小强", "男", "26", "成都金牛区", "91"};
        String[] student3 = {"3", "小明", "男", "28", "成都武侯区", "90"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("学生表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }

        //模拟遍历结果集，把内容加入表格
        //模拟遍历第一个学生
        HSSFRow row1 = sheet.createRow(1);
        for (int i = 0; i < student1.length; i++) {
            HSSFCell cell = row1.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student1[i]);
            cell.setCellValue(text);
        }

        //模拟遍历第二个学生
        HSSFRow row2 = sheet.createRow(2);
        for (int i = 0; i < student2.length; i++) {
            HSSFCell cell = row2.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student2[i]);
            cell.setCellValue(text);
        }

        //模拟遍历第三个学生
        HSSFRow row3 = sheet.createRow(3);
        for (int i = 0; i < student3.length; i++) {
            HSSFCell cell = row3.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student3[i]);
            cell.setCellValue(text);
        }

        String fileName = new String("中央.xls".getBytes(StandardCharsets.UTF_8),
                StandardCharsets.ISO_8859_1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 设置文件名称
        headers.setContentDispositionFormData("attachment", fileName);

        //写到缓冲区中  直接转为byte数组  识别不出来
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        return new ResponseEntity<byte[]>(
                out.toByteArray(), headers,
                HttpStatus.CREATED);

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
//        response.setContentType("application/octet-stream");
//        //这后面可以设置导出Excel的名称，此例中名为student.xls
//        response.setHeader("Content-disposition", "attachment;filename=student.xls");
//        //刷新缓冲
//        response.flushBuffer();
//        //workbook将Excel写入到response的输出流中，供页面下载

//        workbook.write(response.getOutputStream());
    }

    @GetMapping("/getZipByFile")
    public ResponseEntity<byte[]> getZipByFile() throws IOException, ZipException {

        String password = "123";
        String fileName = "中文.txt";
        String resourcePath = this.getClass().getResource("/").getPath();


//        File zipFile = new File("");
//        ZipFile zipFile1 = new ZipFile("");
//        new ZipOutputStream();
//        ZipEntry zipEntry = new ZipEntry("");
//        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));

        //设置压缩文件参数
        ZipParameters parameters = new ZipParameters();
        //设置压缩方法
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

        //设置压缩级别
        //DEFLATE_LEVEL_FASTEST     - Lowest compression level but higher speed of compression
        //DEFLATE_LEVEL_FAST        - Low compression level but higher speed of compression
        //DEFLATE_LEVEL_NORMAL  - Optimal balance between compression level/speed
        //DEFLATE_LEVEL_MAXIMUM     - High compression level with a compromise of speed
        //DEFLATE_LEVEL_ULTRA       - Highest compression level but low speed
        /**
         * {@link Zip4jConstants}
         */
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        //设置压缩文件加密
        parameters.setEncryptFiles(true);
        //设置加密方法
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        //设置aes加密强度
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        //设置密码
        parameters.setPassword(password);


        ArrayList<File> addFiles = new ArrayList<>();
        File addFile = new File(resourcePath + "test.txt");
        File addFile1 = new File(resourcePath + "中文.txt");
        addFiles.add(addFile1);
        addFiles.add(addFile);


        File zipFile = new File(resourcePath + "test.zip");
        FileOutputStream fileOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        fileOutputStream = new FileOutputStream(zipFile);
        zipOutputStream = new ZipOutputStream(fileOutputStream);

        //添加文件到压缩文件
        if (zipFile.exists()) {
            zipFile.delete();
        }


        for (File tempFile : addFiles) {
            zipOutputStream.putNextEntry(tempFile, parameters);

            if (tempFile.isDirectory()) {
                zipOutputStream.closeEntry();
                continue;
            }

            InputStream inputStream = new FileInputStream(tempFile);
            byte[] readBuff = new byte[1024 * 10];
            int readLen = -1;
            while ((readLen = inputStream.read(readBuff)) != -1) {
                zipOutputStream.write(readBuff, 0, readLen);
            }
            zipOutputStream.closeEntry();
            inputStream.close();
        }
        zipOutputStream.finish();
        zipOutputStream.close();

        byte[] bytes = new byte[1024 * 10];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        FileInputStream fileInputStream = new FileInputStream(zipFile);

        int readNum = 0;
        while ((readNum = fileInputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, readNum);
        }

//        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(,);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 设置文件名称

        headers.setContentDispositionFormData("attachment", zipFile.getName());
//        headers.setContentDispositionFormData("attachment", fileName);
        // 返回数据

        return new ResponseEntity<byte[]>(
                byteArrayOutputStream.toByteArray(), headers,
                HttpStatus.CREATED);

    }

    /**
     * 其实不需要使用文件来进行操作
     *
     * @return
     * @throws IOException
     * @throws ZipException
     */
    @GetMapping("/getZipFile")
    public ResponseEntity<byte[]> getZipFile() throws IOException, ZipException {

        String password = "123";


//        File zipFile = new File("");
//        ZipFile zipFile1 = new ZipFile("");
//        new ZipOutputStream();
//        ZipEntry zipEntry = new ZipEntry("");
//        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));

        //设置压缩文件参数
        ZipParameters parameters = new ZipParameters();
        //设置压缩方法
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

        //设置压缩级别
        //DEFLATE_LEVEL_FASTEST     - Lowest compression level but higher speed of compression
        //DEFLATE_LEVEL_FAST        - Low compression level but higher speed of compression
        //DEFLATE_LEVEL_NORMAL  - Optimal balance between compression level/speed
        //DEFLATE_LEVEL_MAXIMUM     - High compression level with a compromise of speed
        //DEFLATE_LEVEL_ULTRA       - Highest compression level but low speed
        /**
         * {@link Zip4jConstants}
         */
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        //设置压缩文件加密
        parameters.setEncryptFiles(true);
        //设置加密方法
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        //设置aes加密强度
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        //设置密码
        parameters.setPassword(password);


//        FileOutputStream fileOutputStream = null;
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArray);
//        zipOutputStream.decrementCompressedFileSize();


        for (int i = 0; i < 2; i++) {
            zipOutputStream.putNextEntry(new File(""), parameters);

            InputStream inputStream = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8));
            byte[] readBuff = new byte[1024 * 10];
            int readLen = -1;
            while ((readLen = inputStream.read(readBuff)) != -1) {
                zipOutputStream.write(readBuff, 0, readLen);
            }
            zipOutputStream.closeEntry();
            inputStream.close();
        }
        zipOutputStream.finish();
        zipOutputStream.close();

//        zipOutputStream.

        byte[] bytes = new byte[1024 * 10];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

//        int readNum = 0;
//        FileInputStream fileInputStream=null;
//        while ((readNum = fileInputStream.read(bytes)) != -1) {
//            byteArrayOutputStream.write(bytes, 0, readNum);
//        }

//        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(,);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 设置文件名称

        headers.setContentDispositionFormData("attachment", "ZipFile.zip");
//        headers.setContentDispositionFormData("attachment", fileName);
        // 返回数据

        return new ResponseEntity<byte[]>(
                byteArray.toByteArray(), headers,
                HttpStatus.CREATED);

    }

    @GetMapping("/getFileByRange")
    public ResponseEntity<byte[]> downloadByRange(HttpServletRequest request) throws FileNotFoundException {
        // Get your file stream from wherever.
        String name = "";
        log.info("name=" + name);
        String fullPath = ResourceUtils.getURL("classpath:").getPath() + "/test.txt";
        log.info("下载路径:" + fullPath);
        File downloadFile = new File(fullPath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // response.setContentLength((int) downloadFile.length());

        // set headers for the response
       headers.setContentDispositionFormData("attachment", downloadFile.getName());
        // 解析断点续传相关信息
        headers.set("Accept-Ranges", "bytes");

        long downloadSize = downloadFile.length();
        long fromPos = 0, toPos = 0;

        // Copy the stream to the response's output stream.
        RandomAccessFile in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new RandomAccessFile(downloadFile, "rw");
            // 设置下载起始位置
            // 缓冲区大小
            int bufLen = (int) (downloadSize < 2048 ? downloadSize : 2048);
            byte[] buffer = new byte[bufLen];
            int num;
            int count = 0; // 当前写到客户端的大小
            out = new ByteArrayOutputStream();
            while ((num = in.read(buffer)) != -1) {
                out.write(buffer, 0, num);
                count += num;
                //处理最后一段，计算不满缓冲区的大小
                if (downloadSize - count < bufLen) {
                    bufLen = (int) (downloadSize - count);
                    if (bufLen == 0) {
                        break;
                    }
                    buffer = new byte[bufLen];
                }
            }

        } catch (IOException e) {
            log.info("数据被暂停或中断。");
//            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.info("数据被暂停或中断。");
//                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.info("数据被暂停或中断。");
//                    e.printStackTrace();
                }
            }
        }

        //如果是没有就直接返回 否则就续传
        if (request.getHeader("Range") == null) {
            headers.set("Content-Length", downloadSize + "");

            return new ResponseEntity<byte[]>(out.toByteArray(), headers,
                    //206
                    HttpStatus.CREATED);
        } else {
            // 若客户端传来Range，说明之前下载了一部分，设置206状态(SC_PARTIAL_CONTENT)
            String range = request.getHeader("Range");
            String bytes = range.replaceAll("bytes=", "");
            String[] ary = bytes.split("-");
            fromPos = Long.parseLong(ary[0]);
            if (ary.length == 2) {
                toPos = Long.parseLong(ary[1]);
            }
            int size;
            if (toPos > fromPos) {
                size = (int) (toPos - fromPos);
            } else {
                size = (int) (downloadSize - fromPos);
            }
            headers.set("Content-Length", size + "");
            downloadSize = size;
            assert out != null;
            return new ResponseEntity<byte[]>(out.toByteArray(), headers,
                    //206
                    HttpStatus.PARTIAL_CONTENT);
        }




    }

}
