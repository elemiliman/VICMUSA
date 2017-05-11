package org.vicmusa.church.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vicmusa.church.entities.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

}
