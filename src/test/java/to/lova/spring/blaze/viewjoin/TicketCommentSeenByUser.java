package to.lova.spring.blaze.viewjoin;

import javax.persistence.Entity;

import to.lova.spring.blaze.collection.model.User;

@Entity
public class TicketCommentSeenByUser extends SeenEntity<TicketComment, User> {

}
