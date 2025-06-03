package com.lspt.Travels_BE.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItineraryResponse {
    private String itineraryId;
    private String tourId;
    private String dayTitle;
    private String description;
    private int dayNumber;

    private DestinationResponse destination;
    private String destinationId;
}
