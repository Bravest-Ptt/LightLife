package ts.af2.lightlife.bean;

/**
 * 文章分类栏目属性
 */
public class ArticleClassify {
    /**
     * 文章id
     */
    public Integer id;
    /**
     * 文章类型
     */
    public String type;
    /**
     * 文章标题
     */
    public String title;
    /**
     * 文章是否感兴趣
     */
    public Boolean isMyInterest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getMyInterest() {
        return isMyInterest;
    }

    public void setMyInterest(Boolean isMyInterest) {
        this.isMyInterest = isMyInterest;
    }

}
