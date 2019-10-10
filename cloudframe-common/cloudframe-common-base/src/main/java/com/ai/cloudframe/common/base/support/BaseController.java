/*
 * Copyright (c) 2019. AI.
 */

package com.ai.cloudframe.common.base.support;

import com.ai.cloudframe.common.config.properties.CloudFrameProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @author tangsz
 */

@RequestMapping(value = "${cloudframe.restPath}")
@RefreshScope
public abstract  class BaseController {

	@Resource
	protected  CloudFrameProperties cloudFrameProperties;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
  