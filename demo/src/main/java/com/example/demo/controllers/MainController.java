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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @GetMapping("/projects")
    public String projectMain(Model model) {
        Iterable<Project> project = projectRepository.findAll();
        model.addAttribute("project", project);
        return "projects";

    }

    @GetMapping("/project/add")
    public String projectAdd(Model model) {

        return "project-add";

    }

    @PostMapping("/project/add")
    public String projectPostAdd(@RequestParam String project_name,
                                 @RequestParam String description,
                                 Model model) {
        Project project = new Project(project_name, description);
        projectRepository.save(project);

        return "redirect:/projects";
    }

    @GetMapping("/project/{id}")
    public String projectDetails(@PathVariable(value = "id") Long id,  Model model) {
        if (!projectRepository.existsById(id)) {
            return "redirect:/task";
        }
        Optional<Project> proj = projectRepository.findById(id);
        ArrayList<Project> res = new ArrayList<>();
        proj.ifPresent(res::add);
        model.addAttribute("project", res);


        return "project-details";

    }

    @PostMapping("/project/{id}/remove")
    public String projectPostRemove(@PathVariable(value = "id") Long id,
                                 Model model) {
        Project proj = projectRepository.findById(id).orElseThrow();
        projectRepository.delete(proj);
        return "redirect:/projects";

    }
}


