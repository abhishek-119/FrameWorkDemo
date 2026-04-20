package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    public static FileOutputStream fo;
    public static FileInputStream fi;
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;
    public String xlfile;

    public ExcelUtils(String path){
        this.xlfile=path;
    }

    public int getRowCounts(String xlSheet) throws IOException {
        fi=new FileInputStream(xlfile);
        wb= new XSSFWorkbook(fi);
        sh=wb.getSheet(xlSheet);
        int rcount=sh.getLastRowNum();
        wb.close();
        fi.close();
        return rcount;
    }

    public int getCellCount(String xlsheet, int rownum) throws IOException {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        sh=wb.getSheet(xlsheet);
        row=sh.getRow(rownum);
        int cnum=row.getLastCellNum();
        fi.close();
        wb.close();
        return cnum;
    }



    public void setCellDatas(String xlsheet, int rownum, int colnum, String data) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(xlsheet);

        if (sh == null) {
            sh = wb.createSheet(xlfile);
        }
        if (sh.getRow(rownum) == null) {
            row = sh.createRow(rownum);
        } else {
            row = sh.getRow(rownum);
        }
        if (row.getCell(colnum) == null) {
            cell = row.createCell(colnum);
        } else {
            cell = row.getCell(colnum);
        }
        if (data.equals(" ")) {
            cell.setCellValue("null");
        } else {
            cell.setCellValue(data);
        }
        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fo.close();
        fi.close();
    }


    public String getCellData(String xlsheet, int rownum, int colnum) throws IOException {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        sh=wb.getSheet(xlsheet);
        row=sh.getRow(rownum);
        if(row==null){
            wb.close();
            fi.close();
            return "";
        }
        cell=row.getCell(colnum);
        if(cell==null){
            wb.close();
            fi.close();
            return "";
        }
        String data;
        try{
            DataFormatter formatter=new DataFormatter();
            data=formatter.formatCellValue(cell);
        }catch(Exception e){
            data="";
        }
        wb.close();
        fi.close();
        return data;
    }



    public void fillGreenColor(String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(xlsheet);
        row = sh.getRow(rownum);
        cell = row.getCell(colnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fo.close();
        fi.close();
    }

    public void fillRedColor(String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(xlsheet);
        row = sh.getRow(rownum);
        cell = row.getCell(colnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fo.close();
        fi.close();
    }

}
