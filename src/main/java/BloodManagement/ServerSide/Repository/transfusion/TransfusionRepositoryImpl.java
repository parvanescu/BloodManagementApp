package BloodManagement.ServerSide.Repository.transfusion;

import BloodManagement.ServerSide.Domain.*;
import BloodManagement.ServerSide.Repository.CustomRepositorySupport;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.print.Doc;
import java.util.Collection;
import java.util.List;

public class TransfusionRepositoryImpl  extends CustomRepositorySupport implements TransfusionRepositoryCustom {
    @Override
    public Collection<Transfusion> getAllWithEveryAttributeJPQL() {
        EntityManager entityManager = getEntityManager();
        String queryString = "select distinct t from Transfusion t " +
                "left join fetch t.donor "+
                "left join fetch t.receiver "+
                "left join fetch t.doctor";
        Query query = entityManager.createQuery(queryString);
        Collection<Transfusion> transfusions = query.getResultList();
        return transfusions;
    }

    @Override
    public Collection<Transfusion> getAllWithEveryAttributeCriteria() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transfusion> criteriaQuery = criteriaBuilder.createQuery(Transfusion.class);
        criteriaQuery.distinct(Boolean.TRUE);
        Root<Transfusion> root = criteriaQuery.from(Transfusion.class);
        Fetch<Transfusion, Person> transfusionPersonFetch = root.fetch(Transfusion_.donor, JoinType.LEFT);
        Fetch<Transfusion, Person> transfusionPersonFetch1 = root.fetch(Transfusion_.receiver, JoinType.LEFT);
        Fetch<Transfusion, Doctor> transfusionDoctorFetch = root.fetch(Transfusion_.doctor, JoinType.LEFT);
        List<Transfusion> transfusions = entityManager.createQuery(criteriaQuery).getResultList();
        return transfusions;
    }

    @Override
    public Collection<Transfusion> getAllWithEveryAttributeSQL() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        String queryString = "select distinct {t.*},{p1.*},{p2.*},{d.*} " +
                " from transfusion t " +
                " left join person p1 on t.donor_id = p1.id " +
                " left join person p2 on t.receiver_id = p2.id " +
                " left join doctor d on t.doctor_id = d.id";
        org.hibernate.Query query = session.createSQLQuery(queryString)
                .addEntity("t",Transfusion.class)
                .addJoin("p1","t.donor")
                .addJoin("p2","t.receiver")
                .addJoin("d","t.doctor")
                .addEntity("t",Transfusion.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Collection<Transfusion> transfusions = query.getResultList();
        return transfusions;
    }

    @Override
    public Collection<Transfusion> getAllWithDonorAndDoctorJPQL() {
        EntityManager entityManager = getEntityManager();
        String queryString = "select distinct t from Transfusion t " +
                "left join fetch t.donor "+
                "left join fetch t.doctor";
        Query query = entityManager.createQuery(queryString);
        Collection<Transfusion> transfusions = query.getResultList();
        return transfusions;
    }

    @Override
    public Collection<Transfusion> getAllWithDonorAndDoctorCriteria() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transfusion> criteriaQuery = criteriaBuilder.createQuery(Transfusion.class);
        criteriaQuery.distinct(Boolean.TRUE);
        Root<Transfusion> root = criteriaQuery.from(Transfusion.class);
        Fetch<Transfusion,Person> donorFetch = root.fetch(Transfusion_.donor,JoinType.LEFT);
        Fetch<Transfusion,Doctor> doctorFetch = root.fetch(Transfusion_.doctor,JoinType.LEFT);
        root.fetch("donor");
        root.fetch("doctor");
        List<Transfusion> transfusions = entityManager.createQuery(criteriaQuery).getResultList();
        return transfusions;
    }

    @Override
    public Collection<Transfusion> getAllWithDonorAndDoctorSQL() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        String queryString = "select distinct {t.*},{p1.*},{p2.*},{d.*} " +
                " from transfusion t " +
                " left join person p1 on t.donor_id = p1.id " +
                " left join doctor d on t.doctor_id = d.id";
        org.hibernate.Query query = session.createSQLQuery(queryString)
                .addEntity("t",Transfusion.class)
                .addJoin("p1","t.donor")
                .addJoin("d","t.doctor")
                .addEntity("t",Transfusion.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Collection<Transfusion> transfusions = query.getResultList();
        return transfusions;
    }
}
