package com.university.contractors.service;

import com.university.contractors.model.dao.ReportModelDAO;
import com.university.contractors.model.dto.reports.ReportDTO;
import com.university.contractors.model.dto.reports.ReportFieldUploadDTO;
import com.university.contractors.model.dto.reports.ReportFieldViewDTO;
import com.university.contractors.model.jpa.domains.ReportFieldName;
import com.university.contractors.model.jpa.domains.ReportFieldType;
import com.university.contractors.model.jpa.domains.ReportFormat;
import com.university.contractors.model.jpa.entity.CommonStudentPaymentReportView;
import com.university.contractors.model.jpa.entity.ReportFields;
import com.university.contractors.repository.jpa.ReportFieldsRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.university.contractors.model.jpa.domains.ReportFieldName.*;

@Service
public class ReportService {

    @Autowired
    private ReportFieldsRepository reportFieldsRepository;

    @Autowired
    private ReportModelDAO reportModelDAO;

    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Get Report Fields List
     *
     * @return Report Fields List
     */
    public List<ReportFieldViewDTO> getFieldsList() {
        List<ReportFieldViewDTO> result = reportModelDAO.getReportFieldsList();

        return result;
    }

    /**
     * Get Template content for Download
     *
     * @param reportDetails     Report Details to Build Template
     * @return ByteArrayInputStream     Template for Download
     */
    public ByteArrayOutputStream getDownloadTemplate (ReportDTO reportDetails) {
        ByteArrayOutputStream result = null;

//        try {

//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            if (reportDetails.getFormat().equals(ReportFormat.HTML)) {
                System.out.println("\n\n\t\t\t\t\tHTML\n\n");

                result = buildHTMLReport(reportDetails.getFields());
            } else if (reportDetails.getFormat().equals(ReportFormat.XLS)) {
                System.out.println("\n\n\t\t\t\t\tXLS\n\n");

                result = buildXLSReport(reportDetails.getFields());
            }

//            byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());

//        } catch (IOException exception) {
//            exception.printStackTrace();
//
//        }

