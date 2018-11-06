package com.program.backend.repositories.search;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.User;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@SuppressWarnings("unchecked")
public class UserSearchRepository {

    //TODO logger

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (Exception e) {
            //TODO logger
        }
    }

    public Set<User> resultsByName(String keyword) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(User.class)
                .get();

        Query query = queryBuilder.keyword().wildcard().onField("name").matching("*" + keyword + "*").createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class);

        return new HashSet<User>(jpaQuery.getResultList());
    }

    public Set<User> resultsBySurname(String keyword) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(User.class)
                .get();

        Query query = queryBuilder.keyword().wildcard().onField("surname").matching("*" + keyword + "*").createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class);

        return new HashSet<>(jpaQuery.getResultList());
    }

    public List resultsBySkillProperties(String keyword) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Skill.class)
                        .get();

        Query query = queryBuilder.keyword().wildcard().onField("title").matching("*" + keyword + "*").createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Skill.class);

        return jpaQuery.getResultList();
    }

    public String[] processQuery(String query) {
        String[] queries = query.toLowerCase().split(" +");
        return queries;
    }

}
