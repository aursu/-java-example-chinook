package com.github.aursu.chinook.chinook.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// https://projectlombok.org/features/constructor
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private int id;
    private String name;
}
