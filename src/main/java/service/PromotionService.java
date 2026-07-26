package service;

import dao.PromotionDAO;
import dto.PromotionDTO;

import java.util.List;

public class PromotionService {

    private PromotionDAO promotionDAO;

    public PromotionService() {
        promotionDAO = new PromotionDAO();
    }

    public List<PromotionDTO> getAllPromotions() {
        return promotionDAO.findAll();
    }

    public PromotionDTO getPromotionById(int id) {
        if (id <= 0) {
            return null;
        }
        return promotionDAO.findById(id);
    }

    public boolean addPromotion(PromotionDTO promotion) {
        if (promotion.getEmployeeId() <= 0) {
            return false;
        }
        if (promotion.getNewRole() == null || promotion.getNewRole().trim().isEmpty()) {
            return false;
        }
        if (promotion.getNewSalary() <= 0) {
            return false;
        }
        return promotionDAO.save(promotion);
    }

    public boolean updatePromotion(PromotionDTO promotion) {
        if (promotion.getId() <= 0) {
            return false;
        }
        return promotionDAO.update(promotion);
    }

    public boolean deletePromotion(int id) {
        if (id <= 0) {
            return false;
        }
        return promotionDAO.delete(id);
    }
}
