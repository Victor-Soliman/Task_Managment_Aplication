package com.nasr.TaskNS.services.impl;

import com.nasr.TaskNS.dto.SearchRequestDto;
import com.nasr.TaskNS.dto.TaskRequest;
import com.nasr.TaskNS.entity.Status;
import com.nasr.TaskNS.entity.Tasks;
import com.nasr.TaskNS.entity.Users;
import com.nasr.TaskNS.repository.TaskRepository;
import com.nasr.TaskNS.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    private Users testUser;
    private Tasks testTask1;
    private Tasks testTask2;


    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private TaskService taskService;


    @BeforeEach
    void setUp() {
        testUser = new Users();

        testTask1 = new Tasks(1L,
                "subject1",
                Date.valueOf("2020-01-01"),
                Status.NEW,
                testUser);
        testTask1 = new Tasks(2L,
                "subject2",
                Date.valueOf("2020-02-01"),
                Status.NEW,
                testUser);

    }

    @Test
    void createTask() {
        TaskRequest testTaskDto = new TaskRequest();
        Users testUser = new Users();
        Tasks testTask = new Tasks();
        testTask.setAssigned(testUser);

        List<Tasks> tasksList = new ArrayList<>();
        tasksList.add(testTask);
        testUser.setTasks(tasksList);

        when(userRepository.findByUsername(testTaskDto.getClientUserName())).thenReturn(Optional.of(testUser));

        Tasks createdTask = taskService.createTask(testTaskDto);

//        Assertions.assertEquals(2,tasksList.size());

        verify(taskRepository).save(any(Tasks.class));
//        verify(testUser).addTaskToUser(any(Tasks.class));
        verify(userRepository).save(any(Users.class));
    }

    @Test
    void getAllTasks() {
        List<Tasks> expectedTasks = new ArrayList<>();
        expectedTasks.add(testTask1);
        expectedTasks.add(testTask2);

        when(taskRepository.findAll()).thenReturn(expectedTasks);
        List<Tasks> actualList = taskService.getAllTasks();

        Assertions.assertEquals(expectedTasks, actualList);
    }

    @Test
    void getTasksByEmail() {
        Users expectedUser = new Users();
        String testEmail = "a@email.com";
        Long testUserId = 1L;
        expectedUser.setId(testUserId);
        List<Tasks> expectedTasks = new ArrayList<>();
        expectedTasks.add(testTask1);
        expectedTasks.add(testTask2);

        when(userService.getUserByEmail(testEmail)).thenReturn(expectedUser);
        when(taskRepository.findAllTasksForUser(testUserId)).thenReturn(expectedTasks);

        List<Tasks> actualList = taskService.getTasksByEmail(testEmail);

        Assertions.assertEquals(expectedTasks, actualList);
        verify(userService).getUserByEmail(testEmail);
        verify(taskRepository).findAllTasksForUser(testUserId);

    }

    @Test
    void editTask() {
        TaskRequest testTaskRequest = new TaskRequest(1L,
                "subject",
                Date.valueOf("2020-01-01"),
                Status.NEW,
                1L,
                "username");

        Users testInitialUser = new Users();
        String clientUserName = "username";
        Users testAssigned = new Users();

        Tasks testTaskFromDB = new Tasks(1L,
                "subject",
                Date.valueOf("2020-01-01"),
                Status.NEW,
                testAssigned);
        testTaskFromDB.setAssigned(testInitialUser);

        Mockito.when(taskRepository.findTasksById(testTaskRequest.getId())).thenReturn(testTaskFromDB);
        Mockito.when(userRepository.findByUsername(clientUserName)).thenReturn(Optional.of(testAssigned));

        Tasks actualTask = taskService.editTask(testTaskRequest);

        Assertions.assertEquals(testTaskRequest.getSubject(), actualTask.getSubject());
        Assertions.assertEquals(testTaskRequest.getDueDate(), actualTask.getDueDate());
        Assertions.assertEquals(testTaskRequest.getStatus(), actualTask.getStatus());

        verify(taskRepository).findTasksById(testTaskRequest.getId());
        verify(userRepository).findByUsername(clientUserName);
        verify(taskRepository).save(any(Tasks.class));
    }

    @Test
    void getTasksById() {
        Long testTaskId = 1L;
        // when then
        when(taskRepository.findById(testTaskId)).thenReturn(Optional.of(testTask1));

        Tasks actualTask = taskService.getTasksById(testTaskId);
        // assertion
        Assertions.assertEquals(testTask1, actualTask);
        // verification
        verify(taskRepository).findById(testTaskId);

    }

    @Test
    void getTasksByIdThrowsException() throws RuntimeException {
        Long testTaskId = 1L;
        // when then
        when(taskRepository.findById(testTaskId)).thenThrow(new RuntimeException());
        // assertion
        Assertions.assertThrows(RuntimeException.class, () -> taskService.getTasksById(testTaskId));

    }


    @Test
    void deleteTaskById() {
        Long testTaskId = 1L;
        Tasks testTask = new Tasks();
        testTask.setId(testTaskId);

        Users testUser = new Users();

        testTask.setAssigned(testUser);

        List<Tasks> tasksList = new ArrayList<>();
        tasksList.add(testTask);
        testUser.setTasks(tasksList);

        Mockito.when(taskRepository.findTasksById(testTaskId)).thenReturn(testTask);
        Mockito.when(userRepository.findById(testTask.getAssigned().getId())).thenReturn(Optional.of(testUser));
        Mockito.when(userRepository.save(any(Users.class))).thenReturn(testUser);
        Mockito.doNothing().when(taskRepository).deleteById(testTaskId);

        taskService.deleteTaskById(testTaskId);

        Assertions.assertEquals(0, testUser.getTasks().size());

        verify(taskRepository).findTasksById(testTaskId);
        verify(userRepository).findById(testUser.getId());
        verify(userRepository, times(1)).save(testUser);
        verify(taskRepository).deleteById(testTaskId);

    }

    @Test
    void search() {
        String subject = "test";
        Date testDate = Date.valueOf("2020-01-01");
        Status testStatus = Status.NEW;
        String testClientName = "testClientName";

        SearchRequestDto testDto = new SearchRequestDto("test", Date.valueOf("2020-01-01"), Status.NEW, "testClientName");
        List<Tasks> testList = new ArrayList<>();

        when(taskRepository.findBySearch(subject, testDate, testStatus, testClientName)).thenReturn(testList);

        List<Tasks> actual = taskService.search(testDto);

        Assertions.assertEquals(testList, actual);

        verify(taskRepository, times(1)).
                findBySearch(subject, testDate, testStatus, testClientName);
    }
}