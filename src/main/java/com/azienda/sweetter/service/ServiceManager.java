package com.azienda.sweetter.service;

import com.azienda.sweetter.model.customException.*;
import com.azienda.sweetter.model.entity.Post;
import com.azienda.sweetter.model.entity.Role;
import com.azienda.sweetter.model.entity.User;
import com.azienda.sweetter.model.entity.dto.*;
import com.azienda.sweetter.model.enums.ExceptionMessage;
import com.azienda.sweetter.model.enums.Roles;
import com.azienda.sweetter.repository.PostRepository;
import com.azienda.sweetter.repository.RoleRepository;
import com.azienda.sweetter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service("ServiceManager")
@Transactional
public class ServiceManager {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // User

    public boolean register(RegisterDTO registerDTO)
            throws UserAlreadyExistsException {

        Optional<User> userByUsername = userRepository.findByUsernameAndIsActiveTrue(registerDTO.getUsername());
        Optional<User> userByEmail = userRepository.findByEmailAndIsActiveTrue(registerDTO.getEmail());

        if (userByUsername.isPresent()) {
            throw new UserAlreadyExistsException(ExceptionMessage.USERNAME_ALREADY_EXISTS.getMessage());
        } else if (userByEmail.isPresent()) {
            throw new UserAlreadyExistsException(ExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage());
        }
        User newUser = new User(registerDTO, findRoleByName(Roles.USER.name()));
        userRepository.save(newUser);
        return true;
    }

    public User login(LoginDTO loginDTO) throws UserCredentialsException,
            UserNotExistsException {
        Optional<User> user = userRepository.findByUsernameAndIsActiveTrue(loginDTO.getUsername());
        if (user.isEmpty()) {
            throw new UserNotExistsException(ExceptionMessage.USER_NOT_EXISTS.getMessage());
        } else if (!(user.get().getUsername().equals(loginDTO.getUsername()) &&
                user.get().getPassword().equals(loginDTO.getPassword()))) {
            throw new UserCredentialsException(ExceptionMessage.USER_WRONG_CREDENTIALS.getMessage());
        } else {
            return user.get();
        }
    }

    public User findUserById(int id) throws UserNotExistsException {
        Optional<User> user = userRepository.findByIdAndIsActiveTrue(id);
        if (user.isEmpty()) {
            throw new UserNotExistsException(ExceptionMessage.USER_NOT_EXISTS.getMessage());
        }
        return user.get();
    }

    // Post

    public boolean createPost(PostDTO postDTO)
            throws UserNotExistsException, UserCredentialsException {
        User user = login((LoginDTO) postDTO);

        Date todayInDate = new Date();
        Timestamp todayInTimeStamp = new Timestamp(todayInDate.getTime());

        Post post = new Post(postDTO, todayInTimeStamp, todayInTimeStamp, userRepository.findById(user.getId()).get());
        postRepository.save(post);
        return true;
    }

    public Post findPostById(int id) throws PostNotExistsException {
        Optional<Post> post = postRepository.findByIdAndIsActiveTrue(id);
        if (post.isEmpty()) {
            throw new PostNotExistsException(ExceptionMessage.POST_NOT_EXISTS.getMessage());
        }
        return post.get();
    }

    public List<PostDTO> findAllPosts(LoginDTO loginDTO)
            throws UserNotExistsException, UserCredentialsException {
        User user = login(loginDTO);
        List<Post> postList = postRepository.findAllByIsActiveTrue();
        return Post.toListPostDTO(postList);
    }

    public int findNumberOfLikes(LoginDTO loginDTO, int postId)
            throws UserNotExistsException, UserCredentialsException {
        User user = login(loginDTO);
        return postRepository.findLikersByIdAndIsActiveTrue(postId).size();
    }

    public int findNumberOfUnlikes(LoginDTO loginDTO, int postId)
            throws UserNotExistsException, UserCredentialsException {
        User user = login(loginDTO);
        return postRepository.findUnlikersByIdAndIsActiveTrue(postId).size();
    }

    public List<PostDTO> findAllPostsOfUser(LoginDTO loginDTO)
            throws UserNotExistsException, UserCredentialsException {
        User user = login(loginDTO);

        List<Post> postList = postRepository.findAllByPostOwnerAndIsActiveTrue(user);
        return Post.toListPostDTO(postList);
    }

    public List<PostDTO> findAllPostsByPeriod(PeriodDTO periodDTO)
            throws UserNotExistsException, UserCredentialsException {
        User user = login((LoginDTO) (periodDTO));

        if (periodDTO.getStart() == null || periodDTO.getEnd() == null) {
            throw new InvalidFieldsException(ExceptionMessage.INVALID_FIELDS.getMessage());
        }
        List<Post> postList = postRepository.findAllByLastModifiedDateBetweenAndIsActiveTrue(periodDTO.getStart(), periodDTO.getEnd());
        return Post.toListPostDTO(postList);
    }

