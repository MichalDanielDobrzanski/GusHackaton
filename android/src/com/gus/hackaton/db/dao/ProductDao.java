package com.gus.hackaton.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.gus.hackaton.db.entity.Product;

import java.util.List;

@Dao
public interface ProductDao
{
    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product where id = :id")
    Product getProduct(String id);

    @Query("UPDATE product SET scanned = 1 WHERE id = :id")
    void productWasScanned(String id);
}
