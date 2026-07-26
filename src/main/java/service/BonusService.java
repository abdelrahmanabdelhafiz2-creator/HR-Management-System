package service;

import dao.BonusDAO;
import dto.BonusDTO;

import java.util.List;

public class BonusService {

    private BonusDAO bonusDAO;

    public BonusService() {
        bonusDAO = new BonusDAO();
    }

    public List<BonusDTO> getAllBonuses() {
        return bonusDAO.findAll();
    }

    public BonusDTO getBonusById(int id) {
        if (id <= 0) {
            return null;
        }
        return bonusDAO.findById(id);
    }

    public boolean addBonus(BonusDTO bonus) {
        if (bonus.getAmount() <= 0) {
            return false;
        }
        if (bonus.getReason() == null || bonus.getReason().trim().isEmpty()) {
            return false;
        }
        return bonusDAO.save(bonus);
    }

    public boolean updateBonus(BonusDTO bonus) {
        if (bonus.getId() <= 0) {
            return false;
        }
        return bonusDAO.update(bonus);
    }

    public boolean deleteBonus(int id) {
        if (id <= 0) {
            return false;
        }
        return bonusDAO.delete(id);
    }
}
