package jp.ac.ccmc.festival_entry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.ccmc.festival_entry.model.Performer;

public interface PerformerRepository extends JpaRepository<Performer, Long> {

}
