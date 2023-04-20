package edu.ntnu.idatt2106.backend.model.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.ntnu.idatt2106.backend.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private double weight;

    @Enumerated(EnumType.STRING)
    private Category category;

    private long bad_in_days;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public CustomItem(String name, double weight, Category category, User user) {
        this.name = name;
        this.weight = weight;
        this.category = category;
        this.user = user;
    }
}
