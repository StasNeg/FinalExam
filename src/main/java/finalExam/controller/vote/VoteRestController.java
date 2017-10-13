package finalExam.controller.vote;


import finalExam.AuthorizedUser;
import finalExam.model.vote.Vote;
import finalExam.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static finalExam.util.UserUtil.fromTo;


@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/vote";

    @Autowired
    private VoteRepository repository;

//    @PutMapping(consumes = MediaType.ALL_VALUE)
//    public void update(@RequestParam(value = "restaurantId") Integer restaurantId) {
//        repository.save(restaurantId, fromTo(AuthorizedUser.get().getUserTo()));
//    }


    @PostMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestParam(value = "restaurantId") Integer restaurantId) {
        Vote created = repository.save(restaurantId,fromTo(AuthorizedUser.get().getUserTo()));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}



