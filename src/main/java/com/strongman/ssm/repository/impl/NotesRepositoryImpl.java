package com.strongman.ssm.repository.impl;

import com.strongman.ssm.model.Notes;
import com.strongman.ssm.repository.NotesRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class NotesRepositoryImpl implements NotesRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Notes> findAll() {
        TypedQuery<Notes> query = em.createQuery("select n from Notes n", Notes.class);
        return query.getResultList();
    }

    @Override
    public Notes findById(Long id){
        TypedQuery<Notes> query = em.createQuery("select n from Notes n where  n.id=:id", Notes.class);
        query.setParameter("id",id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Notes model) {
        if (model.getId() != null) {
            em.merge(model);
        } else {
            em.persist(model);
        }
    }


}
