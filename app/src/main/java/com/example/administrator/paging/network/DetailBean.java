package com.example.administrator.paging.network;

import java.util.List;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/19
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class DetailBean {

    private static final long serialVersionUID = "DetailBean".hashCode();

    /**
     * image_source : 编辑瞎说的
     * title : 大误 · 同学，你也开发火星啊？
     * image : https://pic4.zhimg.com/v2-f36c859906126198a5e0785f0322e76f.jpg
     * share_url : http://daily.zhihu.com/story/9666603
     * js : []
     * id : 9666603
     * ga_prefix : 011812
     * images : ["https://pic1.zhimg.com/v2-38c5e0b5396c3222cec14c6bab7c0448.jpg"]
     * type : 0
     * section : {"thumbnail":"https://pic3.zhimg.com/v2-7dde91dd886f6d5b45472501c9c79452.jpg","name":"大误","id":29}
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private int id;
    private String ga_prefix;
    private int type;
    private SectionBean section;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SectionBean getSection() {
        return section;
    }

    public void setSection(SectionBean section) {
        this.section = section;
    }

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public static class SectionBean {
        /**
         * thumbnail : https://pic3.zhimg.com/v2-7dde91dd886f6d5b45472501c9c79452.jpg
         * name : 大误
         * id : 29
         */

        private String thumbnail;
        private String name;
        private int id;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
