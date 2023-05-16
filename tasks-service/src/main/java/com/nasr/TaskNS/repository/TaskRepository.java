package com.nasr.TaskNS.repository;

import com.nasr.TaskNS.entity.Status;
import com.nasr.TaskNS.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
    @Query(value = "SELECT * FROM tasks WHERE assigned_user_id =:id order by due_date desc ", nativeQuery = true)
    List<Tasks> findAllTasksForUser(Long id);

    Tasks findTasksById(Long id);

//    @Query(value = "SELECT * FROM Tasks WHERE " +
//            "        (:subject IS NULL OR LOWER(subject) LIKE LOWER(CONCAT('%', CAST(:subject AS CHAR), '%'))) " +
//            "    AND (:dueDate IS NULL OR due_date >:dueDate) " +
//            "    AND (:status IS NULL OR status =:status)" +
//            "    AND (:clientUserName IS NULL OR clientUserName = :clientUserName)", nativeQuery = true)
    @Query(value = "SELECT t FROM Tasks t WHERE " +
            "        (:subject IS NULL OR LOWER(t.subject) LIKE LOWER(CONCAT('%', :subject , '%'))) " +
            "    AND (CAST(:dueDate AS date) IS NULL OR t.dueDate > CAST(:dueDate AS date))" +
            "    AND (:status IS NULL OR t.status =:status)" +
            "   AND  (:clientUserName IS NULL OR t.assigned.username=:clientUserName) ORDER BY t.dueDate DESC")
    List<Tasks> findBySearch(@Param("subject") String subject,
                             @Param("dueDate") Date dueDate,
                             @Param("status") Status status,
                             @Param("clientUserName") String clientUserName);
}
