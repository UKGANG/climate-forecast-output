package com.ukgang.universal.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ukgang.universal.domain.Climate;
import com.ukgang.universal.domain.GPSLocation;
import com.ukgang.universal.startup.SystemMetadata.PropertyKey;

@Mapper
public interface ClimateMapper {
	void insertRecords(@Param("climates") List<Climate> climates);
	Long insertGPSLocation(@Param("location") GPSLocation location);

	void updateRecord(@Param("climate") Climate climate);
	Long searchGPSLocationId(@Param("location") GPSLocation location);
	
	Climate getRecord(@Param("datetime") Date datetime, @Param("location") GPSLocation location);
	List<Climate> getRecords(@Param("from") Date from, @Param("to") Date to, @Param("location") GPSLocation location);
	List<Climate> getAllRecords();
	String getConfig(@Param("key")PropertyKey key);
}
