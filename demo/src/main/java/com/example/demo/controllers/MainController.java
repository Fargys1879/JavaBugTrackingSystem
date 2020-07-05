package com.example.demo.controllers;

import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class MainController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @GetMapping("/project")
    public String projectMain( Model model) {
        return "projects";

    }

    @GetMapping("/project/add")
    public String projectAdd( Model model) {

        return "project-add";

    }

    @PostMapping("/project/add")
    public String projectPostAdd( @RequestParam String project_name,
                                  @RequestParam String title,
                                  @RequestParam String description,
                                  @RequestParam String type,
                                  @RequestParam int priority,
                                  @AuthenticationPrincipal User author,
            Model model) {
        //Project project = new Project(project_name );
        //Task task = new Task(title, description, type, priority, author);
        Project pro1 = new Project("new_pro1");

        Task task2 = new Task("fg1","gf1","type1",1,author);
        Task task3 = new Task("fg2","gf2","type2",1,author);
        //Task task4 = new Task("fg2","gf2","type2",1,author);

        //project.setTasks(Arrays.asList(task));

        pro1.setTasks(Arrays.asList(task3));

        //projectRepository.save(project);
        projectRepository.save(pro1);

        //projectRepository.save(project);

        return  "redirect:/project";
    }

}
