package BloodManagement.ServerSide.Repository.doctor;

import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Doctor_;
import BloodManagement.ServerSide.Domain.Hospital;
import BloodManagement.ServerSide.Domain.Hospital_;
import BloodManagement.ServerSide.Repository.CustomRepositorySupport;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import javax.print.Doc;
import java.util.Collection;
import java.util.List;


public class DoctorRepositoryImpl extends CustomRepositorySupport implements DoctorRepositoryCustom{

    @Override
    public Collection<Doctor> findAllWithHospitalJPQL() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct d from Doctor d left join fetch d.hospital");
        List<Doctor> doctorList = query.getResultList();
        return doctorList;
    }

    @Override
    public Collection<Doctor> findAllWithHospitalCriteria() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Doctor> criteriaQuery = criteriaBuilder.createQuery(Doctor.class);
        criteriaQuery.distinct(Boolean.TRUE);
        Root<Doctor> root = criteriaQuery.from(Doctor.class);
        Fetch<Doctor,Hospital> doctorHospitalFetch = root.fetch(Doctor_.hospital);
        doctorHospitalFetch.fetch(Hospital_.doctors);
        List<Doctor> doctorList = entityManager.createQuery(criteriaQuery).getResultList();

        return doctorList;
    }

    @Override
    public Collection<Doctor> findAllWithHospitalSQL() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        String stringQuery = "select distinct {d.*},{h.*} "+
                "from doctor d "+
                "left join hospital h on d.hospital_id=h.id";
        org.hibernate.Query query = session.createSQLQuery(stringQuery)
                .addEntity("d",Doctor.class)
                .addJoin("h","d.hospital")
                .addEntity("d",Doctor.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Doctor> doctorList = query.getResultList();

        return doctorList;
    }
}
