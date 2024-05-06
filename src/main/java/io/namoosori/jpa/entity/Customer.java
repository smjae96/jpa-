package io.namoosori.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity // customer 객체가 entity 객체라는 annotation
@Table(name="customer_tb")  // customer 객체가 customer_tb라는 db의 테이블에 들어갈 것이다.
@TableGenerator(
        name = "id_generator",
        table = "customer_id",
        pkColumnName = "id_name",
        pkColumnValue = "customer_id",
        valueColumnName = "next_value",
        initialValue = 0,
        allocationSize = 1)
public class Customer {
    
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    private long id;

    private String name;
    private long registerDate;

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
        this.registerDate = System.currentTimeMillis();
    }

    public static Customer sample() {
        return new Customer( 1L,  "Kim");
    }
}
