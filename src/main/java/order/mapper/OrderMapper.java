package order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import order.domain.Order;
import order.dto.OrderDto;

@Mapper
public interface OrderMapper {
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	@Mapping(
		source = "customer.name.firstName",
		target = "customerFirstName"
	)
	@Mapping(
		source = "customer.name.lastName",
		target = "customerLastName"
	)
	@Mapping(
		source = "billingAddress.city",
		target = "billingCity"
	)
	@Mapping(
		source = "billingAddress.street",
		target = "billingStreet"
	)
	OrderDto orderToOrderDto(Order order);
}
