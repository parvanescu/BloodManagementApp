package BloodManagement.ServerSide.Repository.prescription;

import BloodManagement.ServerSide.Domain.*;
import BloodManagement.ServerSide.Repository.CustomRepositorySupport;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class PrescriptionRepositoryImpl extends CustomRepositorySupport implements PrescriptionRepositoryCustom{

    @Override
    public Collection<Prescription> findAllWithDrugsJPQL() {
        EntityManager entityManager = getEntityManager();
        String stringQuery = "select distinct p from Prescription p left join fetch p.drugs";
        Query query = entityManager.createQuery(stringQuery);
        return query.getResultList();
    }

    @Override
    public Collection<Prescription> findAllWithDrugsCriteria() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Prescription> criteriaQuery = criteriaBuilder.createQuery(Prescription.class);
        criteriaQuery.distinct(Boolean.TRUE);
        Root<Prescription> root = criteriaQuery.from(Prescription.class);
        Fetch<Prescription, Drug> doctorHospitalFetch = root.fetch(Prescription_.drugs, JoinType.LEFT);
        List<Prescription> prescriptions = entityManager.createQuery(criteriaQuery).getResultList();
        return prescriptions;
    }

    @Override
    public Collection<Prescription> findAllWithDrugsSQL() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        String stringQuery = "select distinct {p.*},{d.*} "+
                "from prescription p "+
                "left join prescribed_drugs pd on p.id = pd.prescription_id"+
                "left join drug d on pd.drug_id = d.id";
        org.hibernate.Query query = session.createSQLQuery(stringQuery)
                .addEntity("p",Prescription.class)
                .addJoin("pd","prescribedDrugs")
                .addJoin("d","p.drugs")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Prescription>) query.getResultList();
    }

    @Override
    public Collection<Prescription> findByMonthWithDoctorJPQL(LocalDate date) {
        EntityManager entityManager = getEntityManager();
        String stringQuery = "select distinct p from Prescription p left join fetch p.doctor where p.releaseDate between :start_date and :end_date";
        Query query = entityManager.createQuery(stringQuery)
                .setParameter("start_date",date.withDayOfMonth(1))
                .setParameter("end_date",date.withDayOfMonth(date.lengthOfMonth()));
        List<Prescription> prescriptions = query.getResultList();
        return prescriptions;
    }

    @Override
    public Collection<Prescription> findByMonthWithDoctorCriteria(LocalDate date) {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Prescription> criteriaQuery = criteriaBuilder.createQuery(Prescription.class);
        criteriaQuery.distinct(Boolean.TRUE);
        Root<Prescription> root = criteriaQuery.from(Prescription.class);
        Fetch<Prescription, Doctor> doctorHospitalFetch = root.fetch(Prescription_.doctor);
        criteriaBuilder.between(root.get("releaseDate"),date.withDayOfMonth(1),date.withDayOfMonth(date.lengthOfMonth()));
        List<Prescription> prescriptions = entityManager.createQuery(criteriaQuery).getResultList();
        return prescriptions;
    }

    @Override
    public Collection<Prescription> findByMonthWithDoctorSQL(LocalDate date) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        String queryString = "select distinct {p.*},{d.*} from prescription p "+
                                "left join doctor d on p.doctor_id=d.id "+
                "where p.release_date between :start_date and :end_date ";
        org.hibernate.Query query = session.createSQLQuery(queryString)
                .addEntity("p",Prescription.class)
                .addJoin("d","p.doctor")
                .setParameter("start_date",date.withDayOfMonth(1))
                .setParameter("end_date",date.withDayOfMonth(date.lengthOfMonth()));
        Collection<Prescription> prescriptions = query.getResultList();
        return prescriptions;
    }
}
