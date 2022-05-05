package order.mapper;

import org.modelmapper.ModelMapper;

import order.domain.Order;
import order.dto.OrderDto;

public interface ModelMapperFactory {
	static ModelMapper getOrderMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(Order.class, OrderDto.class)
			.addMappings(mapper -> {
					mapper.map(
						src -> src.getBillingAddress().getStreet(),
						OrderDto::setBillingStreet
					);
					mapper.map(
						src -> src.getBillingAddress().getCity(),
						OrderDto::setBillingCity
					);
				}
			);

		return modelMapper;
	}
}
