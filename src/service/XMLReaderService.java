package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import model.FortifyDTO;

public class XMLReaderService {

	static String xml_path = "";

	public XMLReaderService(){

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("xls-generator.properties"));
		} catch (IOException e) {
			System.out.println("설정파일을 찾을 수 없습니다.\nxls-generator.properties파일을 확인해주시기 바랍니다.");
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

			ArrayList<FortifyDTO> list = new ArrayList<FortifyDTO>();

			String friority = null;
			String folder = null;
			String kingdom = null;
			String category = null;
			String source_filepath = null;
			String source_filenname = null;
			String source_linestart = null;
			String source_snippet = null;
			String source_targetfunction = null;
			String primary_filepath = null;
			String primary_filenname = null;
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
				}else if (event_type == XmlPullParser.TEXT && tag_start == true) {
					//각 태그별로 값을 가져온다.

					//GroupingSection 안의 내용만 확인
					if(tag_GroupingSection == true){

						//MajorAttributeSummary 안에서 MetaInfo 내용들 저장
						if(tag_MajorAttributeSummary == true){
							if(tag_MetaInfo == true){
								if(tag.equals("Name")){
									metainfo_name = xpp.getText();
								}else if(tag.equals("Value")){
									if(metainfo_name.equals("Abstract")){
										metainfo_abstract = xpp.getText();
									}else if(metainfo_name.equals("Explanation")){
										metainfo_explanation = xpp.getText();
									}else if(metainfo_name.equals("Recommendations")){
										metainfo_recommendations = xpp.getText();
									}else if(metainfo_name.equals("Tips")){
										metainfo_tips = xpp.getText();
									}
								}
							}
							if(tag.equals("MetaInfo")){
								tag_MetaInfo = true;
							}
						}else if(tag_Issue == true){
							if(tag_Primary == true){
								if(tag.equals("FileName")){
									primary_filenname = xpp.getText();
								}else if(tag.equals("FilePath")){
									primary_filepath = xpp.getText();
								}else if(tag.equals("LineStart")){
									primary_linestart = xpp.getText();
								}else if(tag.equals("Snippet")){
									primary_snippet = xpp.getText();
								}else if(tag.equals("TargetFunction")){
									primary_targetfunction = xpp.getText();
								}
							}else if(tag_Source == true){
								if(tag.equals("FileName")){
									source_filenname = xpp.getText();
								}else if(tag.equals("FilePath")){
									source_filepath = xpp.getText();
								}else if(tag.equals("LineStart")){
									source_linestart = xpp.getText();
								}else if(tag.equals("Snippet")){
									source_snippet = xpp.getText();
								}else if(tag.equals("TargetFunction")){
									source_targetfunction = xpp.getText();
								}
							}else if(tag_Tag == true){
								if(tag.equals("Value")){
									Issue_tag = xpp.getText();
								}
							}else if(tag_Comment == true){
								if(tag.equals("UserInfo")){
									userinfo = xpp.getText();
								}else if(tag.equals("Comment")){
									comment = xpp.getText();
								}
							}
							if(tag.equals("Friority")){
								friority = xpp.getText();
							}else if(tag.equals("Folder")){
								folder = xpp.getText();
							}else if(tag.equals("Kingdom")){
								kingdom = xpp.getText();
							}else if(tag.equals("Category")){
								category = xpp.getText();
							}else if(tag.equals("Abstract")){
								issue_abstract = xpp.getText();
							}else if(tag.equals("Primary")){
								tag_Primary = true;
							}else if(tag.equals("Source")){
								tag_Source = true;
							}else if(tag.equals("Tag")){
								tag_Tag = true;
							}else if(tag.equals("Comment")){
								tag_Comment = true;
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
					if(tag.equals("GroupingSection")){
						tag_GroupingSection = false;
						metainfo_abstract = null;
						metainfo_explanation = null;
						metainfo_recommendations = null;
						metainfo_tips = null;
					}else if(tag.equals("MajorAttributeSummary")){
						tag_MajorAttributeSummary = false;
					}else if(tag.equals("MetaInfo")){
						metainfo_name = null;
					}else if(tag.equals("Issue")){
						FortifyDTO entity = new FortifyDTO();
						entity.setFriority(friority);
						entity.setFolder(folder);
						entity.setKingdom(kingdom);
						entity.setCategory(category);
						entity.setIssue_abstract(issue_abstract);
						entity.setPrimary_filenname(primary_filenname);
						entity.setPrimary_filepath(primary_filepath);
						entity.setPrimary_linestart(primary_linestart);
						entity.setPrimary_snippet(primary_snippet);
						entity.setPrimary_targetfunction(primary_targetfunction);
						entity.setSource_filenname(source_filenname);
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
					}else if(tag.equals("Primary")){
						tag_Primary = false;
					}else if(tag.equals("Source")){
						tag_Source = false;
					}else if(tag.equals("Tag")){
						tag_Tag = false;
					}else if(tag.equals("Comment")){
						tag_Comment = false;
					}
				}
				event_type = xpp.next();
			}

			//엑셀파일을 쓰는 부분이다.
			new ExcelWriterService(properties).makeExcelFileParmList(list);

		} catch (Exception e) {
			System.out.println(xml_path + " 파일을 읽을 수 없습니다.");
//			e.printStackTrace();
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
