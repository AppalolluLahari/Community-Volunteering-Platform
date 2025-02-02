package com.example.csvplatform.services.serviceImpl;

import com.example.csvplatform.dtos.TaskDto;
import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.entities.TaskReqSkills;
import com.example.csvplatform.entities.TaskSignUp;
import com.example.csvplatform.repositories.OrganisationRepository;
import com.example.csvplatform.repositories.TaskRepository;
import com.example.csvplatform.repositories.TaskReqSkillsRepository;
import com.example.csvplatform.repositories.TaskSignUpRepository;
import com.example.csvplatform.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskServices {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private TaskReqSkillsRepository taskReqSkillsRepository;

    @Autowired
    private TaskSignUpRepository  taskSignUpRepository;

    @Override
    public void createTask(TaskDto task) {
        LocalDate currDate = LocalDate.now();
        System.out.println(task.getOrganisationId());
        Organisation organisation = organisationRepository.findById(task.getOrganisationId())
                .orElseThrow(() -> new RuntimeException("Organisation not found"));
        System.out.println(task.getOrganisationId());
        Task newTask = new Task();
        newTask.setOrganisation(organisation);
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setLocation(task.getLocation());
        newTask.setCategory(task.getCategory());
        newTask.setCreatedDate(currDate);
        newTask.setStatus(task.getStatus());
        newTask.setEndDate(LocalDate.from(task.getEndDate()));
        taskRepository.save(newTask);

        //Adding Skills to the task
        List<TaskReqSkills> skills = new ArrayList<>();
        for (String skillName : task.getSkills()) {
            TaskReqSkills skill = new TaskReqSkills();
            skill.setTask(newTask);
            skill.setSkillName(skillName);
            skills.add(skill);
        }
        taskReqSkillsRepository.saveAll(skills);
    }

    @Override
    @Transactional
    public void deleteTask (Integer taskId ) {
        if(taskRepository.existsById(taskId)) {
            Optional<TaskSignUp> taskSignUp = taskSignUpRepository.findByTaskId(taskId);
            if(taskSignUp.isPresent()) {
                taskSignUpRepository.deleteByTaskId(taskId);
            }
            taskRepository.deleteById(taskId);
        }
    }


    public void updateTask(Integer taskId,TaskDto taskDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));

        int id = taskDto.getOrganisationId();

        Organisation organisation = organisationRepository.findById(id+1)
                .orElseThrow(() -> new RuntimeException("Organisation not found with ID: " + taskDto.getOrganisationId()));
        System.out.println(organisation);

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setLocation(taskDto.getLocation());
        task.setCategory(taskDto.getCategory());
        task.setStatus(taskDto.getStatus());
        task.setEndDate(taskDto.getEndDate());
        task.setOrganisation(organisation);
        taskRepository.save(task);
    }



    @Override
    public void updateStatus(Integer taskId, String status) {
        //Get the task details
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));
        //Setting the task status to new updated value
        task.setStatus(status);
        //Saving the newly updated task
        taskRepository.save(task);

    }
    @Override
    public List<Task> getMyTasks (Integer organisation_id) {
        List<Task> tasks = taskRepository.findByOrganisationId(organisation_id);
        return tasks;
    }

    @Override
    public List<Task> getAllTasks () {
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

}
