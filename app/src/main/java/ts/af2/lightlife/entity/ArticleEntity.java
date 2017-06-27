package ts.af2.lightlife.entity;

import java.io.Serializable;
import java.util.List;

public class ArticleEntity implements Serializable {
    /**
     * 类别 ID
     */
    private Integer articleCategoryId;
    /**
     * 类型
     */
    private String articleCategory;
    /**
     * 标记状态，如推荐之类的
     */
    private Integer mark;
    /**
     * 评论数量
     */
    private Integer commentNum;
    /**
     * ID
     */
    private Integer articleId;
    /**
     * 标题
     */
    private String title;
    /**
     * 源
     */
    private String source;
    /**
     * 发布时间
     */
    private Long publishTime;
    /**
     * 总结
     */
    private String summary;
    /**
     * 摘要
     */
    private String articleAbstract;
    /**
     * 评论
     */
    private String comment;
    /**
     * 特殊标签，如广告推广之类的，可以为空
     */
    private String local;
    /**
     * 图片列表字符串
     */
    private String picListString;
    /**
     * 图片1 URL
     */
    private String picOne;
    /**
     * 图片2 URL
     */
    private String picTwo;
    /**
     * 图片3 URL
     */
    private String picThr;
    /**
     * 图片列表
     */
    private List<String> picList;
    /**
     * 收藏状态
     */
    private Boolean collectStatus;
    /**
     * 喜欢状态
     */
    private Boolean likeStatus;

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPicListString() {
        return picListString;
    }

    public void setPicListString(String picListString) {
        this.picListString = picListString;
    }

    public String getPicOne() {
        return picOne;
    }

    public void setPicOne(String picOne) {
        this.picOne = picOne;
    }

    public String getPicTwo() {
        return picTwo;
    }

    public void setPicTwo(String picTwo) {
        this.picTwo = picTwo;
    }

    public String getPicThr() {
        return picThr;
    }

    public void setPicThr(String picThr) {
        this.picThr = picThr;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public Boolean getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Boolean collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
