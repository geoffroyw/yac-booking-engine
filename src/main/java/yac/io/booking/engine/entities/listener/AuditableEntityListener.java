package yac.io.booking.engine.entities.listener;

import yac.io.booking.engine.entities.AuditableEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by geoffroy on 01/08/15.
 */
public class AuditableEntityListener {

    @PreUpdate
    public void setUpdatedAt(AuditableEntity e) {
        e.setUpdatedAt(new Date());
    }

    @PrePersist
    public void setCreatedAt(AuditableEntity e) {
        Date now = new Date();
        e.setCreatedAt(now);
        e.setUpdatedAt(now);
    }
}
