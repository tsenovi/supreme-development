package org.vso.models.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.vso.utils.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class TaskRepository {

    private final HibernateUtil hibernateUtil;

    public TaskRepository() {
        this.hibernateUtil = new HibernateUtil();
    }

    public List<Task> readALlTasks() {
        EntityManagerFactory entityManagerFactory = hibernateUtil.buildEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        
        Root<Task> root = criteria.from(Task.class);
        criteria.select(root);
        List<Task> resultList = entityManager.createQuery(criteria).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        return resultList;
    }

    public void createTask(Task task) {
        EntityManagerFactory entityManagerFactory = hibernateUtil.buildEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        entityManager.persist(task);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void deleteTask(int selectedId) {
        EntityManagerFactory entityManagerFactory = hibernateUtil.buildEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Task> delete = builder.createCriteriaDelete(Task.class);
        
        Root<Task> root = delete.from(Task.class);
        
        delete.where(builder.lessThanOrEqualTo(root.get(Task_.ID), selectedId));
        
        entityManager.createQuery(delete).executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public List<Task> getTasksForSelectedDay(LocalDate selectedDate) {
        EntityManagerFactory entityManagerFactory = hibernateUtil.buildEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);

        Root<Task> root = criteria.from(Task.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Task_.timestamp), selectedDate));
        List<Task> resultList = entityManager.createQuery(criteria).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        return resultList;
    }

    public List<Task> getTasksForSelectedDayByPriority(LocalDate selectedDay) {
        return null;
    }
}
