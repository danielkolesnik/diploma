package com.university.contractors.repository;

import com.university.contractors.model.jpa.entity.PaymentReason;
import org.springframework.data.repository.CrudRepository;

public interface PaymentReasonRepository extends CrudRepository<PaymentReason, Long> {
}
