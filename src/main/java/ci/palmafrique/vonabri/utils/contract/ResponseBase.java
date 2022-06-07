
/*
 * Created on 2022-04-25 ( Time 03:19:02 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.contract;

import lombok.*;
import ci.palmafrique.vonabri.utils.Status;

/**
 * Response Base
 * 
 * @author Geo
 *
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseBase {

	protected Status	status = new Status();
	protected boolean	hasError;
	protected String	sessionUser;
	protected Integer	user;
	protected Long		count;
	protected Integer	code;
	protected String	message;
	protected String	generatePassword;
}
