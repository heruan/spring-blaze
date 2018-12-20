package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.TicketStatus;

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
