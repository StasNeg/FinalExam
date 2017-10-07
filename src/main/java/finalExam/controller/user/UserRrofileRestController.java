package finalExam.controller.user;


import finalExam.AuthorizedUser;
import finalExam.model.users.User;
import finalExam.repository.UserRepository;
import finalExam.to.UserTo;
import finalExam.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = UserRrofileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRrofileRestController {
    static final String REST_URL = "/rest/profile";

    @Autowired
    UserRepository repository;

    public User get() {
        return repository.get(AuthorizedUser.id());
    }

    @DeleteMapping
    public void delete() {
        repository.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserTo userTo) {
        if (UserUtil.assureIdConsistent(userTo, AuthorizedUser.id()))
            repository.save(UserUtil.createNewFromTo(userTo));
    }
}
