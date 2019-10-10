/*
 * Copyright (c) 2019. AI
 */

package com.ai.cloudframe.common.config.properties;

import lombok.Data;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author tangsz
 */
@Data
@RefreshScope
public class GwRulesProperties {

	private String name;

	private int count;

	private int intervalSec;

}
