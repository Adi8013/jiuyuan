package com.jiuyuan.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class ExportExcel<T> {
    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名数组
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param paramMap
     *            包含4个其他属性，
     *            receiver 收货单位
     *            saleNo  销售单
     *            address 地址
     *            date 日期
     */

    public void exportExcel(int rownum, String title, String[] headers, Collection<T> dataset, OutputStream out, Map<String, String> paramMap) {
        // 声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 16);
        // 生成一个样式
        HSSFCellStyle style = createComStyle(workbook, HSSFCellStyle.ALIGN_CENTER);
        // 设置样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 生成字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前样式
        style.setFont(font);

        // 生成并设置另一个样式
        HSSFCellStyle style2 = createComStyle(workbook, HSSFCellStyle.ALIGN_CENTER);
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置， 详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("九元古建砖瓦销售单"));
        // 设置注释作者， 当鼠标移动到单元格上可以在状态栏看到该内容
        comment.setAuthor("Adi Zheng");

        //1.1创建合并单元格对象(标题)
        HSSFRow rowTitle = sheet.createRow(0);
        rowTitle.setHeightInPoints(60);
        // 生成表头样式
        HSSFCellStyle styleTitle = workbook.createCellStyle();
//        styleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成字体
        HSSFFont fontTitle = workbook.createFont();
        fontTitle.setFontHeightInPoints((short) 28);
        fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleTitle.setFont(fontTitle);
        HSSFCell cell0 = rowTitle.createCell((short) 0);
        cell0.setCellStyle(styleTitle);
        cell0.setCellValue("九元古建砖瓦销售单");
        Region region = new Region();
        region.setColumnFrom((short) 0);
        region.setColumnTo((short) (headers.length - 1));
        sheet.addMergedRegion(region);

        // 第二行单元格合并
        HSSFRow row1 = sheet.createRow(1);
        row1.setHeightInPoints(37);
        HSSFCellStyle styleSecond = workbook.createCellStyle();
//        styleSecond.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleSecond.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleSecond.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成字体
        HSSFFont fontSecond = workbook.createFont();
        fontSecond.setFontHeightInPoints((short) 15);
        fontSecond.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleSecond.setFont(fontSecond);

        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellStyle(styleSecond);
        cell1.setCellValue(paramMap.get("receiver"));
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 5));

        HSSFCell cell2 = row1.createCell((short) (headers.length - 2));
        cell2.setCellStyle(styleSecond);
        cell2.setCellValue(paramMap.get("saleNo"));
        sheet.addMergedRegion(new Region(1, (short) 6, 1, (short) 7));

        // 第三行单元格合并
        HSSFRow row2 = sheet.createRow(2);
        row2.setHeightInPoints(37);
        // 生成字体
        HSSFFont fontThird = workbook.createFont();
        fontThird.setFontHeightInPoints((short) 15);
        styleSecond.setFont(fontThird);

        HSSFCell cell2_1 = row2.createCell((short) 0);
        cell2_1.setCellStyle(styleSecond);
        cell2_1.setCellValue(paramMap.get("address"));
        sheet.addMergedRegion(new Region(2, (short) 0, 2, (short) 5));

        HSSFCell cell2_2 = row2.createCell((short) (headers.length - 2));
        cell2_2.setCellStyle(styleSecond);
        cell2_2.setCellValue(paramMap.get("date"));
        sheet.addMergedRegion(new Region(2, (short) 6, 2, (short) 7));

        // 产生表格标题行, 行数从rownum开始
        HSSFRow row = sheet.createRow(rownum);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据， 产生数据行
        Iterator<T> it = dataset.iterator();
        int index = rownum;
        style2.setWrapText(true);
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            row.setHeightInPoints(16);
            T t = (T)it.next();
            // 利用反射， 根据JavaBean属性的先后顺序，动态调用getXXX（）方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (short i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);

                Field field = fields[i];
                String fieldName = field.getName();
                if (fieldName.equals("price")) { // 金额数据居右
                    // 生成并设置另一个样式
                    HSSFCellStyle style_price = createComStyle(workbook, HSSFCellStyle.ALIGN_RIGHT);
                    style_price.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
                    style_price.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    style_price.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                    cell.setCellStyle(style_price);
                } else {
                    cell.setCellStyle(style2);
                }
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    if (value != null) {
                        cell.setCellValue(value.toString());// 为当前列赋值
                    }
                    if (index == 4) {
                        sheet.addMergedRegion(new Region(4, (short)(headers.length - 1), dataset.size() + 1, (short)(headers.length - 1)));
                    } else if (index == dataset.size() + 2) {
                        sheet.addMergedRegion(new Region(dataset.size() + 2, (short)(headers.length - 1), dataset.size() + 3, (short)(headers.length - 1)));

                    }
                   /*if (value instanceof byte[]) {
                        // 有图片时， 设置行高为60px；
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其他类型数据都当做字符串处理
                        textValue = value.toString();
                    }
                    // 如果不是图片数据， 就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 数字都当做double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richTextString = new HSSFRichTextString(textValue);
                            HSSFFont font3 = workbook.createFont();
                            font3.setColor(HSSFColor.BLUE.index);
                            richTextString.applyFont(font3);
                            cell.setCellValue(richTextString);
                        }

                    }*/

                }  catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }
        }
        // 合计行放在哪
        int heji_num = dataset.size() <= 9? 13 : 4+dataset.size();
        // 合计行
        // 单元格合并
        HSSFRow row_heji = sheet.createRow(heji_num);
        // 生成一个样式
        HSSFCellStyle style_heji = createComStyle(workbook, HSSFCellStyle.ALIGN_CENTER);
        // 设置样式
