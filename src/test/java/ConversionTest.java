import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import order.domain.Address;
import order.domain.Customer;
import order.domain.Name;
import order.domain.Order;
import order.dto.OrderDto;
import order.mapper.ModelMapperFactory;
import order.mapper.OrderMapper;

class ConversionTest {
	@Test
	@DisplayName("Explicit conversion")
	void explicitConversionTest() {
		Order order = OrderFactory.getSampleOrder();

		OrderDto orderDto = OrderDto.builder()
			.customerFirstName(order.getCustomer().getName().getFirstName())
			.customerLastName(order.getCustomer().getName().getLastName())
			.billingCity(order.getBillingAddress().getCity())
			.billingStreet(order.getBillingAddress().getStreet())
			.build();

		assertThat(orderDto).isNotNull();
		assertThat(orderDto.getCustomerFirstName()).isEqualTo("MG");
		assertThat(orderDto.getCustomerLastName()).isEqualTo("Song");
		assertThat(orderDto.getBillingCity()).isEqualTo("Seoul");
		assertThat(orderDto.getBillingStreet()).isEqualTo("Songpa-daero");
	}

	@Test
	@DisplayName("Conversion by model mapper")
	void modelMapperConversionTest() {
		Order order = OrderFactory.getSampleOrder();
		ModelMapper modelMapper = ModelMapperFactory.getOrderMapper();

		OrderDto orderDto = modelMapper.map(order, OrderDto.class);

		assertThat(orderDto).isNotNull();
		assertThat(orderDto.getCustomerFirstName()).isEqualTo("MG");
		assertThat(orderDto.getCustomerLastName()).isEqualTo("Song");
		assertThat(orderDto.getBillingCity()).isEqualTo("Seoul");
		assertThat(orderDto.getBillingStreet()).isEqualTo("Songpa-daero");
	}

	@Test
	@DisplayName("Conversion by map struct")
	void mapStructConversionTest() {
		Order order = OrderFactory.getSampleOrder();

		OrderDto orderDto = OrderMapper.INSTANCE.orderToOrderDto(order);

		assertThat(orderDto).isNotNull();
		assertThat(orderDto.getCustomerFirstName()).isEqualTo("MG");
		assertThat(orderDto.getCustomerLastName()).isEqualTo("Song");
		assertThat(orderDto.getBillingCity()).isEqualTo("Seoul");
		assertThat(orderDto.getBillingStreet()).isEqualTo("Songpa-daero");
	}

	private static class OrderFactory {
		public static Order getSampleOrder() {
			Name name = new Name("MG", "Song");
			Customer customer = new Customer(name);
			Address address = new Address("Songpa-daero", "Seoul");

			return new Order(customer, address);
		}
	}
}
