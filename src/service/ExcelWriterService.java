package service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import model.FortifyDTO;

public class ExcelWriterService {
    static String excel_path = "";
    Properties properties;
    int nCount = 0;
    Map<String,Integer> seq = new HashMap<String,Integer>();
    Map<String,Boolean> hidden = new HashMap<String,Boolean>();

    public ExcelWriterService(Properties properties) {
        this.properties = properties;

        for(String name : FortifyDTO.parameters){

            // 열 순서 설정 저장
            seq.put(name, properties.getProperty(name) == null ? null : Integer.parseInt(properties.getProperty(name))-1);

            // 열 숨김 설정 저장
            // properties에 설정할때 숨김을 0으로 저장함.
            hidden.put(name, properties.getProperty(name + "_hidden") == null ? false : (properties.getProperty(name + "_hidden").equals("0") ? true : false));

        }
    }

    public void makeExcelFileParmList(ArrayList<FortifyDTO> list) {

        System.out.println("xls 생성중...");

        Workbook workbook = new HSSFWorkbook();

        // 시트명 설정
        Sheet sheet = workbook.createSheet("Sheet");
        // 필터 설정
        sheet.setAutoFilter(CellRangeAddress.valueOf("A1:X" + list.size()));

        // 기본 셀 스타일 설정
        CellStyle defaltStyle = workbook.createCellStyle();
        defaltStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        defaltStyle.setBorderBottom(CellStyle.BORDER_THIN);
        defaltStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        defaltStyle.setBorderLeft(CellStyle.BORDER_THIN);
        defaltStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        defaltStyle.setBorderRight(CellStyle.BORDER_THIN);
        defaltStyle.setRightBorderColor(IndexedColors.BLACK.index);
        defaltStyle.setBorderTop(CellStyle.BORDER_THIN);
        defaltStyle.setTopBorderColor(IndexedColors.BLACK.index);
        defaltStyle.setWrapText(true);

        Row row;

        // 헤더 설정
        row = sheet.createRow(0);
        row.createCell(seq.get("friority")).setCellValue("위험도");
        row.createCell(seq.get("folder")).setCellValue("폴더");
        row.createCell(seq.get("kingdom")).setCellValue("대분류");
        row.createCell(seq.get("category")).setCellValue("취약점");
        row.createCell(seq.get("source_filepath")).setCellValue("위험인자 진입 파일경로");
        row.createCell(seq.get("source_filenname")).setCellValue("위험인자 진입 파일명");
        row.createCell(seq.get("source_linestart")).setCellValue("위험인자 진입 라인넘버");
        row.createCell(seq.get("source_snippet")).setCellValue("위험인자 진입 소스조각");
        row.createCell(seq.get("source_targetfunction")).setCellValue("위험인자 진입 함수");
        row.createCell(seq.get("primary_filepath")).setCellValue("취약점 탐지 파일경로");
        row.createCell(seq.get("primary_filenname")).setCellValue("취약점 탐지 파일명");
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

        int count = 1;

        for (FortifyDTO entity : list) {
            row = sheet.createRow(count);
            count = count + 1;

            setCell(row, seq.get("friority"), entity.getFriority());
            setCell(row, seq.get("folder"), entity.getFolder());
            setCell(row, seq.get("kingdom"), entity.getKingdom());
            setCell(row, seq.get("category"), entity.getCategory());
            setCell(row, seq.get("source_filepath"), entity.getSource_filepath());
            setCell(row, seq.get("source_filenname"), entity.getSource_filenname());
            setCell(row, seq.get("source_linestart"), entity.getSource_linestart(), "int");
            setCell(row, seq.get("source_snippet"), entity.getSource_snippet());
            setCell(row, seq.get("source_targetfunction"), entity.getSource_targetfunction());
            setCell(row, seq.get("primary_filepath"), entity.getPrimary_filepath());
            setCell(row, seq.get("primary_filenname"), entity.getPrimary_filenname());
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

            // 기본 셀 스타일 적용
            for(int i = 0; i < row.getLastCellNum() ; i++){
                row.getCell(i).setCellStyle(defaltStyle);
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
//        	e.printStackTrace();
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
    HSSFRichTextString setWrapCell(String value){
        int count = 0;
        String description = "";

        if(value != null){
            String[] remark = value.split("\n");
            for (int k = 0; k < remark.length; k++)
            {
                if (remark[k].length() > 0)
                {
                    if (count == 0)
                        description += remark[k];
                    else
                        description += "\r\n"+ remark[k];

                    count++;
                }
            }
            if(nCount < count){
                nCount = count;
            }
        }

        return new HSSFRichTextString(description);
    }
}