package com.aayush.libraryManagementAPIs.repository;

import com.aayush.libraryManagementAPIs.model.entity.BookReturnedLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReturnedLogsRepository extends JpaRepository<BookReturnedLogs, Long> {
}