        return result;
    }

    /**
     *
     */
    private ByteArrayOutputStream buildHTMLReport(List<ReportFieldUploadDTO> fields) {
        // TODO generate HTML template
        return new ByteArrayOutputStream();
    }

    /**
     *
     */
    private ByteArrayOutputStream buildXLSReport(List<ReportFieldUploadDTO> fields) {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        // Prepare data
        List<CommonStudentPaymentReportView> reportEntries = reportModelDAO.getReportEntriesList(fields);
        Map<Long, ReportFieldUploadDTO> fieldsMap = buildFieldsMap(fields);


        // ========== ========== ========== ========== ==========  Initialize Required Apache POI instruments ========== ========== ========== ========== ==========
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook();

        /* CreationHelper helps us create instances of various things like DataFormat,
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create sheet
        Sheet sheet = workbook.createSheet("Звiт");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontName("Calibri");
        headerFont.setFontHeightInPoints((short) 13);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setWrapText(true);

        // Create Usual Cell Font
        Font cellFont = workbook.createFont();
        cellFont.setBold(false);
        cellFont.setFontName("Calibri");
        cellFont.setFontHeightInPoints((short) 11);
        cellFont.setColor(IndexedColors.BLACK.getIndex());
        CellStyle bodyCellStyle = workbook.createCellStyle();
        bodyCellStyle.setFont(cellFont);

        // Create Right Aligned Text Cell Font
        Font cellFontRight = workbook.createFont();
        cellFontRight.setBold(false);
        cellFontRight.setFontName("Calibri");
        cellFontRight.setFontHeightInPoints((short) 11);
        cellFontRight.setColor(IndexedColors.BLACK.getIndex());
        CellStyle bodyCellStyleRight = workbook.createCellStyle();
        bodyCellStyleRight.setAlignment(HorizontalAlignment.RIGHT);
        bodyCellStyleRight.setFont(cellFontRight);

        // Create Red Text Cell Font
        Font cellFontRed = workbook.createFont();
        cellFontRed.setBold(false);
        cellFontRed.setFontName("Calibri");
        cellFontRed.setFontHeightInPoints((short) 11);
        cellFontRed.setColor(IndexedColors.RED.getIndex());
        CellStyle bodyCellStyleRed = workbook.createCellStyle();
        bodyCellStyleRed.setFont(cellFontRed);

        // Create Green Text Cell Font
        Font cellFontGreen = workbook.createFont();
        cellFontGreen.setBold(false);
        cellFontGreen.setFontName("Calibri");
        cellFontGreen.setFontHeightInPoints((short) 11);
        cellFontGreen.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
        CellStyle bodyCellStyleGreen = workbook.createCellStyle();
        bodyCellStyleGreen.setFont(cellFontGreen);


        // Create Cell Style for Date formatting
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // ========== ========== ========== ========== ==========  Generate Template Data ========== ========== ========== ========== ==========

        //Set Column Width
        Cell cell;
        int currentCellNumber = 0;

        // Create Headers for Sheet
        Row headerRow = sheet.createRow(0);

        for (Map.Entry<Long, ReportFieldUploadDTO> entry: fieldsMap.entrySet()) {
            if (entry.getValue() != null) {
                cell = headerRow.createCell(currentCellNumber);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(ReportFieldName.of(entry.getValue().getId()).getTitle());
//                sheet.addMergedRegion(new CellRangeAddress(0, 1, currentCellNumber, currentCellNumber));
                currentCellNumber++;
            }
        }

        // Fill sheet with data
        int itemNumber = 1;
        for (CommonStudentPaymentReportView reportView: reportEntries) {
            Row itemRow = sheet.createRow(itemNumber);
            currentCellNumber = 0;
            for (Map.Entry<Long, ReportFieldUploadDTO> entry: fieldsMap.entrySet()) {
                if (entry.getValue() != null) {
                    cell = itemRow.createCell(currentCellNumber);
                    cell.setCellStyle(bodyCellStyle);
                    ReportFieldName fieldName = ReportFieldName.of(entry.getValue().getId());
                    String cellValue;

                    if (fieldName.equals(COUNTER)) {
                        cell.setCellValue(itemNumber);

                    } else if (fieldName.equals(STUDENT_SURNAME)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getStudSurname()) ? reportView.getStudSurname() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(STUDENT_NAME)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getStudName()) ? reportView.getStudName() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(STUDENT_MIDDLENAME)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getStudMiddlename()) ? reportView.getStudMiddlename() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(COUNTRY)) {
                        cellValue = Optional.ofNullable(reportView.getCountryNameRu())
                            .or(() -> Optional.ofNullable(reportView.getCountryNameUa()))
                            .or(() -> Optional.ofNullable(reportView.getCountryNameEng()))
                            .orElse("-");
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(BIRTHDATE)) {
                        cellValue = reportView.getDateOfBirth() != null ? dateFormat.format(reportView.getDateOfBirth()) : "-";
                        cell.setCellValue(cellValue);
//                        cell.setCellStyle(dateCellStyle);
//                        cell.setCellValue(reportView.getDateOfBirth());

                    } else if (fieldName.equals(FACULTY)) {
                        cellValue = Optional.ofNullable(reportView.getFacultyNameRu())
                            .or(() -> Optional.ofNullable(reportView.getFacultyName()))
                            .or(() -> Optional.ofNullable(reportView.getFacultyNameEng()))
                            .orElse("-");
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(DIRECTION)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getDirectionName()) ? reportView.getDirectionName() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(EDUCATION_FORM)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getEducFormName()) ? reportView.getEducFormName() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(EDUCATION_LEVEL)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getEducLevelName()) ? reportView.getEducLevelName() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(EDUCATION_PROGRAM)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getEducProgName()) ? reportView.getEducProgName() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(ENTRANCE_DATE)) {
                        cellValue = reportView.getDateIn() != null ? dateFormat.format(reportView.getDateIn()) : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(GRADUATION_DATE)) {
                        // TODO make sure it is na actual planDateOut (there are some other dates as outRelatedDate, outOrderDate...)
                        cellValue = reportView.getPlanDateOut() != null ? dateFormat.format(reportView.getPlanDateOut()) : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(ARRIVAL_LINE)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getArrivalCenterName()) ? reportView.getArrivalCenterName() : "-";
                        cellValue = StringUtils.isNotEmpty(reportView.getArrivalCenter()) && reportView.getIdArrivalLine() != null && reportView.getIdArrivalLine() != 1 ?
                            MessageFormat.format("{0} ({1})", cellValue, reportView.getArrivalCenter()) : cellValue;
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(COURSE)) {
                        cellValue = reportView.getCourse() != null ? reportView.getCourse().toString() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(EDUCATION_LANGUAGE)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getEducLanguageName()) ? reportView.getEducLanguageName() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(STATUS)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getStudentStatusName()) ? reportView.getStudentStatusName() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(ENROLLMENT)) {
                        // TODO find out where to get ENROLLMENT value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(CONTRACT_VALUE)) {
                        cellValue = reportView.getContractValue() != null ? reportView.getContractValue().toString() : "";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(ENROLLMENT_ORDER_NUMBER)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getInOrderNumber()) ? reportView.getInOrderNumber() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(CONTRACT_NUMBER)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getContractNumber()) ? reportView.getContractNumber() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(CONTRACT_TYPE)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getContractTypeName()) ? reportView.getContractTypeName() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(PAYER)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getPayer()) ? reportView.getPayer() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(PREPAY_HRN)) {
                        // TODO find out where to get PREPAY_HRN value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(PREPAY_USD)) {
                        // TODO find out where to get PREPAY_USD value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(PAYMENT_HRN)) {
                        // TODO find out where to get PAYMENT_HRN value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(PAYMENT_USD)) {
                        // TODO find out where to get PAYMENT_USD value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(DEBT_HRN)) {
                        // TODO find out where to get DEBT_HRN value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(DEBT_USD)) {
                        // TODO find out where to get DEBT_USD value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(FINE_HRN)) {
                        // TODO find out where to get FINE_HRN value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(FINE_USD)) {
                        // TODO find out where to get FINE_USD value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(PERIOD_FROM)) {
                        // TODO find out where to get PERIOD_FROM value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(PERIOD_TO)) {
                        // TODO find out where to get PERIOD_TO value
                        cellValue = "?";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(ENROLLMENT_ORDER_DATE)) {
                        cellValue = reportView.getInOrderDate() != null ? dateFormat.format(reportView.getInOrderDate()) : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(NATIONALITY)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getNationality()) ? reportView.getNationality() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(DEDUCTION_ORDER_NUMBER)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getOutOrderNumber()) ? reportView.getOutOrderNumber() : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(DEDUCTION_ORDER_DATE)) {
                        cellValue = reportView.getOutOrderDate() != null ? dateFormat.format(reportView.getOutOrderDate()) : "-";
                        cell.setCellValue(cellValue);

                    } else if (fieldName.equals(DEDUCTION_REASON)) {
                        cellValue = StringUtils.isNotEmpty(reportView.getOutOrderNote()) ? reportView.getOutOrderNote() : "-";
                        cell.setCellValue(cellValue);

                    } else {
                        // TODO handle unknown field name
                        System.out.println(MessageFormat.format("\n\n\t\t[[fieldName]] ReportField ({0} - {1}) unknown field name occur while filling XLS report with data", entry.getValue().getId(), entry.getValue().getName()));
                    }
//                    sheet.addMergedRegion(new CellRangeAddress(itemNumber))

                    currentCellNumber++;
                }
            }

            itemNumber++;
        }

        if (fieldsMap.get(COUNTER) != null) {
            Row footerRow = sheet.createRow(itemNumber);
            Cell totalCountCell = footerRow.createCell(0);
            totalCountCell.setCellStyle(headerCellStyle);
            totalCountCell.setCellValue(MessageFormat.format("Всего - {0}", --itemNumber));
        }

//        if (fieldsMap.get(ReportFieldName.COUNTER.getId()) != null) {
//            cell = headerRow.createCell(0);
//            cell.setCellValue("#");
//            cell.setCellStyle(headerCellStyle);
//            sheet.addMergedRegion(new CellRangeAddress(0, 1, currentCellNumber, currentCellNumber));
//            currentCellNumber++;
//        }

        try {
            workbook.write(result);

            // Closing the workbook
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     *
     */
    private Map<Long, ReportFieldUploadDTO> buildFieldsMap(List<ReportFieldUploadDTO> fields) {
        Map<Long, ReportFieldUploadDTO> result = new HashMap<>();

        List<ReportFields> reportFields = reportFieldsRepository.getList();
        // initialize fieldsMap with fields ids as keys and nulls as values
        reportFields.stream().forEach(reportField -> {
            result.put(reportField.getId(), null);
        });
        // replace nulls with actual selected fields
        fields.stream().forEach(field -> {
            result.put(field.getId(), field);
        });

        return result;
    }
}
