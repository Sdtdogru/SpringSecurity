package com.pen.securitymanager.repository;

import com.towpen.base.db.model.TOpenDbEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseDaoRepository <T extends TOpenDbEntity>extends PagingAndSortingRepository<T, String>, CrudRepository<T,String> {

}
