package service;

import model.FortifyDTO;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ExcelWriterService {
    static String excel_path = "";
    Properties properties;
    int nCount = 0;
    Map<String,Integer> seq = new HashMap<>();
    Map<String,Boolean> hidden = new HashMap<>();

    public ExcelWriterService(Properties properties) {
        this.properties = properties;

        for(String name : FortifyDTO.parameters){

            // 열 순서 설정 저장
            seq.put(name, properties.getProperty(name) == null ? null : Integer.parseInt(properties.getProperty(name))-1);

            // 열 숨김 설정 저장
            // properties 에 설정할때 숨김을 0으로 저장함.
            hidden.put(name, properties.getProperty(name + "_hidden") != null && (properties.getProperty(name + "_hidden").equals("0")));

        }
    }

    public void makeExcelFileParmList(ArrayList<FortifyDTO> list) {

        System.out.println("xlsx 생성중...");

        Workbook workbook = new XSSFWorkbook();

        // 시트명 설정
        Sheet sheet = workbook.createSheet("Sheet");
        // 필터 설정
        sheet.setAutoFilter(CellRangeAddress.valueOf("A1:Y" + list.size()));

        // 기본 셀 스타일 설정
        CellStyle defaultStyle = workbook.createCellStyle();
        defaultStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        defaultStyle.setBorderBottom(BorderStyle.THIN);
        defaultStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        defaultStyle.setBorderLeft(BorderStyle.THIN);
        defaultStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        defaultStyle.setBorderRight(BorderStyle.THIN);
        defaultStyle.setRightBorderColor(IndexedColors.BLACK.index);
        defaultStyle.setBorderTop(BorderStyle.THIN);
        defaultStyle.setTopBorderColor(IndexedColors.BLACK.index);
        defaultStyle.setWrapText(true);

        // 헤더 설정
        Row row = sheet.createRow(0);
        row.createCell(seq.get("friority")).setCellValue("위험도");
        row.createCell(seq.get("folder")).setCellValue("폴더");
        row.createCell(seq.get("kingdom")).setCellValue("대분류");
        row.createCell(seq.get("category")).setCellValue("취약점");
        row.createCell(seq.get("source_filepath")).setCellValue("위험인자 진입 파일경로");
        row.createCell(seq.get("source_filename")).setCellValue("위험인자 진입 파일명");
        row.createCell(seq.get("source_linestart")).setCellValue("위험인자 진입 라인넘버");
        row.createCell(seq.get("source_snippet")).setCellValue("위험인자 진입 소스조각");
        row.createCell(seq.get("source_targetfunction")).setCellValue("위험인자 진입 함수");
        row.createCell(seq.get("primary_filepath")).setCellValue("취약점 탐지 파일경로");
        row.createCell(seq.get("primary_filename")).setCellValue("취약점 탐지 파일명");
        row.createCell(seq.get("primary_linestart")).setCellValue("취약점 탐지 라인넘버");
        row.createCell(seq.get("primary_snippet")).setCellValue("취약점 탐지 소스조각");
        row.createCell(seq.get("primary_targetfunction")).setCellValue("취약점 탐지 함수");
        row.createCell(seq.get("issue_abstract")).setCellValue("취약점 이슈 개요");
        row.createCell(seq.get("metainfo_abstract")).setCellValue("취약점 개요");
        row.createCell(seq.get("metainfo_explanation")).setCellValue("취약점 설명");
        row.createCell(seq.get("metainfo_recommendations")).setCellValue("취약점 조치 권고내용");
        row.createCell(seq.get("metainfo_tips")).setCellValue("취약점 조치 tip");
        row.createCell(seq.get("iid")).setCellValue("취약점 이슈 고유키");
        row.createCell(seq.get("ruleid")).setCellValue("취약점 rule 고유키");
        row.createCell(seq.get("tag")).setCellValue("auditor 태그");
        row.createCell(seq.get("userinfo")).setCellValue("auditor 아이디");
        row.createCell(seq.get("comment")).setCellValue("auditor 의견");
        row.createCell(seq.get("scandate")).setCellValue("scan 일자");

        int count = 1;

        for (FortifyDTO entity : list) {
            row = sheet.createRow(count);
            count = count + 1;

            setCell(row, seq.get("friority"), entity.getFriority());
            setCell(row, seq.get("folder"), entity.getFolder());
            setCell(row, seq.get("kingdom"), entity.getKingdom());
            setCell(row, seq.get("category"), entity.getCategory());
            setCell(row, seq.get("source_filepath"), entity.getSource_filepath());
            setCell(row, seq.get("source_filename"), entity.getSource_filename());
            setCell(row, seq.get("source_linestart"), entity.getSource_linestart(), "int");
            setCell(row, seq.get("source_snippet"), entity.getSource_snippet());
            setCell(row, seq.get("source_targetfunction"), entity.getSource_targetfunction());
            setCell(row, seq.get("primary_filepath"), entity.getPrimary_filepath());
            setCell(row, seq.get("primary_filename"), entity.getPrimary_filename());
            setCell(row, seq.get("primary_linestart"), entity.getPrimary_linestart(), "int");
            setCell(row, seq.get("primary_snippet"), entity.getPrimary_snippet());
            setCell(row, seq.get("primary_targetfunction"), entity.getPrimary_targetfunction());
            setCell(row, seq.get("issue_abstract"), entity.getIssue_abstract());
            setCell(row, seq.get("metainfo_abstract"), entity.getMetainfo_abstract());
            setCell(row, seq.get("metainfo_explanation"), entity.getMetainfo_explanation());
            setCell(row, seq.get("metainfo_recommendations"), entity.getMetainfo_recommendations());
            setCell(row, seq.get("metainfo_tips"), entity.getMetainfo_tips());
            setCell(row, seq.get("iid"), entity.getIid());
            setCell(row, seq.get("ruleid"), entity.getRuleid());
            setCell(row, seq.get("tag"), entity.getTag());
            setCell(row, seq.get("userinfo"), entity.getUserinfo());
            setCell(row, seq.get("comment"), entity.getComment());
            setCell(row, seq.get("scandate"), entity.getScanDate());

            // 기본 셀 스타일 적용
            for(int i = 0; i < row.getLastCellNum() ; i++){
                row.getCell(i).setCellStyle(defaultStyle);
            }

            // 줄바꿈 셀기준 행 높이 설정
            row.setHeightInPoints((nCount * sheet.getDefaultRowHeightInPoints()));
            nCount = 1;

        }

        int i;
        for(String name : FortifyDTO.parameters){
            i = seq.get(name);

            sheet.setColumnHidden(i, hidden.get(name));

            //TODO 열너비 조절
            if(name.equals("source_snippet")
                    || name.equals("primary_snippet")
                    || name.equals("issue_abstract")
                    || name.equals("metainfo_abstract")
                    || name.equals("metainfo_explanation")
                    || name.equals("metainfo_recommendations")
                    || name.equals("metainfo_tips")){
                sheet.setColumnWidth(i, 10000);
            }else{
                sheet.autoSizeColumn(i);
            }
        }


        FileOutputStream fos;
        try {
            fos = new FileOutputStream(excel_path);
            workbook.write(fos);
            fos.close();
            workbook.close();
            System.out.println("완료.");
        } catch (Exception e) {
            System.out.println(excel_path + " 파일을 생성할 수 없습니다.");
        	e.printStackTrace();
        }
    }

    // 셀 값 입력
    void setCell(Row row, int index, String value, String style){
        if(value != null){
            if(style == null){
                row.createCell(index).setCellValue(value);
            }else{

                switch(style){
                    case "int": row.createCell(index).setCellValue(Integer.parseInt(value)); break;
                    case "wrap" : row.createCell(index).setCellValue(setWrapCell(value)); break;

                }
            }
        }else{
            row.createCell(index);
        }
    }
    void setCell(Row row, int index, String value){
        setCell(row, index, value, null);
    }

    //줄바뀜 셀 설정
    String setWrapCell(String value){
        int count = 0;
        StringBuilder description = new StringBuilder();

        if(value != null){
            String[] remark = value.split("\n");
            for (String s : remark) {
                if (!s.isEmpty()) {
                    if (count == 0)
                        description.append(s);
                    else
                        description.append("\r\n").append(s);

                    count++;
                }
            }
            if(nCount < count){
                nCount = count;
            }
        }

        return description.toString();
    }
}