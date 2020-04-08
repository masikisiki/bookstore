package com.bookstore.bookstoreapi.auditing;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    private void prePersist(Object audit) {
        ((Audit) audit).setCreatedBy("Test User");
        ((Audit) audit).setCreatedOn(LocalDateTime.now());
    }

    @PreUpdate
    private void preUpdate(Object audit) {
        ((Audit) audit).setUpdatedBy("Test User");
        ((Audit) audit).setUpdatedOn(LocalDateTime.now());
    }
}
