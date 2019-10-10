package com.ai.cloudframe.provider.sys.entity;

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
 * @since 2019-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_asiainfo_menu_element")
public class MenuElement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("BUTTON_ID")
    private String buttonId;

    @TableField("BUTTON_TITLE")
    private String buttonTitle;

    @TableField("BUTTON_NAME")
    private String buttonName;

    @TableField("MENU_ID")
    private String menuId;

    @TableField("MODE_CODE")
    private String modeCode;

    @TableField("ORDER")
    private Integer order;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
