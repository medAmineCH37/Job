package com.example.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping
    public List<Job> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable int id) {
        return service.getJobById(id);
    }

    @GetMapping("/service/{name}")
    public Optional<Job> getJobByService(@PathVariable String name) {
        return service.getJobByService(name);
    }

    @PutMapping("/{id}/etat")
    public Job updateEtat(@PathVariable int id, @RequestParam boolean etat) {
        return service.updateEtat(id, etat);
    }
}
