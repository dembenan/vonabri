
/*
 * Java dto for entity table production 
 * Created on 2022-07-27 ( Time 01:31:45 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.utils.dto.customize;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.palmafrique.vonabri.utils.dto.CommentaireDto;
import ci.palmafrique.vonabri.utils.dto.SiteDto;
import ci.palmafrique.vonabri.utils.dto.StockHuileDto;
import lombok.Data;

/**
 * DTO customize for table "production"
 * 
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class _ProductionDto {
	
	private List<CommentaireDto> itemsCommentaire;
	private List<SiteDto> itemsSiteLivraisions;
	private List<StockHuileDto> itemsStockHuile;

}
