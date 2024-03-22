package com.hth.constants;


/*
    字面值(代码中的固定值)处理，把字面值都在这里定义成常量
*/
public class SystemConstants {
    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;

    /**
     * 文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 文章列表当前查询页数
     */
    public static final int ARTICLE_STATUS_CURRENT = 1;

    /**
     * 文章列表每页显示的数据条数
     */
    public static final int ARTICLE_STATUS_SIZE = 10;

    /**
     * 文章分类是正常分类状态
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";

    /**
     * 友链审核状态
     */
    public static final String Link_STATUS_NORMAL = "0";

    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友链评论
     */
    public static final String  LINK_COMMENT = "1";

    /**
     * 权限类型，菜单
     */
    public static final String TYPE_MENU = "C";

    /**
     * 权限类型，按钮
     */
    public static final String TYPE_BUTTON = "F";

    /**
     * 正常状态
     */
    public static final String  STATUS_NORMAL = "0";

    /**
     * 判断为管理员用户
     */
    public static final String ADMAIN = "1";
    /**
     * 根评论为-1
     */
    public static final String COMMENT_ROOT = "-1";
}
