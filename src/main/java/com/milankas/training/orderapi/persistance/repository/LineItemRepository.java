package com.milankas.training.orderapi.persistance.repository;

import com.milankas.training.orderapi.persistance.model.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LineItemRepository extends JpaRepository <LineItem, UUID> {
}
