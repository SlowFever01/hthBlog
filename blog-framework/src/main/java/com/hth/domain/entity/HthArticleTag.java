package com.hth.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 文章标签关联表(HthArticleTag)表实体类
 *
 * @author makejava
 * @since 2024-03-13 13:45:18
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hth_article_tag")
public class HthArticleTag  implements Serializable{
    private static final long serialVersionUID = 625337492348897098L;
    //文章id@TableId
    private Long articleId;
    //标签id@TableId
    private Long tagId;




}
