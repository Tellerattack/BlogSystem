package com.duan.blogos.service.dto.blog;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2018/9/13.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogDTO implements Serializable {

    //博文id
    private Long id;

    //博文所属博主id
    private Long bloggerId;

    //博文所属类别id
    private String categoryIds;

    //博文包含的标签
    private String labelIds;

    //文章状态
    private Integer state;

    //博文标题
    private String title;

    //博文主体内容
    private String content;

    //博文主体内容(md格式)
    private String contentMd;

    //博文摘要
    private String summary;

    //首次发布日期
    private Timestamp releaseDate;

    //最后一次修改时间
    private Timestamp nearestModifyDate;

    //博文关键字
    private String keyWords;

    //总字数
    private Integer wordCount;

}
