/*
 * Created on 2022-07-27 ( Time 01:05:42 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */
// This Bean has a basic Primary Key (not composite) 

package ci.palmafrique.vonabri.dao.entity;

import java.io.Serializable;

import lombok.*;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "livraison"
 *
 * @author Telosys Tools Generator
 *
 */
@Data
@ToString
@Entity
@Table(name="livraison" )
public class Livraison implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="stock_livrer")
    private Double     stockLivrer  ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date       createdAt    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="deleted_at")
    private Date       deletedAt    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date       updatedAt    ;

    @Column(name="created_by")
    private Integer    createdBy    ;

    @Column(name="updated_by")
    private Integer    updatedBy    ;

    @Column(name="deleted_by")
    private Integer    deletedBy    ;

    @Column(name="is_deleted")
    private Boolean    isDeleted    ;

	// "livraisonTypeId" (column "livraison_type_id") is not defined by itself because used as FK in a link 
	// "siteId" (column "site_id") is not defined by itself because used as FK in a link 
	// "productionId" (column "production_id") is not defined by itself because used as FK in a link 

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="production_id", referencedColumnName="id")
    private Production production  ;
    @ManyToOne
    @JoinColumn(name="site_id", referencedColumnName="id")
    private Site site        ;
    @ManyToOne
    @JoinColumn(name="livraison_type_id", referencedColumnName="id")
    private LivraisonType livraisonType;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Livraison() {
		super();
    }
    
	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public java.lang.Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}