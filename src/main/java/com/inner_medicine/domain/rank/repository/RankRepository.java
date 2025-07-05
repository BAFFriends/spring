package com.inner_medicine.domain.rank.repository;

import com.inner_medicine.domain.applicant.entity.Applicant;
import com.inner_medicine.domain.rank.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankRepository extends JpaRepository<Rank, Long> {
    void deleteAllByApplicant(Applicant applicant);
    List<Rank> findByApplicantOrderByRankAsc(Applicant applicant);
}
