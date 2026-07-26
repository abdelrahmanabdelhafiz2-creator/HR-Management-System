package service;

import dao.FeedbackDAO;
import dto.FeedbackDTO;

import java.util.List;

public class FeedbackService {

    private FeedbackDAO feedbackDAO;

    public FeedbackService() {
        feedbackDAO = new FeedbackDAO();
    }

    public List<FeedbackDTO> getAllFeedback() {
        return feedbackDAO.findAll();
    }

    public FeedbackDTO getFeedbackById(int id) {
        if (id <= 0) {
            return null;
        }
        return feedbackDAO.findById(id);
    }

    public boolean addFeedback(FeedbackDTO feedback) {
        if (feedback.getRate() < 1 || feedback.getRate() > 5) {
            return false;
        }
        if (feedback.getComment() == null || feedback.getComment().trim().isEmpty()) {
            return false;
        }
        return feedbackDAO.save(feedback);
    }

    public boolean updateFeedback(FeedbackDTO feedback) {
        if (feedback.getId() <= 0) {
            return false;
        }
        return feedbackDAO.update(feedback);
    }

    public boolean deleteFeedback(int id) {
        if (id <= 0) {
            return false;
        }
        return feedbackDAO.delete(id);
    }
}
