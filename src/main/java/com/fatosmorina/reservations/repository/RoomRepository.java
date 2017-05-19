package com.fatosmorina.reservations.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatosmorina.reservations.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
