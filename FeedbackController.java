package com.example.feedbackportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    @ResponseBody
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    @ResponseBody
    public List<Feedback> getFeedbackByCourseId(@PathVariable String courseId) {
        return feedbackService.getByCourseId(courseId);
    }

    @GetMapping("/course/html/{courseId}")
    public String getFeedbackByCourseIdHtml(@PathVariable String courseId, Model model) {
        List<Feedback> feedbacks = feedbackService.getByCourseId(courseId);
        model.addAttribute("feedbacks", feedbacks);
        return "feedback_list"; // points to feedback_list.html in /resources/templates/
    }

    @PostMapping
    @ResponseBody
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.save(feedback);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.update(id, feedback));
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Void> deleteFeedback(@RequestParam Long id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback_form"; // in src/main/resources/templates/
    }
    @PostMapping("/feedback")
    public String submitFeedback(@ModelAttribute Feedback feedback) {
        feedbackService.save(feedback);
        return "redirect:/feedbacks/list"; // ⬅️ Redirects to list page after submission
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<Feedback> feedbacks = feedbackService.getAll(); // ✅ FIXED
        model.addAttribute("feedbacks", feedbacks);
        return "feedback_list"; // in src/main/resources/templates/
    }
 
}
