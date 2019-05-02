package to.lova.spring.blaze.model.common.view;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.boot.internal.EnversService;

import com.blazebit.persistence.FromProvider;
import com.blazebit.persistence.spi.ServiceProvider;
import com.blazebit.persistence.view.CorrelationBuilder;
import com.blazebit.persistence.view.CorrelationProvider;
import com.blazebit.persistence.view.FetchStrategy;
import com.blazebit.persistence.view.MappingCorrelated;

public interface AuditedView extends Serializable {

    @MappingCorrelated(correlationBasis = "this",
            correlationResult = "originalId.REV",
            correlator = CteRegistrationSubqueryProvider.class,
            fetch = FetchStrategy.JOIN)
    EntityRevisionDetail getCreationMetadata();

    class CteRegistrationSubqueryProvider implements CorrelationProvider {

        private static final String REVISION_TYPE_FORMAT = "%s.REVTYPE";

        private static final String ORIGINAL_ID_FORMAT = "%s.originalId.%s";

        @Override
        public void applyCorrelation(CorrelationBuilder correlationBuilder,
                String correlationExpression) {
            var fromProvider = correlationBuilder.getCorrelationFromProvider();
            var entityType = this.getEntityType(correlationBuilder::getService,
                    fromProvider);
            var idType = entityType.getIdType().getJavaType();
            var idName = entityType.getId(idType).getName();
            var auditEntityType = this.getAuditEntityType(
                    correlationBuilder::getService, fromProvider);
            var alias = correlationBuilder.getCorrelationAlias();
            var originalId = getAuditedEntityOriginalId(
                    correlationBuilder::getService, fromProvider, alias);
            var revisionType = getRevisionType(alias);
            correlationBuilder.correlate(auditEntityType).on(originalId)
                    .eqExpression(correlationExpression + "." + idName)
                    .on(revisionType).eq(RevisionType.ADD).end();
        }

        private String getRevisionType(String auditedEntityAlias) {
            return String.format(REVISION_TYPE_FORMAT, auditedEntityAlias);
        }

        private String getAuditedEntityOriginalId(
                ServiceProvider serviceProvider, FromProvider fromProvider,
                String auditedEntityAlias) {
            var entityType = this.getEntityType(serviceProvider, fromProvider);
            var idType = entityType.getIdType().getJavaType();
            var idName = entityType.getId(idType).getName();
            return String.format(ORIGINAL_ID_FORMAT, auditedEntityAlias,
                    idName);
        }

        private Class<?> getEntityClass(FromProvider fromProvider) {
            return fromProvider.getRoots().iterator().next().getJavaType();
        }

        private EntityType<?> getEntityType(ServiceProvider serviceProvider,
                FromProvider fromProvider) {
            var entityClass = this.getEntityClass(fromProvider);
            var metamodel = this.getSessionFactory(serviceProvider)
                    .getMetamodel();
            return metamodel.entity(entityClass);
        }

        private EntityType<?> getAuditEntityType(
                ServiceProvider serviceProvider, FromProvider fromProvider) {
            var entityClass = this.getEntityClass(fromProvider);
            var metamodel = this.getSessionFactory(serviceProvider)
                    .getMetamodel();
            var entityName = metamodel.entityPersister(entityClass)
                    .getEntityName();
            var auditedEntityName = this.getEnvers(serviceProvider)
                    .getAuditEntitiesConfiguration()
                    .getAuditEntityName(entityName);
            return metamodel.entity(auditedEntityName);
        }

        private EnversService getEnvers(ServiceProvider serviceProvider) {
            return this.getSessionFactory(serviceProvider).getServiceRegistry()
                    .getService(EnversService.class);
        }

        private SessionFactoryImplementor getSessionFactory(
                ServiceProvider serviceProvider) {
            return serviceProvider.getService(EntityManager.class)
                    .unwrap(SessionImplementor.class).getSessionFactory();
        }

    }

}