
/*
 * Created on 18 avr. 2018 ( Time 16:55:28 )
 * Generator tool : Telosys Tools Generator ( version 2.1.1 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Param Config
 * 
 * @author SFL Back-End developper
 *
 */
@Component
@Data
public class ParamConfig {
	
	@Value("${config.session.interval}")
	public Integer sessionInterval;
	
	@Value("${config.cache.interval}")
	public Integer cacheInterval;
	
}