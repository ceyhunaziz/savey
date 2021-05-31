package az.savey.ms.savey.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequestDto {
    private String description;
    private String item;
    private String paymentLink;
    private double totalAmount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
}
