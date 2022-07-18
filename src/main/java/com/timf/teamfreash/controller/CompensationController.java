package com.timf.teamfreash.controller;


import com.timf.teamfreash.model.Compensation;
import com.timf.teamfreash.model.dto.CompensationDto;
import com.timf.teamfreash.model.type.ClientType;
import com.timf.teamfreash.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compensation")
public class CompensationController {
    private CompensationService compensationService;

    @Autowired
    public CompensationController(CompensationService compensationService) {
        this.compensationService = compensationService;
    }

    @PostMapping("/{voc_id}")
    public ResponseEntity<CompensationDto> makeAmendByCompensation(@PathVariable long voc_id , @RequestBody final CompensationDto compensationDto) {
        Compensation compensation = compensationService.createCompensation(Compensation.from(compensationDto), voc_id);
        return new ResponseEntity<>(CompensationDto.from(compensation), HttpStatus.CREATED);
    }

    @PostMapping("/penalty/{voc_id}")
    public ResponseEntity<CompensationDto> makeAmendByCompensationFromPenalty(@PathVariable long voc_id , @RequestBody final CompensationDto compensationDto) {
        Compensation compensation = compensationService.createCompensationFromPenaltyAmount(Compensation.from(compensationDto), voc_id);
        return new ResponseEntity<>(CompensationDto.from(compensation), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<CompensationDto>> showCompensationList() {
        List<Compensation> compensationList = compensationService.getAllCompensations();
        List<CompensationDto> compensationDtoList = compensationList.stream().map(CompensationDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(compensationDtoList, HttpStatus.OK);
    }

    @GetMapping("/{client_id}")
    public ResponseEntity<List<CompensationDto>> showCompensationsFromTypeAndClientId(@PathVariable final long client_id) {
        List<Compensation> compensationList = compensationService.getCompensationsFromDefendantId(client_id);
        List<CompensationDto> compensationDtoList = compensationList.stream().map(CompensationDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(compensationDtoList, HttpStatus.OK);
    }

//    @GetMapping("/complete/{client_id}")

//    @GetMapping("/incomplete/{client_id}")
}
