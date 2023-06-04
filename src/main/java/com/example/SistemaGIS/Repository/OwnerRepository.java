package com.example.SistemaGIS.Repository;


import com.example.SistemaGIS.Model.Owner;
import com.example.SistemaGIS.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUser(User user);
    Optional<Owner> findAllByOwnerCarNumberPlate(String numberPlate);
}
