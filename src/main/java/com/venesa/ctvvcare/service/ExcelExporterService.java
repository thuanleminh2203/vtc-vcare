package com.venesa.ctvvcare.service;

import com.venesa.ctvvcare.payload.response.CustomerResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author thuanlm
 * @created at 10/28/2020
 */
public class ExcelExporterService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CustomerResponse> listCustomer;

    public ExcelExporterService(List<CustomerResponse> listCustomer) {
        this.listCustomer = listCustomer;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Mã giới thiệu", style);
        createCell(row, 2, "Số điện thoại", style);
        createCell(row, 3, "Địa chỉ", style);
        createCell(row, 4, "Email", style);

        createCell(row, 5, "Ngân hàng", style);
        createCell(row, 6, "Số tài khoản", style);
        createCell(row, 7, "Tên tài khoản", style);
        createCell(row, 8, "Họ tên", style);
        createCell(row, 9, "Trạng thái", style);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (CustomerResponse customer : listCustomer) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, customer.getCustomerId(), style);
            createCell(row, columnCount++, customer.getIntroductionCode(), style);
            createCell(row, columnCount++, customer.getPhoneNumber(), style);
            createCell(row, columnCount++, customer.getAddress(), style);
            createCell(row, columnCount++, customer.getEmail(), style);
//            createCell(row, columnCount++, customer.getIntroductionCode(), style);

            createCell(row, columnCount++, customer.getBankName(), style);
            createCell(row, columnCount++, customer.getBankAccountNumber(), style);
            createCell(row, columnCount++, customer.getBankAccountName(), style);
            createCell(row, columnCount++, customer.getCustomerName(), style);
            createCell(row, columnCount++, customer.isActive() ? "Hoạt Động" : "Đã vô hiệu hóa", style);

        }
    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}
