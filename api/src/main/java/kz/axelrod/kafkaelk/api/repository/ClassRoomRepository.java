package kz.axelrod.kafkaelk.api.repository;

import kz.axelrod.kafkaelk.api.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long>, JpaSpecificationExecutor<ClassRoom> {

}
