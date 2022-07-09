package org.vso.models.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Task.class)
public abstract class Task_ {

	public static volatile SingularAttribute<Task, String> description;
	public static volatile SingularAttribute<Task, Long> id;
	public static volatile SingularAttribute<Task, Priority> priority;
	public static volatile SingularAttribute<Task, LocalDate> timestamp;
	public static volatile SingularAttribute<Task, Boolean> isCompleted;

	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String PRIORITY = "priority";
	public static final String TIMESTAMP = "timestamp";
	public static final String IS_COMPLETED = "isCompleted";

}

