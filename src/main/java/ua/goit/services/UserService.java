package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ua.goit.dao.UserDao;
import ua.goit.dao.UserRoleDao;
import ua.goit.domain.User;
import ua.goit.domain.UserRole;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

public class UserService {
    private UserDao userDao;
    private UserRoleDao userRoleDao;
    private final String DEFAULT_ROLE = "user";

    @Transactional
    public Set<User> getAllUsers() {
        return userDao.getAll();
    }

    @Transactional
    public User findUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) throw new RuntimeException("Cannot use null/empty username");
        return userDao.findByUsername(username);
    }

    @Transactional
    public void addNewUser(String username, String password, String confirmPassword, String email, String name)
            throws NoSuchAlgorithmException {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(email) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(confirmPassword) || StringUtils.isEmpty(name))
            throw new RuntimeException("Some of your credentials were not entered. " +
                    "Please go back and make sure you enter all details.");

        Set<User> allUsers = userDao.getAll();

        for (User u : allUsers) {
            if (u.getUsername().equals(username) || u.getEmail().equals(email)) {
                throw new RuntimeException("Username '" + username + "' or email '" + email +
                        "' are already registered in the system. Please go back and choose another one.");
            }
        }

        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match. Make sure you have entered the same password twice.");
        }

        String sha512Pass = getSha512Pass(password);

        UserRole role = userRoleDao.findByName(DEFAULT_ROLE);
        User user = new User(username, sha512Pass, email, name, role, null);

        userDao.add(user);
    }

    @Transactional
    public void editUserName(String username, String newName) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(newName))
            throw new RuntimeException("Cannot user null/empty values");

        User user = userDao.findByUsername(username);
        user.setName(newName);
        userDao.edit(user);
    }

    @Transactional
    public void editUserEmail(String username, String newEmail) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(newEmail))
            throw new RuntimeException("Cannot user null/empty values");

        User user = userDao.findByUsername(username);
        user.setEmail(newEmail);
        userDao.edit(user);
    }

    @Transactional
    public UserRole checkUserAndGetRole(String username, String password) throws NoSuchAlgorithmException {
        User user = userDao.findByUsername(username);
        String sha512Password = getSha512Pass(password);

        if (user == null | (user != null && !user.getPassword().equals(sha512Password))) {
            throw new RuntimeException("User not found or password incorrect. Please go back and try again.");
        }

        return user.getRole();
    }

    private String getSha512Pass(String passwordToHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(passwordToHash.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16)).substring(1);
        }

        return sb.toString();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }
}