package com.example.shopdemo.service;

import com.example.shopdemo.entity.AbstractEntity;
import com.example.shopdemo.repository.AbstractEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends AbstractEntity, R extends AbstractEntityRepository<T>> {

    protected final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    /***GET***/
    @Transactional(readOnly = true)
    public List<T> findByIds(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Transactional(readOnly = true)
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public T getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("couldn't find %s with id: %s", getClass().getName(), id)));
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    /***SAVE***/
    @Transactional
    public void save(T entity) {
        repository.save(entity);
    }

    @Transactional
    public void save(Iterable<T> iterable) {
        repository.saveAll(iterable);
    }

    /***DELETE***/
    @Transactional(rollbackFor = {Throwable.class})
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Transactional(rollbackFor = {Throwable.class})
    public void delete(Iterable<T> iterable) {
        repository.deleteAll(iterable);
    }

    @Transactional(rollbackFor = {Throwable.class})
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
