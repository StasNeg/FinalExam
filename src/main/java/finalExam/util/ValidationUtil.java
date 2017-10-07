package finalExam.util;


import finalExam.model.IdAbstractClass;

import java.time.LocalTime;

public class ValidationUtil{
    public static final LocalTime TIME_VOTE_LIMIT = LocalTime.of(23,0);
    public static void assureIdConsistent(IdAbstractClass bean, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    public static boolean onTimeVote() {
        return LocalTime.now().isBefore(TIME_VOTE_LIMIT);
    }
}
