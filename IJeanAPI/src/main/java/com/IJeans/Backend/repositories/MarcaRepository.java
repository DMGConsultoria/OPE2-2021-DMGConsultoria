package com.IJeans.Backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.IJeans.Backend.model.MarcaModel;

public interface MarcaRepository extends JpaRepository<MarcaModel, Integer>{

	@Query(
			value="SELECT * FROM marca WHERE STATUS <> false",
			nativeQuery=true)
	public List<MarcaModel> findAll();

	public Optional<MarcaModel> findById(String id);
	
	public void deleteById(String id);
}
