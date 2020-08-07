package com.dogmates.dogmates.rest.walk;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WalksModel {
    private List<WalkModel> currentWalks;
}
