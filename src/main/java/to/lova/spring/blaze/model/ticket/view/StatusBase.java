package to.lova.spring.blaze.model.ticket.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.article.view.LocalizedEntityBase;
import to.lova.spring.blaze.model.ticket.entity.TicketStatus;

@EntityView(TicketStatus.class)
public interface StatusBase
        extends StatusId, LocalizedEntityBase<TicketStatus> {

    boolean isInitial();

    boolean isActive();

    boolean isAssigneeRequired();

    boolean isActivityRequired();

    boolean isScheduledActivityRequired();

    boolean isPublicCommentRequired();

    boolean isAppliedChangeRequired();

    boolean isIncidentReportRequired();

    String getTheme();

}
