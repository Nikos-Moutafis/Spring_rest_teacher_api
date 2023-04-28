package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public User registerUser(UserDTO userToRegister) {
        return null;
    }

    @Override
    public User updateUser(UserDTO userDTO) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {

    }

    @Override
    public User getUserByUsername(String username) throws EntityNotFoundException {
        return null;
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public boolean usernameAlreadyExists(String username) {
        return false;
    }
}
