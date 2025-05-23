
package com.crio.qeats.models;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "items")
@NoArgsConstructor
public class ItemEntity {
  @Id
  private String id;
  @NotNull
  private String itemId;
  @NotNull
  private String name;
  @NotNull
  private String imageUrl;
  @NotNull
  private Double price;
  @NotNull
  private List<String> attributes = new ArrayList<>();

}
