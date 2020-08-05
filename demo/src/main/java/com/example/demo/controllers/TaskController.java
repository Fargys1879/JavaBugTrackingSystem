package com.example.demo.controllers;

import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repo.ProjectRepository;
import com.example.demo.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    /////////////Map for (id ---> project_name)////////////////////
    private static Map<Long,String> projectsMap = new HashMap<>();

    @GetMapping("/task")
    public String taskMain( Model model) {
        Iterable<Task> task = taskRepository.findAll();
        model.addAttribute("task", task);
        return  "task-main";

    }
    @GetMapping("/task/add")
    public String taskAddGET( Model model) {
        Set<String> projectsString = new HashSet<>();
        Iterable<Project> projects = projectRepository.findAll();
        Iterator<Project> projectIterator = projects.iterator();
        while (projectIterator.hasNext()) {
            Project project = projectIterator.next();
            String name = project.getProject_name();
            Long id = project.getId();
            projectsMap.put(id,name);
            projectsString.add(name);
        }
        model.addAttribute("projects", projectsString);
        return  "task-add";

    }
    @PostMapping("/task/add")
    public String taskAddPOST( @RequestParam String title,
                                @RequestParam String description,
                                @RequestParam String type,
                                @RequestParam int priority,
                                @RequestParam String projectString,
                                @AuthenticationPrincipal User author,
                            Model model) {
        Project project = new Project();
        for (Map.Entry<Long,String> pair : projectsMap.entrySet()) {
            Long id = pair.getKey();
            String name = pair.getValue();
            if(projectString.equals(name)) {
                Optional<Project> projects = projectRepository.findById(id);
                Iterator<Project> projectIterator = projects.stream().iterator();
                while (projectIterator.hasNext()) {
                    project = projectIterator.next();
                }
            }
        }
        Task task = new Task(title, description, type, priority, project, author);
        taskRepository.save(task);
        return  "redirect:/task";
    }

    @GetMapping("/task/{id}")
    public String taskDetails(@PathVariable(value = "id") Long id, Model model) {
        if(!taskRepository.existsById(id)) {
            return "redirect:/task";
        }
        Optional<Task> task = taskRepository.findById(id);
        ArrayList<Task> res = new ArrayList<>();
        task.ifPresent(res::add);
        model.addAttribute("task", res);
        return  "task-details";

    }

    @GetMapping("/task/{id}/edit")
    public String taskEdit(@PathVariable(value = "id") Long id, Model model) {
        if(!taskRepository.existsById(id)) {
            return "redirect:/task";
        }
        Optional<Task> task = taskRepository.findById(id);
        ArrayList<Task> res = new ArrayList<>();
        task.ifPresent(res::add);
        model.addAttribute("task", res);
        return  "task-edit";

    }

    @PostMapping("/task/{id}/edit")
    public String taskUpdate(
                                 @PathVariable(value = "id") Long id,
                                 @RequestParam String title,
                                 @RequestParam String type,
                                 @RequestParam String description,
                                 //@RequestParam boolean progress,
                                 Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(title);
        task.setType(type);
        task.setDescription(description);
        taskRepository.save(task);
        return  "redirect:/task";

    }

    @PostMapping("/task/{id}/remove")
    public String taskRemove(@PathVariable(value = "id") Long id,
                                 Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
        return  "redirect:/task";

    }
}
