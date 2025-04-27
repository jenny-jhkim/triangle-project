package org.comit.project.triangle.repository;

import org.comit.project.triangle.jpa.Triangle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriangleRepository extends JpaRepository<Triangle, Integer> {

}
