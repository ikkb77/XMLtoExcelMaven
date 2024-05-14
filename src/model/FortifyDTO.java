package model;

public class FortifyDTO {
	private String friority;
	private String folder;
	private String kingdom;
	private String category;
	private String source_filepath;
	private String source_filename;
	private String source_linestart;
	private String source_snippet;
	private String source_targetfunction;
	private String primary_filepath;
	private String primary_filename;
	private String primary_linestart;
	private String primary_snippet;
	private String primary_targetfunction;
	private String issue_abstract;
	private String metainfo_abstract;
	private String metainfo_explanation;
	private String metainfo_recommendations;
	private String metainfo_tips;
	private String iid;
	private String ruleid;
	private String tag;
	private String userinfo;
	private String comment;
	private String scandate;

	public static String[] parameters = {"friority",
			"folder",
			"kingdom",
			"category",
			"source_filepath",
			"source_filename",
			"source_linestart",
			"source_snippet",
			"source_targetfunction",
			"primary_filepath",
			"primary_filename",
			"primary_linestart",
			"primary_snippet",
			"primary_targetfunction",
			"issue_abstract",
			"metainfo_abstract",
			"metainfo_explanation",
			"metainfo_recommendations",
			"metainfo_tips",
			"iid",
			"ruleid",
			"tag",
			"userinfo",
			"comment",
			"scandate"};

	public FortifyDTO() {
		this.friority = null;
		this.folder = null;
		this.kingdom = null;
		this.category = null;
		this.source_filepath = null;
		this.source_filename = null;
		this.source_linestart = null;
		this.source_snippet = null;
		this.source_targetfunction = null;
		this.primary_filepath = null;
		this.primary_filename = null;
		this.primary_linestart = null;
		this.primary_snippet = null;
		this.primary_targetfunction = null;
		this.issue_abstract = null;
		this.metainfo_abstract = null;
		this.metainfo_explanation = null;
		this.metainfo_recommendations = null;
		this.metainfo_tips = null;
		this.iid = null;
		this.ruleid = null;
		this.tag = null;
		this.userinfo = null;
		this.comment = null;
		this.scandate = null;
	}

	public FortifyDTO(String friority, String folder, String kingdom, String category, String source_filepath,
					  String source_filename, String source_linestart, String source_snippet, String source_targetfunction,
					  String primary_filepath, String primary_filename, String primary_linestart, String primary_snippet,
					  String primary_targetfunction, String issue_abstract, String metainfo_abstract, String metainfo_explanation,
					  String metainfo_recommendations, String metainfo_tips, String iid, String ruleid, String tag,
					  String userinfo, String comment, String scandate) {
		super();
		this.friority = friority;
		this.folder = folder;
		this.kingdom = kingdom;
		this.category = category;
		this.source_filepath = source_filepath;
		this.source_filename = source_filename;
		this.source_linestart = source_linestart;
		this.source_snippet = source_snippet;
		this.source_targetfunction = source_targetfunction;
		this.primary_filepath = primary_filepath;
		this.primary_filename = primary_filename;
		this.primary_linestart = primary_linestart;
		this.primary_snippet = primary_snippet;
		this.primary_targetfunction = primary_targetfunction;
		this.issue_abstract = issue_abstract;
		this.metainfo_abstract = metainfo_abstract;
		this.metainfo_explanation = metainfo_explanation;
		this.metainfo_recommendations = metainfo_recommendations;
		this.metainfo_tips = metainfo_tips;
		this.iid = iid;
		this.ruleid = ruleid;
		this.tag = tag;
		this.userinfo = userinfo;
		this.comment = comment;
		this.scandate = scandate;
	}
	public String getFriority() {
		return friority;
	}
	public void setFriority(String friority) {
		this.friority = friority;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getKingdom() {
		return kingdom;
	}
	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSource_filepath() {
		return source_filepath;
	}
	public void setSource_filepath(String source_filepath) {
		this.source_filepath = source_filepath;
	}
	public String getSource_filename() {
		return source_filename;
	}
	public void setSource_filename(String source_filename) {
		this.source_filename = source_filename;
	}
	public String getSource_linestart() {
		return source_linestart;
	}
	public void setSource_linestart(String source_linestart) {
		this.source_linestart = source_linestart;
	}
	public String getSource_snippet() {
		return source_snippet;
	}
	public void setSource_snippet(String source_snippet) {
		this.source_snippet = source_snippet;
	}
	public String getSource_targetfunction() {
		return source_targetfunction;
	}
	public void setSource_targetfunction(String source_targetfunction) {
		this.source_targetfunction = source_targetfunction;
	}
	public String getPrimary_filepath() {
		return primary_filepath;
	}
	public void setPrimary_filepath(String primary_filepath) {
		this.primary_filepath = primary_filepath;
	}
	public String getPrimary_filename() {
		return primary_filename;
	}
	public void setPrimary_filename(String primary_filename) {
		this.primary_filename = primary_filename;
	}
	public String getPrimary_linestart() {
		return primary_linestart;
	}
	public void setPrimary_linestart(String primary_linestart) {
		this.primary_linestart = primary_linestart;
	}
	public String getPrimary_snippet() {
		return primary_snippet;
	}
	public void setPrimary_snippet(String primary_snippet) {
		this.primary_snippet = primary_snippet;
	}
	public String getPrimary_targetfunction() {
		return primary_targetfunction;
	}
	public void setPrimary_targetfunction(String primary_targetfunction) {
		this.primary_targetfunction = primary_targetfunction;
	}
	public String getIssue_abstract() {
		return issue_abstract;
	}
	public void setIssue_abstract(String issue_abstract) {
		this.issue_abstract = issue_abstract;
	}
	public String getMetainfo_abstract() {
		return metainfo_abstract;
	}
	public void setMetainfo_abstract(String metainfo_abstract) {
		this.metainfo_abstract = metainfo_abstract;
	}
	public String getMetainfo_explanation() {
		return metainfo_explanation;
	}
	public void setMetainfo_explanation(String metainfo_explanation) {
		this.metainfo_explanation = metainfo_explanation;
	}
	public String getMetainfo_recommendations() {
		return metainfo_recommendations;
	}
	public void setMetainfo_recommendations(String metainfo_recommendations) {
		this.metainfo_recommendations = metainfo_recommendations;
	}
	public String getMetainfo_tips() {
		return metainfo_tips;
	}
	public void setMetainfo_tips(String metainfo_tips) {
		this.metainfo_tips = metainfo_tips;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getRuleid() {
		return ruleid;
	}
	public void setRuleid(String ruleid) {
		this.ruleid = ruleid;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getScanDate() { return scandate; }
	public void setScanDate(String scandate) { this.scandate = scandate; }
	public void setNull(){
		friority = null;
		folder = null;
		kingdom = null;
		category = null;
		source_filepath = null;
		source_filename = null;
		source_linestart = null;
		source_snippet = null;
		source_targetfunction = null;
		primary_filepath = null;
		primary_filename = null;
		primary_linestart = null;
		primary_snippet = null;
		primary_targetfunction = null;
		issue_abstract = null;
		metainfo_abstract = null;
		metainfo_explanation = null;
		metainfo_recommendations = null;
		metainfo_tips = null;
		iid = null;
		ruleid = null;
		tag = null;
		userinfo = null;
		comment = null;
		scandate = null;
	}


}
