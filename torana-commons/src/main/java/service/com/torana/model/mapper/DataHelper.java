package com.torana.model.mapper;
/**
 * @author torana
 * @version 1.0
 * @since 2nd feb 2015
 * */
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class DataHelper 
{
	protected final ModelMapper modelMapper = new ModelMapper();

	public DataHelper() {
	}

	public  <D> D map(Object source, Class<D> destinationType) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(source, destinationType);
	}

	public final void map(Object source, Object destinationType) {
		modelMapper.map(source, destinationType);
	}

	public void init(){

	}
}


