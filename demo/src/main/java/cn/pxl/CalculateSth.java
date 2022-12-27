package cn.pxl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class CalculateSth {

    private Workbook wb;
    private Sheet sheet;
    private Row row;

    public static void main(String[] args) {
        CalculateSth calculateSth = new CalculateSth();
        calculateSth.readExcelUtils();
        calculateSth.readExcel();
    }

    private static void cal(double x) {
        //Y=1/(0.632+0.3153-0.831x)
        BigDecimal bigDecimal = BigDecimal.valueOf(1 / (0.632 + Math.pow(0.3153, -0.831 * x))).setScale(6, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(bigDecimal);
    }

    public void readExcelUtils() {
        try {
            InputStream is = new FileInputStream("/Users/pengxiaoliang/Downloads/shuju.xlsx");
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取Excel表格表头的内容
     *
     * @return String 表头内容的数组
     * @author zengwendong
     */
    public void readExcel()  {
        if (wb == null) {
            System.out.println("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            Double ddd=  (Double)sheet.getRow(i).getCell(0).getNumericCellValue();
            cal(ddd);
        }
    }

}