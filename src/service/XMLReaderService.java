package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import model.FortifyDTO;

public class XMLReaderService {

	static String xml_path = "";

	public XMLReaderService(){

		Properties properties = new Properties();
		try {
			properties.load(Files.newInputStream(Paths.get("xls-generator.properties")));
		} catch (IOException e) {
			System.out.println("설정파일을 찾을 수 없습니다.\nxls-generator.properties 파일을 확인해주시기 바랍니다.");
		}

		readXMLFile(properties);
	}

	public void readXMLFile(Properties properties){
		try {

			File file = new File(xml_path);

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			FileInputStream fis	= new FileInputStream(file);
			xpp.setInput(fis, "utf-8");



			String tag = null;
			boolean tag_start = false;
			boolean tag_GroupingSection = false;
			boolean tag_MajorAttributeSummary = false;
			boolean tag_MetaInfo = false;
			boolean tag_Issue = false;
			boolean tag_Primary = false;
			boolean tag_Source = false;
			boolean tag_Tag = false;
			boolean tag_Comment = false;
			int event_type = xpp.getEventType();

			ArrayList<FortifyDTO> list = new ArrayList<>();

			String friority = null;
			String folder = null;
			String kingdom = null;
			String category = null;
			String source_filepath = null;
			String source_filename = null;
			String source_linestart = null;
			String source_snippet = null;
			String source_targetfunction = null;
			String primary_filepath = null;
			String primary_filename = null;
			String primary_linestart = null;
			String primary_snippet = null;
			String primary_targetfunction = null;
			String issue_abstract = null;
			String metainfo_abstract = null;
			String metainfo_explanation = null;
			String metainfo_recommendations = null;
			String metainfo_tips = null;
			String iid = null;
			String ruleid = null;
			String Issue_tag = null;
			String userinfo = null;
			String comment = null;
			String metainfo_name = null;

			while (event_type != XmlPullParser.END_DOCUMENT) {
				if (event_type == XmlPullParser.START_TAG) {
					tag = xpp.getName();
					tag_start = true;
					if(tag.equals("Issue")){
						iid = xpp.getAttributeValue(null, "iid");
						ruleid = xpp.getAttributeValue(null, "ruleID");
					}
				}else if (event_type == XmlPullParser.TEXT && tag_start) {
					//각 태그별로 값을 가져온다.

					//GroupingSection 안의 내용만 확인
					if(tag_GroupingSection){

						//MajorAttributeSummary 안에서 MetaInfo 내용들 저장
						if(tag_MajorAttributeSummary){
							if(tag_MetaInfo){
								if(tag.equals("Name")){
									metainfo_name = xpp.getText();
								}else if(tag.equals("Value")){
									switch (Objects.requireNonNull(metainfo_name)) {
										case "Abstract":
											metainfo_abstract = xpp.getText();
											break;
										case "Explanation":
											metainfo_explanation = xpp.getText();
											break;
										case "Recommendations":
											metainfo_recommendations = xpp.getText();
											break;
										case "Tips":
											metainfo_tips = xpp.getText();
											break;
									}
								}
							}
							if(tag.equals("MetaInfo")){
								tag_MetaInfo = true;
							}
						}else if(tag_Issue){
							if(tag_Primary){
								switch (tag) {
									case "FileName":
										primary_filename = xpp.getText();
										break;
									case "FilePath":
										primary_filepath = xpp.getText();
										break;
									case "LineStart":
										primary_linestart = xpp.getText();
										break;
									case "Snippet":
										primary_snippet = xpp.getText();
										break;
									case "TargetFunction":
										primary_targetfunction = xpp.getText();
										break;
								}
							}else if(tag_Source){
								switch (tag) {
									case "FileName":
										source_filename = xpp.getText();
										break;
									case "FilePath":
										source_filepath = xpp.getText();
										break;
									case "LineStart":
										source_linestart = xpp.getText();
										break;
									case "Snippet":
										source_snippet = xpp.getText();
										break;
									case "TargetFunction":
										source_targetfunction = xpp.getText();
										break;
								}
							}else if(tag_Tag){
								if(tag.equals("Value")){
									Issue_tag = xpp.getText();
								}
							}else if(tag_Comment){
								if(tag.equals("UserInfo")){
									userinfo = xpp.getText();
								}else if(tag.equals("Comment")){
									comment = xpp.getText();
								}
							}
							switch (tag) {
								case "Friority":
									friority = xpp.getText();
									break;
								case "Folder":
									folder = xpp.getText();
									break;
								case "Kingdom":
									kingdom = xpp.getText();
									break;
								case "Category":
									category = xpp.getText();
									break;
								case "Abstract":
									issue_abstract = xpp.getText();
									break;
								case "Primary":
									tag_Primary = true;
									break;
								case "Source":
									tag_Source = true;
									break;
								case "Tag":
									tag_Tag = true;
									break;
								case "Comment":
									tag_Comment = true;
									break;
							}
						}
						if(tag.equals("MajorAttributeSummary")){
							tag_MajorAttributeSummary = true;
						}if(tag.equals("Issue")){
							tag_Issue = true;
						}
					}

					if(tag.equals("GroupingSection")){
						tag_GroupingSection = true;
					}
				}else if (event_type == XmlPullParser.END_TAG) {
					tag = xpp.getName();
					tag_start = false;
					switch (tag) {
						case "GroupingSection":
							tag_GroupingSection = false;
							metainfo_abstract = null;
							metainfo_explanation = null;
							metainfo_recommendations = null;
							metainfo_tips = null;
							break;
						case "MajorAttributeSummary":
							tag_MajorAttributeSummary = false;
							break;
						case "MetaInfo":
							metainfo_name = null;
							break;
						case "Issue":
							FortifyDTO entity = new FortifyDTO();
							entity.setFriority(friority);
							entity.setFolder(folder);
							entity.setKingdom(kingdom);
							entity.setCategory(category);
							entity.setIssue_abstract(issue_abstract);
							entity.setPrimary_filename(primary_filename);
							entity.setPrimary_filepath(primary_filepath);
							entity.setPrimary_linestart(primary_linestart);
							entity.setPrimary_snippet(primary_snippet);
							entity.setPrimary_targetfunction(primary_targetfunction);
							entity.setSource_filename(source_filename);
							entity.setSource_filepath(source_filepath);
							entity.setSource_linestart(source_linestart);
							entity.setSource_snippet(source_snippet);
							entity.setSource_targetfunction(source_targetfunction);
							entity.setTag(Issue_tag);
							entity.setUserinfo(userinfo);
							entity.setComment(comment);
							entity.setMetainfo_abstract(metainfo_abstract);
							entity.setMetainfo_explanation(metainfo_explanation);
							entity.setMetainfo_recommendations(metainfo_recommendations);
							entity.setMetainfo_tips(metainfo_tips);
							entity.setIid(iid);
							entity.setRuleid(ruleid);

							list.add(entity);
							tag_Issue = false;
							break;
						case "Primary":
							tag_Primary = false;
							break;
						case "Source":
							tag_Source = false;
							break;
						case "Tag":
							tag_Tag = false;
							break;
						case "Comment":
							tag_Comment = false;
							break;
					}
				}
				event_type = xpp.next();
			}

			//엑셀파일을 쓰는 부분이다.
			new ExcelWriterService(properties).makeExcelFileParmList(list);

		} catch (Exception e) {
			System.out.println(xml_path + " 파일을 읽을 수 없습니다.");
			e.printStackTrace();
		}


	}

	static String setXmlURL(String path){

		if(!path.endsWith(".xml"))
			path = path + ".xml";

		return path;
	}

	static String setXlsURL(String path){

		if(!path.endsWith(".xls"))
			path = path + ".xls";

		return path;
	}

	public static void main(String[] args) {
		if (args == null || args.length != 2) {
			System.out.println("Usage : xls-generator <xml file> <xls file>");
			System.out.println("Convert xml file into xls file");

			System.exit(-1);
		}

		XMLReaderService.xml_path = setXmlURL(args[0]);
		ExcelWriterService.excel_path = setXlsURL(args[1]);
		new XMLReaderService();

	}

}