    public List<PostDTO> findAllPostsByPeriodOfUser(PeriodDTO periodDTO)
            throws UserNotExistsException, UserCredentialsException, InvalidFieldsException {
        List<PostDTO> postListOfUser = findAllPostsOfUser((LoginDTO) periodDTO);

        if (periodDTO.getStart() == null || periodDTO.getEnd() == null) {
            throw new InvalidFieldsException(ExceptionMessage.INVALID_FIELDS.getMessage());
        }
        Predicate<Timestamp> checkPeriodTime = p -> {
            if ((p.equals(periodDTO.getStart()) || p.equals(periodDTO.getEnd())) ||
                    (p.after(periodDTO.getStart()) && p.before(periodDTO.getEnd()))) {
                return true;
            }
            return false;
        };

        return postListOfUser.stream().filter(p -> checkPeriodTime.test(p.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    public List<PostDTO> findAllPostsByTitleLikeAndTextLike(PostDTO postDTO)
            throws UserNotExistsException, UserCredentialsException, InvalidFieldsException {

        User user = login((LoginDTO) postDTO);
        if (postDTO.getStringLike() == null) {
            throw new InvalidFieldsException(ExceptionMessage.INVALID_FIELDS.getMessage());
        }
        List<Post> postList = postRepository.findAllByIsActiveTrueAndTitleContainingOrTextContaining(
                postDTO.getStringLike(), postDTO.getStringLike());
        return Post.toListPostDTO(postList);
    }

    public List<PostDTO> findAllPostsByTitleLikeAndTextLikeOfUser(PostDTO postDTO)
            throws UserNotExistsException, UserCredentialsException {

        User user = login((LoginDTO) postDTO);

        if (postDTO.getStringLike() == null) {
            throw new InvalidFieldsException(ExceptionMessage.INVALID_FIELDS.getMessage());
        }
        List<Post> postList = postRepository.findAllByIsActiveTrueAndPostOwnerAndTitleContainingOrTextLike(
                user, postDTO.getStringLike(), postDTO.getStringLike());
        return Post.toListPostDTO(postList);
    }

    public boolean updatePost(PostDTO postDTO, int postId)
            throws UserNotExistsException, UserCredentialsException, PostNotExistsException {

        User user = login((LoginDTO) postDTO);
        Post originalPost = findPostById(postId);
        if (user.getId() == originalPost.getPostOwner().getId()) {

            if (postDTO.getTitle() != null && !(originalPost.getTitle().equalsIgnoreCase(postDTO.getTitle()))) {
                originalPost.setTitle(postDTO.getTitle());
                originalPost.setLastModifiedDate(new Timestamp(new Date().getTime()));
            }
            if (postDTO.getText() != null && !(originalPost.getText().equalsIgnoreCase(postDTO.getText()))) {
                originalPost.setText(postDTO.getText());
                originalPost.setLastModifiedDate(new Timestamp(new Date().getTime()));
            }
            postRepository.save(originalPost);
            return true;
        }
        return false;
    }

    public boolean addLike(LoginDTO loginDTO, int postId) throws InteractionAlreadyExists, PostOwnerLikeException {
        User user = login(loginDTO);
        Post post = findPostById(postId);
        List<User> likers, unlikers;

        if (user.getId() != post.getPostOwner().getId()) {
            likers = postRepository.findLikersByIdAndIsActiveTrue(postId);
            if (likers.contains(user)) {
                throw new InteractionAlreadyExists(ExceptionMessage.LIKE_ALREADY_EXISTS.getMessage());
            } else {
                unlikers = postRepository.findUnlikersByIdAndIsActiveTrue(postId);
                unlikers.remove(user);
                likers.add(user);
                post.setLikesList(likers);
                post.setUnlikesList(unlikers);
                postRepository.save(post);
                return true;
            }
        }
        throw new PostOwnerLikeException(ExceptionMessage.POST_OWNER_LIKE.getMessage());
    }

    public boolean addUnlike(LoginDTO loginDTO, int postId) {
        User user = login(loginDTO);
        Post post = findPostById(postId);
        List<User> likers, unlikers;

        if (user.getId() != post.getPostOwner().getId()) {
            unlikers = postRepository.findUnlikersByIdAndIsActiveTrue(postId);
            if (unlikers.contains(user)) {
                throw new InteractionAlreadyExists(ExceptionMessage.UNLIKE_ALREADY_EXISTS.getMessage());
            } else {
                likers = postRepository.findUnlikersByIdAndIsActiveTrue(postId);
                likers.remove(user);
                unlikers.add(user);
                post.setLikesList(likers);
                post.setUnlikesList(unlikers);
                postRepository.save(post);
                return true;
            }
        }
        throw new PostOwnerLikeException(ExceptionMessage.POST_OWNER_LIKE.getMessage());
    }

    public boolean deletePostById(LoginDTO loginDTO, int id) throws AccessDeniedException {
        User user = login(loginDTO);
        Post post = findPostById(id);
        if (user.getId() == post.getPostOwner().getId() ||
                user.getRole().getRole().equals(Roles.ADMIN.name())) {
            post.setIsActive(false);
            postRepository.save(post);
        } else {
            throw new AccessDeniedException(ExceptionMessage.POST_ACCESS_DENIED.getMessage());
        }
        return true;
    }

    // Role

    public Role findRoleByName(String roleName) throws RoleNotExistsException {
        Optional<Role> role = roleRepository.findByRole(roleName);
        if (role.isEmpty()) {
            throw new RoleNotExistsException(ExceptionMessage.ROLE_NOT_EXISTS.getMessage());
        } else {
            return role.get();
        }
    }
}
