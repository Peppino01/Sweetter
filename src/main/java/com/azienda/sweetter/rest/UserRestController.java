package com.azienda.sweetter.rest;

import com.azienda.sweetter.model.customException.*;
import com.azienda.sweetter.model.entity.Post;
import com.azienda.sweetter.model.entity.dto.LoginDTO;
import com.azienda.sweetter.model.entity.dto.PeriodDTO;
import com.azienda.sweetter.model.entity.dto.PostDTO;
import com.azienda.sweetter.model.entity.dto.RegisterDTO;
import com.azienda.sweetter.model.enums.ExceptionMessage;
import com.azienda.sweetter.service.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/user", produces = "application/json")
@CrossOrigin("*")
public class UserRestController {
    @Autowired
    private ServiceManager serviceManager;

    @PostMapping(path= "/register", consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        try {
            serviceManager.register(registerDTO);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            serviceManager.login(loginDTO);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UserCredentialsException | UserNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/createPost", consumes = "application/json")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        try {
            serviceManager.createPost(postDTO);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch(UserCredentialsException | UserNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/feed")
    public ResponseEntity<?> findAllPosts(@RequestBody LoginDTO loginDTO) {
        try {
            List<PostDTO> postList = serviceManager.findAllPosts(loginDTO);
            if(postList.isEmpty()) {
                return new ResponseEntity<String>(ExceptionMessage.POST_NOT_EXISTS.getMessage(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
            }
        } catch (UserCredentialsException | UserNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/postsOfUser")
    public ResponseEntity<?> findAllPostsOfUser(@RequestBody LoginDTO loginDTO) {
        try {
            List<PostDTO> postList = serviceManager.findAllPostsOfUser(loginDTO);
            if(postList.isEmpty()) {
                return new ResponseEntity<String>(ExceptionMessage.POST_NOT_EXISTS.getMessage(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
            }
        } catch (UserCredentialsException | UserNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/postsOfPeriod")
    public ResponseEntity<?> findAllPostsByPeriod(@RequestBody PeriodDTO periodDTO) {
        try {
            List<PostDTO> postList = serviceManager.findAllPostsByPeriod(periodDTO);
            if(postList.isEmpty()) {
                return new ResponseEntity<String>(ExceptionMessage.POST_NOT_EXISTS.getMessage(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
            }
        } catch (UserCredentialsException | UserNotExistsException | InvalidFieldsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/postsOfPeriodOfUser")
    public ResponseEntity<?> findAllPostsByPeriodOfUser(@RequestBody PeriodDTO periodDTO) {
        try {
            List<PostDTO> postList = serviceManager.findAllPostsByPeriodOfUser(periodDTO);
            if(postList.isEmpty()) {
                return new ResponseEntity<String>(ExceptionMessage.POST_NOT_EXISTS.getMessage(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
            }
        } catch (UserCredentialsException | UserNotExistsException | InvalidFieldsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/postsLikeThis")
    public ResponseEntity<?> findAllPostsByTitleLikeAndTextLike(@RequestBody PostDTO postDTO) {
        try {
            List<PostDTO> postList = serviceManager.findAllPostsByTitleLikeAndTextLike(postDTO);
            if(postList.isEmpty()) {
                return new ResponseEntity<String>(ExceptionMessage.POST_NOT_EXISTS.getMessage(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
            }
        } catch (UserCredentialsException | UserNotExistsException | InvalidFieldsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/postsLikeThisOfUser")
    public ResponseEntity<?> findAllPostsByTitleLikeAndTextLikeOfUser(@RequestBody PostDTO postDTO) {
        try {
            List<PostDTO> postList = serviceManager.findAllPostsByTitleLikeAndTextLikeOfUser(postDTO);
            if(postList.isEmpty()) {
                return new ResponseEntity<String>(ExceptionMessage.POST_NOT_EXISTS.getMessage(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
            }
        } catch (UserCredentialsException | UserNotExistsException | InvalidFieldsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(path = "/updatePost", consumes = "application/json")
    public ResponseEntity<String> updatePost(@RequestBody PostDTO postDTO, @RequestParam(name = "id") int postId) {
        try {
            serviceManager.updatePost(postDTO, postId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UserCredentialsException | UserNotExistsException | PostNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/addLike", consumes = "application/json")
    public ResponseEntity<String> addLike(@RequestBody LoginDTO loginDTO, @RequestParam(name = "id") int postId) {
        try {
            serviceManager.addLike(loginDTO, postId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UserCredentialsException | UserNotExistsException | InteractionAlreadyExists |
                PostOwnerLikeException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/addUnlike", consumes = "application/json")
    public ResponseEntity<String> addUnlike(@RequestBody LoginDTO loginDTO, @RequestParam(name = "id") int postId) {
        try {
            serviceManager.addUnlike(loginDTO, postId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (UserCredentialsException | UserNotExistsException | InteractionAlreadyExists |
                PostOwnerLikeException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/deletePost", consumes = "application/json")
    public ResponseEntity<String> deletePost(@RequestBody LoginDTO loginDTO, @RequestParam(name = "id") int postId) {
        try {
            serviceManager.deletePostById(loginDTO, postId);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } catch (UserCredentialsException | UserNotExistsException | AccessDeniedException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(ExceptionMessage.GENEREIC_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
