package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TodoController {

    List<Task> tasks = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String task) {
        tasks.add(new Task(task));
        return "redirect:/";
    }

    @GetMapping("/toggle/{id}")
    public String toggleTask(@PathVariable int id) {
        Task t = tasks.get(id);
        t.setCompleted(!t.isCompleted());
        return "redirect:/";
    }
}