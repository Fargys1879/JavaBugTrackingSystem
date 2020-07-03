package com.example.demo.controllers;

import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/task/add")
    public String taskAdd( @AuthenticationPrincipal User user, Model model) {
        return  "task-add";

    }

    @PostMapping("/task/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String type,
                              @RequestParam String full_text,
                              //@RequestParam boolean progress,
                              @RequestParam int priority,
                              @AuthenticationPrincipal User author,
                              Model model) {
        Task task = new Task(title, full_text, type, priority,author );
        taskRepository.save(task);
        return  "redirect:/task";

    }

    @GetMapping("/task/{id}")
    public String blogDetails(@PathVariable(value = "id") Long id, Model model) {
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
    public String blogPostUpdate(@RequestParam String title,
                                 @PathVariable(value = "id") Long id,
                                 @RequestParam String type,
                                 @RequestParam String full_text,
                                 Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(title);
        task.setType(type);
        task.setFull_text(full_text);
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
