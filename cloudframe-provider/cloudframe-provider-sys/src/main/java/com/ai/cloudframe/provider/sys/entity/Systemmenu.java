package com.ai.cloudframe.provider.sys.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Automated procedures
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_s_systemmenu")
public class Systemmenu implements Serializable {


    @TableId("MENU_ID")
    private String menuId;

    @TableField("PARENT_MENU_ID")
    private String parentMenuId;

    @TableField("MENU_TITLE")
    private String menuTitle;

    @TableField("MENU_EXPLAIN")
    private String menuExplain;

    @TableField("ITEM_ORDER")
    private BigDecimal itemOrder;

    @TableField("CATEGORY")
    private String category;

    @TableField("SUBSYS_CODE")
    private String subsysCode;

    @TableField("MODE_CODE")
    private String modeCode;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
