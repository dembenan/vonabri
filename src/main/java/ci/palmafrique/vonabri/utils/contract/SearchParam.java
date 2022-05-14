
/*
 * Created on 2022-04-25 ( Time 03:19:02 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.contract;

import lombok.*;

/**
 * Search Param
 * 
 * @author Geo
 *
 */
@Data
@ToString
@NoArgsConstructor
public class SearchParam<T> {

	String	operator;
	T		start;
	T		end;
}
