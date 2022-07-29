/*
 * Created on 2018-04-14 ( Time 21:52:32 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.enums;

/**
 * 
 * @author Geo
 *
 */
 public enum UtilsEnum {
 	ELEMENT_TYPE_FORMULAIRE("FORMULAIRE"),
 	ELEMENT_TYPE_MENU("MENU"),
 	
 	
 	STATUS_FLASH_SUBMITING("SUBMITING"),
 	STATUS_FLASH_SAVING("SAVING");


	 

	private final String value;
 	public String getValue() {
 		return value;
 	}
 	private UtilsEnum(String value) {
 		this.value = value;
 	}
}
