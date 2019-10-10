/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/25 9:45]
 * @Version: [v1.0]
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class  PageVo <T> implements Serializable {

  @TableField("size")
  private long size;

  @TableField("total")
  private long total;

  @TableField("current")
  private long current;

  @TableField("pages")
  private long pages;

  @TableField("data")
  private List<T> data;


}
