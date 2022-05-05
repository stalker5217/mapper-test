package order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Name {
	private final String firstName;
	private final String lastName;
}