//        style_heji.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 生成字体
        HSSFFont font_heji = workbook.createFont();
        font_heji.setFontHeightInPoints((short) 12);
        // 把字体应用到当前样式
        style_heji.setFont(font_heji);

        setRegionStyle(headers, row_heji, style_heji);
        
        HSSFCell cell_heji = row_heji.createCell((short) 0);
        cell_heji.setCellStyle(style_heji);
        cell_heji.setCellValue(paramMap.get("heji"));
        sheet.addMergedRegion(new Region(heji_num, (short) 0, heji_num, (short) 4));

        HSSFCell cell_heji_2 = row_heji.createCell((short) 5);
        HSSFCellStyle style_sum = createComStyle(workbook, HSSFCellStyle.ALIGN_RIGHT);
        style_sum.setFont(font_heji);
        cell_heji_2.setCellStyle(style_sum);
        cell_heji_2.setCellValue(paramMap.get("sum"));

        HSSFCell cell_heji_3 = row_heji.createCell((short) 6);
        HSSFCellStyle style_operator = createComStyle(workbook, HSSFCellStyle.ALIGN_LEFT);
        style_operator.setFont(font_heji);
        cell_heji_3.setCellStyle(style_operator);
        cell_heji_3.setCellValue(paramMap.get("operator"));
        sheet.addMergedRegion(new Region(heji_num, (short) 6, heji_num, (short) 7));
        
        
        // 大写人民币
        HSSFRow row_CN = sheet.createRow(heji_num + 1);
        setRegionStyle(headers, row_CN, style_operator);
        HSSFCell cell_CN = row_CN.createCell((short) 0);
        cell_CN.setCellStyle(style_operator);
        cell_CN.setCellValue(paramMap.get("CN"));
        sheet.addMergedRegion(new Region(heji_num + 1, (short) 0, heji_num + 1, (short) 5));

        HSSFCell cell_CN_2 = row_CN.createCell((short) 6);
        cell_CN_2.setCellStyle(style_operator);
        cell_CN_2.setCellValue(paramMap.get("customer"));
        sheet.addMergedRegion(new Region(heji_num + 1, (short) 6, heji_num + 1, (short) 7));

        // 厂址单元格合并
        HSSFRow row_factory = sheet.createRow(heji_num + 2);
        HSSFCell cell_factory = row_factory.createCell((short) 0);
        cell_factory.setCellStyle(styleSecond);
        cell_factory.setCellValue(paramMap.get("factory"));
        sheet.addMergedRegion(new Region(heji_num + 2, (short) 0, heji_num + 2, (short) 5));

        HSSFCell cell_factory_2 = row_factory.createCell((short) 6);
        cell_factory_2.setCellStyle(styleSecond);
        cell_factory_2.setCellValue(paramMap.get("phone1"));
        sheet.addMergedRegion(new Region(heji_num + 2, (short) 6, heji_num + 2, (short) 7));

        // 深圳单元格合并
        HSSFRow row_shenzhen = sheet.createRow(heji_num + 3);
        HSSFCell cell_shenzhen = row_shenzhen.createCell((short) 0);
        cell_shenzhen.setCellStyle(styleSecond);
        cell_shenzhen.setCellValue(paramMap.get("shenzhen"));
        sheet.addMergedRegion(new Region(heji_num + 3, (short) 0, heji_num + 3, (short) 5));

        HSSFCell cell_shenzhen_2 = row_shenzhen.createCell((short) 6);
        cell_shenzhen_2.setCellStyle(styleSecond);
        cell_shenzhen_2.setCellValue(paramMap.get("phone2"));
        sheet.addMergedRegion(new Region(heji_num + 3, (short) 6, heji_num + 3, (short) 7));




        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setRegionStyle(String[] headers, HSSFRow row, HSSFCellStyle style) {
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell_heji = row.createCell((short) i);
            cell_heji.setCellStyle(style);
        }
    }

    private HSSFCellStyle createComStyle(HSSFWorkbook workbook, Short align) {
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置样式
//        style_heji.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(align);
        return style;
    }

}
