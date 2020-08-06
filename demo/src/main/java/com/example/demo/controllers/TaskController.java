package com.example.demo.controllers;

import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repo.ProjectRepository;
import com.example.demo.repo.TaskRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Класс TaskController назначен для отображения страниц с задачами.
 * @Controller привязывает класс к Spring Boot stereotype
 * @RequestParam привязывает значение имени параметра строки запроса к параметру имени
 * метода отображения(ответа).
 * @AuthenticationPrincipal берет значение User авторизованного пользователя для передачи его параметров.
 * @author Evgeny Shabalin
 */

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
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
        //////////////Set projectString for Project Choice form//////////
        Set<String> projectsString = new HashSet<>();
        //////////////Set projectString for Project Choice form//////////
        Iterable<Project> projects = projectRepository.findAll();
        Iterator<Project> projectIterator = projects.iterator();
        while (projectIterator.hasNext()) {
            Project project = projectIterator.next();
            String name = project.getProject_name();
            Long id = project.getId();
            projectsMap.put(id,name);
            projectsString.add(name);
        }
        //////////////Set userString for User Choice form//////////
        Set<String> userString = new HashSet<>();
        //////////////Set userString for User Choice form//////////
        List<User> users = userRepository.findAll();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            String username = user.getUsername();
            userString.add(username);
        }
        model.addAttribute("users", userString);
        model.addAttribute("projects", projectsString);
        return  "task-add";

    }
    @PostMapping("/task/add")
    public String taskAddPOST( @RequestParam String title,
                                @RequestParam String description,
                                @RequestParam String type,
                                @RequestParam int priority,
                                @RequestParam String projectString,
                                @RequestParam String userString,
                                @AuthenticationPrincipal User author,
                            Model model) {
        ////////////Get project by input projectString for put our project----> in new Task/////
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
        ////////////Get project by input projectString for put our project----> in new Task/////
        ////////////Get user by input userString for put our user----> in new Task/////
        List<User> users = userRepository.findAll();
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            String username = user.getUsername();
            if (username.equals(userString)) {
                author = user;
            }
        }
        ////////////Get user by input userString for put our user----> in new Task/////
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
