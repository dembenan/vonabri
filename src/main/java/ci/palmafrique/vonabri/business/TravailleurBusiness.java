



                                                                                                                                                                        																																								

/*
 * Java transformer for entity table travailleur 
 * Created on 2022-06-11 ( Time 19:24:50 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.vonabri.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import ci.palmafrique.vonabri.dao.entity.Anciennete;
import ci.palmafrique.vonabri.dao.entity.Civilite;
import ci.palmafrique.vonabri.dao.entity.Direction;
import ci.palmafrique.vonabri.dao.entity.Employeur;
import ci.palmafrique.vonabri.dao.entity.Ethnie;
import ci.palmafrique.vonabri.dao.entity.Fonction;
import ci.palmafrique.vonabri.dao.entity.GestionDeBien;
import ci.palmafrique.vonabri.dao.entity.Nationnalite;
import ci.palmafrique.vonabri.dao.entity.Pays;
import ci.palmafrique.vonabri.dao.entity.PosteDeTravail;
import ci.palmafrique.vonabri.dao.entity.Profil;
import ci.palmafrique.vonabri.dao.entity.Regime;
import ci.palmafrique.vonabri.dao.entity.Site;
import ci.palmafrique.vonabri.dao.entity.SousPosteDeTravail;
import ci.palmafrique.vonabri.dao.entity.SousSite;
import ci.palmafrique.vonabri.dao.entity.Statut;
import ci.palmafrique.vonabri.dao.entity.StatutMatrimonial;
import ci.palmafrique.vonabri.dao.entity.Travailleur;
import ci.palmafrique.vonabri.dao.entity.TravailleurNationnalite;
import ci.palmafrique.vonabri.dao.entity.TypeMariage;
import ci.palmafrique.vonabri.dao.entity.User;
import ci.palmafrique.vonabri.dao.entity.UserType;
import ci.palmafrique.vonabri.dao.repository.AncienneteRepository;
import ci.palmafrique.vonabri.dao.repository.CiviliteRepository;
import ci.palmafrique.vonabri.dao.repository.DirectionRepository;
import ci.palmafrique.vonabri.dao.repository.EmployeurRepository;
import ci.palmafrique.vonabri.dao.repository.EthnieRepository;
import ci.palmafrique.vonabri.dao.repository.FonctionRepository;
import ci.palmafrique.vonabri.dao.repository.GestionDeBienRepository;
import ci.palmafrique.vonabri.dao.repository.NationnaliteRepository;
import ci.palmafrique.vonabri.dao.repository.PaysRepository;
import ci.palmafrique.vonabri.dao.repository.PosteDeTravailRepository;
import ci.palmafrique.vonabri.dao.repository.ProfilRepository;
import ci.palmafrique.vonabri.dao.repository.RegimeRepository;
import ci.palmafrique.vonabri.dao.repository.SiteRepository;
import ci.palmafrique.vonabri.dao.repository.SousPosteDeTravailRepository;
import ci.palmafrique.vonabri.dao.repository.SousSiteRepository;
import ci.palmafrique.vonabri.dao.repository.StatutMatrimonialRepository;
import ci.palmafrique.vonabri.dao.repository.StatutRepository;
import ci.palmafrique.vonabri.dao.repository.TravailleurNationnaliteRepository;
import ci.palmafrique.vonabri.dao.repository.TravailleurRepository;
import ci.palmafrique.vonabri.dao.repository.TypeMariageRepository;
import ci.palmafrique.vonabri.dao.repository.UserRepository;
import ci.palmafrique.vonabri.dao.repository.UserTypeRepository;
import ci.palmafrique.vonabri.rest.api.UserController;
import ci.palmafrique.vonabri.utils.ExceptionUtils;
import ci.palmafrique.vonabri.utils.FunctionalError;
import ci.palmafrique.vonabri.utils.ParamsUtils;
import ci.palmafrique.vonabri.utils.SmtpUtils;
import ci.palmafrique.vonabri.utils.TechnicalError;
import ci.palmafrique.vonabri.utils.Utilities;
import ci.palmafrique.vonabri.utils.Validate;
import ci.palmafrique.vonabri.utils.contract.IBasicBusiness;
import ci.palmafrique.vonabri.utils.contract.Request;
import ci.palmafrique.vonabri.utils.contract.Response;
import ci.palmafrique.vonabri.utils.dto.NationnaliteDto;
import ci.palmafrique.vonabri.utils.dto.TravailleurDto;
import ci.palmafrique.vonabri.utils.dto.TravailleurNationnaliteDto;
import ci.palmafrique.vonabri.utils.dto.UserDto;
import ci.palmafrique.vonabri.utils.dto.transformer.NationnaliteTransformer;
import ci.palmafrique.vonabri.utils.dto.transformer.TravailleurNationnaliteTransformer;
import ci.palmafrique.vonabri.utils.dto.transformer.TravailleurTransformer;
import ci.palmafrique.vonabri.utils.dto.transformer.UserTransformer;
import ci.palmafrique.vonabri.utils.enums.FunctionalityEnum;

import lombok.extern.java.Log;

/**
BUSINESS for table "travailleur"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class TravailleurBusiness implements IBasicBusiness<Request<TravailleurDto>, Response<TravailleurDto>> {

	private Response<TravailleurDto> response;
	@Autowired
	private TravailleurRepository travailleurRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SousPosteDeTravailRepository sousPosteDeTravailRepository;
	
	@Autowired
	private PaysRepository paysRepository;
	@Autowired
	private CiviliteRepository civiliteRepository;
	
	@Autowired
	private ProfilRepository profilRepository;
	
	@Autowired
	private UserTypeRepository userTypeRepository;

	
	@Autowired
	private EmployeurRepository employeurRepository;
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private AncienneteRepository anciennete2Repository;
	@Autowired
	private RegimeRepository regimeRepository;
	@Autowired
	private AncienneteRepository ancienneteRepository;
	@Autowired
	private TravailleurNationnaliteRepository travailleurNationnaliteRepository;
	@Autowired
	private EthnieRepository ethnie2Repository;
	@Autowired
	private StatutRepository statutRepository;
	@Autowired
	private EthnieRepository ethnieRepository;
	@Autowired
	private GestionDeBienRepository gestionDeBienRepository;
	@Autowired
	private PosteDeTravailRepository posteDeTravailRepository;
	@Autowired
	private FonctionRepository fonctionRepository;
	@Autowired
	private DirectionRepository directionRepository;
	@Autowired
	private TypeMariageRepository typeMariageRepository;
	@Autowired
	private StatutMatrimonialRepository statutMatrimonialRepository;
	@Autowired
	private SousSiteRepository sousSiteRepository;

	@Autowired
	private NationnaliteRepository nationnaliteRepository;
	
	private Context context;
	private final String ENTETE = "Vonabri";
	private final String TITRE = "Administrator";
	
	@Autowired
	private ParamsUtils paramsUtils;
	@Autowired
	private SmtpUtils smtpUtils;
	
    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private UserController userController;
    
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	
	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public TravailleurBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	/**
	 * create Travailleur by using TravailleurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<TravailleurDto> create(Request<TravailleurDto> request, Locale locale)  {
		log.info("----begin create Travailleur-----");
		
		response = new Response<TravailleurDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.CREATE_TRAVAILLEUR.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}
			
			List<Travailleur> items = new ArrayList<Travailleur>();

			//Map<String, User> users = new HashMap<String, User>();
			for (TravailleurDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("matricule", dto.getMatricule());
				fieldsToVerify.put("nom", dto.getNom());
				fieldsToVerify.put("prenom", dto.getPrenom());
				fieldsToVerify.put("email", dto.getEmail());
				fieldsToVerify.put("contact1", dto.getContact1());
				//fieldsToVerify.put("contact2", dto.getContact2());
				fieldsToVerify.put("domicile", dto.getDomicile());
				fieldsToVerify.put("dateDeNaissance", dto.getDateDeNaissance());
				fieldsToVerify.put("dateEmbauche", dto.getDateEmbauche());
				
				//fieldsToVerify.put("dateFinContrat", dto.getDateFinContrat());
				fieldsToVerify.put("lieuNaissance", dto.getLieuNaissance());
				fieldsToVerify.put("paysId", dto.getPaysId());
				fieldsToVerify.put("civiliteId", dto.getCiviliteId());
				
				fieldsToVerify.put("matricule", dto.getMatricule());
				
				fieldsToVerify.put("siteId", dto.getSiteId());
				fieldsToVerify.put("sousSiteId", dto.getSousSiteId());
				fieldsToVerify.put("statutId", dto.getStatutId());
				fieldsToVerify.put("employeurId", dto.getEmployeurId());
				fieldsToVerify.put("directionId", dto.getDirectionId());
				fieldsToVerify.put("fonctionId", dto.getFonctionId());
				fieldsToVerify.put("posteDeTravailId", dto.getPosteDeTravailId());
				fieldsToVerify.put("sousPosteDeTravailId", dto.getSousPosteDeTravailId());
				fieldsToVerify.put("ancienneteSocieteId", dto.getAncienneteSocieteId());
				fieldsToVerify.put("anciennetePosteId", dto.getAnciennetePosteId());
				fieldsToVerify.put("ethniePereId", dto.getEthniePereId());
				fieldsToVerify.put("ethnieMereId", dto.getEthnieMereId());
				fieldsToVerify.put("statutMatrimonialId", dto.getStatutMatrimonialId());
				fieldsToVerify.put("nationnalites", dto.getNationnalites());

				//fieldsToVerify.put("typeMariageId", dto.getTypeMariageId());
				//fieldsToVerify.put("regimeId", dto.getRegimeId());
				//fieldsToVerify.put("gestionDeBienId", dto.getGestionDeBienId());
				//fieldsToVerify.put("nombreEpouse", dto.getNombreEpouse());
				//fieldsToVerify.put("nombreEnfants", dto.getNombreEnfants());
				//fieldsToVerify.put("nombreFilles", dto.getNombreFilles());
				//fieldsToVerify.put("nombreGarcons", dto.getNombreGarcons());
				//fieldsToVerify.put("nombreAdoption", dto.getNombreAdoption());
				//fieldsToVerify.put("nombreEnfantSousTutelleDirect", dto.getNombreEnfantSousTutelleDirect());
				//fieldsToVerify.put("nombreEnfantSousTutelleIndirect", dto.getNombreEnfantSousTutelleIndirect());
				//fieldsToVerify.put("deletedAt", dto.getDeletedAt());
				//fieldsToVerify.put("deletedBy", dto.getDeletedBy());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField()+"  de "+dto.getNom(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if travailleur to insert do not exist
				Travailleur existingEntity = null;
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("travailleur id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// verif unique email in db
				existingEntity = travailleurRepository.findByEmail(dto.getEmail(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("travailleur email -> " + dto.getEmail(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique email in items to save
				if (items.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(dto.getEmail()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" email ", locale));
					response.setHasError(true);
					return response;
				}
				// verif unique matricule in db
				existingEntity = travailleurRepository.findByMatricule(dto.getMatricule(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("travailleur matricule -> " + dto.getMatricule(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique matricule in items to save
				if (items.stream().anyMatch(a -> a.getMatricule().equalsIgnoreCase(dto.getMatricule()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" matricule ", locale));
					response.setHasError(true);
					return response;
				}
				// Verify if pays exist
				Pays existingPays = null;
				if (dto.getPaysId() != null && dto.getPaysId() > 0){
					existingPays = paysRepository.findOne(dto.getPaysId(), false);
					if (existingPays == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("travailleur paysId -> " + dto.getPaysId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				
				// Verify if pays exist
				Civilite existingCivilite = null;
				if (dto.getCiviliteId() != null && dto.getCiviliteId() > 0){
					existingCivilite = civiliteRepository.findOne(dto.getCiviliteId(), false);
					if (existingCivilite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("travailleur civiliteId -> " + dto.getCiviliteId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if sousPosteDeTravail exist
				SousPosteDeTravail existingSousPosteDeTravail = null;
				if (dto.getSousPosteDeTravailId() != null && dto.getSousPosteDeTravailId() > 0){
					existingSousPosteDeTravail = sousPosteDeTravailRepository.findOne(dto.getSousPosteDeTravailId(), false);
					if (existingSousPosteDeTravail == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("sousPosteDeTravail sousPosteDeTravailId -> " + dto.getSousPosteDeTravailId(), locale));
						response.setHasError(true);
						return response;
					}
				}

				// Verify if employeur exist
				Employeur existingEmployeur = null;
				if (dto.getEmployeurId() != null && dto.getEmployeurId() > 0){
					existingEmployeur = employeurRepository.findOne(dto.getEmployeurId(), false);
					if (existingEmployeur == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("employeur employeurId -> " + dto.getEmployeurId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if site exist
				Site existingSite = null;
				if (dto.getSiteId() != null && dto.getSiteId() > 0){
					existingSite = siteRepository.findOne(dto.getSiteId(), false);
					if (existingSite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("site siteId -> " + dto.getSiteId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if anciennete2 exist
				Anciennete existingAncienneteSociete = null;
				if (dto.getAncienneteSocieteId() != null && dto.getAncienneteSocieteId() > 0){
					existingAncienneteSociete = anciennete2Repository.findOne(dto.getAncienneteSocieteId(), false);
					if (existingAncienneteSociete == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("anciennete2 ancienneteSocieteId -> " + dto.getAncienneteSocieteId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if regime exist
				Regime existingRegime = null;
				if (dto.getRegimeId() != null && dto.getRegimeId() > 0){
					existingRegime = regimeRepository.findOne(dto.getRegimeId(), false);
					if (existingRegime == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("regime regimeId -> " + dto.getRegimeId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if anciennete exist
				Anciennete existingAnciennetePoste = null;
				if (dto.getAnciennetePosteId() != null && dto.getAnciennetePosteId() > 0){
					existingAnciennetePoste = ancienneteRepository.findOne(dto.getAnciennetePosteId(), false);
					if (existingAnciennetePoste == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("anciennete anciennetePosteId -> " + dto.getAnciennetePosteId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if ethnie2 exist
				Ethnie existingEthniePere = null;
				if (dto.getEthniePereId() != null && dto.getEthniePereId() > 0){
					existingEthniePere = ethnie2Repository.findOne(dto.getEthniePereId(), false);
					if (existingEthniePere == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("ethnie2 ethniePereId -> " + dto.getEthniePereId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if statut exist
				Statut existingStatut = null;
				if (dto.getStatutId() != null && dto.getStatutId() > 0){
					existingStatut = statutRepository.findOne(dto.getStatutId(), false);
					if (existingStatut == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("statut statutId -> " + dto.getStatutId(), locale));
						response.setHasError(true);
						return response;
					}
					if (!existingStatut.getCode().equals("CDI")) {
						Map<String, java.lang.Object> fieldsToVerifyStatus = new HashMap<String, java.lang.Object>();
						fieldsToVerifyStatus.put("dateFinContrat", dto.getDateFinContrat());
						if (!Validate.RequiredValue(fieldsToVerifyStatus).isGood()) {
							response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
							response.setHasError(true);
							return response;
						}
					}else {
						dto.setDateFinContrat(null);		
				    }
				}
				// Verify if ethnie exist
				Ethnie existingEthnieMere = null;
				if (dto.getEthnieMereId() != null && dto.getEthnieMereId() > 0){
					existingEthnieMere = ethnieRepository.findOne(dto.getEthnieMereId(), false);
					if (existingEthnieMere == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("ethnie ethnieMereId -> " + dto.getEthnieMereId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if gestionDeBien exist
				GestionDeBien existingGestionDeBien = null;
				if (dto.getGestionDeBienId() != null && dto.getGestionDeBienId() > 0){
					existingGestionDeBien = gestionDeBienRepository.findOne(dto.getGestionDeBienId(), false);
					if (existingGestionDeBien == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("gestionDeBien gestionDeBienId -> " + dto.getGestionDeBienId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if posteDeTravail exist
				PosteDeTravail existingPosteDeTravail = null;
				if (dto.getPosteDeTravailId() != null && dto.getPosteDeTravailId() > 0){
					existingPosteDeTravail = posteDeTravailRepository.findOne(dto.getPosteDeTravailId(), false);
					if (existingPosteDeTravail == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("posteDeTravail posteDeTravailId -> " + dto.getPosteDeTravailId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if fonction exist
				Fonction existingFonction = null;
				if (dto.getFonctionId() != null && dto.getFonctionId() > 0){
					existingFonction = fonctionRepository.findOne(dto.getFonctionId(), false);
					if (existingFonction == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("fonction fonctionId -> " + dto.getFonctionId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if direction exist
				Direction existingDirection = null;
				if (dto.getDirectionId() != null && dto.getDirectionId() > 0){
					existingDirection = directionRepository.findOne(dto.getDirectionId(), false);
					if (existingDirection == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("direction directionId -> " + dto.getDirectionId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if typeMariage exist
				TypeMariage existingTypeMariage = null;
				if (dto.getTypeMariageId() != null && dto.getTypeMariageId() > 0){
					existingTypeMariage = typeMariageRepository.findOne(dto.getTypeMariageId(), false);
					if (existingTypeMariage == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("typeMariage typeMariageId -> " + dto.getTypeMariageId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if statutMatrimonial exist
				StatutMatrimonial existingStatutMatrimonial = null;
				if (dto.getStatutMatrimonialId() != null && dto.getStatutMatrimonialId() > 0){
					existingStatutMatrimonial = statutMatrimonialRepository.findOne(dto.getStatutMatrimonialId(), false);
					if (existingStatutMatrimonial == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("statutMatrimonial statutMatrimonialId -> " + dto.getStatutMatrimonialId(), locale));
						response.setHasError(true);
						return response;
					}
				}
				// Verify if sousSite exist
				SousSite existingSousSite = null;
				if (dto.getSousSiteId() != null && dto.getSousSiteId() > 0){
					existingSousSite = sousSiteRepository.findOne(dto.getSousSiteId(), false);
					if (existingSousSite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("sousSite sousSiteId -> " + dto.getSousSiteId(), locale));
						response.setHasError(true);
						return response;
					}
				}

				Travailleur entityToSave = null;
				entityToSave = TravailleurTransformer.INSTANCE.toEntity(dto, existingSousPosteDeTravail, existingEmployeur, existingSite, existingAncienneteSociete, existingRegime, existingAnciennetePoste, existingEthniePere, existingStatut, existingEthnieMere, existingGestionDeBien, existingPosteDeTravail, existingFonction, existingDirection, existingTypeMariage, existingStatutMatrimonial, existingSousSite,existingPays,existingCivilite);
				entityToSave.setCreatedAt(Utilities.getCurrentDate());
				entityToSave.setCreatedBy(request.getUser());
				entityToSave.setIsDeleted(false);
				Travailleur Saved = travailleurRepository.save(entityToSave);
				if (Saved == null) {
					response.setStatus(functionalError.SAVE_FAIL("Travailleur Travailleur", locale));
					response.setHasError(true);
					return response;
				}
				for (NationnaliteDto nationnalite : dto.getNationnalites()) {
					if(nationnalite.getId() != null && nationnalite.getId() >0) {
						Nationnalite existingNationnalite = null;
						existingNationnalite = nationnaliteRepository.findOne(nationnalite.getId(), false);
						if (existingNationnalite == null) {
							response.setStatus(functionalError.DATA_NOT_EXIST("nationnaliteId  -> " + nationnalite.getId(), locale));
							response.setHasError(true);
							return response;
						}
						TravailleurNationnalite travailleurNationnalite = new TravailleurNationnalite();
						travailleurNationnalite.setNationnalite(existingNationnalite);
						travailleurNationnalite.setTravailleur(Saved);
						travailleurNationnalite.setUpdatedAt(Utilities.getCurrentDate());
						travailleurNationnalite.setUpdatedBy(request.getUser());
						travailleurNationnalite.setIsDeleted(false);
						TravailleurNationnalite travailleurNationnaliteSaved = travailleurNationnaliteRepository.save(travailleurNationnalite);
						if (travailleurNationnaliteSaved == null) {
							response.setStatus(functionalError.SAVE_FAIL("travailleur Nationnalite pour la nationalité "+ nationnalite.getId(), locale));
							response.setHasError(true);
							return response;
						}
						System.out.println(travailleurNationnaliteSaved);
					}
				}
				if (dto.getProfilId() != null && dto.getProfilId() > 0 && dto.getUserTypeId() != null && dto.getUserTypeId() > 0){
					Profil userProfil = profilRepository.findOne(dto.getProfilId(), false);
					if (userProfil == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("userProfil userProfil -> " + dto.getProfilId(), locale));
						response.setHasError(true);
						return response;
					}
					UserType userType = userTypeRepository.findOne(dto.getUserTypeId(), false);
					if (userType == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("userType userType -> " + dto.getUserTypeId(), locale));
						response.setHasError(true);
						return response;
					}
					UserDto userDto = new UserDto();
					if (dto.getIsSuperAdmin() != null) {
						userDto.setIsSuperAdmin(dto.getIsSuperAdmin());
					}
					userDto.setEmail(dto.getEmail());
					User userEntityToSave = null;
					userEntityToSave = UserTransformer.INSTANCE.toEntity(userDto, userType, userProfil,Saved);
					String password = Utilities.generateAlphabeticCode(8);
					userEntityToSave.setCreatedAt(Utilities.getCurrentDate());
					userEntityToSave.setCreatedBy(request.getUser());
					userEntityToSave.setIsDeleted(false);
					User userSaved = userRepository.save(userEntityToSave);
					if (userSaved == null) {
						response.setStatus(functionalError.SAVE_FAIL("User", locale));
						response.setHasError(true);
						return response;
					}
					if (Utilities.notBlank(userSaved.getEmail())) {
						// set mail to user
						Map<String, String> from = new HashMap<>();
						from.put("email", paramsUtils.getSmtpLogin());
						from.put("user", ENTETE);

						List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
						Map<String, String> recipient = new HashMap<String, String>();
						recipient = new HashMap<String, String>();
						recipient.put("email", userSaved.getEmail());
						// recipient.put("user", user.getLogin());
						toRecipients.add(recipient);
						// choisir la vraie url
						String appLink = paramsUtils.getUrlAdmin();
						// subject
						String subject = "Vonabri access";
						context = new Context();
						String template = "mail_new_mdp";
						context.setVariable("email", userSaved.getEmail());
						context.setVariable("entete", ENTETE);
						context.setVariable("appLink", appLink);
						context.setVariable("password", password);
						context.setVariable("date", dateTimeFormat.format(new Date()));
						log.info("********************* from " + from);
						log.info("********************* Recipeints " + toRecipients);
						log.info("********************* subject " + toRecipients);
						log.info("********************* context " + context);
						smtpUtils.sendEmail(from, toRecipients, subject, null, null, context, template, null);
					}
					
				}

				items.add(Saved);
			}
			
			if (!items.isEmpty()) {
				List<Travailleur> itemsSaved = items;
				// inserer les donnees en base de donnees
//				itemsSaved = travailleurRepository.saveAll((Iterable<Travailleur>) items);
//				if (itemsSaved == null) {
//					response.setStatus(functionalError.SAVE_FAIL("travailleur", locale));
//					response.setHasError(true);
//					return response;
//				}

				List<TravailleurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TravailleurTransformer.INSTANCE.toLiteDtos(itemsSaved) : TravailleurTransformer.INSTANCE.toDtos(itemsSaved);
				final int size = itemsSaved.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setHasError(false);
			}
			log.info("----end create Travailleur-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}
	
	public static Cell getCell(Row row, Integer index) {
		return (row.getCell(index) == null) ? row.createCell(index) : row.getCell(index);
	}
	
//	// Creation de Travailleur en masse 
//	public Response<TravailleurDto> creationEnMasse(Request<TravailleurDto> request,String file_name_full) throws IOException {
//		log.info("----begin creationEnMasse Travailleur-----");
//		Response<TravailleurDto> response = new Response<TravailleurDto>();
//		//		List<Rechargement> itemsToSaveRecgharge = new ArrayList<Rechargement>();
//		List<Travailleur> itemsToSaveTravailleur = new ArrayList<Travailleur>();
//		List<TravailleurDto> datas = new ArrayList<TravailleurDto>();
//
//		Locale locale = new Locale("fr");
//		try {
//			
//			FileInputStream fileInputStream = new FileInputStream(new File(file_name_full));
//			System.out.println("--pathFile--" + file_name_full);
//			Iterator<Row> iterator = null;
//			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//			iterator = (Iterator<Row>) sheet.iterator();				
//			while (iterator.hasNext()) {
//				TravailleurDto travailleurDto = new TravailleurDto();
//				Cell cell = null;
//				final Row currentRow = iterator.next();
//				if (currentRow.getRowNum() >= 1) {
//					String matricule = null;
//					cell = getCell(currentRow, 0);
//					if (Utilities.isCellEmpty(cell)) {
//						matricule = "G-" + Utilities.generateAlphanumericCode(4);
//						// verif unique code in db
//						Travailleur existingEntity = travailleurRepository.findByMatricule(matricule, false);
//						while (existingEntity != null) {
//							matricule = "G-" + Utilities.generateAlphanumericCode(4);
//						}
//					}
//
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						matricule = cell.getStringCellValue();
//						System.out.println("matricule ===>"+matricule);
//						travailleurDto.setMatricule(matricule);
//						//travailleur.setLibelle(matricule);
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						matricule = cellValueInString;
//						System.out.println("matricule ===>"+matricule);
//						travailleurDto.setMatricule(matricule);
//						//travailleur.setLibelle(matricule);;
//						break;
//					default:
//						
//						break;
//
//					}
//
//					String nom = null;
//					cell = getCell(currentRow, 1);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						nom = cell.getStringCellValue();
//						travailleurDto.setNom(nom);	
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						nom = cellValueInString;
//						travailleurDto.setNom(nom);	
//						break;
//					default:
//						break;
//
//					}
//
//					String prenom = null;
//					cell = getCell(currentRow, 2);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						prenom = cell.getStringCellValue();
//						travailleurDto.setPrenom(prenom);
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						prenom = cellValueInString ;
//						travailleurDto.setPrenom(prenom);
//					default:
//						break;	
//					}
//
//					String email = null;
//					cell = getCell(currentRow, 3);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						email = cell.getStringCellValue();
//						travailleurDto.setEmail(email);
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						email = cellValueInString ;
//						travailleurDto.setEmail(email);
//						break;
//					default:
//						break;	
//					}
//
//
//					String contact1 = null;
//					cell = getCell(currentRow, 4);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						contact1 = cell.getStringCellValue();
//						travailleurDto.setContact1(contact1);
//						
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						contact1 = cellValueInString;
//						travailleurDto.setContact1(contact1);
//						break;
//					default:
//						break;
//
//					}
//
//					String contact2 = null;
//					cell = getCell(currentRow, 5);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						contact2 = cell.getStringCellValue();
//						travailleurDto.setContact2(contact2);
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						contact2 = cellValueInString;
//						travailleurDto.setContact2(contact2);
//						break;
//					default:
//						break;
//
//					}
//					String domicile = null;
//					cell = getCell(currentRow, 6);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						domicile = cell.getStringCellValue();
//						travailleurDto.setDomicile(domicile);
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						domicile = cellValueInString;
//						travailleurDto.setDomicile(domicile);
//						break;
//					default:
//						break;
//
//					}
//					String dateDeNaissance = null;
//					cell = getCell(currentRow, 7);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						dateDeNaissance = cell.getStringCellValue();
//						travailleurDto.setDateDeNaissance(dateDeNaissance);
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						dateDeNaissance = cellValueInString;
//						travailleurDto.setDateDeNaissance(dateDeNaissance);
//						break;
//					default:
//						break;
//
//					}
//					String dateEmbauche = null;
//					cell = getCell(currentRow, 8);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						dateEmbauche = cell.getStringCellValue();
//						travailleurDto.setDateEmbauche(dateEmbauche);
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						dateEmbauche = cellValueInString;
//						travailleurDto.setDateEmbauche(dateEmbauche);
//						break;
//					default:
//						break;
//
//					}
//					String lieuNaissance = null;
//					cell = getCell(currentRow, 9);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						lieuNaissance = cell.getStringCellValue();
//						travailleurDto.setLieuNaissance(lieuNaissance);
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						lieuNaissance = cellValueInString;
//						travailleurDto.setLieuNaissance(lieuNaissance);
//						break;
//					default:
//						break;
//
//					}
//					String pays = null;
//					Pays existingPays = null;
//					cell = getCell(currentRow, 10);
//					if (Utilities.isCellEmpty(cell)) {
//						response.setStatus(functionalError.DISALLOWED_OPERATION("Cellule non renseignée à la ligne  "+currentRow.getRowNum() , locale));
//						response.setHasError(true);
//						return response;
//					}
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue());
//						pays = cell.getStringCellValue();
//					    existingPays = paysRepository.findByLibelle(pays, false);
//
//						break;
//
//					case NUMERIC:
//						String cellValueInString = new BigDecimal(cell.getNumericCellValue()).toPlainString();
//						System.out.println(cellValueInString);
//						pays = cellValueInString;
//						existingPays = paysRepository.findByLibelle(pays, false);
//						break;
//					default:
//						break;
//
//					//travailleurDto.setPaysId(existingPays.getId());
//					
//
//				}
//				}
//					datas.add(travailleurDto);
//
//				}
//
//
//			if (Utilities.isNotEmpty(itemsToSaveTravailleur)) {
//
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return response;
//	}

	/**
	 * update Travailleur by using TravailleurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<TravailleurDto> update(Request<TravailleurDto> request, Locale locale)  {
		log.info("----begin update Travailleur-----");
		
		response = new Response<TravailleurDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.UPDATE_TRAVAILLEUR.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Travailleur> items = new ArrayList<Travailleur>();
			List<Nationnalite> itemsNationnalite = new ArrayList<Nationnalite>();
			List<NationnaliteDto> itemsNationnaliteDto = new ArrayList<NationnaliteDto>();
			
			for (TravailleurDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}
				// Verifier si la travailleur existe
				Travailleur entityToSave = null;
				entityToSave = travailleurRepository.findOne(dto.getId(), false);
				if (entityToSave == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("travailleur id -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if sousPosteDeTravail exist
				if (dto.getSousPosteDeTravailId() != null && dto.getSousPosteDeTravailId() > 0){
					SousPosteDeTravail existingSousPosteDeTravail = sousPosteDeTravailRepository.findOne(dto.getSousPosteDeTravailId(), false);
					if (existingSousPosteDeTravail == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("sousPosteDeTravail sousPosteDeTravailId -> " + dto.getSousPosteDeTravailId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setSousPosteDeTravail(existingSousPosteDeTravail);
				}
				// Verify if employeur exist
				if (dto.getEmployeurId() != null && dto.getEmployeurId() > 0){
					Employeur existingEmployeur = employeurRepository.findOne(dto.getEmployeurId(), false);
					if (existingEmployeur == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("employeur employeurId -> " + dto.getEmployeurId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setEmployeur(existingEmployeur);
				}
				// Verify if site exist
				if (dto.getSiteId() != null && dto.getSiteId() > 0){
					Site existingSite = siteRepository.findOne(dto.getSiteId(), false);
					if (existingSite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("site siteId -> " + dto.getSiteId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setSite(existingSite);
				}
				// Verify if anciennete2 exist
				if (dto.getAncienneteSocieteId() != null && dto.getAncienneteSocieteId() > 0){
					Anciennete existingAncienneteSociete = anciennete2Repository.findOne(dto.getAncienneteSocieteId(), false);
					if (existingAncienneteSociete == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("anciennete2 ancienneteSocieteId -> " + dto.getAncienneteSocieteId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setAncienneteSociete(existingAncienneteSociete);
				}
				// Verify if regime exist
				if (dto.getRegimeId() != null && dto.getRegimeId() > 0){
					Regime existingRegime = regimeRepository.findOne(dto.getRegimeId(), false);
					if (existingRegime == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("regime regimeId -> " + dto.getRegimeId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setRegime(existingRegime);
				}
				// Verify if anciennete exist
				if (dto.getAnciennetePosteId() != null && dto.getAnciennetePosteId() > 0){
					Anciennete existingAnciennetePoste = ancienneteRepository.findOne(dto.getAnciennetePosteId(), false);
					if (existingAnciennetePoste == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("anciennete anciennetePosteId -> " + dto.getAnciennetePosteId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setAnciennetePoste(existingAnciennetePoste);
				}
				// Verify if ethnie2 exist
				if (dto.getEthniePereId() != null && dto.getEthniePereId() > 0){
					Ethnie existingEthniePere = ethnie2Repository.findOne(dto.getEthniePereId(), false);
					if (existingEthniePere == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("ethnie2 ethniePereId -> " + dto.getEthniePereId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setEthniePere(existingEthniePere);
				}
				// Verify if statut exist
				if (dto.getStatutId() != null && dto.getStatutId() > 0){
					Statut existingStatut = statutRepository.findOne(dto.getStatutId(), false);
					if (existingStatut == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("statut statutId -> " + dto.getStatutId(), locale));
						response.setHasError(true);
						return response;
					}
					if (!existingStatut.getCode().equals("CDI")) {
						Map<String, java.lang.Object> fieldsToVerifyStatus = new HashMap<String, java.lang.Object>();
						fieldsToVerifyStatus.put("dateFinContrat", dto.getDateFinContrat());
						if (!Validate.RequiredValue(fieldsToVerifyStatus).isGood()) {
							response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
							response.setHasError(true);
							return response;
						}
					}else {
						dto.setDateFinContrat(null);		
				    }
					entityToSave.setStatut(existingStatut);
				}
				// Verify if ethnie exist
				if (dto.getEthnieMereId() != null && dto.getEthnieMereId() > 0){
					Ethnie existingEthnieMere = ethnieRepository.findOne(dto.getEthnieMereId(), false);
					if (existingEthnieMere == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("ethnie ethnieMereId -> " + dto.getEthnieMereId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setEthnieMere(existingEthnieMere);
				}
				// Verify if gestionDeBien exist
				if (dto.getGestionDeBienId() != null && dto.getGestionDeBienId() > 0){
					GestionDeBien existingGestionDeBien = gestionDeBienRepository.findOne(dto.getGestionDeBienId(), false);
					if (existingGestionDeBien == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("gestionDeBien gestionDeBienId -> " + dto.getGestionDeBienId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setGestionDeBien(existingGestionDeBien);
				}
				// Verify if posteDeTravail exist
				if (dto.getPosteDeTravailId() != null && dto.getPosteDeTravailId() > 0){
					PosteDeTravail existingPosteDeTravail = posteDeTravailRepository.findOne(dto.getPosteDeTravailId(), false);
					if (existingPosteDeTravail == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("posteDeTravail posteDeTravailId -> " + dto.getPosteDeTravailId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setPosteDeTravail(existingPosteDeTravail);
				}
				// Verify if fonction exist
				if (dto.getFonctionId() != null && dto.getFonctionId() > 0){
					Fonction existingFonction = fonctionRepository.findOne(dto.getFonctionId(), false);
					if (existingFonction == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("fonction fonctionId -> " + dto.getFonctionId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setFonction(existingFonction);
				}
				// Verify if direction exist
				if (dto.getDirectionId() != null && dto.getDirectionId() > 0){
					Direction existingDirection = directionRepository.findOne(dto.getDirectionId(), false);
					if (existingDirection == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("direction directionId -> " + dto.getDirectionId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setDirection(existingDirection);
				}
				// Verify if typeMariage exist
				if (dto.getTypeMariageId() != null && dto.getTypeMariageId() > 0){
					TypeMariage existingTypeMariage = typeMariageRepository.findOne(dto.getTypeMariageId(), false);
					if (existingTypeMariage == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("typeMariage typeMariageId -> " + dto.getTypeMariageId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setTypeMariage(existingTypeMariage);
				}
				// Verify if statutMatrimonial exist
				if (dto.getStatutMatrimonialId() != null && dto.getStatutMatrimonialId() > 0){
					StatutMatrimonial existingStatutMatrimonial = statutMatrimonialRepository.findOne(dto.getStatutMatrimonialId(), false);
					if (existingStatutMatrimonial == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("statutMatrimonial statutMatrimonialId -> " + dto.getStatutMatrimonialId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setStatutMatrimonial(existingStatutMatrimonial);
				}
				// Verify if sousSite exist
				if (dto.getSousSiteId() != null && dto.getSousSiteId() > 0){
					SousSite existingSousSite = sousSiteRepository.findOne(dto.getSousSiteId(), false);
					if (existingSousSite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("sousSite sousSiteId -> " + dto.getSousSiteId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setSousSite(existingSousSite);
				}
				// Verify if sousSite exist
				if (dto.getPaysId() != null && dto.getPaysId() > 0){
					Pays existingPays = paysRepository.findOne(dto.getPaysId(), false);
					if (existingPays == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("pays  -> " + dto.getPaysId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setPays(existingPays);
				}
				if (dto.getCiviliteId() != null && dto.getCiviliteId() > 0){
					Civilite existingCivilite = civiliteRepository.findOne(dto.getCiviliteId(), false);
					if (existingCivilite == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("civilite  -> " + dto.getCiviliteId(), locale));
						response.setHasError(true);
						return response;
					}
					entityToSave.setCivilite(existingCivilite);
				}
				if (Utilities.notBlank(dto.getNom())) {
					entityToSave.setNom(dto.getNom());
				}
				if (Utilities.notBlank(dto.getPrenom())) {
					entityToSave.setPrenom(dto.getPrenom());
				}
				if (Utilities.notBlank(dto.getEmail())) {
					entityToSave.setEmail(dto.getEmail());
				}
				if (Utilities.notBlank(dto.getContact1())) {
					entityToSave.setContact1(dto.getContact1());
				}
				if (Utilities.notBlank(dto.getContact2())) {
					entityToSave.setContact2(dto.getContact2());
				}
				if (Utilities.notBlank(dto.getDomicile())) {
					entityToSave.setDomicile(dto.getDomicile());
				}
				if (Utilities.notBlank(dto.getDateEmbauche())) {
					entityToSave.setDateEmbauche(dateFormat.parse(dto.getDateEmbauche()));
				}
				if (Utilities.notBlank(dto.getDateFinContrat())) {
					entityToSave.setDateFinContrat(dateFormat.parse(dto.getDateFinContrat()));
				}
				if (Utilities.notBlank(dto.getDateDeNaissance())) {
					entityToSave.setDateDeNaissance(dateFormat.parse(dto.getDateDeNaissance()));
				}
				if (Utilities.notBlank(dto.getLieuNaissance())) {
					entityToSave.setLieuNaissance(dto.getLieuNaissance());
				}
				if (Utilities.notBlank(dto.getMatricule())) {
					entityToSave.setMatricule(dto.getMatricule());
				}
				if (dto.getNombreEpouse() != null && dto.getNombreEpouse() > 0) {
					entityToSave.setNombreEpouse(dto.getNombreEpouse());
				}
				if (dto.getNombreEnfants() != null && dto.getNombreEnfants() > 0) {
					entityToSave.setNombreEnfants(dto.getNombreEnfants());
				}
				if (dto.getNombreFilles() != null && dto.getNombreFilles() > 0) {
					entityToSave.setNombreFilles(dto.getNombreFilles());
				}
				if (dto.getNombreGarcons() != null && dto.getNombreGarcons() > 0) {
					entityToSave.setNombreGarcons(dto.getNombreGarcons());
				}
				if (dto.getNombreAdoption() != null && dto.getNombreAdoption() > 0) {
					entityToSave.setNombreAdoption(dto.getNombreAdoption());
				}
				if (dto.getNombreEnfantSousTutelleDirect() != null && dto.getNombreEnfantSousTutelleDirect() > 0) {
					entityToSave.setNombreEnfantSousTutelleDirect(dto.getNombreEnfantSousTutelleDirect());
				}
				if (dto.getNombreEnfantSousTutelleIndirect() != null && dto.getNombreEnfantSousTutelleIndirect() > 0) {
					entityToSave.setNombreEnfantSousTutelleIndirect(dto.getNombreEnfantSousTutelleIndirect());
				}
				if (Utilities.notBlank(dto.getDeletedAt())) {
					entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
				}
				if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
					entityToSave.setCreatedBy(dto.getCreatedBy());
				}
				if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
					entityToSave.setUpdatedBy(dto.getUpdatedBy());
				}
				if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
					entityToSave.setDeletedBy(dto.getDeletedBy());
				}
				if (dto.getProfilId() != null && dto.getProfilId() > 0 ){
					Profil userProfil = profilRepository.findOne(dto.getProfilId(), false);
					if (userProfil == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("userProfil userProfil -> " + dto.getProfilId(), locale));
						response.setHasError(true);
						return response;
					}
					User user = userRepository.findByTravailleurId(dto.getId(), false);
					if(user != null) {
						user.setProfil(userProfil);
						User userSave = userRepository.save(user);
					}
				}
				if (dto.getUserTypeId() != null && dto.getUserTypeId() > 0 ){
					UserType userType = userTypeRepository.findOne(dto.getUserTypeId(), false);
					if (userType == null) {
						response.setStatus(functionalError.DATA_NOT_EXIST("userType userType -> " + dto.getUserTypeId(), locale));
						response.setHasError(true);
						return response;
					}
					User user = userRepository.findByTravailleurId(dto.getId(), false);
					if(user != null) {
						user.setUserType(userType);
						User userSave = userRepository.save(user);
					}
				}
				if (dto.getIsSuperAdmin() != null) {
					User user = userRepository.findByTravailleurId(dto.getId(), false);
					if(user != null) {
						user.setIsSuperAdmin(dto.getIsSuperAdmin());
						User userSave = userRepository.save(user);
					}
				}
				if (!dto.getNationnalites().isEmpty()) {
					for (NationnaliteDto nationnalite : dto.getNationnalites()) {
						Nationnalite existingNationnalite = null;
						existingNationnalite = nationnaliteRepository.findOne(nationnalite.getId(), false);
						if (existingNationnalite == null) {
							response.setStatus(functionalError.DATA_NOT_EXIST("nationnaliteId  -> " + nationnalite.getId(), locale));
							response.setHasError(true);
							return response;
						}
						TravailleurNationnalite travailleurNationnalite = null;
						travailleurNationnalite = TravailleurNationnaliteTransformer.INSTANCE.toEntity(new TravailleurNationnaliteDto(), existingNationnalite,entityToSave);
						travailleurNationnalite.setUpdatedAt(Utilities.getCurrentDate());
						travailleurNationnalite.setUpdatedBy(request.getUser());						
						TravailleurNationnalite travailleurNationnaliteSaved = travailleurNationnaliteRepository.save(travailleurNationnalite);
						if (travailleurNationnaliteSaved == null) {
							response.setStatus(functionalError.SAVE_FAIL("travailleur Nationnalite", locale));
							response.setHasError(true);
							return response;
						}
					}
				}
				entityToSave.setUpdatedAt(Utilities.getCurrentDate());
				entityToSave.setUpdatedBy(request.getUser());
				items.add(entityToSave);
			}

			if (!items.isEmpty()) {
				List<Travailleur> itemsSaved = null;
				// maj les donnees en base
				itemsSaved = travailleurRepository.saveAll((Iterable<Travailleur>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("travailleur", locale));
					response.setHasError(true);
					return response;
				}
				List<TravailleurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TravailleurTransformer.INSTANCE.toLiteDtos(itemsSaved) : TravailleurTransformer.INSTANCE.toDtos(itemsSaved);

				final int size = itemsSaved.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setHasError(false);
			}

			log.info("----end update Travailleur-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * delete Travailleur by using TravailleurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	@Override
	public Response<TravailleurDto> delete(Request<TravailleurDto> request, Locale locale)  {
		log.info("----begin delete Travailleur-----");
		
		response = new Response<TravailleurDto>();
		
		try {

			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.DELETE_TRAVAILLEUR.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Travailleur> items = new ArrayList<Travailleur>();
			
			for (TravailleurDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
				fieldsToVerify.put("id", dto.getId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verifier si la travailleur existe
				Travailleur existingEntity = null;
				existingEntity = travailleurRepository.findOne(dto.getId(), false);
				if (existingEntity == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("travailleur -> " + dto.getId(), locale));
					response.setHasError(true);
					return response;
				}

				// -----------------------------------------------------------------------
				// ----------- CHECK IF DATA IS USED
				// -----------------------------------------------------------------------

				// travailleurNationnalite
				List<TravailleurNationnalite> listOfTravailleurNationnalite = travailleurNationnaliteRepository.findByTravailleurId(existingEntity.getId(), false);
				if (listOfTravailleurNationnalite != null && !listOfTravailleurNationnalite.isEmpty()){
					response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfTravailleurNationnalite.size() + ")", locale));
					response.setHasError(true);
					return response;
				}


				existingEntity.setDeletedAt(Utilities.getCurrentDate());
				existingEntity.setDeletedBy(request.getUser());
				existingEntity.setIsDeleted(true);
				items.add(existingEntity);
			}

			if (!items.isEmpty()) {
				// supprimer les donnees en base
				travailleurRepository.saveAll((Iterable<Travailleur>) items);

				response.setHasError(false);
			}

			log.info("----end delete Travailleur-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * get Travailleur by using TravailleurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public Response<TravailleurDto> getByCriteria(Request<TravailleurDto> request, Locale locale) {
		log.info("----begin get Travailleur-----");
		
		response = new Response<TravailleurDto>();
		
		try {
			Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Response<UserDto> userResponse = userBusiness.isGranted(request, FunctionalityEnum.VIEW_TRAVAILLEUR.getValue(), locale);
			if (userResponse.isHasError()) {
				response.setHasError(true);
				response.setStatus(userResponse.getStatus());
				return response;
			}

			List<Travailleur> items = null;
			items = travailleurRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<TravailleurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TravailleurTransformer.INSTANCE.toLiteDtos(items) : TravailleurTransformer.INSTANCE.toDtos(items);

				final int size = items.size();
				List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
				itemsDto.parallelStream().forEach(dto -> {
					try {
						dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
					} catch (Exception e) {
						listOfError.add(e.getMessage());
						e.printStackTrace();
					}
				});
				if (Utilities.isNotEmpty(listOfError)) {
					Object[] objArray = listOfError.stream().distinct().toArray();
					throw new RuntimeException(StringUtils.join(objArray, ", "));
				}
				response.setItems(itemsDto);
				response.setCount(travailleurRepository.count(request, em, locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DATA_EMPTY("travailleur", locale));
				response.setHasError(false);
				return response;
			}

			log.info("----end get Travailleur-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * get full TravailleurDto by using Travailleur as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private TravailleurDto getFullInfos(TravailleurDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here
		List<TravailleurNationnalite> tns = travailleurNationnaliteRepository.findByTravailleurId(dto.getId(), false);
		if(!tns.isEmpty()) {
			List<NationnaliteDto> itemsNationnaliteDto = new ArrayList<NationnaliteDto>();
			for(TravailleurNationnalite tn : tns) {
				Nationnalite nat = nationnaliteRepository.findOne(tn.getNationnalite().getId(),false);
				NationnaliteDto natDto = NationnaliteTransformer.INSTANCE.toDto(nat);
				itemsNationnaliteDto.add(natDto)	;		
			}
			dto.setNationnalites(itemsNationnaliteDto);
		}
		User user = userRepository.findByTravailleurId(dto.getId(), false);
		if(user != null) {
			UserDto userDto = UserTransformer.INSTANCE.toDto(user)	;
			dto.setProfilId(userDto.getProfilId());
			dto.setProfilLibelle(userDto.getProfilLibelle());
			dto.setUserTypeId(userDto.getUserTypeId());
			dto.setUserTypeLibelle(userDto.getUserTypeLibelle());
			dto.setIsSuperAdmin(userDto.getIsSuperAdmin());
		}
		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

}
