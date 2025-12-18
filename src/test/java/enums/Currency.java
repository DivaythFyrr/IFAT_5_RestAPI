package enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum Currency {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR");
    private final String code;
}
