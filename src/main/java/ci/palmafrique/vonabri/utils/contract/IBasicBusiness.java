
/*
 * Created on 2022-04-25 ( Time 03:19:02 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.contract;

import java.util.Locale;

/**
 * IBasic Business
 * 
 * @author Geo
 *
 */
public interface IBasicBusiness<T, K> {

	/**
	 * create Object by using T as object.
	 * 
	 * @param T
	 * @return K
	 * 
	 */
	public abstract K create(T request, Locale locale);

	/**
	 * update Object by using T as object.
	 * 
	 * @param T
	 * @return K
	 * 
	 */
	public abstract K update(T request, Locale locale);

	/**
	 * delete Object by using T as object.
	 * 
	 * @param T
	 * @return K
	 * 
	 */
	public abstract K delete(T request, Locale locale);

	/**
	 * get a List of Object by using T as criteria object.
	 * 
	 * @param T
	 * @return K
	 * 
	 */
	public abstract K getByCriteria(T request, Locale locale);
}
