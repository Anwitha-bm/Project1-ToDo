package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    @Autowired
    private TaskRepository repo;

    // Home page
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tasks", repo.findAll());
        return "index";
    }

    // Add task
    @PostMapping("/add")
    public String addTask(@RequestParam String task) {
        repo.save(new Task(task));
        return "redirect:/";
    }

    // Delete task
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }

    // Toggle complete
    @GetMapping("/toggle/{id}")
    public String toggleTask(@PathVariable Long id) {
        Task t = repo.findById(id).orElse(null);
        if (t != null) {
            t.setCompleted(!t.isCompleted());
            repo.save(t);
        }
        return "redirect:/";
    }

    // Edit page
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = repo.findById(id).orElse(null);

        if (task == null) {
            return "redirect:/";
        }

        model.addAttribute("task", task);
        return "edit";
    }

    // Update task
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @RequestParam String name) {
        Task task = repo.findById(id).orElse(null);

        if (task != null) {
            task.setName(name);
            repo.save(task);
        }

        return "redirect:/";
    }
}