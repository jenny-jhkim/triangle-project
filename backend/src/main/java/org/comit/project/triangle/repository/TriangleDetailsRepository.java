package org.comit.project.triangle.repository;

import org.comit.project.triangle.jpa.TriangleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriangleDetailsRepository extends JpaRepository<TriangleDetails, Integer> {

}
