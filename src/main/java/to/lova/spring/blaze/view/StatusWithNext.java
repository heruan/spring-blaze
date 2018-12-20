package to.lova.spring.blaze.view;

import java.util.Comparator;
import java.util.SortedSet;

import com.blazebit.persistence.view.CollectionMapping;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

import to.lova.spring.blaze.model.TicketStatus;

@EntityView(TicketStatus.class)
@UpdatableEntityView
public interface StatusWithNext extends StatusId {

    @UpdatableMapping
    @CollectionMapping(comparator = TicketStatusComparator.class)
    SortedSet<StatusBase> getNext();

    class TicketStatusComparator implements Comparator<StatusBase> {

        @Override
        public int compare(StatusBase o1, StatusBase o2) {
            var comparator = Comparator.comparing(StatusBase::getName,
                    Comparator.naturalOrder());
            return comparator.compare(o1, o2);
        }

    }

}
