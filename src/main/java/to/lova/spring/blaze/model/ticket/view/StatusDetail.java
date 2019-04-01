package to.lova.spring.blaze.model.ticket.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.FlushStrategy;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.article.view.LocalizedEntityView;
import to.lova.spring.blaze.model.ticket.entity.TicketStatus;

@EntityView(TicketStatus.class)
@CreatableEntityView
@UpdatableEntityView(strategy = FlushStrategy.ENTITY)
public interface StatusDetail
        extends StatusBase, StatusWithNext, LocalizedEntityView<TicketStatus> {

    void setInitial(boolean initial);

    void setActive(boolean active);

    void setAssigneeRequired(boolean assigneeRequired);

    void setActivityRequired(boolean activityRequired);

    void setScheduledActivityRequired(boolean scheduledActivityRequired);

    void setPublicCommentRequired(boolean publicCommentRequired);

    void setAppliedChangeRequired(boolean appliedChangeRequired);

    void setIncidentReportRequired(boolean incidentReportRequired);

    void setTheme(String theme);

}
