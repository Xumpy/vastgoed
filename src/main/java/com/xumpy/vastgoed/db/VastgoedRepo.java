package com.xumpy.vastgoed.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VastgoedRepo extends CrudRepository<VastgoedDBPojo, String> {
    @Query("from VastgoedDBPojo where uniquename = :uniquename order by scraped asc")
    public List<VastgoedDBPojo> selectVastgoedUniqueName(String uniquename);
}