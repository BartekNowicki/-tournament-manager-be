package com.app.tmbe.repository;

import com.app.tmbe.dataModel.GroupInSingles;
import com.app.tmbe.dataModel.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GroupRepository extends JpaRepository<GroupInSingles, Long> {}
