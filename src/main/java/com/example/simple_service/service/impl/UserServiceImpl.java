package com.example.simple_service.service.impl;

import com.example.simple_service.entity.User;
import com.example.simple_service.repository.UserRepository;
import com.example.simple_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl, UserService arayüzündeki metodların uygulanmasını sağlar.
 * Uygulamanın iş mantığı burada konumlanır.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Constructor injection: Test edilebilirlik ve sağlamlık açısından alan enjeksiyonuna tercih edilir.
     *
     * @param userRepository Kullanıcı veritabanı işlemlerinden sorumlu Repository
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User saveUser(User user) {
        // İsteğe bağlı: email veya diğer alanlar üzerinde ek validasyonlar yapılabilir.
        return userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Bulunamaması durumunda null döndürmek yerine bir "NotFoundException"
     * fırlatmak veya {@link Optional} ile yönetmek genellikle daha temiz bir yaklaşımdır.
     */
    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Mevcut kaydı güncellemeden önce ek validasyonlar veya
     * yetkilendirme kontrolleri yapabilirsiniz.
     */
    @Override
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        }
        return null; // Veya kendi özel mantığınızı veya özel bir Exception fırlatmayı tercih edebilirsiniz.
    }

    /**
     * {@inheritDoc}
     * <p>
     * Silme işlemi başarıyla gerçekleşirse true, aksi halde false döndürür.
     */
    @Override
    public boolean deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> getUserByEmail(String email) {
        // Gerekirse e-posta formatı için ek validasyon yapılabilir.
        return userRepository.findByEmail(email);
    }
}
