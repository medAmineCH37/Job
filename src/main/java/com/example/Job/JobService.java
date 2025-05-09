package com.example.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    public Optional<Job> getJobById(int id) {
        return repository.findById(id);
    }

    public Optional<Job> getJobByService(String service) {
        return repository.findByService(service);
    }

    public Job updateEtat(int id, boolean etat) {
        Job job = repository.findById(id).orElseThrow();
        job.setEtat(etat);
        return repository.save(job);
    }
}
