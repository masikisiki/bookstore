package com.bookstore.bookstoreapi.auditing;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditListener.class)
public class Auditable extends Audit {
}
