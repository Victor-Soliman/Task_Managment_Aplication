package com.nasr.TaskNS.repository;

import com.nasr.TaskNS.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {

    //    @Query(value = "select * from Tasks order by dueDate desc where userId = ? ",nativeQuery = true)
//    @Query("select dueDate,subject,status from Tasks order by dueDate desc ", nativeQuery = true)
//    List<Tasks> findAllTasksOrderedByDueDateDesc(Long userId);

    //    List<Tasks> findAllByUserId(Long userId);
//    @Query(value = "SELECT * FROM tasks t JOIN users u ON t.task_id = u.user_id ",nativeQuery = true)
//    @Query(value = "SELECT * FROM user_task  WHERE assigned.user_id = ? ",nativeQuery = true)
//    List<Tasks> findAllByAssignedUserId(Long user_id);

//    List<Tasks> findAllByEm

    @Query(value = "SELECT * FROM tasks WHERE assigned_user_id =:id ", nativeQuery = true)
    List<Tasks> findAllTasksForUser(Long id);
}
