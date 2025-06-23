package com.maria.web_access.domain.service;

import com.maria.web_access.domain.entity.User;
import com.maria.web_access.domain.entity.UserDetails;
import com.maria.web_access.domain.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsService {
    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public UserDetailsService(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    @Transactional
    public void saveUserDetails(User user,
                                String fullName,
                                String cardNumber,
                                String group,
                                String position,
                                boolean isStudent,
                                boolean isTeacher) {
        UserDetails details = new UserDetails();
        details.setUser(user);
        details.setFullName(fullName);
        details.setCardNumber(cardNumber);

        if(isStudent) {
            details.setGroupName(group);
        } else if(isTeacher) {
            details.setPosition(position);
        }
        // Для админа ничего не заполняем

        userDetailsRepo.save(details);
    }

    public void deleteByUserId(Long userId) {
        userDetailsRepo.deleteById(userId);
    }

    public UserDetails findByUser(User user) {
        return userDetailsRepo.findById(user.getId())
                .orElse(new UserDetails()); // Возвращаем пустой объект, если деталей нет
    }

}