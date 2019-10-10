/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.model.dto;

import java.util.List;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/24 14:34]
 * @Version: [v1.0]
 */
public class AbilityRegisterDto {

  private ApiDto abilityForm;

  private List<ApiParamDto> params;

  public List<ApiParamDto> getParams() {
    return params;
  }

  public void setParams(List<ApiParamDto> params) {
    this.params = params;
  }

  public ApiDto getAbilityForm() {
    return abilityForm;
  }

  public void setAbilityForm(ApiDto abilityForm) {
    this.abilityForm = abilityForm;
  }
}
