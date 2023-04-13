package com.hizam.rest_service.entity;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parameters.class)
public abstract class Parameters_ {

	public static volatile SingularAttribute<Parameters, Date> date_of_birth;
	public static volatile SingularAttribute<Parameters, Integer> ECG;
	public static volatile SingularAttribute<Parameters, Integer> Puls;
	public static volatile SingularAttribute<Parameters, Long> id;
	public static volatile SingularAttribute<Parameters, Integer> Breathing;
	public static volatile SingularAttribute<Parameters, Integer> national_number;
	public static volatile SingularAttribute<Parameters, String> first_name;
	public static volatile SingularAttribute<Parameters, String> family_name;
	public static volatile SingularAttribute<Parameters, Integer> NIBP;

}

