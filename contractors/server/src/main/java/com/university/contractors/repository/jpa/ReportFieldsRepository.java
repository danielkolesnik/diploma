package com.university.contractors.repository.jpa;

import com.university.contractors.model.jpa.entity.ReportFields;
import com.university.contractors.repository.jpa.core.CoreRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportFieldsRepository extends CoreRepository<ReportFields, Long> {

    Optional<ReportFields> findById(Long itemId);

    @Query("SELECT rf FROM ReportFields rf")
    List<ReportFields> getList();

}
