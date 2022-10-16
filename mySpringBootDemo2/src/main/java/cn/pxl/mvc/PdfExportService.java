package cn.pxl.mvc;

import java.util.Map;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PdfExportService {
    public void make(Map<String,Object> module, Document document, PdfWriter pdfWriter, HttpServletRequest request, HttpServletResponse response);
}
