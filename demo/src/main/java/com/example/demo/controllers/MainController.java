package com.example.demo.controllers;

import com.example.demo.models.Project;
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
import java.util.Optional;

/**
 * Класс MainController назначен для отображения главной страницы и страниц с проектами
 * @Controller привязывает класс к Spring Boot stereotype
 * @RequestParam привязывает значение имени параметра строки запроса к параметру имени
 * метода отображения(ответа).
 * @author Evgeny Shabalin
 */

@Controller
public class MainController {
    @Autowired // This means to get the bean called Repository
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;

    /**
     *Метод для загрузки главной страницы
     * @param model переменная для передачи атрибутов для шаблонизатора.
     * @return возвразает название html документа отображаемого браузером.
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @GetMapping("/projects")
    public String projectMain(Model model) {
        Iterable<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
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

    @PostMapping("/project/{id}/edit")
    public String projectPOSTUpdate(
            @PathVariable(value = "id") Long id,
            @RequestParam String project_name,
            @RequestParam String description,
            Model model) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setProject_name(project_name);
        project.setDescription(description);
        projectRepository.save(project);
        return  "redirect:/projects";

    }

    @PostMapping("/project/{id}/remove")
    public String projectPostRemove(@PathVariable(value = "id") Long id,
                                 Model model) {
        Project proj = projectRepository.findById(id).orElseThrow();
        projectRepository.delete(proj);
        return "redirect:/projects";

    }
}


