package com.showmeyourcode.projects.springmvc.infrastructure.repository;

import com.showmeyourcode.projects.springmvc.infrastructure.entity.CategoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class DefaultCategoryRepository implements CategoryRepository {

    private final SessionFactory sessionFactory;
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    public DefaultCategoryRepository(
            SessionFactory sessionFactory,
            JdbcTemplate jdbcTemplate,
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        this.sessionFactory = sessionFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * An example using EntityManager (JPA).
     */
    @Override
    public void delete(CategoryEntity entity) {
        log.info("Deleting a category...");
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Cannot remove the category: {} ", entity.getId(), e);
        }
    }

    /**
     * An example using entityManager for finding a single entity..
     */
    @Override
    public Long exists(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> criteria = builder.createQuery(CategoryEntity.class);
        Root<CategoryEntity> u = criteria.from(CategoryEntity.class);
        TypedQuery<CategoryEntity> query = entityManager.createQuery(
                criteria.select(u).where(builder.equal(u.get("name"), name)));
        List<CategoryEntity> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0).getId();
    }

    /**
     * An example using a single session through a sessionFactory.
     * It's not recommended because each time you are creating a new session and then close it.
     */
    @Override
    public CategoryEntity get(Long identifier) {
        Session session = getSession();
        CategoryEntity result = session.get(CategoryEntity.class, identifier);
        closeSession(session);
        return result;
    }

    /**
     * An example using entityManager for fetching entity list.
     */
    @Override
    public List<CategoryEntity> getAll() {
        CriteriaQuery<CategoryEntity> criteriaQuery = entityManager.getCriteriaBuilder()
                .createQuery(CategoryEntity.class);
        criteriaQuery.from(CategoryEntity.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * An example using JDBC template.
     */
    @Override
    public List<CategoryEntity> getByCount(int limit) {
        return jdbcTemplate.query(
                "SELECT * FROM category order by random() limit ? offset 0",
                new Object[]{limit},
                new BeanPropertyRowMapper(CategoryEntity.class));
    }

    /**
     * An example using a single session and transaction.
     * It's not recommended because each time you are creating a new session and then close it.
     */
    @Override
    public Long save(CategoryEntity entity) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Long entityId = (Long) session.save(entity);
            tx.commit();
            return entityId;
        } catch (RuntimeException e) {
            log.error("Cannot save a new category! Rollback...", e);
            if (Objects.nonNull(tx)) {
                tx.rollback();
            }
            throw e;
        } finally {
            closeSession(session);
        }
    }

    private void closeSession(Session session) {
        session.close();
    }

    // Hibernate SessionFactory openSession() method always opens a new session.
    // We should close this session object once we are done with all the database operations.
    // We should open a new session for each request in a multi-threaded environment.
    //
    // SessionFactory.getCurrentSession():
    // - if it is called for the first time in the current thread, a new Session is opened and returned,
    // - if it is called again in the same thread, the same session will be returned.
    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        if (!session.isOpen()) {
            session = session.getSessionFactory().openSession();
        }
        return session;
    }
}
