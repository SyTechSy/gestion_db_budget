package com.groupe2_API.tp_gestion_budget.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    // Class data membre

    private String email;
    private String msgBody;
    private String subject;
}
