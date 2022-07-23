/*
 * Created on 2022-04-25 ( Time 03:19:02 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.contract;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Request Base
 * 
 * @author Geo
 *
 */
@Data
@ToString
@NoArgsConstructor
public class RequestBase {
	protected String		sessionUser;
	protected Integer		size;
	protected Integer		index;
	protected String		lang;
	protected String		businessLineCode;
	protected String		caseEngine;
	protected Boolean		isAnd;
	protected Integer		user;
	protected String		token;
	protected Boolean 		isSimpleLoading;
	protected Boolean       hierarchyFormat;
	protected MultipartFile       file;
	protected String       fileName;

	
	

}