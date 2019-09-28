package com.aayush.libraryManagementAPIs.repository;

import com.aayush.libraryManagementAPIs.model.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
