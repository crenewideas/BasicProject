package cn.pxl.controller;

import cn.pxl.mvc.MyPdfView;
import cn.pxl.mvc.PdfExportService;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @RequestMapping("/export/pdf")
    public ModelAndView exportPdf(ModelAndView modelAndView){
        modelAndView.setView(new MyPdfView(getExport()));
        return modelAndView;
    }

    private PdfExportService getExport(){
        return (model,document,writer,request,response)->{
            document.setPageSize(PageSize.A4);
            document.addTitle("彭笑良的PDF测试");
            try {
                document.add(new Chunk("\n"));
                //表格3列
                PdfPTable pdfPTable = new PdfPTable(3);
                //单元格
                PdfPCell pdfPCell = null;
                //字体，定义为蓝色加粗
                Font font = new Font();
                font.setColor(Color.BLUE);
                font.setStyle(Font.BOLD);
                //标题
                pdfPCell = new PdfPCell(new Paragraph("id",font));
                //居中对齐
                pdfPCell.setHorizontalAlignment(1);
                pdfPTable.addCell(pdfPCell);
                //标题
                pdfPCell = new PdfPCell(new Paragraph("name",font));
                //居中对齐
                pdfPCell.setHorizontalAlignment(1);
                pdfPTable.addCell(pdfPCell);
                //标题
                pdfPCell = new PdfPCell(new Paragraph("age",font));
                //居中对齐
                pdfPCell.setHorizontalAlignment(1);
                pdfPTable.addCell(pdfPCell);
                for (int i = 0; i < 2; i++) {
                    document.add(new Chunk("\n"));
                    pdfPCell = new PdfPCell(new Paragraph(i+""));
                    pdfPTable.addCell(pdfPCell);
                    pdfPCell = new PdfPCell(new Paragraph("彭笑良"+i));
                    pdfPTable.addCell(pdfPCell);
                    pdfPCell = new PdfPCell(new Paragraph("男"+i));
                    pdfPTable.addCell(pdfPCell);
                }
                document.add(pdfPTable);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        };
    }

}
