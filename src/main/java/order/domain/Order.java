package order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Order {
	private final Customer customer;
	private final Address billingAddress;
}
