package yac.io.booking.engine.entities;

import yac.io.booking.engine.entities.listener.AuditableEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geoffroy on 01/08/15.
 */
@EntityListeners(AuditableEntityListener.class)
@MappedSuperclass
public abstract class AuditableEntity {

    private Date createdAt;
    private Date updatedAt;

    @Column(name = "created_at", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at", insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
