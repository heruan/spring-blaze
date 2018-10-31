/*-
 * Copyright 2017-2018 Axians SAIV S.p.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
-*/
package to.lova.spring.blaze.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Embeddable
public class TicketHistoryItem implements Serializable {

    public enum Type {
        COMMENT, TICKET_REFERENCED, COMMENT_REFERENCED, STATUS_CHANGE, ASSIGNEE_CHANGE;
    }

    private Type type;

    private Instant instant;

    @ManyToOne
    private User author;

    @ManyToOne
    private User assigneeBefore;

    @ManyToOne
    private User assigneeAfter;

    @OneToOne
    private TicketComment comment;

    @ManyToOne
    private Ticket referencedFromTicket;

    @ManyToOne
    private TicketComment referencedFromComment;

    TicketHistoryItem() {
    }

    public TicketHistoryItem(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Instant getInstant() {
        return this.instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAssigneeBefore() {
        return this.assigneeBefore;
    }

    public void setAssigneeBefore(User assigneeBefore) {
        this.assigneeBefore = assigneeBefore;
    }

    public User getAssigneeAfter() {
        return this.assigneeAfter;
    }

    public void setAssigneeAfter(User assigneeAfter) {
        this.assigneeAfter = assigneeAfter;
    }

    public TicketComment getComment() {
        return this.comment;
    }

    public void setComment(TicketComment comment) {
        this.comment = comment;
    }

    public Ticket getReferencedFromTicket() {
        return this.referencedFromTicket;
    }

    public void setReferencedFromTicket(Ticket referencedFromTicket) {
        this.referencedFromTicket = referencedFromTicket;
    }

    public TicketComment getReferencedFromComment() {
        return this.referencedFromComment;
    }

    public void setReferencedFromComment(TicketComment referencedFromComment) {
        this.referencedFromComment = referencedFromComment;
    }

}
