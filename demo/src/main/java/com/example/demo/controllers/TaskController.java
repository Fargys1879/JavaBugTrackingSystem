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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;


    @GetMapping("/task")
    public String taskMain( @AuthenticationPrincipal User user, Model model) {
        Iterable<Task> task = taskRepository.findAll();
        model.addAttribute("task", task);
        return  "task-main";

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
    public String blogEdit(@PathVariable(value = "id") Long id, Model model) {
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
    public String blogPostUpdate(
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
    public String blogPostRemove(@PathVariable(value = "id") Long id,
                                 Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
        return  "redirect:/task";

    }
}
