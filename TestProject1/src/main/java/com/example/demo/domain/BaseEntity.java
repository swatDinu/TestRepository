package com.example.demo.domain;

import javax.persistence.EntityListeners;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;


@Configurable
@EntityListeners(BaseEntity.class)
public class BaseEntity {

    protected Log logger = LogFactory.getLog(getClass().getSimpleName());
    
    public static String FIELD_LEVEL_SECURITY_PROP = "field.level.security.enable";

    @PersistenceContext(unitName = "persistenceUnit")
    protected transient EntityManager entityManager;
    
    @Transactional
    public void persist() {
        if (this.entityManager == null)
            this.entityManager = entityManager();

        this.entityManager.persist(this);
        this.entityManager.flush();
        this.entityManager.refresh(this);
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null)
            this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        }
        // } else {
        // MdmjobSchedule attached = MdmjobSchedule.findMdmjobSchedule(this.mdmjobScheduleId);
        // this.entityManager.remove(attached);
        // }
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null)
            this.entityManager = entityManager();
        this.entityManager.flush();
    }

    @Transactional
    public void clear() {
        if (this.entityManager == null)
            this.entityManager = entityManager();
        this.entityManager.clear();
    }

    @Transactional
    public BaseEntity merge() {
        if (this.entityManager == null)
            this.entityManager = entityManager();

        BaseEntity merged = this.entityManager.merge(this);

        this.entityManager.flush();
        return merged;
    }

    public static final EntityManager entityManager() {
        EntityManager em = new BaseEntity().entityManager;
        if (em == null)
            throw new IllegalStateException(
                    "Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }


    @Transactional
    public void refresh() {
        try {
            if (this.entityManager == null)
                this.entityManager = entityManager();
            this.entityManager.refresh(this);
        } catch (Exception e) {
            logger.error("failed during refresh", e);
        }
    }
    
}
