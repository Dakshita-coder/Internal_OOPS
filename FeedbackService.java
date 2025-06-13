package com.example.feedbackportal;

import com.example.feedbackportal.Feedback;
import com.example.feedbackportal.FeedbackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service


public class FeedbackService {

    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public List<Feedback> getAll() {
        return repository.findAll();
    }

    public Optional<Feedback> getById(Long id) {
        return repository.findById(id);
    }

    public List<Feedback> getByCourseId(String courseId) {
        return repository.findByCourseId(courseId);
    }

    public Feedback save(Feedback feedback) {
        return repository.save(feedback);
    }
    @Autowired
     private FeedbackRepository feedbackRepository;
        
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }


    public Feedback update(Long id, Feedback feedbackDetails) {
        Feedback feedback = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        feedback.setCourseId(feedbackDetails.getCourseId());
        feedback.setRating(feedbackDetails.getRating());
        feedback.setComment(feedbackDetails.getComment());

        return repository.save(feedback);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
