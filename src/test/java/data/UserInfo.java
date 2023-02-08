package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data //на этапе компиляции сгенерирует геттеры\сеттеры для всех полей
@Value //Все поля делаются приватными и final по умолчанию, методы установки значений не генерируются.
@AllArgsConstructor
public class UserInfo {

    String name;
    String city;
    int phone;
}
