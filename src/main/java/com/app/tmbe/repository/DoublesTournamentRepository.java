package com.app.tmbe.repository;

import com.app.tmbe.dataModel.DoublesTournament;
import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.dataModel.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoublesTournamentRepository extends JpaRepository<DoublesTournament, Long> { }

// for inheritance: public abstract <S extends T> S save(S entity)
// public interface DoublesTournamentRepository extends JpaRepository<DoublesTournament, Long> {
//  <DoublesTournament extends Tournament> DoublesTournament save(
//      DoublesTournament doublesTournament);
//
//  Optional<DoublesTournament> findById(Long id);
// }
